package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Solution6076 {
    public static void main(String[] args) {
        System.out.println(new Solution6076().minimumLines(new int[][]{
                {72, 98}, {62, 27}, {32, 7},
                {71, 4}, {25, 19}, {91, 30},
                {52, 73}, {10, 9}, {99, 71},
                {47, 22}, {19, 30}, {80, 63},
                {18, 15}, {48, 17}, {77, 16},
                {46, 27}, {66, 87}, {55, 84},
                {65, 38}, {30, 9}, {50, 42},
                {100, 60}, {75, 73}, {98, 53},
                {22, 80}, {41, 61}, {37, 47},
                {95, 8}, {51, 81}, {78, 79},
                {57, 95}
        }));
    }

    public int minimumLines(int[][] stockPrices) {
        int len = stockPrices.length;
        if (len <= 1) {
            return 0;
        }
        Arrays.sort(stockPrices, Comparator.comparingInt(o -> o[0]));
        int lines = 1;
        int preDeltaY = stockPrices[1][1] - stockPrices[0][1];
        int preDeltaX = stockPrices[1][0] - stockPrices[0][0];
        for (int i = 2; i < len; i++) {
            int deltaY = stockPrices[i][1] - stockPrices[i - 1][1];
            int deltaX = stockPrices[i][0] - stockPrices[i - 1][0];
            long m1 = (long) preDeltaY * (long) deltaX;
            long m2 = (long) preDeltaX * (long) deltaY;
            if (m1 != m2) {
                lines++;
            }
            preDeltaY = deltaY;
            preDeltaX = deltaX;
        }
        return lines;
    }
}
