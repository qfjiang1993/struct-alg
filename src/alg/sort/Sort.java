package alg.sort;

/**
 * @author QFJiang on 2018/02/26 14:09
 */
public class Sort {

    /**
     * 冒泡排序：可以检测输入序列是否已经排序
     *
     * @param array 待排序数组
     */
    public static void bubbleSort(int... array) {
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序改进版：没有交换操作则意味着排序完成，增加标志进行改进
     *
     * @param array 待排序数组
     */
    public static void bubbleSortImproved(int... array) {
        boolean unsorted = true;
        for (int i = array.length - 1; i >= 0 && unsorted; i--) {
            unsorted = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    unsorted = true;
                }
            }
        }
    }


    /**
     * 选择排序：重复选择最小的元素与当前位置交换，只在需要时执行交换
     *
     * @param array 待排序数组
     */
    public static void selectionSort(int... array) {
        int min, temp;
        for (int i = 0; i < array.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }


    /**
     * 插入排序：每次从输入数据移除一个元素，将其插入已排序序列的正确位置
     *
     * @param array 待排序数组
     */
    public static void insertionSort(int... array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j >= 1 && array[j - 1] > temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }


    /**
     * 希尔排序：插入排序的扩展，交换相距较远的元素，最后一次为插入排序
     *
     * @param array 待排序数组
     */
    public static void shellSort(int... array) {
        for (int step = (int) Math.ceil(array.length / 2); step > 0; step /= 2) {
            for (int i = step; i < array.length; i++) {
                int temp = array[i];
                int j = i;
                while (j >= step && array[j - step] > temp) {
                    array[j] = array[j - step];
                    j -= step;
                }
                array[j] = temp;
            }
        }
    }


    /**
     * 归并排序：自上向下
     * 1、分解：将当前区间一分为二，即求分裂点 mid = (start + end)/2；
     * 2、递归：递归地对子区间a[start...mid]和a[mid+1...end]进行归并排序，子区间长度为1时终止；
     * 3、合并：将已排序的子区间a[start...mid]和a[mid+1...end]归并为一个有序的区间a[start...end]。
     *
     * @param array 待排序数组
     * @param start 排序开始索引
     * @param end   排序结束索引
     */
    public static void mergeSortUp2Down(int[] array, int start, int end) {
        if (end > start) {
            int mid = (start + end) / 2;
            mergeSortUp2Down(array, start, mid);
            mergeSortUp2Down(array, mid + 1, end);
            merge(array, start, mid + 1, end);
        }
    }

    /**
     * 合并数组中的两个有序区间
     *
     * @param array 数组
     * @param start 有序区间1的起始索引
     * @param mid   有序区间1的结束地址（不包括），有序区间2的起始地址（包括）
     * @param end   有序区间2的结束地址
     */
    private static void merge(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid;
        int k = 0;
        while (i < mid && j <= end) {
            if (array[i] < array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        while (i < mid) {
            temp[k++] = array[i++];
        }
        while (j <= end) {
            temp[k++] = array[j++];
        }
        for (i = 0; i < temp.length; i++) {
            array[start + i] = temp[i];
        }
    }


    /**
     * 归并排序：自下向上
     * 将待排序的数列分成若干个长度为1的子数列，然后将这些数列两两合并；
     * 得到若干个长度为2的有序数列，再将这些数列两两合并；
     * 得到若干个长度为4的有序数列，再将它们两两合并；直至合并成一个数列为止。
     *
     * @param array 待排序数组
     */
    public static void mergeSortDown2Up(int... array) {
        for (int len = 1; len < array.length; len *= 2) {
            mergeGroups(array, len);
        }
    }

    /**
     * 合并相邻的两个子数组
     *
     * @param array  待排序数组
     * @param length 子数组的长度
     */
    private static void mergeGroups(int[] array, int length) {
        int i;
        for (i = 0; i + 2 * length < array.length; i += 2 * length) {
            merge(array, i, i + length, i + 2 * length - 1);
        }
        if (i + length < array.length) {
            merge(array, i, i + length, array.length - 1);
        }
    }


    /**
     * 快速排序：分区交换排序
     * 1、从数列中挑出一个基准值（常规方法选最左边的值，随机化方法随机选择一个）；
     * 2、将所有比基准值小的放在基准值前面，所有比基准值大的放在基准值的后面（相等的数可以到任一边），
     * 在这个分区退出之后，该基准值就处于数列的中间位置，左边都小于基准值，右边都大于基准值；
     * 3、递归地把“基准值前面的子数列”和“基准值后面的子数列”进行排序。
     *
     * @param array 待排序数组
     * @param left  排序开始索引
     * @param right 排序结束索引
     */
    public static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int temp = array[i];
            while (i < j) {
                while (i < j && array[j] > temp)
                    j--;
                if (i < j)
                    array[i++] = array[j];
                while (i < j && array[i] < temp)
                    i++;
                if (i < j)
                    array[j--] = array[i];
            }
            array[i] = temp;
            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        }
    }
}
