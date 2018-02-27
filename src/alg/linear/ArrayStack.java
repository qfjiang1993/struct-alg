package alg.linear;

/**
 * 基于固定数组实现的栈
 * <p>
 * 最大局限性在于栈的最大空间固定且必须预先声明
 *
 * @param <T> 栈元素类型
 * @author QFJiang on 2017/10/18
 */
public class ArrayStack<T> {

    private int top;
    private int capacity;
    private T[] values;

    public ArrayStack() {
        this.top = -1;
        this.capacity = 20;
        this.values = (T[]) new Object[capacity];
    }

    public ArrayStack(int capacity) {
        this.top = -1;
        this.capacity = capacity;
        this.values = (T[]) new Object[capacity];
    }

    // Basic Operator

    public void push(T data) {
        if (!full())
            values[++top] = data;
        else
            System.out.println("Stack is full!");
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
