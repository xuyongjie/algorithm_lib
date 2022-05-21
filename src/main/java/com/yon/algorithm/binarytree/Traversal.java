package com.yon.algorithm.binarytree;

import java.util.Stack;

/**
 * 二叉树遍历
 */
public class Traversal {
    public static void main(String[] args) {
        Traversal traversal = new Traversal();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        node4.left = new TreeNode(3);
        root.right = node4;
        traversal.inOrder2(root);
    }

    /**
     * 中序遍历-递归方式
     *
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        //visit root
        inOrder(root.right);
    }

    /**
     * 中序遍历-非递归
     *
     * @param root
     */
    public void inOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            //visit p;
            System.out.println(p.val);
            p = p.right;
        }
    }

    /**
     * 先序遍历-递归
     *
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        //visit root
        preOrder(root.left);
        preOrder(root.right);
    }
}
