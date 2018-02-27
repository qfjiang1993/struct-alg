package alg.tree;

import static alg.tree.BinaryTreeProblems.*;
import static alg.tree.BinaryTreeTraversal.*;

/**
 * @author QFJiang on 2017/10/26
 */
public class TreeTest {

    public static void main(String[] args) {

        BinaryTreeNode<Integer> r = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> a = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> b = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> c = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> d = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> e = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> f = new BinaryTreeNode<>(7);

        r.setLeft(a);
        r.setRight(b);
        a.setLeft(c);
        a.setRight(d);
        b.setLeft(e);
        b.setRight(f);

        preOrderIterative(r);
        inOrderIterative(r);
        postOrderIterative(r);
        levelOrderTraversal(r);
        System.out.println();

        System.out.println(leastCommonAncestor(r, a, b));
        System.out.println(leastCommonAncestor(r, a, d));
        System.out.println(leastCommonAncestor(r, a, e));
        System.out.println(leastCommonAncestor(r, c, d));
        System.out.println(leastCommonAncestor(r, c, e));

        Integer[] pre = new Integer[]{1, 2, 4, 1, 3, 6, 7};
        Integer[] in = new Integer[]{4, 2, 1, 1, 6, 3, 7};
        Integer[] post = new Integer[]{4, 5, 1, 6, 7, 3, 1};
        Integer[] level = new Integer[]{1, 1, 3, 4, 5, 6, 7};

        BinaryTreeNode<Integer> root = rebuildTreeByPreIn(in, pre, 0, 6);
        levelOrderTraversal(root);

        zigZagTraversal(r);
        levelOrderTraversal(rebuildTreeByPreOrder(new Character[]{'I', 'I', 'I', 'L', 'L', 'L', 'L'}));
    }
}
