package alg.tree;

/**
 * @param <T> 平衡二叉搜索树结点存储的元素类型
 * @author QFJiang on 2017/11/01
 */
public class AVLTreeNode<T> {

    private T data;
    private int height;
    private AVLTreeNode<T> left;
    private AVLTreeNode<T> right;

    public AVLTreeNode() {
    }

    public AVLTreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode<T> left) {
        this.left = left;
    }

    public AVLTreeNode<T> getRight() {
        return right;
    }

    public void setRight(AVLTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" + "data=" + data + ", height=" + height + "}";
    }
}
