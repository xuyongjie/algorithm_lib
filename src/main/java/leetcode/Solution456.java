package leetcode;

import java.util.Stack;

public class Solution456 {
    public static void main(String[] args) {
        Solution456 solution456 = new Solution456();
        System.out.println(solution456.find132pattern(new int[]{3, 1, 4, 2}));
    }

    public boolean find132pattern(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int mid = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < mid) {
                return true;
            }
            while (!st.isEmpty() && nums[i] > st.peek()) {
                mid = st.pop();
            }
            st.push(nums[i]);
        }
        return false;
    }
}
