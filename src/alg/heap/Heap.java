package alg.heap;

/**
 * 基于动态数组实现的堆数据结构
 *
 * @param <T> 堆的元素类型
 * @author QFJiang on 2017/11/04
 */
public class Heap<T extends Comparable<T>> {

    /**
     * 堆类型
     */
    private HeapType heapType;
    private T[] array;
    private int count;
    private int capacity;

    @SuppressWarnings({"unchecked"})
    public Heap(HeapType heapType) {
        this.heapType = heapType;
        this.count = 0;
        this.capacity = 10;
        this.array = (T[]) new Comparable[capacity];
    }

    @SuppressWarnings({"unchecked"})
    public Heap(int capacity, HeapType heapType) {
        this.heapType = heapType;
        this.count = 0;
        this.capacity = capacity;
        this.array = (T[]) new Comparable[capacity];
    }

    /**
     * @return 堆中元素的数量
     */
    public int getCount() {
        return count;
    }

    /**
     * @return 堆是否为空
     */
    public boolean empty() {
        return count == 0;
    }

    /**
     * 获取堆中第i个元素
     *
     * @param i 索引
     * @return 第i个元素
     */
    public T getData(int i) {
        if (i >= 0 && i < count)
            return array[i];
        return null;
    }

    /**
     * 获取双亲结点的索引
     */
    public int parentIndex(int childIndex) {
        if (childIndex <= 0 || childIndex > count)
            return -1;
        return (childIndex - 1) / 2;
    }

    /**
     * 获取左孩子结点的索引
     */
    public int leftChildIndex(int parentIndex) {
        int left = 2 * parentIndex + 1;
        if (left < count)
            return left;
        return -1;
    }

    /**
     * 获取右孩子结点的索引
     */
    public int rightChildIndex(int parentIndex) {
        int right = 2 * parentIndex + 2;
        if (right < count)
            return right;
        return -1;
    }

    /**
     * 获取堆中最大的元素，但不删除。最小堆的最大元素只能为叶子结点。
     *
     * @return 堆中最大元素
     */
    public T getMaximum() {
        if (count > 0) {
            if (heapType == HeapType.MAX_HEAP)
                return array[0];
            else {
                int index = (count - 1) / 2;
                T max = array[index];
                for (int i = index + 1; i < count; i++) {
                    if (max.compareTo(array[i]) < 0)
                        max = array[i];
                }
                return max;
            }
        }
        return null;
    }

    /**
     * 获取堆中最小的元素，但不删除。最大堆的最小元素只能为叶子结点。
     *
     * @return 堆中最小元素
     */
    public T getMinimum() {
        if (count > 0) {
            if (heapType == HeapType.MIN_HEAP)
                return array[0];
            else {
                int index = (count - 1) / 2;
                T min = array[index];
                for (int i = index + 1; i < count; i++) {
                    if (min.compareTo(array[i]) > 0)
                        min = array[i];
                }
                return min;
            }
        }
        return null;
    }

    /**
     * 删除并返回堆中最大的元素
     *
     * @return 堆中最大元素
     */
    public T deleteMax() {
        if (count <= 0)
            return null;
        if (heapType == HeapType.MAX_HEAP) {
            T max = array[0];
            array[0] = array[count - 1];
            count--;
            percolateDown(0);
            return max;
        } else {
            int index = (count - 1) / 2;
            T max = array[index];
            int maxIndex = index;
            for (int i = index + 1; i < count; i++) {
                if (max.compareTo(array[i]) < 0) {
                    max = array[i];
                    maxIndex = i;
                }
            }
            array[maxIndex] = array[count - 1];
            count--;
            while (parentIndex(maxIndex) != -1 &&
                    array[maxIndex].compareTo(array[parentIndex(maxIndex)]) < 0) {
                T temp = array[maxIndex];
                array[maxIndex] = array[parentIndex(maxIndex)];
                array[parentIndex(maxIndex)] = temp;
                maxIndex = parentIndex(maxIndex);
            }
            percolateDown(maxIndex);
            return max;
        }
    }

    /**
     * 删除并返回堆中最小的元素
     *
     * @return 堆中最小元素
     */
    public T deleteMin() {
        if (count <= 0)
            return null;
        if (heapType == HeapType.MIN_HEAP) {
            T min = array[0];
            array[0] = array[count - 1];
            percolateDown(0);
            count--;
            return min;
        } else {
            int index = (count - 1) / 2;
            T min = array[index];
            int minIndex = index;
            for (int i = index + 1; i < count; i++) {
                if (min.compareTo(array[i]) > 0) {
                    min = array[i];
                    minIndex = i;
                }
            }
            array[minIndex] = array[count - 1];
            count--;
            while (parentIndex(minIndex) != -1 &&
                    array[minIndex].compareTo(array[parentIndex(minIndex)]) > 0) {
                T temp = array[minIndex];
                array[minIndex] = array[parentIndex(minIndex)];
                array[parentIndex(minIndex)] = temp;
                minIndex = parentIndex(minIndex);
            }
            percolateDown(minIndex);
            return min;
        }
    }

