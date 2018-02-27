package alg.linear;

/**
 * @author QFJiang on 2017/10/16
 */
public class ListProblems {

    /**
     * 找出链表中倒数第n个元素（不考虑存在环），双指针法，指针相隔n-1，只需遍历一遍。
     * 另解：先遍历一遍得出链表长度L，然后遍历到第L-n个元素即为所求。
     *
     * @param head 链表头结点
     * @param n    1表示最后一个
     * @param <T>  链表元素类型
     * @return 倒数第n个元素，null表示不存在
     */
    public static <T> ListNode<T> findNthFromEnd(ListNode<T> head, int n) {
        if (head == null || n <= 0)
            return null;
        ListNode<T> nthPtr = head;
        ListNode<T> tempPtr = head;
        while (--n > 0) {
            if (tempPtr != null)
                tempPtr = tempPtr.getNext();
            else
                return null;
        }
        while (tempPtr != null) {
            if (tempPtr.getNext() == null)
                return nthPtr;
            else {
                tempPtr = tempPtr.getNext();
                nthPtr = nthPtr.getNext();
            }
        }
        return null;
    }

    /**
     * 判断链表是否存在环，Floyd环判定算法，快慢指针。
     * 另解：散列表，遍历链表将元素地址存入散列表，第二次插入相同的地址即存在环。
     *
     * @param head 链表头结点
     * @param <T>  链表元素类型
     * @return 是否存在环
     */
    public static <T> boolean existsLoop(ListNode<T> head) {
        if (head == null)
            return false;
        ListNode<T> fastPtr = head;
        ListNode<T> slowPtr = head;
        while (fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext();
            if (fastPtr.getNext() != null)
                fastPtr = fastPtr.getNext();
            else
                return false;
            slowPtr = slowPtr.getNext();
            if (fastPtr == slowPtr)
                return true;
        }
        return false;
    }

    /**
     * 判断链表是否存在环，存在则找出环的起始结点。扩展Floyd环判定算法，找到环之后，
     * 将慢指针指向头结点，然后快慢指针每次移动一步，相遇位置即为环起始结点。
     * 另解：散列表，遍历链表将元素地址存入散列表，第二次插入相同地址的元素即为环的起始结点。
     *
     * @param head 链表头结点
     * @param <T>  链表元素类型
     * @return 环的起始结点，null表示不存在环
     */
    public static <T> ListNode<T> findBeginOfLoop(ListNode<T> head) {
        if (head == null)
            return null;
        ListNode<T> fastPtr = head;
        ListNode<T> slowPtr = head;
        boolean existsLoop = false;
        while (fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext();
            if (fastPtr.getNext() != null)
                fastPtr = fastPtr.getNext();
            else
                return null;
            slowPtr = slowPtr.getNext();
            if (fastPtr == slowPtr) {
                existsLoop = true;
                break;
            }
        }
        if (existsLoop) {
            slowPtr = head;
            while (true) {
                slowPtr = slowPtr.getNext();
                fastPtr = fastPtr.getNext();
                if (fastPtr == slowPtr)
                    return slowPtr;
            }
        }
        return null;
    }

    /**
     * 判断链表是否存在环，存在则找出环的长度。扩展Floyd环判定算法，找到环之后，
     * 快指针不动，慢指针每次移动一步并将计数器加一，相遇时即得环的长度。
     * 另解：散列表，遍历链表将元素地址存入散列表，并辅以计数器。
     *
     * @param head 链表头结点
     * @param <T>  链表元素类型
     * @return 环的长度，0表示不存在环
     */
    public static <T> int findLengthOfLoop(ListNode<T> head) {
        if (head == null)
            return 0;
        ListNode<T> fastPtr = head;
        ListNode<T> slowPtr = head;
        boolean existsLoop = false;
        while (fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext();
            if (fastPtr.getNext() != null)
                fastPtr = fastPtr.getNext();
            else
                return 0;
            slowPtr = slowPtr.getNext();
            if (fastPtr == slowPtr) {
                existsLoop = true;
                break;
            }
        }
        int length = 0;
        if (existsLoop) {
            while (true) {
                slowPtr = slowPtr.getNext();
                length++;
                if (fastPtr == slowPtr)
                    return length;
            }
        }
        return 0;
    }

