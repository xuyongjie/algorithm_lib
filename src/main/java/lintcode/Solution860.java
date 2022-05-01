package lintcode;

import java.util.HashSet;
import java.util.Set;

public class Solution860 {
    public static void main(String[] args) {
        Solution860 solution860 = new Solution860();
        System.out.println(solution860.numberOfDistinctIslands(new int[][]{{1, 1, 0, 0, 1}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 1, 0, 1, 1}}));
    }

    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    public int numberOfDistinctIslands(int[][] grid) {
        // write your code here
        Set<String> islands = new HashSet<>();
        boolean[][] visited = new boolean[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            visited[i] = new boolean[grid[i].length];
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    island = "";
                    dfs(grid, i, j, i, j, visited);
                    islands.add(island);
                }
            }
        }
        return islands.size();
    }

    private String island;

    private void dfs(int[][] grid, int firstI, int firstJ, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        if (grid[i][j] == 1) {
            island = island + (i - firstI) + "," + (j - firstJ) + ";";
            dfs(grid, firstI, firstJ, i - 1, j, visited);//上
            dfs(grid, firstI, firstJ, i + 1, j, visited);//下
            dfs(grid, firstI, firstJ, i, j - 1, visited);//左
            dfs(grid, firstI, firstJ, i, j + 1, visited);//右
        }
    }
}
