package datastructure.tree;

import datastructure.TreeNode;

import java.util.HashMap;

/**
 * 给定一颗二叉树的头节点和一个整数sum，求累加和为sum的最长路径长度
 */
public class LongestPathSum {
    /**
     * 路径必须从头节点开始
     * (自己实现，未全面测试)
     */
    public int getMaxLength(TreeNode head, int sum) {
        if (head == null){
            return 0;
        }
        //return getMaxLength0(head, 0, 0, sum); //等效于getMaxLength00，只是getMaxLength0是自己利用height参数计算返回maxLen,而getMaxLength00是利用参数传下去的

        return getMaxLength00(head, 1,0,0, sum); //推荐使用getMaxLength00，更好理解和想到
    }

    private int getMaxLength0(TreeNode head, int height, int grandTotal, int sum){
        int len = 0;
        if (head == null){
            return len;
        }
        int curSum = head.value + grandTotal;
        if (curSum == sum){
            len = height + 1;
        }
        else {
            int leftMax = getMaxLength0(head.left, height + 1, curSum, sum);
            int rightMax = getMaxLength0(head.right, height + 1, curSum, sum);
            int childMax = Math.max(leftMax, rightMax);
            len = Math.max(len, childMax);
        }
        return len;
    }

    private int getMaxLength00(TreeNode head, int height, int maxLen, int grandTotal, int sum){
        if (head == null){
            return maxLen;
        }
        int curSum = head.value + grandTotal;
        if (curSum == sum){
            maxLen = Math.max(maxLen, height);
        }
        else {
            maxLen = getMaxLength00(head.left, height + 1, maxLen, curSum, sum);
            maxLen = getMaxLength00(head.right, height + 1, maxLen, curSum, sum);
        }
        return maxLen;
    }

    /**
     * 路径可从任一结点开始
     */
    public int getMaxLength1(TreeNode head, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        return recordMaxLenInPreOrder(head, 1, 0, 0, sum, map);
    }

    /**
     * 前序遍历收集信息,找到二叉树中累加和为指定值的过程放在递归的前面。
     * 一条路径遍历完成，向上回退到合适节点，从合适结点右孩子方向开始下一路径
     */
    private int recordMaxLenInPreOrder(TreeNode head, int height, int maxLen, int grandTotal, int sum, HashMap<Integer, Integer> map){
        if (head == null){
            return maxLen;
        }
        int curSum = head.value + grandTotal;
        if (map.containsKey(curSum - sum)){
            maxLen = Math.max(maxLen, height - map.get(curSum - sum));
        }
        if (!map.containsKey(curSum)){
            map.put(curSum, height);
        }
        maxLen = recordMaxLenInPreOrder(head.left, height+1, maxLen, curSum, sum, map);
        maxLen = recordMaxLenInPreOrder(head.right, height+1, maxLen, curSum, sum, map);
        // 回退机制，第一次出现cursum的层数等于当前层数，故说明此cursum由当前节点带来的。回退时需要删除map中的记录
        if (map.get(curSum) == height){
            map.remove(curSum);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(-3);
        head.left = new TreeNode(3);
        head.right = new TreeNode(-9);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(0);
        head.left.right.left = new TreeNode(1);
        head.left.right.right = new TreeNode(6);
        head.right.left = new TreeNode(2);
        head.right.right = new TreeNode(1);
        //printTree(head);
        LongestPathSum ins = new LongestPathSum();
        System.out.println(ins.getMaxLength1(head, 6));
        System.out.println(ins.getMaxLength1(head, -9));

    }
}
