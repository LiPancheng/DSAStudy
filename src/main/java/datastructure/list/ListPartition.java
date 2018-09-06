package datastructure.list;

import datastructure.ListNode;

/**
 * 将单链表按某给定值划分为左边小，中间相等，右边大的形式
 */
public class ListPartition {
    public ListNode partition(ListNode head, int pivot){
        ListNode bigHead = null, bigTail = null,
                smallHead = null, smallTail = null,
                equalHead = null, equalTail = null,
                next = head;
        while (head != null){
            //着重要注意next置为null的处理，如果没有这里的处理，最后链接成的链表尾结点将还指向其中某个结点
            next = head.next;
            head.next = null;

            if (head.value < pivot){
                if (smallHead == null){
                    smallHead = head;
                    smallTail = head;
                }
                else {
                    smallTail.next = head;
                    smallTail = head;
                }
            }
            else if (head.value > pivot){
                if (bigHead == null){
                    bigHead = head;
                    bigTail = head;
                }
                else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            else {
                if (equalHead == null){
                    equalHead = head;
                    equalTail = head;
                }
                else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }
            head = next;
        }

        if (smallTail != null){
            equalHead = equalHead != null ? equalHead : bigHead; //如果没有这句，equalHead为空时将无法去链接bigHead
            smallTail.next = equalHead;
        }
        if (equalTail != null){
            equalTail.next = bigHead;
        }

        return smallHead != null ? smallHead : equalHead != null ? equalHead : bigHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(0);
        node1.next = node2;
        ListNode node3 = new ListNode(4);
        node2.next = node3;
        ListNode node4 = new ListNode(5);
        node3.next = node4;
        ListNode node5 = new ListNode(1);
        node4.next = node5;

        ListNode res = new ListPartition().partition(node1, 3);
        while (res != null){
            System.out.print(res.value);
            res = res.next;
        }
    }
}
