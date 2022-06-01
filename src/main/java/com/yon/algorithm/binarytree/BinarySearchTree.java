package com.yon.algorithm.binarytree;

/**
 * 二叉搜索树，插入、查找、删除操作
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(6);
        bst.insert(15);
        bst.insert(4);
        bst.insert(7);
        bst.insert(3);
        bst.insert(8);
        bst.insert(13);
        bst.insert(20);
        bst.insert(12);
        bst.insert(17);
        System.out.println(bst);

        System.out.println(bst.find(7));
        System.out.println(bst.find(9));

        bst.delete(10);
        System.out.println(bst);
        bst.delete(7);
        System.out.println(bst);
        bst.delete(20);
        System.out.println(bst);
    }

    private TreeNode root;

    public BinarySearchTree() {
    }


    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
        } else {
            insertInto(root, key);
        }
    }

    private void insertInto(TreeNode node, int key) {
        if (key == node.val) {
            throw new RuntimeException("already exist");
        }
        if (key < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(key);
            } else {
                insertInto(node.left, key);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(key);
            } else {
                insertInto(node.right, key);
            }
        }
    }

    public TreeNode find(int key) {
        return doFind(null, root, key)[0];
    }

    /**
     * 返回被查找节点及父节点
     *
     * @param parent
     * @param node
     * @param key
     * @return
     */
    private TreeNode[] doFind(TreeNode parent, TreeNode node, int key) {
        if (node == null) {
            return new TreeNode[]{null, null};
        }
        if (key == node.val) {
            return new TreeNode[]{node, parent};
        } else if (key < node.val) {
            return doFind(node, node.left, key);
        } else {
            return doFind(node, node.right, key);
        }
    }

    public void delete(int key) {
        TreeNode[] curAndParent = doFind(null, root, key);
        TreeNode cur = curAndParent[0], parent = curAndParent[1];
        if (cur == null) {
            return;
        }
        if (cur.right == null) {
            if (parent == null) {
                root = cur.left;
                return;
            }
            if (cur.val < parent.val) {
                //左边
                parent.left = cur.left;
            } else {
                //右边
                parent.right = cur.left;
            }
            return;
        }
        if (cur.left == null) {
            if (parent == null) {
                root = cur.right;
                return;
            }
            if (cur.val < parent.val) {
                //左边
                parent.left = cur.right;
            } else {
                //右边
                parent.right = cur.right;
            }
            return;
        }
        //将cur.left接到cur.right的最左侧
        //cur.right接到parent
        TreeNode node = cur.right;
        while (node.left != null) {
            node = node.left;
        }
        node.left = cur.left;
        if (parent == null) {
            root = cur.right;
            return;
        }
        if (cur.val < parent.val) {
            parent.left = cur.right;
        } else {
            parent.right = cur.right;
        }
    }

    @Override
    public String toString() {
        //中序遍历打印
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString();
    }

    private void inOrderTraversal(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, sb);
        sb.append(root.val);
        sb.append(" ");
        inOrderTraversal(root.right, sb);
    }
}
