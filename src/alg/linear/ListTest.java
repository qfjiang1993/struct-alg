package alg.linear;

/**
 * @author QFJiang on 2017/10/16
 */
public class ListTest {

    public static void main(String[] args) {

        ListNode<Integer> node0 = new ListNode<>(0);
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        ListNode<Integer> node6 = new ListNode<>(6);
        ListNode<Integer> node7 = new ListNode<>(7);
        ListNode<Integer> node8 = new ListNode<>(8);
        ListNode<Integer> node9 = new ListNode<>(9);

        node0.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);
        node9.setNext(null);

        ListBasicOperation.printLinkedList(node0);
        ListBasicOperation.printLinkedList(ListProblems.reverseListInKthBlock(node0, 3));

        ListNode<Integer> node = new ListNode<>(15, null);
        System.out.println(node);
        changeNode(node);
        System.out.println(node);

    }

    private static void changeNode(ListNode<Integer> node) {
        ListNode<Integer> temp = new ListNode<>(23, null);
        node = temp;
        System.out.println(node);
    }
}
