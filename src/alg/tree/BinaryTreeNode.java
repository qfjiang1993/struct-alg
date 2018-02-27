package alg.tree;

/**
 * @param <T> 二叉树结点存储的数据类型
 * @author QFJiang on 2017/10/26
 */
public class BinaryTreeNode<T> {

    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" + "data=" + data + "}";
    }
}
