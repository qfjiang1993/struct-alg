package alg.linear;

/**
 * @author QFJiang on 2017/10/13
 */
public class ListBasicOperation {

    public static <T> void deleteLinkedList(ListNode<T> headNode) {
        int size = getListLength(headNode);
        ListNode<T> node = headNode, next;
        int pos = 0;
        while (pos++ < size) {
            next = node.getNext();
            node.setData(null);
            node.setNext(null);
            node = next;
        }
    }

    /**
     * 删除链表中指定位置的结点
     *
     * @param headNode 头结点
     * @param position 为插入结点在新链表的位置，范围在0到链表长度
     * @param <T>      链表元素类型
     * @return 头结点
     */
    public static <T> ListNode<T> removeFromLinkedList(ListNode<T> headNode, int position) {
        int size = getListLength(headNode);
        if (position < 0 || position > size - 1) {
            System.out.println("Invalid position.The valid position between 0 and " + (size - 1));
            return headNode;
        }
        if (position == 0) {
            ListNode<T> currentNode = headNode.getNext();
            headNode = null;
            return currentNode;
        } else {
            ListNode<T> previousNode = headNode;
            while (position-- > 1) {
                previousNode = previousNode.getNext();
            }
            ListNode<T> currentNode = previousNode.getNext();
            previousNode.setNext(currentNode.getNext());
            currentNode = null;
            return headNode;
        }
    }

    /**
     * 向链表指定位置插入结点
     *
     * @param headNode   头结点
     * @param insertNode 插入结点
     * @param position   为插入结点在新链表的位置，范围在0到链表长度
     * @param <T>        链表元素类型
     * @return 头结点
     */
    public static <T> ListNode<T> insertToLinkedList(ListNode<T> headNode, ListNode<T> insertNode, int position) {
        if (headNode == null)
            return insertNode;
        int size = getListLength(headNode);
        if (position > size || position < 0) {
            System.out.println("Invalid position.The valid position between 0 and " + size);
            return headNode;
        }
        if (position == 0) {
            insertNode.setNext(headNode);
            return insertNode;
        } else {
            ListNode<T> posNode = headNode;
            while (position-- > 1) {
                posNode = posNode.getNext();
            }
            insertNode.setNext(posNode.getNext());
            posNode.setNext(insertNode);
        }
        return headNode;
    }

    // 打印链表（不考虑环）
    public static <T> void printLinkedList(ListNode<T> headNode) {
        ListNode<T> currentNode = headNode;
        while (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            currentNode = currentNode.getNext();
        }
        System.out.println();
    }

    // 计算链表长度（不考虑环）
    public static int getListLength(ListNode headNode) {
        int length = 0;
        ListNode currentNode = headNode;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }
}
