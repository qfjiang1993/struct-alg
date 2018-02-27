package alg.tree;

import alg.linear.LLQueue;
import alg.linear.LLStack;

/**
 * @author QFJiang on 2017/10/27
 */
public class BinaryTreeProblems {

    /**
     * 计算二叉树的高度
     */
    public static <T> int heightOfBinaryTree(BinaryTreeNode<T> root) {
        if (root == null)
            return 0;
        int level = 0;
        LLQueue<BinaryTreeNode<T>> queue = new LLQueue<>();
        queue.enQueue(root);
        queue.enQueue(null);
        while (!queue.empty()) {
            root = queue.deQueue();
            if (root == null) {
                if (!queue.empty())
                    queue.enQueue(null);
                level++;
            } else {
                if (root.getLeft() != null)
                    queue.enQueue(root.getLeft());
                if (root.getRight() != null)
                    queue.enQueue(root.getRight());
            }
        }
        return level;
    }

    /**
     * 查找二叉树中最深的结点
     */
    public static <T> BinaryTreeNode<T> deepestNodeInBinaryTree(BinaryTreeNode<T> root) {
        if (root == null)
            return null;
        LLQueue<BinaryTreeNode<T>> queue = new LLQueue<>();
        queue.enQueue(root);
        while (!queue.empty()) {
            root = queue.deQueue();
            if (root.getLeft() != null)
                queue.enQueue(root.getLeft());
            if (root.getRight() != null)
                queue.enQueue(root.getRight());
        }
        return root;
    }

    /**
     * 找出同一层结点数据和最大的层
     */
    public static int findMaxLevelSum(BinaryTreeNode<Integer> root) {
        if (root == null)
            return 0;
        LLQueue<BinaryTreeNode<Integer>> queue = new LLQueue<>();
        BinaryTreeNode<Integer> temp;
        int levelSum = 0;
        int maxSum = 0;
        int level = 0;
        int maxLevel = 0;
        queue.enQueue(root);
        queue.enQueue(null);
        while (!queue.empty()) {
            temp = queue.deQueue();
            if (temp == null) {
                if (!queue.empty())
                    queue.enQueue(null);
                level++;
                if (levelSum > maxSum) {
                    maxSum = levelSum;
                    maxLevel = level;
                }
                levelSum = 0;
            } else {
                levelSum += temp.getData();
                if (temp.getLeft() != null)
                    queue.enQueue(temp.getLeft());
                if (temp.getRight() != null)
                    queue.enQueue(temp.getRight());
            }
        }
        return maxLevel;
    }

    /**
     * 计算给定二叉树的镜像
     */
    public static <T> BinaryTreeNode<T> mirrorOfBinaryTree(BinaryTreeNode<T> root) {
        if (root == null)
            return null;
        BinaryTreeNode<T> temp = new BinaryTreeNode<>(root.getData());
        BinaryTreeNode<T> left = mirrorOfBinaryTree(root.getRight());
        BinaryTreeNode<T> right = mirrorOfBinaryTree(root.getLeft());
        temp.setLeft(left);
        temp.setRight(right);
        return temp;
    }

    private static int preIndex = 0;

    /**
     * 根据先序和中序序列重建二叉树
     *
     * @param inOrder  中序序列
     * @param preOrder 前序序列
     * @param inStart  中序序列起始搜索位置
     * @param inEnd    中序序列结束搜索位置
     * @param <T>      二叉树元素类型
     * @return 二叉树的根结点
     */
    public static <T> BinaryTreeNode<T> rebuildTreeByPreIn(T[] inOrder, T[] preOrder, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;
        BinaryTreeNode<T> root = new BinaryTreeNode<>(preOrder[preIndex++]);
        if (inStart == inEnd)
            return root;
        int inIndex = searchIndexInOrder(inOrder, inStart, inEnd, root.getData());
        root.setLeft(rebuildTreeByPreIn(inOrder, preOrder, inStart, inIndex - 1));
        root.setRight(rebuildTreeByPreIn(inOrder, preOrder, inIndex + 1, inEnd));
        return root;
    }

    /**
     * 根据后序和中序序列重建二叉树
     *
     * @param inOrder   中序序列
     * @param postOrder 前序序列
     * @param inStart   中序序列起始搜索位置
     * @param inEnd     中序序列结束搜索位置
     * @param <T>       二叉树元素类型
     * @return 二叉树的根结点
     */
    public static <T> BinaryTreeNode<T> rebuildTreeByPostIn(T[] inOrder, T[] postOrder, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;
        BinaryTreeNode<T> root = new BinaryTreeNode<>(postOrder[postOrder.length - 1 - preIndex++]);
        if (inStart == inEnd)
            return root;
        int inIndex = searchIndexInOrder(inOrder, inStart, inEnd, root.getData());
        root.setRight(rebuildTreeByPostIn(inOrder, postOrder, inIndex + 1, inEnd));
        root.setLeft(rebuildTreeByPostIn(inOrder, postOrder, inStart, inIndex - 1));
        return root;
    }

