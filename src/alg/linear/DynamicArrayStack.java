package alg.linear;

/**
 * 基于动态数组实现的栈
 * <p>
 * 解决了固定数组的局限性
 *
 * @param <T> 栈元素类型
 * @author QFJiang on 2017/10/18
 */
public class DynamicArrayStack<T> {

    private int top;
    private int capacity;
    private T[] values;

    public DynamicArrayStack() {
        this.top = -1;
        this.capacity = 10;
        this.values = (T[]) new Object[capacity];
    }

    public DynamicArrayStack(int capacity) {
        this.top = -1;
        this.capacity = capacity;
        this.values = (T[]) new Object[capacity];
    }

    // Basic Operator

    public void push(T data) {
        if (!full())
            values[++top] = data;
        else {
            T[] array = (T[]) new Object[capacity * 2];
            System.arraycopy(values, 0, array, 0, capacity);
            capacity = capacity * 2;
            values = array;
            values[++top] = data;
        }
    }

    public T pop() {
        if (!empty())
            return values[top--];
        else
            return null;
    }

    public T peek() {
        if (!empty())
            return values[top];
        else {
            return null;
        }
    }

    public void clear() {
        top = -1;
    }

    // Auxiliary Operator

    public boolean empty() {
        return top == -1;
    }

    public boolean full() {
        return top == capacity - 1;
    }

    public int size() {
        return top + 1;
    }

    public int capacity() {
        return capacity;
    }
}
