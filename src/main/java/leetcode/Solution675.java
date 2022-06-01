package leetcode;

import java.util.*;

public class Solution675 {

    public static void main(String[] args) {
        List<List<Integer>> g = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(54581641);
        row1.add(64080174);
        row1.add(24346381);
        row1.add(69107959);
        List<Integer> row2 = new ArrayList<>();
        row2.add(86374198);
        row2.add(61363882);
        row2.add(68783324);
        row2.add(79706116);
        List<Integer> row3 = new ArrayList<>();
        row3.add(668150);
        row3.add(92178815);
        row3.add(89819108);
        row3.add(94701471);
        List<Integer> row4 = new ArrayList<>();
        row4.add(83920491);
        row4.add(22724204);
        row4.add(46281641);
        row4.add(47531096);
        List<Integer> row5 = new ArrayList<>();
        row5.add(89078499);
        row5.add(18904913);
        row5.add(25462145);
        row5.add(60813308);
        g.add(row1);
        g.add(row2);
        g.add(row3);
        g.add(row4);
        g.add(row5);
        System.out.println(new Solution675().cutOffTree(g));
    }

    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int cutOffTree(List<List<Integer>> forest) {
        int steps = 0;
        int m = forest.size();
        int n = forest.get(0).size();
        List<int[]> trees = new ArrayList<>();//存储坐标和高度
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i * n + j, forest.get(i).get(j)});
                }
            }
        }
        trees.sort(Comparator.comparingInt(o -> o[1]));
        int from = 0;
        for (int[] tree : trees) {
            int step = cutOffEach(forest, m, n, from, tree[0]);
            if (step == -1) {
                return -1;
            }
            steps += step;
            from = tree[0];
        }
        return steps;
    }

    private int cutOffEach(List<List<Integer>> g, int m, int n, int from, int to) {
        Queue<Integer> q = new LinkedList<>();
        int[] d = new int[m * n];
        boolean[] visit = new boolean[m * n];
        q.add(from);
        visit[from] = true;
        d[from] = 0;
        while (!q.isEmpty()) {
            Integer u = q.poll();
            int ud = d[u];
            if (u == to) {
                return ud;
            }
            for (int[] dir : directions) {
                int nx = u / n + dir[0], ny = u % n + dir[1];
                int v = nx * n + ny;
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[v] && g.get(nx).get(ny) > 0) {
                    q.add(v);
                    visit[v] = true;
                    d[v] = ud + 1;
                }
            }
        }
        return -1;
    }
}
