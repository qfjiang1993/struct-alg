package alg.sort;

/**
 * @author QFJiang on 2018/02/26
 */
public class SortTest {

    public static void main(String[] args) {
        int[] arr = {6, 8, 1, 4, 5, 3, 7, 2, 0};
//        Sort.bubbleSort(arr);
//        Sort.bubbleSortImproved(arr);
//        Sort.selectionSort(arr);
//        Sort.insertionSort(arr);
//        Sort.shellSort(arr);
//        Sort.mergeSortUp2Down(arr, 0, arr.length - 1);
//        Sort.mergeSortDown2Up(arr);
        Sort.quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
