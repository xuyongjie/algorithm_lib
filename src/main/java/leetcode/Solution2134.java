package leetcode;

public class Solution2134 {
    public static void main(String[] args) {
        System.out.println(new Solution2134().minSwaps(new int[]{0, 1, 0, 1, 1, 0, 0}));
    }

    public int minSwaps(int[] nums) {
        int k = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            k += nums[i];
        }
        int min = Integer.MAX_VALUE;
        int start = 0, end = 0, sum = 0;
        while (start < len) {
            sum += nums[end % len];
            if (end - start + 1 == k) {
                min = Math.min(min, k - sum);
                sum -= nums[start];
                start++;
                end++;
            } else {
                end++;
            }
        }
        return min;
    }
}
