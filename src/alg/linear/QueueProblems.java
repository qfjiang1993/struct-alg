package alg.linear;

/**
 * @author QFJiang on 2017/10/25
 */
public class QueueProblems {

    /**
     * 逆置队列的前k个元素
     */
    public static <T> void reverseQueueFirstKth(int k, LLQueue<T> queue) {
        if (queue == null || k > queue.size())
            throw new IllegalArgumentException("Argument Illegal");
        if (k > 0) {
            LLStack<T> stack = new LLStack<>();
            for (int i = 0; i < k; i++)
                stack.push(queue.deQueue());
            while (!stack.empty())
                queue.enQueue(stack.pop());
            for (int i = 0; i < queue.size() - k; i++) {
                queue.enQueue(queue.deQueue());
            }
        }
    }
}
