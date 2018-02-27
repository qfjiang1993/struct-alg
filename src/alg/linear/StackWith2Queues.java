package alg.linear;

/**
 * 使用两个队列实现栈
 *
 * @param <T> 栈元素类型
 * @author QFJiang on 2017/10/25
 */
public class StackWith2Queues<T> {
    private LLQueue<T> q1;
    private LLQueue<T> q2;

    public StackWith2Queues() {
        q1 = new LLQueue<>();
        q2 = new LLQueue<>();
    }

    public boolean empty() {
        return q1.empty() && q2.empty();
    }

    public void push(T data) {
        if (q1.empty())
            q2.enQueue(data);
        else
            q1.enQueue(data);
    }

    public T pop() {
        if (empty())
            throw new RuntimeException("Empty Stack");
        if (q1.empty()) {
            int size = q2.size();
            int i = 0;
            while (i++ < size - 1)
                q1.enQueue(q2.deQueue());
            return q2.deQueue();
        } else {
            int size = q1.size();
            int i = 0;
            while (i++ < size - 1)
                q2.enQueue(q1.deQueue());
            return q1.deQueue();
        }
    }

    public T peek() {
        if (empty())
            return null;
        else if (!q1.empty())
            return q1.front();
        else
            return q2.front();
    }
}