    /**
     * 根据中序和层次序列重建二叉树
     *
     * @param inOrder    中序序列
     * @param levelOrder 层次序列
     * @param inStart    中序序列起始搜索位置
     * @param inEnd      中序序列结束搜索位置
     * @param <T>        二叉树元素类型
     * @return 二叉树的根结点
     */
    public static <T> BinaryTreeNode<T> rebuildTreeByLevelIn(T[] inOrder, T[] levelOrder, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;
        if (inStart == inEnd)
            return new BinaryTreeNode<>(inOrder[inStart]);
        BinaryTreeNode<T> root = new BinaryTreeNode<>();
        boolean found = false;
        int inIndex = 0;
        for (int i = 0; i < levelOrder.length - 1; i++) {
            T data = levelOrder[i];
            for (int j = inStart; j < inEnd; j++) {
                if (data == inOrder[j]) {
                    root = new BinaryTreeNode<>(inOrder[j]);
                    inIndex = j;
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }
        root.setLeft(rebuildTreeByLevelIn(inOrder, levelOrder, inStart, inIndex - 1));
        root.setRight(rebuildTreeByLevelIn(inOrder, levelOrder, inIndex + 1, inEnd));
        return root;
    }

    /**
     * 在中序序列中查找指定值的索引
     *
     * @param inOrder 中序序列
     * @param start   起始搜索位置
     * @param end     结束搜索位置
     * @param value   搜索值
     * @param <T>     二叉树元素类型
     * @return 搜索值的索引
     */
    private static <T> int searchIndexInOrder(T[] inOrder, int start, int end, T value) {
        int i;
        for (i = start; i <= end; i++) {
            if (inOrder[i] == value)
                return i;
        }
        return i;
    }

    /**
     * 查找二叉树中两个结点的最近公共祖先（Least Common Ancestor）
     *
     * @param root 根结点
     * @param a    结点a
     * @param b    结点b
     * @param <T>  二叉树元素类型
     * @return 最近公共祖先
     */
    public static <T> BinaryTreeNode<T> leastCommonAncestor(BinaryTreeNode<T> root, BinaryTreeNode<T> a, BinaryTreeNode<T> b) {
        if (root == null)
            return null;
        if (root == a || root == b)
            return root;
        BinaryTreeNode<T> left = leastCommonAncestor(root.getLeft(), a, b);
        BinaryTreeNode<T> right = leastCommonAncestor(root.getRight(), a, b);
        if (left != null && right != null)
            return root;
        else if (left != null)
            return left;
        else
            return right;
    }

    /**
     * 以ZigZag顺序遍历二叉树，第一层L2R、第二层R2L，依次遍历。
     *
     * @param root 根结点
     * @param <T>  二叉树元素类型
     */
    public static <T> void zigZagTraversal(BinaryTreeNode<T> root) {
        if (root == null)
            return;
        LLStack<BinaryTreeNode<T>> curLevel = new LLStack<>();
        LLStack<BinaryTreeNode<T>> nextLevel = new LLStack<>();
        int left2right = 1;
        BinaryTreeNode<T> temp;
        curLevel.push(root);
        while (!curLevel.empty()) {
            temp = curLevel.pop();
            if (temp != null) {
                System.out.print(temp.getData() + " ");
                if (left2right == 1) {
                    if (temp.getLeft() != null)
                        nextLevel.push(temp.getLeft());
                    if (temp.getRight() != null)
                        nextLevel.push(temp.getRight());
                } else {
                    if (temp.getRight() != null)
                        nextLevel.push(temp.getRight());
                    if (temp.getLeft() != null)
                        nextLevel.push(temp.getLeft());
                }
            }
            if (curLevel.empty()) {
                left2right = 1 - left2right;
                LLStack<BinaryTreeNode<T>> stack = curLevel;
                curLevel = nextLevel;
                nextLevel = stack;
            }
        }
        System.out.println();
    }

    private static int index = 0;

    /**
     * 严格二叉树，叶子结点为L，内部结点为I，根据前序序列重建二叉树。
     *
     * @param preOrder 前序序列
     * @return 二叉树根结点
     */
    public static BinaryTreeNode<Character> rebuildTreeByPreOrder(Character[] preOrder) {
        if (preOrder == null)
            return null;
        BinaryTreeNode<Character> root = new BinaryTreeNode<>(preOrder[index]);
        if (preOrder[index] == 'L')
            return root;
        index++;
        root.setLeft(rebuildTreeByPreOrder(preOrder));
        index++;
        root.setRight(rebuildTreeByPreOrder(preOrder));
        return root;
    }
}
