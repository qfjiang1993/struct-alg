package alg.linear;

/**
 * 基于链表实现的栈
 *
 * @param <T> 栈元素类型
 * @author QFJiang on 2017/10/18
 */
public class LLStack<T> {

    private ListNode<T> head;

    public LLStack() {
        this.head = null;
    }

    // Basic Operator

    public void push(T data) {
        if (head == null) {
            head = new ListNode<>(data);
            head.setNext(null);
        } else {
            ListNode<T> temp = new ListNode<>(data);
            temp.setNext(head);
            head = temp;
        }
    }

    public T pop() {
        if (head == null)
            return null;
        else {
            T temp = head.getData();
            head = head.getNext();
            return temp;
        }
    }

    public T peek() {
        if (head == null)
            return null;
        else {
            return head.getData();
        }
    }

    public void clear() {
        head = null;
    }

    // Auxiliary Operator

    public boolean empty() {
        return head == null;
    }

    public int size() {
        ListNode<T> temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }
}
