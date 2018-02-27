package alg.linear;

/**
 * 基于链表实现的队列
 *
 * @param <T> 队列元素类型
 * @author QFJiang on 2017/10/25
 */
public class LLQueue<T> {

    private ListNode<T> front;
    private ListNode<T> rear;

    public LLQueue() {
        this.front = null;
        this.rear = null;
    }

    // Basic Operator

    public void enQueue(T item) {
        ListNode<T> node = new ListNode<>(item);
        if (rear != null)
            rear.setNext(node);
        rear = node;
        if (front == null)
            front = rear;
    }

    public T deQueue() {
        T data;
        if (empty())
            throw new RuntimeException("Empty Queue!");
        else {
            data = front.getData();
            front = front.getNext();
        }
        return data;
    }

    public T front() {
        if (front == null)
            return null;
        else
            return front.getData();
    }

    public void clear() {
        front = null;
        rear = null;
    }

    // Auxiliary Operator

    public boolean empty() {
        return front == null;
    }

    public int size() {
        int length = 0;
        ListNode<T> temp = front;
        while (temp != null) {
            length++;
            temp = temp.getNext();
        }
        return length;
    }
}
