package alg.linear;

/**
 * @author QFJiang on 2017/10/25
 */
public class QueueTest {

    public static void main(String[] args) {

        LLQueue<Integer> queue = new LLQueue<>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        QueueProblems.reverseQueueFirstKth(2, queue);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
