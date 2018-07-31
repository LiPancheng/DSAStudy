package datastructure.tree;

import datastructure.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTraversalTest {
    @Test
    public void LDRRecur() throws Exception {
    }

    @Test
    public void LRDRecur() throws Exception {
    }

    @Test
    public void DLRUnRecur() throws Exception {
    }

    @Test
    public void LDRUnRecur() throws Exception {
    }

    @Test
    public void LRDUnRecur() throws Exception {
    }

    @Test
    public void LRDUnRecur2() throws Exception {
    }

    @Test
    public void morrisDLR() throws Exception {
    }

    @Test
    public void morrisLDR() throws Exception {
    }

    @Test
    public void morrisLRD() throws Exception {
    }


    private TreeNode generateTree(){
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        return head;
    }

    private TreeNode generateTree1(){
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.right = new TreeNode(4);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(6);
        head.left.right.left = new TreeNode(7);
        head.left.right.right = new TreeNode(8);
        head.right.left.left = new TreeNode(9);
        head.right.left.right = new TreeNode(10);
        head.left.right.right.right = new TreeNode(11);
        head.right.left.left.left = new TreeNode(12);
        head.left.right.right.right.left = new TreeNode(13);
        head.left.right.right.right.right = new TreeNode(14);
        head.right.left.left.left.left = new TreeNode(15);
        head.right.left.left.left.right = new TreeNode(16);

        return head;
    }

}