package com.yon.algorithm.binarytree;

/**
 * 二叉树最近公共祖先(Lowest Common Ancestor)
 */
public class LCA {

    public static void main(String[] args) {
        TreeNode p = new TreeNode(8), q = new TreeNode(9);
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3,
                new TreeNode(6), new TreeNode(7, p, q)));
        System.out.println(new LCA().lca2(root, p, q));
        System.out.println(new LCA().lca(root, p, q));
    }

    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        boolean pInLeft = contains(root.left, p);
        boolean qInRight = contains(root.right, q);
        if (pInLeft == qInRight) {
            //在两边
            return root;
        } else if (pInLeft) {
            //都在左边
            return lca(root.left, p, q);
        } else {
            //都在右边
            return lca(root.right, p, q);
        }
    }

    private boolean contains(TreeNode root, TreeNode node) {
        if (root == node) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return contains(root.left, node) || contains(root.right, node);
    }


    public TreeNode lca2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lca2(root.left, p, q);
        TreeNode right = lca2(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
}
