package com.yon.algorithm.linkedlist;

public class Traversal {

    public static void main(String[] args) {
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.printf("Before:%s%n", root);
        System.out.printf("After:%s%n", new Traversal().reverse2(root));
    }

    /**
     * 递归反转链表
     *
     * @param root
     * @return
     */
    public ListNode reverse(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }
        ListNode reversed = reverse(root.next);
        root.next.next = root;//反转
        root.next = null;
        return reversed;
    }

    public ListNode reverse2(ListNode root) {
        ListNode reversed = null;
        ListNode p = root;
        while (p != null) {
            ListNode pNext = p.next;
            p.next = reversed;
            reversed = p;
            p = pNext;
        }
        return reversed;
    }
}
