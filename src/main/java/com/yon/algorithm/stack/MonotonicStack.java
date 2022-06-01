package com.yon.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调栈，可用来找元素最近小于该元素的值
 */
public class MonotonicStack {

    public static void main(String[] args) {
        int[][] result = new MonotonicStack().findLittle(new int[]{2, 1, 5, 6, 2, 3});
        for (int[] each : result) {
            System.out.println("[" + each[0] + "," + each[1] + "]");
        }
    }

    /**
     * 找出nums中每个元素左右最近小于nums[i]的下标
     *
     * @param nums
     * @return int[i][0]存储左边索引，int[i][1]存储右边索引
     */
    public int[][] findLittle(int[] nums) {
        int[][] result = new int[nums.length][2];
        Deque<int[]> stack = new ArrayDeque<>();//栈元素存储索引及值
        stack.push(new int[]{-1, Integer.MIN_VALUE});//左侧哨兵,在nums最后添加一个作为右侧哨兵
        for (int i = 0; i <= nums.length; i++) {
            int val = i < nums.length ? nums[i] : Integer.MIN_VALUE;
            while (val < stack.peek()[1]) {
                int[] top = stack.pop();
                result[top[0]][1] = i;//右边第一个小于top[1]的数索引为i
                result[top[0]][0] = stack.peek()[0];//左边第一个小于等于top[1]的索引为栈顶元素
            }
            stack.push(new int[]{i, val});
        }
        return result;
    }
}
