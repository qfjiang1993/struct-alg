package alg.tree;

/**
 * @param <T> 通用树结点存储的数据类型
 * @author QFJiang on 2017/10/30
 */
public class TreeNode<T> {

    private T data;
    private TreeNode<T> child;
    private TreeNode<T> sibling;

    public TreeNode() {
    }

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getChild() {
        return child;
    }

    public void setChild(TreeNode<T> child) {
        this.child = child;
    }

    public TreeNode<T> getSibling() {
        return sibling;
    }

    public void setSibling(TreeNode<T> sibling) {
        this.sibling = sibling;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "data=" + data + "}";
    }
}
