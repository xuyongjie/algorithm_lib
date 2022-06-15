package leetcode;

public class Solution498 {
    public static void main(String[] args) {
        new Solution498().findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int i = 0, j = 0, index = 0;
        int[] result = new int[m * n];
        while (i < m && j < n) {
            while (i >= 0 && j < n) {
                result[index++] = mat[i--][j++];
            }
            if (i < 0) {
                i++;
            }
            if (j == n) {
                i++;
                j--;
            }

            while (i < m && j >= 0) {
                result[index++] = mat[i++][j--];
            }
            if (j < 0) {
                j++;
            }
            if (i == m) {
                i--;
                j++;
            }
        }
        return result;
    }
}
