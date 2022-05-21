package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution1863 {

    public static void main(String[] args) {
        Solution1863 solution1863 = new Solution1863();
        System.out.println(solution1863.subsetXORSum(new int[]{1, 3}));
    }

    public int subsetXORSum(int[] nums) {
        int result = 0;
        for (int i = 0; i < (1 << nums.length); i++) {
            result += xor(getSubSet(nums, i));
        }
        return result;
    }

    private List<Integer> getSubSet(int[] nums, int i) {
        List<Integer> sub = new ArrayList<>();
        int j = nums.length - 1;
        while (j >= 0) {
            if ((i & 1) == 1) {
                sub.add(nums[j]);
            }
            i = (i >> 1);
            j--;
        }
        return sub;
    }

    private int xor(List<Integer> subSet) {
        int r = 0;
        for (Integer i : subSet) {
            r = r ^ i;
        }
        return r;
    }
}