    /**
     * 自顶向下堆化，向下渗透。
     *
     * @param index 向下渗透开始的索引
     */
    private void percolateDown(int index) {
        if (index < 0 || index >= count)
            return;
        int left = leftChildIndex(index);
        int right = rightChildIndex(index);

        // 最大堆，若不满足堆的性质，则将根结点与较大的孩子结点互换
        int maxIndex;
        if (heapType == HeapType.MAX_HEAP) {
            if (left != -1 && array[left].compareTo(array[index]) > 0)
                maxIndex = left;
            else
                maxIndex = index;
            if (right != -1 && array[right].compareTo(array[maxIndex]) > 0)
                maxIndex = right;
            if (maxIndex != index) {
                T temp = array[index];
                array[index] = array[maxIndex];
                array[maxIndex] = temp;
            } else
                return;
            percolateDown(maxIndex);
        }

        // 最小堆，若不满足堆的性质，则将根结点与较小的孩子结点互换
        int minIndex;
        if (heapType == HeapType.MIN_HEAP) {
            if (left != -1 && array[left].compareTo(array[index]) < 0)
                minIndex = left;
            else
                minIndex = index;
            if (right != -1 && array[right].compareTo(array[minIndex]) < 0)
                minIndex = right;
            if (minIndex != index) {
                T temp = array[index];
                array[index] = array[minIndex];
                array[minIndex] = temp;
            } else
                return;
            percolateDown(index);
        }
    }

    /**
     * 在堆中插入一个元素
     *
     * @param data 插入的元素
     */
    public void insert(T data) {
        if (count == capacity)
            resizeHeap();
        int index = count;
        if (heapType == HeapType.MAX_HEAP) {
            while (index > 0 && data.compareTo(array[(index - 1) / 2]) > 0) {
                array[index] = array[(index - 1) / 2];
                index = (index - 1) / 2;
            }
        } else {
            while (index > 0 && data.compareTo(array[(index - 1) / 2]) < 0) {
                array[index] = array[(index - 1) / 2];
                index = (index - 1) / 2;
            }
        }
        array[index] = data;
        count++;
    }

    /**
     * 调整堆的大小
     */
    private void resizeHeap() {
        capacity = capacity * 2;
        @SuppressWarnings({"unchecked"})
        T[] arrayNew = (T[]) new Comparable[capacity];
        System.arraycopy(array, 0, arrayNew, 0, count);
        array = arrayNew;
    }

    /**
     * 删除堆中的指定元素
     *
     * @param data 指定元素
     */
    public void delete(T data) {
        if (count <= 0 || find(data) == -1)
            return;
        int index = find(data);
        array[index] = array[count - 1];
        count--;
        if (heapType == HeapType.MIN_HEAP) {
            while (parentIndex(index) != -1 &&
                    array[index].compareTo(array[parentIndex(index)]) < 0) {
                T temp = array[index];
                array[index] = array[parentIndex(index)];
                array[parentIndex(index)] = temp;
                index = parentIndex(index);
            }
        } else {
            while (parentIndex(index) != -1 &&
                    array[index].compareTo(array[parentIndex(index)]) > 0) {
                T temp = array[index];
                array[index] = array[parentIndex(index)];
                array[parentIndex(index)] = temp;
                index = parentIndex(index);
            }
        }
        percolateDown(index);
    }

    /**
     * 查找指定元素在堆中的索引
     *
     * @param data 给定元素
     * @return 元素在堆中的索引
     */
    public int find(T data) {
        if (count <= 0)
            return -1;
        for (int i = 0; i < count; i++)
            if (array[i].compareTo(data) == 0)
                return i;
        return -1;
    }

    /**
     * 清空堆
     */
    public void clearHeap() {
        count = 0;
    }

    /**
     * 从数组创建最大堆或最小堆
     *
     * @param values   数组
     * @param heapType 堆类型
     * @return 最大堆或最小堆
     */
    public static <T extends Comparable<T>> Heap<T> buildHeap(T[] values, HeapType heapType) {
        Heap<T> heap = new Heap<>(values.length, heapType);
        for (T value : values)
            heap.insert(value);
        for (int index = (values.length - 1) / 2; index >= 0; index--)
            heap.percolateDown(index);
        return heap;
    }

    /**
     * 堆排序：先创建最大堆，逐个删除即得降序序列；反之，得升序序列。
     *
     * @param values    待排序的数组
     * @param ascendant 升序或降序，true为升序，false为降序。
     * @return 排序后的数组
     */
    public static <T extends Comparable<T>> T[] heapSort(T[] values, boolean ascendant) {
        Heap<T> heap;
        if (ascendant) {
            heap = buildHeap(values, HeapType.MIN_HEAP);
            for (int i = 0; i < values.length; i++)
                values[i] = heap.deleteMin();
        } else {
            heap = buildHeap(values, HeapType.MAX_HEAP);
            for (int i = 0; i < values.length; i++)
                values[i] = heap.deleteMax();
        }
        return values;
    }

    /**
     * 打印堆中的元素
     */
    public void printHeap() {
        for (int i = 0; i < count; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

    /**
     * 将堆转换为数组并返回
     *
     * @return 堆的数组表示
     */
    public T[] toArray() {
        return array;
    }

    /**
     * 堆类型枚举类：最大堆、最小堆。
     */
    public enum HeapType {
        MIN_HEAP, MAX_HEAP
    }
}
