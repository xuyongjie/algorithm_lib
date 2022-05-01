package leetcode;

import java.util.LinkedList;

public class Solution1631 {

    public static void main(String[] args) {
        Solution1631 solution1631 = new Solution1631();
        solution1631.minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}});
    }

    private int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int minimumEffortPath(int[][] heights) {
        int row = heights.length, column = heights[0].length;
        int[][] minEffort = new int[row][];
        boolean[][] visited = new boolean[row][];
        for (int i = 0; i < row; i++) {
            minEffort[i] = new int[column];
            visited[i] = new boolean[column];
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int i = 0, j = 0;
        queue.push(i * row + j);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            i = cur / row;
            j = cur % row;
            visited[i][j] = true;
            for (int d = 0; d < directions.length; d++) {
                int newI = i + directions[d][0], newJ = j + directions[d][1];
                if (newI < 0 || newI >= row || newJ < 0 || newJ >= column) {
                    continue;
                }
                if (!visited[newI][newJ]) {
                    minEffort[newI][newJ] = Math.max(minEffort[i][j], Math.abs(heights[newI][newJ] - heights[i][j]));
                    queue.add(newI * row + newJ);
                }
            }
        }
        return minEffort[row - 1][column - 1];
    }
}
