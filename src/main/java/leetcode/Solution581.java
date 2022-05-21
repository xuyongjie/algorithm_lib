package leetcode;

public class Solution581 {
    public static void main(String[] args) {
        Solution581 solution581 = new Solution581();
        System.out.println(solution581.findUnsortedSubarray(new int[]{1, 3, 4, 5, 2}));
    }

    public int findUnsortedSubarray(int[] nums) {
        int startIndex = 0, endIndex = 0;
        int[] maxIndex = new int[nums.length];//maxIndex[i]存储从前往后截止i的最大值索引
        maxIndex[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[maxIndex[i - 1]]) {
                maxIndex[i] = i;
            } else {
                maxIndex[i] = maxIndex[i - 1];
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (maxIndex[i] != i) {
                endIndex = i;
                break;
            }
        }
        int[] minIndex = new int[nums.length];//minIndex[i]存储从后往前截止i最小值索引
        minIndex[nums.length - 1] = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] <= nums[minIndex[i + 1]]) {
                minIndex[i] = i;
            } else {
                minIndex[i] = minIndex[i + 1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (minIndex[i] != i) {
                startIndex = i;
                break;
            }
        }
        return startIndex == endIndex ? 0 : (endIndex - startIndex + 1);

    }
}
