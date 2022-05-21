package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Solution6068 {

    public static void main(String[] args) {
        int a = 62 & 13 & 12 & 24 & 14 & 45 & 84;
        int b = 12 & 24 & 62 & 45 & 14 & 13 & 84;
        System.out.println(a + " " + b);

        System.out.println(new Solution6068().maximumWhiteTiles(new int[][]{{8051, 8057}, {8074, 8089},
                {7994, 7995}, {7969, 7987}, {8013, 8020}, {8123, 8139}, {7930, 7950},
                {8096, 8104}, {7917, 7925}, {8027, 8035}, {8003, 8011}}, 9854));
    }

    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, Comparator.comparingInt(o -> o[0]));
        int longest = tiles[tiles.length - 1][1];
        int max = 0, sum = 0;
        int start = tiles[0][0];
        return max;
    }

    private boolean isWhite(int[][] tiles, int a) {
        int from = 0, end = tiles.length - 1;
        while (from <= end) {
            int mid = (from + end) >> 1;
            int little = tiles[mid][0], large = tiles[mid][1];
            if (a >= little && a <= large) {
                return true;
            } else if (a < little) {
                end = mid - 1;
            } else {
                from = mid + 1;
            }
        }
        return false;
    }
}
