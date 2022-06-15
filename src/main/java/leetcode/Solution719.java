package leetcode;

import java.util.Arrays;

public class Solution719 {

    public static void main(String[] args) {
        System.out.println(new Solution719().smallestDistancePair(new int[]{1, 6, 1}, 3));
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[n - 1] - nums[0];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cnt = countLittleOrEqual(nums, mid);
            if (cnt >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int countLittleOrEqual(int[] nums, int distance) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int target = nums[i] + distance;
            int left = i, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            count += right - i;
        }
        return count;
    }
}
