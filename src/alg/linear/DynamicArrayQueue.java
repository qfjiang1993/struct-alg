package alg.linear;

/**
 * 基于动态数组实现的队列
 *
 * @param <T> 队列元素类型
 * @author QFJiang on 2017/10/25
 */
public class DynamicArrayQueue<T> {

    private int front;
    private int rear;
    private int capacity;
    private T[] values;

    public DynamicArrayQueue() {
        this.front = -1;
        this.rear = -1;
        this.capacity = 10;
        this.values = (T[]) new Object[capacity];
    }

    public DynamicArrayQueue(int capacity) {
        this.front = -1;
        this.rear = -1;
        this.capacity = capacity;
        this.values = (T[]) new Object[capacity];
    }
}
