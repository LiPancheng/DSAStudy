package datastructure.tree;

import datastructure.TreeNode;

public class EdgeOfTree {
    /**
     * 逆时针打印树的边界
     *
     * 边界定义：
     * 头节点为边界节点
     * 叶子节点为边界结点
     * 每层最左或最后的结点也为边界结点
     *
     * 即树的外圈结点
     */
    public void printEdge1(TreeNode head){
        int height = getTreeHeight(head, 0);
        TreeNode[][] edgeMap = new TreeNode[height][2]; //树的每层左右边界
        setEdgeMap(head, 0, edgeMap);
        //打印左边界
        for (int i = 0; i < height; i++) {
            System.out.print(edgeMap[i][0].value + " ");
        }
        //打印底边（左右边界的底部叶子节点由打印左右边界时输出，此处应忽略）。即叶子节点中不在edgeMap中的
        printLeafNotInMap(head, 0, edgeMap);

        //打印右边界
        for (int i = height - 1; i >= 0; i--) {
            if (edgeMap[i][0] != edgeMap[i][1]){ //注意，左右边界可能有重合（如根节点只有左子树）
                System.out.print(edgeMap[i][1].value + " ");
            }
        }
    }

    private int getTreeHeight(TreeNode node, int curHeight){
        if (node == null){
            return curHeight;
        }
        int leftHeight = getTreeHeight(node.left, curHeight + 1);
        int rightHeight = getTreeHeight(node.right, curHeight + 1);
        return Math.max(leftHeight, rightHeight);
    }

    private void setEdgeMap(TreeNode node, int row, TreeNode[][] edgeMap){
        if (node == null){
            return;
        }
        if (edgeMap[row][0] == null){ //递归根节点的右子树时，由于已经有值，保证不会重新赋值
            edgeMap[row][0] = node;
        }
        edgeMap[row][1] = node; //递归根节点的右子树时会重新赋值为右边界
        setEdgeMap(node.left, row + 1, edgeMap);
        setEdgeMap(node.right, row + 1, edgeMap);
    }

    private void printLeafNotInMap(TreeNode node, int row, TreeNode[][] map){
        if (node == null){
            return;
        }
        if (node.left == null && node.right == null && node != map[row][0] && node != map[row][1]){
            System.out.print(node.value + " ");
        }
        printLeafNotInMap(node.left, row + 1, map);
        printLeafNotInMap(node.right, row + 1, map);
    }

    /**
     * 逆时针打印树的边界
     *
     * 边界定义：
     * 头节点为边界节点
     * 叶子节点为边界结点
     * 树左边界延伸下去的路径为边界节点
     * 树右边界延伸下去的路径是边界节点
     *
     * 思路：->第一个既有左孩子又有右孩子的节点及其这个节点前的节点全部打印
     *       ->打印从这个节点开始的左边界延伸路径以及叶子节点
     *       ->打印从这个节点开始的右边界的延伸路径以及叶子节点。
     */
    public void printEdge2(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true);
            printRightEdge(head.right, true);
        } else {
            printEdge2(head.left != null ? head.left : head.right);
        }
        System.out.println();
    }

    private void printLeftEdge(TreeNode h, boolean print) {
        if (h == null) {
            return;
        }
        if (print || (h.left == null && h.right == null)) {
            System.out.print(h.value + " ");
        }
        printLeftEdge(h.left, print);
        printLeftEdge(h.right, print && h.left == null);
    }

    private static void printRightEdge(TreeNode h, boolean print) {
        if (h == null) {
            return;
        }
        printRightEdge(h.left, print && h.right == null);
        printRightEdge(h.right, print);
        if (print || (h.left == null && h.right == null)) {
            System.out.print(h.value + " ");
        }
    }


    public static void main(String[] args) {
        EdgeOfTree edgeOfTree = new EdgeOfTree();
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

        edgeOfTree.printEdge1(head);
    }
}
