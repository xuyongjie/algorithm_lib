package leetcode;

import java.util.HashSet;
import java.util.Set;

public class Solution2261 {

    public static void main(String[] args) {
        System.out.println(countDistinct(new int[]{2, 3, 3, 2, 2}, 2, 2));
    }

    public static int countDistinct(int[] nums, int k, int p) {
        int len = nums.length;
        int[] canDel = new int[nums.length];
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % p == 0) {
                counter++;
            }
            canDel[i] = counter;
        }
        Set<String> sets = new HashSet<>();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (canDel[j] - canDel[i] + (nums[i] % p == 0 ? 1 : 0) <= k) {
                    StringBuilder s = new StringBuilder();
                    for (int t = i; t <= j; t++) {
                        s.append(nums[t]).append(",");
                    }
                    sets.add(s.toString());
                }
            }
        }
        return sets.size();
    }
}
