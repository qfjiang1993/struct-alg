package alg.tree;

/**
 * @author QFJiang on 2017/11/02
 */
public class AVLTest {

    public static void main(String[] args) {

        AVLTreeNode<Integer> root = new AVLTreeNode<>(3);
        root = AVLOperation.insert(root, 2);
        root = AVLOperation.insert(root, 1);
        root = AVLOperation.insert(root, 4);
        root = AVLOperation.insert(root, 5);
        root = AVLOperation.insert(root, 6);
        root = AVLOperation.insert(root, 7);
        root = AVLOperation.delete(root, 8);
        System.out.println(root);
    }
}
