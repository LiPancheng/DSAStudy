package datastructure.tree;

import datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {
//---------------递归方式遍历二叉树-----------------
    /**
     * 先序遍历，递归方式
     */
    public void DLRRecur(TreeNode root){
        if (root == null){
            return;
        }
        System.out.print(root.value + " ");
        LDRRecur(root.left);
        LDRRecur(root.right);
    }

    /**
     * 中序遍历，递归方式
     */
    public void LDRRecur(TreeNode root){
        if (root == null){
            return;
        }
        LDRRecur(root.left);
        System.out.print(root.value + " ");
        LDRRecur(root.right);
    }

    /**
     * 后序遍历，递归方式
     */
    public void LRDRecur(TreeNode root){
        if (root == null){
            return;
        }
        LDRRecur(root.left);
        LDRRecur(root.right);
        System.out.print(root.value + " ");
    }

//---------------非递归方式遍历二叉树-----------------
    /**
     * 先序遍历，非递归方式
     * 相当于图的深度优先遍历（DFS）
     */
    public void DLRUnRecur(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            root = stack.pop();
            System.out.print(root.value + " ");
            if (root.right != null){
                stack.push(root.right);
            }
            if (root.left != null){
                stack.push(root.left);
            }
        }
    }

    /**
     * 中序遍历，非递归方式
     */
    public void LDRUnRecur(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null){
            if (root == null){
                root = stack.pop();
                System.out.print(root.value + " ");
                root = root.right;
            }
            else {
                stack.push(root);
                root = root.left;
            }
        }
    }

    /**
     * 后序遍历，非递归方式
     * 用两个栈实现
     */
    public void LRDUnRecur(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> printstak = new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            root = stack.pop();
            printstak.push(root);
            if (root.right != null){
                stack.push(root.right);
            }
            if (root.left != null){
                stack.push(root.left);
            }
        }
        while (!printstak.empty()){
            System.out.print(printstak.pop().value + " ");
        }
    }
    /**
     * 后序遍历，非递归方式
     *
     * 要保证根结点在左孩子和右孩子访问之后才能访问，因此对于任一结点P，先将其入栈。
     * 如果P不存在左孩子和右孩子，则可以直接访问它；
     * 或者P存 在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点。
     * 若非上述两种情况，则将P的右孩子和左孩子依次入栈
     */
    public void LRDUnRecur2(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prePrint = null, cur = null;
        while (!stack.empty()){
            cur = stack.peek();
            if ((cur.left == null && cur.right == null) ||
                (prePrint != null && (prePrint.left == cur || prePrint.right == cur))){
                System.out.print(cur.value + " ");
                prePrint = cur;
            }
            else {
                if (cur.right != null){
                    stack.push(cur.right);
                }
                if (cur.left != null){
                    stack.push(cur.left);
                }
            }
        }
    }

    /**
     * 树的层次遍历
     * 相当于图的广度优先遍历
     */
    public void BFS(TreeNode root){
        if (root == null)
            return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (queue.peekFirst() != null){
            TreeNode node = queue.pollFirst();
            System.out.println(node.value);
            if (node.left != null)
                queue.offerLast(node.left);
            if (node.right != null)
                queue.offerLast(node.right);
        }
    }


    //---------------非递归方式遍历二叉树之 Morris算法   时间复杂度为O(N) 空间复杂度为O(1)-----------------
    public void morrisDLR(TreeNode root){
        if (root == null){
            return;
        }
        TreeNode cur = root, node = null;
        while (cur != null){
            node = cur.left;
            if (node != null){
                //找到cur结点的左子树的最右结点，将其右孩子设置为cur
                while (node.right != null && node.right != cur){
                    node = node.right;
                }
                if (node.right == null){ //找到了最右叶子结点
                    node.right = cur;
                    System.out.print(cur.value + " "); //此处根节点只访问一次。如果打印放到顶层while一开始，根节点会被访问两次
                    cur = cur.left; //继续向左孩子移动，继续while以设置空指针指向
                }
                else { //一定是发生node.right != null && node.right == cur才会到这
                    node.right = null; //将树还原
                    cur = cur.right;
                }
            }
            else {
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
    }

    public void morrisLDR(TreeNode root){
        if (root == null){
            return;
        }
        TreeNode cur = root, node = null;
        while (cur != null){
            node = cur.left;
            if (node != null){
                //找到cur结点的左子树的最右结点，将其右孩子设置为cur
                while (node.right != null && node.right != cur){
                    node = node.right;
                }
                if (node.right == null){ //找到了最右叶子结点
                    node.right = cur;
                    cur = cur.left; //继续向左孩子移动，继续while以设置空指针指向
                }
                else { //一定是发生node.right != null && node.right == cur才会到这
                    System.out.print(cur.value + " ");
                    node.right = null; //将树还原
                    cur = cur.right;
                }
            }
            else {
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
    }

    public void morrisLRD(TreeNode root){
        if (root == null){
            return;
        }
        TreeNode cur = root, node = null;
        while (cur != null){
            node = cur.left;
            if (node != null){
                //找到cur结点的左子树的最右结点，将其右孩子设置为cur
                while (node.right != null && node.right != cur){
                    node = node.right;
                }
                if (node.right == null){ //找到了最右叶子结点
                    node.right = cur;
                    cur = cur.left; //继续向左孩子移动，继续while以设置空指针指向
                }
                else { //一定是发生node.right != null && node.right == cur才会到这
                    node.right = null; //将树还原
                    printEdge(cur.left);
                    cur = cur.right;
                }
            }
            else {
                cur = cur.right;
            }
        }
        //由于后序遍历会先打印整个树的右子树最右结点再打印整个树的根。但右子树最右结点并没有指向root，因为都是用的左子树的最右结点
        //所以打印了整个树的右子树最右结点后，cur = cur.cRight就为null，会退出循环，可此时整个树的右边界还未打印
        printEdge(root);
    }

    private void printEdge(TreeNode from){
        TreeNode tail = reverseEdge(from);
        TreeNode tmp = tail;
        while (tmp != null){
            System.out.print(tmp.value + " ");
            tmp = tmp.right;
        }
        reverseEdge(tail);
    }

    private TreeNode reverseEdge(TreeNode from){
        TreeNode pre = null, next = null;
        while (from != null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }
}
