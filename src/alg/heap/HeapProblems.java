package alg.heap;

import java.util.Arrays;

/**
 * @author QFJiang on 2017/11/05
 */
public class HeapProblems {

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int w = 3;
        int[] b = maxSlidingWindow(a, w);
        System.out.println(Arrays.toString(b));
    }

    public static <T extends Comparable<T>> void findLessThanK(Heap<T> heap, int index, T k) {
        if (heap == null)
            return;
        if (k == null) {
            heap.printHeap();
            return;
        }
        if (heap.getData(index).compareTo(k) < 0)
            System.out.print(heap.getData(index) + " ");
        int left = heap.leftChildIndex(index);
        if (left != -1 && heap.getData(left).compareTo(k) < 0)
            findLessThanK(heap, left, k);
        int right = heap.rightChildIndex(index);
        if (right != -1 && heap.getData(right).compareTo(k) < 0)
            findLessThanK(heap, right, k);
    }

    /**
     * 滑动窗口中的最大值。
     * 解法：用最小值初始化最大堆，若新的元素比最大元素大，则删除最大元素，并插入新元素。
     *
     * @param A 给定数组
     * @param w 窗口大小
     * @return 窗口最大值数组
     */
    public static int[] maxSlidingWindow(int[] A, int w) {
        int[] B = new int[A.length - w + 1];
        Heap<Integer> maxHeap = new Heap<>(Heap.HeapType.MAX_HEAP);
        maxHeap.insert(Integer.MIN_VALUE);
        for (int i = 0; i < A.length - w + 1; i++) {
            for (int j = i; j < i + w; j++) {
                if (maxHeap.getMaximum() < A[j]) {
                    maxHeap.deleteMax();
                    maxHeap.insert(A[j]);
                }
            }
            B[i] = maxHeap.getMaximum();
        }
        return B;
    }
}
