package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution84 {
    public static void main(String[] args) {
        System.out.println(new Solution84().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{-1, -1});//第一个-1为索引，第二个-1为值，此项为左侧哨兵,heights最后加一个-1作为右侧哨兵
        for (int i = 0; i <= heights.length; i++) {
            int v = i == heights.length ? -1 : heights[i];
            while (v < stack.peek()[1]) {
                int[] certain = stack.pop();
                int right = i;
                int left = stack.peek()[0];
                max = Math.max(max, (right - left - 1) * certain[1]);
            }
            stack.push(new int[]{i, v});
        }
        return max;
    }
}
