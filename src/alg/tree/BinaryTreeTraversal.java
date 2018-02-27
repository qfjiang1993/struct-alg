package alg.tree;

import alg.linear.LLQueue;
import alg.linear.LLStack;

/**
 * 二叉树的遍历
 *
 * @author QFJiang on 2017/10/26
 */
public class BinaryTreeTraversal {

    /**
     * 前序遍历的递归实现
     */
    public static <T> void preOrderRecursive(BinaryTreeNode<T> root) {
        if (root != null) {
            System.out.print(root.getData() + " ");
            preOrderRecursive(root.getLeft());
            preOrderRecursive(root.getRight());
        }
    }

    /**
     * 前序遍历的迭代实现
     */
    public static <T> void preOrderIterative(BinaryTreeNode<T> root) {
        LLStack<BinaryTreeNode<T>> stack = new LLStack<>();
        while (true) {
            while (root != null) {
                System.out.print(root.getData() + " ");
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.empty())
                break;
            root = stack.pop();
            root = root.getRight();
        }
        System.out.println();
    }

    /**
     * 中序遍历的递归实现
     */
    public static <T> void inOrderRecursive(BinaryTreeNode<T> root) {
        if (root != null) {
            inOrderRecursive(root.getLeft());
            System.out.print(root.getData() + " ");
            inOrderRecursive(root.getRight());
        }
    }

    /**
     * 中序遍历的迭代实现
     */
    public static <T> void inOrderIterative(BinaryTreeNode<T> root) {
        LLStack<BinaryTreeNode<T>> stack = new LLStack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.empty())
                break;
            root = stack.pop();
            System.out.print(root.getData() + " ");
            root = root.getRight();
        }
        System.out.println();
    }

    /**
     * 后序遍历的递归实现
     */
    public static <T> void postOrderRecursive(BinaryTreeNode<T> root) {
        if (root != null) {
            postOrderRecursive(root.getLeft());
            postOrderRecursive(root.getRight());
            System.out.print(root.getData() + " ");
        }
    }

    /**
     * 后序遍历的迭代实现
     * <p>
     * 巧妙解法：后序遍历为LRD，逆序之后就是DRL，即将先序遍历的LR对换，遍历完成后逆序输出。
     * 辅助结点法：使用prev表示上一次访问的结点，判断当前结点cur与prev的关系，
     * 从而确定当前是从上到下遍历、还是从下到上遍历，进一步决定采取的操作。
     * 另解：1、先从根结点，依次入栈左子树的根结点，直到左子树为空。
     * 2、从栈中弹出一个结点作为当前cur结点，则cur结点为叶子结点或只包含右子树的结点，
     * cur结点被访问的前提是无右子树或右子树已被访问。若cur结点被访问，则将上一个访问
     * 结点prev设置为cur结点；否则，cur结点入栈，并依次遍历到右子树的最左边。
     */
    public static <T> void postOrderIterative(BinaryTreeNode<T> root) {
        LLStack<BinaryTreeNode<T>> stack = new LLStack<>();
        LLStack<T> output = new LLStack<>();
        while (true) {
            while (root != null) {
                output.push(root.getData());
                stack.push(root);
                root = root.getRight();
            }
            if (stack.empty())
                break;
            root = stack.pop();
            root = root.getLeft();
        }
        while (!output.empty())
            System.out.print(output.pop() + " ");
        System.out.println();

//        if (root == null)
//            return;
//        LLStack<BinaryTreeNode<T>> stack = new LLStack<>();
//        BinaryTreeNode<T> prev = null;
//        BinaryTreeNode<T> cur = null;
//        stack.push(root);
//        while (!stack.empty()) {
//            cur = stack.peek();
//            if (prev == null || prev.getLeft() == cur || prev.getRight() == cur) {
//                if (cur.getLeft() != null)
//                    stack.push(cur.getLeft());
//                else if (cur.getRight() != null)
//                    stack.push(cur.getRight());
//                else {
//                    System.out.print(cur.getData() + " ");
//                    stack.pop();
//                }
//            } else if (cur.getLeft() == prev) {
//                if (cur.getRight() != null)
//                    stack.push(cur.getRight());
//                else {
//                    System.out.print(cur.getData() + " ");
//                    stack.pop();
//                }
//            } else if (cur.getRight() == prev) {
//                System.out.print(cur.getData() + " ");
//                stack.pop();
//            }
//            prev = cur;
//        }
//        System.out.println();

//        LLStack<BinaryTreeNode<T>> stack = new LLStack<>();
//        BinaryTreeNode<T> prev = null;
//        while (root != null) {
//            stack.push(root);
//            root = root.getLeft();
//        }
//        while (!stack.empty()) {
//            root = stack.pop();
//            if (root.getRight() != null && root.getRight() != prev) {
//                stack.push(root);
//                root = root.getRight();
//                while (root != null) {
//                    stack.push(root);
//                    root = root.getLeft();
//                }
//            } else {
//                System.out.print(root.getData() + " ");
//                prev = root;
//            }
//        }
//        System.out.println();
    }

    /**
     * 层次遍历，从上到下，从左到右
     */
    public static <T> void levelOrderTraversal(BinaryTreeNode<T> root) {
        if (root == null)
            return;
        LLQueue<BinaryTreeNode<T>> queue = new LLQueue<>();
        BinaryTreeNode<T> temp;
        queue.enQueue(root);
        while (!queue.empty()) {
            temp = queue.deQueue();
            System.out.print(temp.getData() + " ");
            if (temp.getLeft() != null)
                queue.enQueue(temp.getLeft());
            if (temp.getRight() != null)
                queue.enQueue(temp.getRight());
        }
        System.out.println();
    }

    /**
     * 逆向层次遍历，从下到上，从左到右
     */
    public static <T> void levelOrderTraversalReverse(BinaryTreeNode<T> root) {
        if (root == null)
            return;
        LLStack<T> stack = new LLStack<>();
        LLQueue<BinaryTreeNode<T>> queue = new LLQueue<>();
        BinaryTreeNode<T> temp;
        queue.enQueue(root);
        while (!queue.empty()) {
            temp = queue.deQueue();
            if (temp.getRight() != null)
                queue.enQueue(temp.getRight());
            if (temp.getLeft() != null)
                queue.enQueue(temp.getLeft());
            stack.push(temp.getData());
        }
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
        System.out.println();
    }
}
