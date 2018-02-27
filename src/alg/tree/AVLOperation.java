package alg.tree;

import alg.linear.LLStack;

/**
 * @author QFJiang on 2017/11/01
 */
public class AVLOperation {

    /**
     * 在AVL树中查找最大元素
     */
    public static <T> AVLTreeNode<T> findMax(AVLTreeNode<T> root) {
        if (root == null)
            return null;
        while (root.getRight() != null)
            root = root.getRight();
        return root;
    }

    /**
     * 在AVL树中查找最小元素
     */
    public static <T> AVLTreeNode<T> findMin(AVLTreeNode<T> root) {
        if (root == null)
            return null;
        while (root.getLeft() != null)
            root = root.getLeft();
        return root;
    }

    /**
     * 在AVL树中查找指定元素
     */
    public static <T extends Comparable<T>> AVLTreeNode<T> find(AVLTreeNode<T> root, T data) {
        if (root == null)
            return null;
        while (root != null) {
            if (data.compareTo(root.getData()) == 0)
                return root;
            else if (data.compareTo(root.getData()) < 0)
                root = root.getLeft();
            else
                root = root.getRight();
        }
        return null;
    }

    /**
     * 获取AVL数的高度
     */
    public static <T> int avlHeight(AVLTreeNode<T> root) {
        if (root == null)
            return -1;
        else
            return root.getHeight();
    }

    /**
     * LL旋转，用于LL失衡情况。
     *
     * @param k1 从插入结点到根结点第一个不满足AVL性质的结点
     * @return 旋转后满足AVL性质的新结点
     */
    public static <T> AVLTreeNode<T> llRotate(AVLTreeNode<T> k1) {
        AVLTreeNode<T> k2 = k1.getLeft();
        k1.setLeft(k2.getRight());
        k2.setRight(k1);
        k1.setHeight(Math.max(avlHeight(k1.getLeft()), avlHeight(k1.getRight())) + 1);
        k2.setHeight(Math.max(avlHeight(k2.getLeft()), k1.getHeight()) + 1);
        return k2;
    }

    /**
     * RR旋转，用于RR失衡情况。
     *
     * @param k1 从插入结点到根结点第一个不满足AVL性质的结点
     * @return 旋转后满足AVL性质的新结点
     */
    public static <T> AVLTreeNode<T> rrRotate(AVLTreeNode<T> k1) {
        AVLTreeNode<T> k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        k1.setHeight(Math.max(avlHeight(k1.getRight()), avlHeight(k1.getLeft())) + 1);
        k2.setHeight(Math.max(avlHeight(k2.getRight()), k1.getHeight()) + 1);
        return k2;
    }

    /**
     * LR旋转，用于LR失衡情况。先对Z的左结点RR旋转，再对Z进行LL旋转。
     *
     * @param Z 从插入结点到根结点第一个不满足AVL性质的结点
     * @return 旋转后满足AVL性质的新结点
     */
    public static <T> AVLTreeNode<T> lrRotate(AVLTreeNode<T> Z) {
        Z.setLeft(rrRotate(Z.getLeft()));
        return llRotate(Z);
    }

    /**
     * RL旋转，用于RL失衡情况。先对Z的右结点LL旋转，再对Z进行RR旋转。
     *
     * @param Z 从插入结点到根结点第一个不满足AVL性质的结点
     * @return 旋转后满足AVL性质的新结点
     */
    public static <T> AVLTreeNode<T> rlRotate(AVLTreeNode<T> Z) {
        Z.setRight(llRotate(Z.getRight()));
        return rrRotate(Z);
    }

    /**
     * 在AVL树中插入结点
     *
     * @param root AVL树根结点
     * @param data 插入的数据
     * @return AVL树的新根结点
     */
    public static <T extends Comparable<T>> AVLTreeNode<T> insert(AVLTreeNode<T> root, T data) {
        if (data == null)
            return root;
        if (root == null)
            return new AVLTreeNode<>(data);
        else if (data.compareTo(root.getData()) < 0) {
            root.setLeft(insert(root.getLeft(), data));
            root.setHeight(Math.max(avlHeight(root.getLeft()), avlHeight(root.getRight())) + 1);
            if (avlHeight(root.getLeft()) - avlHeight(root.getRight()) == 2) {
                if (data.compareTo(root.getLeft().getData()) < 0)
                    root = llRotate(root);
                else if (data.compareTo(root.getLeft().getData()) > 0)
                    root = lrRotate(root);
            }
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(insert(root.getRight(), data));
            root.setHeight(Math.max(avlHeight(root.getLeft()), avlHeight(root.getRight())) + 1);
            if (avlHeight(root.getRight()) - avlHeight(root.getLeft()) == 2) {
                if (data.compareTo(root.getRight().getData()) < 0)
                    root = rlRotate(root);
                else if (data.compareTo(root.getRight().getData()) > 0)
                    root = rrRotate(root);
            }
        }
        return root;
    }

    /**
     * 在AVL中删除结点
     *
     * @param root AVL树根结点
     * @param data 删除的数据
     * @return AVL树的新根结点
     */
    public static <T extends Comparable<T>> AVLTreeNode<T> delete(AVLTreeNode<T> root, T data) {
        if (data == null)
            return root;
        if (root == null)
            return null;
        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(delete(root.getLeft(), data));
            root.setHeight(Math.max(avlHeight(root.getLeft()), avlHeight(root.getRight())) + 1);
            if (avlHeight(root.getRight()) - avlHeight(root.getLeft()) == 2) {
                AVLTreeNode<T> node = root.getRight();
                if (avlHeight(node.getLeft()) > avlHeight(node.getRight()))
                    root = rlRotate(root);
                else
                    root = rrRotate(root);
            }
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(delete(root.getRight(), data));
            root.setHeight(Math.max(avlHeight(root.getLeft()), avlHeight(root.getRight())) + 1);
            if (avlHeight(root.getLeft()) - avlHeight(root.getRight()) == 2) {
                AVLTreeNode<T> node = root.getLeft();
                if (avlHeight(node.getLeft()) > avlHeight(node.getRight()))
                    root = llRotate(root);
                else
                    root = lrRotate(root);
            }
        } else {
            if (root.getLeft() != null && root.getRight() != null) {
                if (avlHeight(root.getLeft()) > avlHeight(root.getRight())) {
                    T leftMax = findMax(root.getLeft()).getData();
                    root.setData(leftMax);
                    root.setLeft(delete(root.getLeft(), leftMax));
                } else {
                    T rightMax = findMax(root.getRight()).getData();
                    root.setData(rightMax);
                    root.setRight(delete(root.getRight(), rightMax));
                }
            } else {
                root = root.getLeft() == null ? root.getRight() : root.getLeft();
            }
        }
        return root;
    }

    /**
     * 中序遍历AVL树
     *
     * @param root AVL根结点
     */
    public static <T> void inOrderTraversal(AVLTreeNode<T> root) {
        LLStack<AVLTreeNode<T>> stack = new LLStack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.empty())
                break;
            root = stack.pop();
            System.out.print(root.getData() + " -> ");
            root = root.getRight();
        }
        System.out.println();
    }
}
