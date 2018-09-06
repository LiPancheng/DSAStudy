package datastructure.tree;

import datastructure.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 两个节点的最低公共祖先
 */
public class LowestCommonAncestor {
    /**
     * 通过后续遍历
     */
    public TreeNode getLCA(TreeNode root, TreeNode n1, TreeNode n2){
        if (root == null || n1 == root || n2 == root)
            return root;
        TreeNode left = getLCA(root.left, n1, n2);
        TreeNode right = getLCA(root.right, n1, n2);
        if (left == null && right == null)
            return null;
        else if (left == null || right == null)
            return left != null ? left : right;
        else
            return root;
    }


    /**
     * 为了便于多次查询，利用一个map存储每个结点的父节点
     */
    class LCAQueryHelper{
        HashMap<TreeNode, TreeNode> map;

        public LCAQueryHelper(TreeNode root) {
            map = new HashMap<>();
            if (root != null)
                map.put(root, null);
            setMap(root);
        }

        public void setMap(TreeNode node){
            if (node == null)
                return;
            if (node.left != null)
                map.put(node.left, node);
            if (node.right != null)
                map.put(node.right, node);

            setMap(node.left);
            setMap(node.right);
        }

        /**
         * 首先set存储所有 n1 的祖先（包括自己）
         * 依次遍历 n2 的祖先，看是否在set中，是则返回
         */
        public TreeNode query(TreeNode n1, TreeNode n2){
            HashSet<TreeNode> set = new HashSet<>();
            set.add(n1);
            TreeNode node = n1;
            while ((node = map.get(node)) != null){
                set.add(node);
            }
            TreeNode lca = n2;
            while (!set.contains(lca)){
                lca = map.get(lca);
            }
            return lca;
        }
    }
}
