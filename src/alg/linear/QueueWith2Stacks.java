package alg.linear;

/**
 * 使用两个栈实现队列，S1负责入队，出队时先将S1弹出压入到S2，然后由S2出队。
 *
 * @param <T> 队列元素类型
 * @author QFJiang on 2017/10/25
 */
public class QueueWith2Stacks<T> {
    private LLStack<T> s1;
    private LLStack<T> s2;

    public QueueWith2Stacks() {
        s1 = new LLStack<>();
        s2 = new LLStack<>();
    }

    public boolean empty() {
        if (s2.empty())
            while (!s1.empty())
                s2.push(s1.pop());
        return s2.empty();
    }

    public void enQueue(T data) {
        s1.push(data);
    }

    public T deQueue() {
        if (!s2.empty())
            return s2.pop();
        else {
            while (!s1.empty())
                s2.push(s1.pop());
            if (!s2.empty())
                return s2.pop();
            else
                System.out.println("Empty queue!");
            return null;
        }
    }
}
