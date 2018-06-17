package datastructure.list;

import datastructure.ListNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JosephProblemTest {

    @Test
    public void josephProcess() throws Exception {
        ListNode head = new ListNode(1);
        ListNode tmp = head;
        for (int i = 2; i <= 5; i++) {
            ListNode node = new ListNode(i);
            tmp.setNext(node);
            tmp = tmp.next();
            if (i == 5){
                node.setNext(head);
            }
        }
        ListNode lastAlive = new JosephProblem().josephProcess(head, 3);
        Assert.assertEquals(4, lastAlive.getValue());

    }

    @Test
    public void josephProcessAdvance() throws Exception {
        ListNode head = new ListNode(1);
        ListNode tmp = head;
        for (int i = 2; i <= 5; i++) {
            ListNode node = new ListNode(i);
            tmp.setNext(node);
            tmp = tmp.next();
            if (i == 5){
                node.setNext(head);
            }
        }
        ListNode lastAlive = new JosephProblem().josephProcess(head, 3);
        Assert.assertEquals(4, lastAlive.getValue());
    }

}