package datastructure.list;

import datastructure.ListNode;

import java.util.Stack;

/**
 * 回文结构
 * 即正着看反正看都一样，当然其也具有对称结构
 */
public class Palindrome {
    public boolean isPalindrome(ListNode head){
        if (head == null || head.next == null){
            return true;
        }
        //找到整个链表的右半部分
        ListNode rightPartStart = head.next, p = head;
        while (p.next != null && p.next.next != null){
            p = p.next.next;
            rightPartStart = rightPartStart.next;
        }
        Stack<ListNode> stack = new Stack<>();
        while (rightPartStart != null){
            stack.push(rightPartStart);
            rightPartStart = rightPartStart.next;
        }
        while (!stack.empty()){
            if (stack.pop().value != head.value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 如果是数组，除了栈的方式，还可以直接计算得到中间位置的下标，然后左右两边依次对比
     */
    public boolean isPalindrome(int[] arr){
        return false;
    }
}
