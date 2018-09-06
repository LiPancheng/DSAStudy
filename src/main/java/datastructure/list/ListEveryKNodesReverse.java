package datastructure.list;

import datastructure.ListNode;

import java.util.Stack;

public class ListEveryKNodesReverse {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        printLinkedList(new ListEveryKNodesReverse().reverseKNodes(head, 3));

    }

    /**
     * 利用栈进行调整
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public static ListNode reverseKNodesUseStack(ListNode head, int k){
        if (k < 2)
            return head;
        ListNode newHead = head, newTail = null;
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head, kStart = head; // 每 k 个（每组）的第一个结点
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
            if (stack.size() == k){
                kStart = cur; // 保存下一组的第一个结点
                while (!stack.empty()){
                    ListNode node = stack.pop();
                    if (newTail == null){
                        newHead = node;
                        newTail = node;
                    }
                    else{
                        newTail.next = node;
                        newTail = node;
                        newTail.next = null;
                    }
                }
            }
        }
        // 如果最后不足k个，则最后几个结点按原序链接上去
        if (!stack.empty() && newTail != null)
                newTail.next = kStart;
        return newHead;
    }

    /**
     * 直接在链表中进行调整
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public ListNode reverseKNodes(ListNode head, int k){
        if (k < 2)
            return head;
        // start 每段的第一个结点；preSegTail 上一段的最后一个结点
        ListNode preSegTail = null, start, cur = head, next;
        int count = 0;
        while (cur != null){
            count++;
            next = cur.next;
            if (count == k){
                start = preSegTail == null ? head : preSegTail.next;
                head = preSegTail == null ? cur : head; // 记录反转后的整个链表的头节点
                reverseSegment(preSegTail, start, cur, next);
                preSegTail = start;
                count = 0;
            }
            cur = next;
        }
        return head;
    }

    private void reverseSegment(ListNode preSegTail, ListNode start, ListNode end, ListNode nextSeg){
        ListNode pre = start, cur = start.next, next = null;
        while (cur != nextSeg){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (preSegTail != null){
            preSegTail.next = end;
        }
        start.next = nextSeg;
    }

    private static void printLinkedList(ListNode head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}
