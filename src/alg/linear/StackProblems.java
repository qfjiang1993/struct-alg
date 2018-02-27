package alg.linear;

import java.util.Stack;

/**
 * @author QFJiang on 2017/10/18
 */
public class StackProblems {

    /**
     * 求直方图中的最大矩形面积，不完整子问题栈的线性搜索。
     * 元素入栈时创建子问题，出栈时子问题完成并更新最大矩形面积。
     *
     * @param arr 直方图的高度数组
     * @return 最大矩形面积
     */
    public static long maxRectangleArea(int[] arr) {
        long maxArea = 0;
        if (arr == null || arr.length == 0)
            return maxArea;
        Stack<Item> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            Item cur = new Item(arr[i], i);
            if (stack.empty()) {
                stack.push(cur);
                continue;
            }
            while (!stack.empty()) {
                Item top = stack.peek();
                if (cur.height == top.height)
                    break;
                else if (cur.height > top.height) {
                    stack.push(new Item(arr[i], i));
                    break;
                } else {
                    maxArea = Math.max(maxArea, top.height * (i - top.index));
                    stack.pop();
                }
            }
        }
        while (!stack.empty()) {
            Item top = stack.pop();
            maxArea = Math.max(maxArea, top.height * (arr.length - top.index));
        }
        return maxArea;
    }

    /**
     * 栈元素辅助类，保存直方图的高度和索引
     */
    private static class Item {
        int height;
        int index;

        Item(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }

    public static boolean checkStackPairwiseOrder(LLStack<Integer> llStack) {
        if (llStack == null)
            return false;
        int length = llStack.size();
        if (length == 0 || length == 1)
            return false;
        if (length % 2 == 1)
            llStack.pop();
        while (!llStack.empty()) {
            int data1 = llStack.pop();
            int data2 = llStack.pop();
            if (data1 + 1 != data2 && data1 - 1 != data2)
                return false;
        }
        return true;
    }
}
