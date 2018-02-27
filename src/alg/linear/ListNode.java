package alg.linear;

/**
 * 链表结点结构
 *
 * @param <T> 结点元素类型
 * @author QFJiang on 2017/10/13
 */
public class ListNode<T> {

    private T data;
    private ListNode<T> next;

    public ListNode() {
    }

    public ListNode(T data) {
        this.data = data;
    }

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" + "data=" + data + "}";
    }
}
