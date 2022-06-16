package leetcode;

import com.yon.algorithm.linkedlist.ListNode;

public class Solution25 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        new Solution25().reverseKGroup(head, 2);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int i = 1;
        ListNode tail = head;
        while (i < k && tail != null) {
            tail = tail.next;
            i++;
        }
        if (tail == null) {
            return head;
        }
        ListNode newHead = tail.next;
        tail.next = null;
        reverse(head).next = reverseKGroup(newHead, k);
        return tail;
    }

    private ListNode reverse(ListNode head) {
        //反转后返回尾节点
        ListNode reversed = null;
        ListNode p = head;
        while (p != null) {
            ListNode pNext = p.next;
            p.next = reversed;
            reversed = p;
            p = pNext;
        }
        return head;
    }
}
