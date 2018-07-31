package datastructure.tree;

import datastructure.TreeNode;

public class MaxBinarySearchTree {
    public TreeNode biggestSubBST(TreeNode head){
        //记录子树的情况。record[0]记录子树的节点总数；record[1]记录子树最小值；record[2]记录子树最大值
        int[] record = new int[3];
        return getBRTRoot(head, record);
    }

    private TreeNode getBRTRoot(TreeNode node, int[] record){
        if (node == null){ // 叶子节点
            record[0] = 0; // 叶子节点子树节点总数为0
            record[1] = Integer.MAX_VALUE; // 叶子节点子树最小值
            record[2] = Integer.MIN_VALUE; // 叶子节点子树最大值
            return null;
        }
        TreeNode left = node.left;

        TreeNode lbrt = getBRTRoot(node.left, record);
        int lsize = record[0];
        int lmax = record[2];

        TreeNode right = node.right;
        TreeNode rbrt = getBRTRoot(node.right, record);
        int rsize = record[0];
        int rmin = record[1];

        record[1] = Math.min(record[1], node.value);
        record[2] = Math.max(record[2], node.value);

        if (left == lbrt && right == rbrt && lmax < node.value && node.value < rmin){
            record[0] = lsize + rsize + 1;
            return node;
        }
        record[0] = Math.max(lsize, rsize);
        return lsize > rsize ? lbrt : rbrt;
    }

    public static void main(String[] args) {

        TreeNode head = new TreeNode(6);
        head.left = new TreeNode(1);
        head.left.left = new TreeNode(0);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(12);
        head.right.left = new TreeNode(10);
        head.right.left.left = new TreeNode(4);
        head.right.left.left.left = new TreeNode(2);
        head.right.left.left.right = new TreeNode(5);
        head.right.left.right = new TreeNode(14);
        head.right.left.right.left = new TreeNode(11);
        head.right.left.right.right = new TreeNode(15);
        head.right.right = new TreeNode(13);
        head.right.right.left = new TreeNode(20);
        head.right.right.right = new TreeNode(16);

        TreeNode bst = new MaxBinarySearchTree().biggestSubBST(head);
        System.out.println(bst.value);


    }


}
