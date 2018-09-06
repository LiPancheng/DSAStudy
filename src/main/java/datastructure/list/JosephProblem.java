package datastructure.list;

import datastructure.ListNode;

/**
 * N个人排成一个圈，从第一个人开始报数，报数到3的人出列，然后由下一个人重新报1，报数到3的人再出列
 * 依次下去，直到剩最后一个人
 *
 * 输入：一个环形单链表的头节点head 和 报数的值m
 * 返回：最后生存下来的结点，且这个结点自己组成环形单链表
 */
public class JosephProblem {
    /**
     * 普通解法。不断用链表删除操作模拟人的出列
     *
     * 在第二个while处，由于是环形链表，所以其时间复杂度为O（N * m）
     */
    public ListNode josephProcess(ListNode head, int m){
        if (head == null || head == head.next || m < 1){
            return head;
        }
        //需要pre来进行删除节点的重链接
        ListNode pre = head;
        //先找到头节点的pre，以防m=1时一来就删除头节点
        while (pre.next != head){
            pre = pre.next;
        }
        int count = 0;
        while (head != pre){
            if (++count == m){
                pre.next = head.next;
                count = 0;
            }
            else {
                pre = pre.next;
            }
            head = head.next;
        }
        return head;
    }

    /**
     * 进阶解法。（与IT名企pdf中的解法不同）
     * 对结点编号，通过数学推算建立报数与结点的关系，一步到位求得最终存活的结点，再遍历找到返回
     *
     假设下标从0开始，0，1，2 .. m-1共m个人，从1开始报数，报到k则此人从环出退出，问最后剩下的一个人的编号是多少？
     现在假设m=10
     0 1 2 3 4 5 6 7 8 9    k=3

     第一个人出列后的序列为：
     0 1 3 4 5 6 7 8 9
     即:
     3 4 5 6 7 8 9 0 1          （*）

     我们把该式转化为:
     0 1 2 3 4 5 6 7 8          (**)

     则你会发现: （(**)+3）%10 则转化为(*)式了
     也就是说，我们求出9个人中第9次出环的编号，最后进行上面的转换就能得到10个人第10次出环的编号了
     设f(m,k,i)为m个人的环，报数为k，第i个人出环的编号，则f(10,3,10)是我们要的结果
     当i=1时，  f(m,k,i) = (m-1 + k)%m
     当i!=1时，  f(m,k,i)= (f(m-1, k, i-1) + k) % m
     */
    public ListNode josephProcessAdvance(ListNode head, int m){
        if (head == null || head == head.next || m < 1){
            return head;
        }
        int size = 1;
        ListNode cur = head.next;
        while (cur != head){
            size++;
            cur = cur.next;
        }
        int lastAlive = getKthOutNum(size, m, size);
        while (lastAlive-- > 0){
            head = head.next;
        }
        return head;
    }
    private int getKthOutNum(int size, int m, int kth){
        if (m == 1){
            return (size-1 + m) % m;
        }
        else {
            getKthOutNum(size-1, m, kth);
        }
        return 0;
    }
}
