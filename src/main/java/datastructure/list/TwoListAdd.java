package datastructure.list;

import datastructure.ListNode;

/**
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 你可以假设除了数字 0 之外，这两个数字都不会以零开头。

 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807
 */
public class TwoListAdd {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = null, pre = null;
        while (l1 != null && l2 != null){
            int sum = l1.value + l2.value + carry;
            if (sum >= 10){
                carry = 1;
                sum -= 10;
            }
            else {
                carry = 0;
            }
            ListNode node = new ListNode(sum);
            if (pre != null){
                pre.next = node;
            }
            else {
                head = node;
            }
            pre = node;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null){
            int sum = l1.value + carry;
            if (sum >= 10){
                carry = 1;
                sum -= 10;
            }
            else {
                carry = 0;
            }
            ListNode node = new ListNode(sum);
            pre.next = node;
            pre = node;
            l1 = l1.next;
        }
        while (l2 != null){
            int sum = l2.value + carry;
            if (sum >= 10){
                carry = 1;
                sum -= 10;
            }
            else {
                carry = 0;
            }
            ListNode node = new ListNode(sum);
            pre.next = node;
            pre = node;
            l2 = l2.next;
        }
        if (carry == 1){
            ListNode node = new ListNode(1);
            pre.next = node;
        }
        return head;
    }

    public ListNode addTwoNumbersNo1(ListNode l1, ListNode l2) {
        int sum = l1.value + l2.value;
        int carry = sum/10;
        ListNode head = new ListNode(sum%10);
        l1 = l1.next;
        l2 = l2.next;
        ListNode tmp = head;
        while (l1 != null || l2 != null || carry > 0) {
            sum = carry;
            if (l1 != null) {
                sum += l1.value;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.value;
                l2 = l2.next;
            }
            tmp.next = new ListNode(sum%10);
            carry = sum/10;
            tmp = tmp.next;
        }
        return head;
    }
}
