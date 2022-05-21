package leetcode;

public class Solution6059 {
    public static void main(String[] args) {
        Solution6059 solution6059 = new Solution6059();
        System.out.println(solution6059.hasValidPath(new char[][]{{'(', '(', '('}, {')', '(', ')'}, {'(', '(', ')'}, {'(', '(', ')'}}));
    }

    private int row, column;
    char[][] grid;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        if (grid[0][0] == ')') {
            return false;
        }
        row = grid.length;
        column = grid[0].length;
        if ((row + column) % 2 == 0) {
            return false;
        }
        return backTracking(0, 0, 1);
    }

    int[][] directions = new int[][]{{1, 0}, {0, 1}};

    private boolean backTracking(int r, int c, int v) {
        if (r == row - 1 && c == column - 1) {
            return v == 0;
        }
        if (v > row - 1 - r + column - 1 - c) {
            return false;
        }
        if (v < 0) {
            return false;
        }
        for (int[] d : directions) {
            int nRow = r + d[0];
            int nColumn = c + d[1];
            if (nRow < row && nColumn < column) {
                char cur = grid[nRow][nColumn];
                if (backTracking(nRow, nColumn, v + (cur == '(' ? 1 : -1))) {
                    return true;
                }
            }
        }
        return false;
    }
}
