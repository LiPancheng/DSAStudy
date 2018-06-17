package datastructure.list;

import datastructure.BiLNode;
import datastructure.ListNode;

public class SimpleListExe {
    /**
     * 给定两个有序链表的头指针，打印两个链表的公共部分
     */
    public void printCommPart1(ListNode head1, ListNode head2){
        while (head1 != null && head2 != null){
            if (head1.getValue() < head2.getValue()){
                head1 = head1.next();
            }
            else if (head1.getValue() > head2.getValue()){
                head2 = head2.next();
            }
            else {
                System.out.print(head1.getValue()+ " ");
                head1 = head1.next();
                head2 = head2.next();
            }
        }
    }

    /**
     * 给定两个单链表的头指针，打印两个链表的公共部分
     *
     * 由于单链表只有一个next域，两个链表有公共部分则形状一定如Y
     * 则只需考虑两个链表的长度，表长的链表先遍历到第dist个结点，然后同步遍历对比
     */
    public void printCommPart2(ListNode head1, ListNode head2){
        int len1 = length(head1);
        int len2 = length(head2);

        ListNode longList;
        ListNode shortList;
        if (len1 > len2) {
            longList = head1;
            shortList = head2;
        }else{
            longList = head2;
            shortList = head1;
        }
        int dist = Math.abs(len1 - len2);
        while (dist-- > 0){
            longList = longList.next();
        }
        while (shortList != null){
            if (longList == shortList){
                System.out.println(shortList.getValue()+ " ");
            }
            else {
                longList = longList.next();
                shortList = shortList.next();
            }
        }
    }

    /**
     * 删除单链表中倒数第K个结点
     * 要求 时间复杂度 O(n)    空间复杂度O(1)
     */
    public ListNode removelastKthNode(ListNode head, int k){
        int len = 0;
        ListNode p = head;
        while (p != null){
            len++;
            p = p.next();
        }
        int dif = len - k;
        p = head;
        if (dif == 0){
            head = head.next();
        }
        else if (dif > 0){
            while (--dif > 0){
                p = p.next();
            }
            p.setNext(p.next().next());
            head = p;
        }

        return head;
    }

    /**
     * 删除双向链表中倒数第K个结点
     * 要求 时间复杂度 O(n)    空间复杂度O(1)
     */
    public void removelastKthNode(BiLNode head){

    }

    /**
     * 反转单链表
     * 要求 时间复杂度 O(n)    空间复杂度O(1)
     */
    public ListNode reverse(ListNode head){
        ListNode next = null, pre = null;
        while (head != null){
            next = head.next();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转(非循环)双向链表
     * 要求 时间复杂度 O(n)    空间复杂度O(1)
     *
     * 这个要做的主要就是将每个结点的pre和next值交换
     */
    public BiLNode reverse(BiLNode head){
        BiLNode next, pre = null;
        while (head != null){
            next = head.next();
            head.setNext(pre);
            head.setPre(next);
            pre = head;
            head = next;
        }
        return pre;
    }


    private int length(ListNode head){
        int len = 0;
        while (head != null){
            len++;
            head = head.next();
        }
        return len;
    }
}