    /**
     * 逆置单向链表，相当于每次在表头插入元素。
     *
     * @param head 链表头结点
     * @param <T>  链表元素类型
     * @return 新链表头结点
     */
    public static <T> ListNode<T> reverseList(ListNode<T> head) {
        ListNode<T> temp = null;
        ListNode<T> next;
        while (head != null) {
            next = head.getNext();
            head.setNext(temp);
            temp = head;
            head = next;
        }
        return temp;
    }

    /**
     * 求两个单向链表的合并点，遍历两个链表计算长度之差。
     *
     * @param list1 链表1的头结点
     * @param list2 链表2的头结点
     * @param <T>   链表元素类型
     * @return 合并点
     */
    public static <T> ListNode<T> findIntersectingNode(ListNode<T> list1, ListNode<T> list2) {
        int length1 = 0;
        int length2 = 0;
        ListNode<T> head1 = list1;
        ListNode<T> head2 = list2;
        while (head1 != null) {
            length1++;
            head1 = head1.getNext();
        }
        while (head2 != null) {
            length2++;
            head2 = head2.getNext();
        }
        head1 = list1;
        head2 = list2;
        int delta = length1 - length2;
        if (delta >= 0)
            while (delta-- > 0)
                head1 = head1.getNext();
        else
            while (delta++ < 0)
                head2 = head2.getNext();

        while (head1 != null && head2 != null) {
            if (head1 == head2)
                return head1;
            else {
                head1 = head1.getNext();
                head2 = head2.getNext();
            }
        }
        return null;
    }

    /**
     * 找到链表的中间结点，快慢指针。
     *
     * @param head 链表头结点
     * @param <T>  链表元素类型
     * @return 中间结点，奇数个结点返回第n/2个，偶数个返回第n/2-1或n/2个
     */
    public static <T> ListNode<T> findMiddleNode(ListNode<T> head) {
        if (head == null)
            return null;
        ListNode<T> fastPtr = head;
        ListNode<T> slowPtr = head;
        while (fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext();
            if (fastPtr.getNext() != null)
                fastPtr = fastPtr.getNext();
            else
                break;
            slowPtr = slowPtr.getNext();
        }
        return slowPtr; // 偶数时返回第n/2-1个
//        return slowPtr.getNext(); // 偶数时返回第n/2个
    }

