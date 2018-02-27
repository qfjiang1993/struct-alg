package alg.linear;

/**
 * @author QFJiang on 2017/10/24
 */
public class StackTest {

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 5, 6, 1, 4, 4};
        int[] arr2 = {3, 4, 5, 6, 4, 2, 3};
        int[] arr3 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(StackProblems.maxRectangleArea(arr1));
        System.out.println(StackProblems.maxRectangleArea(arr2));
        System.out.println(StackProblems.maxRectangleArea(arr3));

    }
}
