package alg.tree;

/**
 * @param <T> 二叉搜索树结点存储的元素类型
 * @author QFJiang on 2017/10/31
 */
public class BSTNode<T> {

    private T data;
    private BSTNode<T> left;
    private BSTNode<T> right;

    public BSTNode() {
    }

    public BSTNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BSTNode{" + "data=" + data + "}";
    }
}
