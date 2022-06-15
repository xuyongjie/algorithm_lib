package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution560 {
    public static void main(String[] args) {
        System.out.println(new Solution560().subarraySum(new int[]{1, 1, 1}, 2));
    }

    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int res = 0;
        Map<Integer, Integer> sumCounter = new HashMap<>();
        sumCounter.put(0, 1);
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            res += sumCounter.getOrDefault(sum - k, 0);
            sumCounter.put(sum, sumCounter.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
