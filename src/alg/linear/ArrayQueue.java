package alg.linear;

/**
 * 基于固定数组实现的队列
 *
 * @param <T> 队列元素类型
 * @author QFJiang on 2017/10/25
 */
public class ArrayQueue<T> {

    private int front;
    private int rear;
    private int capacity;
    private T[] values;

    public ArrayQueue() {
        this.front = -1;
        this.rear = -1;
        this.capacity = 10;
        this.values = (T[]) new Object[capacity];
    }

    public ArrayQueue(int capacity) {
        this.front = -1;
        this.rear = -1;
        this.capacity = capacity;
        this.values = (T[]) new Object[capacity];
    }

    public void enQueue(T data) {
        if (full())
            throw new RuntimeException("Full Queue!");
        else {
            rear = (rear + 1) % capacity;
            values[rear] = data;
            if (front == -1)
                front = rear;
        }
    }

    public T deQueue() {
        if (empty())
            throw new RuntimeException("Empty Queue!");
        else {
            T data = values[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % capacity;
            }
            return data;
        }
    }

    public boolean empty() {
        return rear == -1;
    }

    public boolean full() {
        return (rear + 1) % capacity == front;
    }

    public int size() {
        int length = (capacity + rear - front + 1) % capacity;
        return empty() ? 0 : length == 0 ? capacity : length;
    }
}
