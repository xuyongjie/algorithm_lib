package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution6080 {

    public static void main(String[] args) {
        System.out.println(new Solution6080().totalSteps(new int[]{4, 5, 7, 7, 13}));
    }

    public int totalSteps(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i <= nums.length; i++) {
            int val = (i == nums.length ? Integer.MAX_VALUE : nums[i]);
            while (val >= (stack.peek() == -1 ? Integer.MAX_VALUE : nums[stack.peek()])) {
                int top = stack.pop();
                if (top == -1) {
                    break;
                }
                max = Math.max(max, i - top - 1);
            }
            stack.push(i);
        }
        return max;
    }
}
