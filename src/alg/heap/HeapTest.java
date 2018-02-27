package alg.heap;

/**
 * @author QFJiang on 2017/11/04
 */
public class HeapTest {

    public static void main(String[] args) {


        Heap<Integer> maxHeap = new Heap<>(5, Heap.HeapType.MAX_HEAP);
        maxHeap.insert(1);
        maxHeap.insert(2);
        maxHeap.insert(3);
        maxHeap.insert(4);
        maxHeap.insert(5);
        maxHeap.insert(6);
        maxHeap.insert(7);
        maxHeap.printHeap();
        maxHeap.delete(1);
        maxHeap.delete(5);
        maxHeap.delete(3);
        maxHeap.printHeap();

        Heap<Integer> minHeap = new Heap<>(5, Heap.HeapType.MIN_HEAP);
        minHeap.insert(7);
        minHeap.insert(6);
        minHeap.insert(5);
        minHeap.insert(4);
        minHeap.insert(3);
        minHeap.insert(2);
        minHeap.insert(1);
        minHeap.printHeap();
        minHeap.delete(7);
        minHeap.delete(3);
        minHeap.delete(5);
        minHeap.printHeap();

        // test buildHeap, including MAX_HEAP and MIN_HEAP
        Heap<Integer> max = Heap.buildHeap(new Integer[]{1, 2, 3, 4, 5, 6, 7}, Heap.HeapType.MAX_HEAP);
        max.printHeap();
        Heap<Integer> min = Heap.buildHeap(new Integer[]{7, 6, 5, 4, 3, 2, 1}, Heap.HeapType.MIN_HEAP);
        min.printHeap();

        HeapProblems.findLessThanK(min, 0, 3);

        // test heapSort, including ascendant and descendant
        Integer[] asc = Heap.heapSort(new Integer[]{7, 6, 5, 4, 3, 2, 1}, true);
        for (Integer i : asc)
            System.out.print(i + " ");
        System.out.println();
        Integer[] des = Heap.heapSort(new Integer[]{1, 2, 3, 4, 5, 6, 7}, false);
        for (Integer i : des)
            System.out.print(i + " ");
        System.out.println();
    }
}
