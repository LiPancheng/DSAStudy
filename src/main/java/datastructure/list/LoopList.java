package datastructure.list;

import datastructure.ListNode;

public class LoopList {

    /**
     * 一、判断一个单链表是否有环
     * 设置一个快指针fast和慢指针slow，初始都指向
     */
    public boolean hasLoop(ListNode head){
        ListNode slow = head, fast = head;
        while (fast != null && fast.next() != null){ //fast肯定在slow前面，因此只需判断fast
            slow = slow.next();
            fast = fast.next().next();
            if (slow == fast){
                return true;
            }
        }
        return false;
    }
    /**
     * 二、对于一个有环的单链表，找到环的入口结点，空间复杂度为O(1)的方法
     * （另一种方法就是使用个Set，遍历时第一次重复的结点就是环入口，空间复杂度为O(n)）
     *
     * 空间复杂度为O(1)的方法：
     * 在判断有环后，fast,slow指针在环上的某一点相遇。设如下标记：
     *     环入口结点距头节点的步数：      E
     *     环长：                          Q
     *     快慢指针相遇点距头节点的步数：  P
     *     链表长（含结点个数）：          L
     *
     * 既然fast,slow指针在环上的某一点相遇，则slow指针停在环内，fast指针已经比slow多走若干个环
     * 有    Df = k * Q + Ds
     * 又fast每次走两步,slow走一步，则 Df = 2 * Ds
     * 得出  Ds = k * Q = （k - 1） * Q + Q = （k - 1） * Q + L - E
     * slow走的距离又即是 Ds = E + P
     * 则有 E + P = （k - 1） * Q + L - E
     * 推出 E = （k - 1） * Q + (L - E - P) ---------- (*)
     * 而 L - E - P 表示从相遇点再走到环入口结点的步数
     *
     * 如果一个指针(fast)从头开始一步步走，走 E 步；另一个指针(slow)从相遇点一步步走，
     * 同样，相遇时一个指针肯定已经比另一个多走若干个环。
     * 而对于(*)式可以看出，另一个指针(slow)从相遇点一步步走到环入口时，两者走过的步数之差（k - 1） * Q正好满足环长的整数倍，
     * 那么它们就一定是在环入口结点遇见的
     */
    public ListNode getLoopEntry(ListNode head){
        if (head == null || head.next() == null || head.next().next() == null){
            return null;
        }
        //判断单链表是否有环。与上面函数思想一致，实现略有不同而已
        ListNode slow = head.next(), fast = head.next().next();
        while (slow != fast){
            if (slow.next() == null || fast.next().next() == null){
                return null;
            }
            slow = slow.next();
            fast = fast.next().next();
        }

        fast = head;
        while (slow != fast){
            slow = slow.next();
            fast = fast.next();
        }
        return slow;
    }

}
