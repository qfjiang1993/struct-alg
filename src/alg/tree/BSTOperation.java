package alg.tree;

import alg.linear.LLStack;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树的基本操作
 *
 * @author QFJiang on 2017/10/31
 */
public class BSTOperation {

    /**
     * 在二叉搜索树中插入元素
     *
     * @param root 二叉搜索树根结点
     * @param data 插入结点的数据
     * @return 二叉搜索树根结点
     */
    public static BSTNode<Integer> insert(BSTNode<Integer> root, int data) {
        if (root == null)
            return new BSTNode<>(data);
        else {
            if (root.getData() > data)
                root.setLeft(insert(root.getLeft(), data));
            if (root.getData() < data)
                root.setRight(insert(root.getRight(), data));
        }
        return root;
    }

    /**
     * 在二叉搜索树中删除元素
     *
     * @param root 二叉搜索树根结点
     * @param data 删除结点的数据
     * @return 二叉搜索树根结点
     */
    public static BSTNode<Integer> delete(BSTNode<Integer> root, int data) {
        if (root == null)
            return null;
        else {
            BSTNode<Integer> temp;
            if (root.getData() > data)
                root.setLeft(delete(root.getLeft(), data));
            else if (root.getData() < data)
                root.setRight(delete(root.getRight(), data));
            else {
                if (root.getLeft() != null && root.getRight() != null) {
                    temp = findMax(root.getLeft());
                    root.setData(temp.getData());
                    root.setLeft(delete(root.getLeft(), temp.getData()));
                } else {
                    if (root.getLeft() == null)
                        root = root.getRight();
                    else if (root.getRight() == null)
                        root = root.getLeft();
                }
            }
            return root;
        }
    }

    /**
     * 在二叉搜索树中查找最大元素
     */
    public static <T> BSTNode<T> findMax(BSTNode<T> root) {
        if (root == null)
            return null;
        while (root.getRight() != null)
            root = root.getRight();
        return root;
    }

    /**
     * 在二叉搜索树中查找最小元素
     */
    public static <T> BSTNode<T> findMin(BSTNode<T> root) {
        if (root == null)
            return null;
        while (root.getLeft() != null)
            root = root.getLeft();
        return root;
    }

    /**
     * 在二叉搜索树中查找指定元素
     */
    public static BSTNode<Integer> find(BSTNode<Integer> root, int data) {
        if (root == null)
            return null;
        while (root != null) {
            if (root.getData() == data)
                return root;
            else if (root.getData() > data)
                root = root.getLeft();
            else
                root = root.getRight();
        }
        return null;

//        // Recursive
//        if (root == null)
//            return false;
//        if (root.getData() == data)
//            return true;
//        else if (root.getData() > data)
//            return find(root.getLeft(), data);
//        else
//            return find(root.getRight(), data);
    }

    /**
     * 中序遍历二叉搜索树
     *
     * @param root 根结点
     * @param <T>  元素类型
     * @return 中序遍历序列
     */
    public static <T> List<BSTNode<T>> inOrderBst(BSTNode<T> root) {
        ArrayList<BSTNode<T>> list = new ArrayList<>();
        LLStack<BSTNode<T>> stack = new LLStack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.empty())
                break;
            root = stack.pop();
            list.add(root);
            root = root.getRight();
        }
        return list;
    }

    /**
     * 在二叉搜索树中查找给定两个结点的最近公共祖先
     *
     * @param root 根结点
     * @param a    结点a
     * @param b    结点b
     * @return 最近公共祖先
     */
    public static BSTNode<Integer> findLCA(BSTNode<Integer> root, BSTNode<Integer> a, BSTNode<Integer> b) {
        if (root == null || a == null || b == null)
            return null;
        while (true) {
            if ((a.getData() <= root.getData() && b.getData() > root.getData()) ||
                    (a.getData() < root.getData() && b.getData() >= root.getData()) ||
                    (a.getData() >= root.getData() && b.getData() < root.getData()) ||
                    (a.getData() > root.getData() && b.getData() <= root.getData()))
                return root;
            else if (a.getData() < root.getData())
                root = root.getLeft();
            else
                root = root.getRight();
        }
    }

    /**
     * 判断一棵二叉树是否为二叉搜索树，前序遍历二叉搜索树得到递增序列。
     *
     * @param root 根结点
     * @return 是否为BST
     */
    public static boolean isBST(BSTNode<Integer> root) {
        LLStack<BSTNode<Integer>> stack = new LLStack<>();
        int prev = Integer.MIN_VALUE;
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.empty())
                break;
            root = stack.pop();
            if (prev > root.getData())
                return false;
            root = root.getRight();
        }
        return true;
    }

    /**
     * 将有序数组转化为平衡BST，分治法。
     *
     * @param array 有序数组
     * @param left  起始索引
     * @param right 结束索引
     * @param <T>   元素类型
     * @return 平衡BST根结点
     */
    public static <T> BSTNode<T> buildBSTByOrderedArray(T[] array, int left, int right) {
        if (array == null || left > right)
            return null;
        if (left == right)
            return new BSTNode<>(array[left]);
        else {
            int middle = left + (right - left) / 2;
            BSTNode<T> node = new BSTNode<>(array[middle]);
            node.setLeft(buildBSTByOrderedArray(array, left, middle - 1));
            node.setRight(buildBSTByOrderedArray(array, middle + 1, right));
            return node;
        }
    }
}
