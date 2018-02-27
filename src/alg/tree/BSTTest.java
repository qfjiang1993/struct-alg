package alg.tree;

/**
 * @author QFJiang on 2017/10/31
 */
public class BSTTest {

    public static void main(String[] args) {
        BSTNode<Integer> r = new BSTNode<>(5);
        BSTOperation.insert(r, 3);
        BSTOperation.insert(r, 7);
        BSTOperation.insert(r, 4);
        BSTOperation.insert(r, 6);
        BSTOperation.insert(r, 9);
        BSTOperation.insert(r, 13);
        System.out.println(BSTOperation.inOrderBst(r));
        System.out.println(BSTOperation.findMax(r));
        System.out.println(BSTOperation.findMin(r));
        System.out.println(BSTOperation.isBST(r));

        BSTNode<Integer> root = BSTOperation.buildBSTByOrderedArray(new Integer[]{1, 3, 4, 5, 6, 8, 11, 16}, 0, 7);
        System.out.println(BSTOperation.inOrderBst(root));
    }
}
