package datastructure.list;

import datastructure.ListNode;

public class ListPartition {
    /**
     *
     */
    public ListNode partition(ListNode head, int pivot){
        ListNode bigHead = null, bigTail = null,
                smallHead = null, smallTail = null,
                equalHead = null, equalTail = null,
                next = head;
        while (head != null){
            //着重要注意next置为null的处理，如果没有这里的处理，最后链接成的链表尾结点将还指向其中某个结点
            next = head.next();
            head.setNext(null);

            if (head.getValue() < pivot){
                if (smallHead == null){
                    smallHead = head;
                    smallTail = head;
                }
                else {
                    smallTail.setNext(head);
                    smallTail = head;
                }
            }
            else if (head.getValue() > pivot){
                if (bigHead == null){
                    bigHead = head;
                    bigTail = head;
                }
                else {
                    bigTail.setNext(head);
                    bigTail = head;
                }
            }
            else {
                if (equalHead == null){
                    equalHead = head;
                    equalTail = head;
                }
                else {
                    equalTail.setNext(head);
                    equalTail = head;
                }
            }
            head = next;
        }

        if (smallTail != null){
            equalHead = equalHead != null ? equalHead : bigHead; //如果没有这句，equalHead为空时将无法去链接bigHead
            smallTail.setNext(equalHead);
        }
        if (equalTail != null){
            equalTail.setNext(bigHead);
        }

        return smallHead != null ? smallHead : equalHead != null ? equalHead : bigHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(0);
        node1.setNext(node2);
        ListNode node3 = new ListNode(4);
        node2.setNext(node3);
        ListNode node4 = new ListNode(5);
        node3.setNext(node4);
        ListNode node5 = new ListNode(1);
        node4.setNext(node5);

        ListNode res = new ListPartition().partition(node1, 3);
        while (res != null){
            System.out.print(res.getValue());
            res = res.next();
        }
    }
}
