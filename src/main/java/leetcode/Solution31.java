package leetcode;

public class Solution31 {

    public static void main(String[] args) {
        new Solution31().nextPermutation(new int[]{1, 2, 3});
    }

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 2;
        for (; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                int j = len - 1;
                for (; j > i; j--) {
                    if (nums[j] > nums[i]) {
                        break;
                    }
                }
                swap(nums, i, j);
                reverse(nums, i + 1, len - 1);
                break;
            }
        }
        if (i < 0) {
            reverse(nums, 0, len - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int from, int to) {
        int i = from, j = to;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