    /**
     * 合并两个有序链表（均为递增）。
     *
     * @param list1 链表1头结点
     * @param list2 链表2头结点
     * @return 新的链表头结点
     */
    public static ListNode<Integer> mergeList(ListNode<Integer> list1, ListNode<Integer> list2) {
        ListNode<Integer> result;
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.getData() <= list2.getData()) {
            result = list1;
            result.setNext(mergeList(list1.getNext(), list2));
        } else {
            result = list2;
            result.setNext(mergeList(list2.getNext(), list1));
        }
        return result;
    }

    /**
     * 逐对逆置链表，例如1，2，3，4，X，逆置后为2，1，4，3，X。
     *
     * @param head 链表头结点
     * @param <T>  链表元素类型
     * @return 新链表头结点
     */
    public static <T> ListNode<T> reverseListByPair(ListNode<T> head) {
        ListNode<T> temp = null;
        ListNode<T> result = null;
        while (head != null && head.getNext() != null) {
            if (temp != null)
                temp.getNext().setNext(head.getNext());
            temp = head.getNext();
            head.setNext(head.getNext().getNext());
            temp.setNext(head);
            if (result == null)
                result = temp;
            head = head.getNext();
        }
        return result;
    }

    /**
     * 将循环链表差分成等长的两个循环链表。
     * <p>
     * （Java传递的是引用的拷贝值，list1和list2传递不出去）
     *
     * @param head  链表头结点
     * @param list1 链表1的头结点
     * @param list2 链表2的头结点
     * @param <T>   链表元素类型
     */
    public static <T> void splitList(ListNode<T> head, ListNode<T> list1, ListNode<T> list2) {
        ListNode<T> fastPtr = head;
        ListNode<T> slowPtr = head;
        if (head == null)
            return;
        while (fastPtr.getNext() != head && fastPtr.getNext().getNext() != head) {
            fastPtr = fastPtr.getNext().getNext();
            slowPtr = slowPtr.getNext();
        }
        if (fastPtr.getNext().getNext() == head)
            fastPtr = fastPtr.getNext();
        list1 = head;
        if (head.getNext() != head)
            list2 = slowPtr.getNext();
        fastPtr.setNext(list2);
        slowPtr.setNext(list1);
    }

    /**
     * 判断链表是否为回文，先找到中点，然后逆置后半部分，比较完成后，还原链表。
     *
     * @param head 链表头结点
     * @param <T>  链表元素类型
     * @return 是否为回文，链表为空时返回true
     */
    public static <T> boolean isPlalindromeList(ListNode<T> head) {
        boolean result = true;
        ListNode<T> fastPtr = head;
        ListNode<T> slowPtr = head;
        if (head == null)
            return true;
        while (fastPtr.getNext() != null && fastPtr.getNext().getNext() != null) {
            fastPtr = fastPtr.getNext().getNext();
            slowPtr = slowPtr.getNext();
        }
        int flag = 1;   // 链表长度奇偶性，0为偶数，1为奇数
        if (fastPtr.getNext() != null && fastPtr.getNext().getNext() == null)
            flag = 0;
        ListNode<T> secondHead;
        if (flag == 0)
            secondHead = reverseList(slowPtr.getNext());
        else
            secondHead = reverseList(slowPtr);
        slowPtr.setNext(null);
        ListNode<T> temp1 = head;
        ListNode<T> temp2 = secondHead;
        while (temp1 != null && temp2 != null) {
            if (!temp1.getData().equals(temp2.getData()))
                result = false;
            temp1 = temp1.getNext();
            temp2 = temp2.getNext();
        }
        secondHead = reverseList(secondHead);
        if (flag == 0)
            slowPtr.setNext(secondHead);
        else
            slowPtr.setNext(secondHead.getNext());
        return result;
    }

    /**
     * 逆置链表中包含k个结点的块，每次取出k个结点逆置，然后将前一块与当前块拼接起来。
     *
     * @param head 链表头结点
     * @param k    块包含的结点数
     * @param <T>  链表元素类型
     * @return 新链表头结点
     */
    public static <T> ListNode<T> reverseListInKthBlock(ListNode<T> head, int k) {
        if (head == null)
            return null;
        if (k == 0 || k == 1)
            return head;
        ListNode<T> cur = head;
        ListNode<T> prevTail = null;
        ListNode<T> kthHead = null;
        ListNode<T> kthTail = null;
        ListNode<T> newHead = null;
        while (cur != null) {
            if (hasNextKnodes(cur, k)) {
                if (kthHead != null)
                    prevTail = kthTail;
                kthHead = cur;
                int count = k;
                while (--count > 0)
                    cur = cur.getNext();
                kthTail = cur;
                cur = cur.getNext();
                kthTail.setNext(null);
                kthTail = kthHead;
                kthHead = reverseList(kthHead);
                if (prevTail != null)
                    prevTail.setNext(kthHead);
                if (newHead == null)
                    newHead = kthHead;
            } else {
                if (kthHead != null) {
                    prevTail = kthTail;
                    prevTail.setNext(cur);
                }
                break;
            }
        }
        return newHead == null ? head : newHead;
    }

    /**
     * 检查链表当前是否还有k个元素。
     *
     * @param head 链表头结点
     * @param k    元素个数
     * @param <T>  链表元素类型
     * @return 是否还有k个元素
     */
    public static <T> boolean hasNextKnodes(ListNode<T> head, int k) {
        ListNode<T> temp = head;
        while (k-- > 0) {
            if (temp == null)
                return false;
            temp = temp.getNext();
        }
        return true;
    }

//    public static int getJosephusPosition(int n, int m) {
//        ListNode<Integer> p1 = new ListNode<>();
//        ListNode<Integer> p2 = new ListNode<>();
//        p1.setData(1);
//        p1.setNext(p2);
//        for (int i = 2; i <= n; i++) {
//            p2.setData(i);
//            p2.setNext(new ListNode<>());
//            p2 = p2.getNext();
//        }
//
//    }

}
