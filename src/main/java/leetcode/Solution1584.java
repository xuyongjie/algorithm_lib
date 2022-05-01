package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Solution1584 {

    public static void main(String[] args) {
        Solution1584 solution1584 = new Solution1584();
        System.out.println(solution1584.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }

    public static class UnionFindSet {
        private int[] group;//存储i所属的集团代表

        public UnionFindSet(int size) {
            group = new int[size];
            for (int i = 0; i < size; i++) {
                group[i] = i;
            }
        }

        public int find(int a) {
            if (group[a] == a) {
                return a;
            }
            group[a] = find(group[a]);//路径压缩
            return group[a];
        }


        public boolean union(int a, int b) {
            int groupA = find(a);
            int groupB = find(b);
            if (groupA == groupB) {
                return false;
            }
            group[groupB] = groupA;
            return true;
        }
    }


    public int minCostConnectPoints(int[][] points) {
        int totalCost = 0;
        int n = points.length;
        int[][] allEdges = new int[n * (n - 1) / 2][3];//{距离,坐标1,坐标2}
        int edgeIndex = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                allEdges[edgeIndex][0] = cost(points[i], points[j]);
                allEdges[edgeIndex][1] = i;
                allEdges[edgeIndex][2] = j;
                edgeIndex++;
            }
        }
        Arrays.sort(allEdges, Comparator.comparingInt(o -> o[0]));
        UnionFindSet unionFindSet = new UnionFindSet(n);
        int selectedEdges = 0;
        for (int i = 0; i < allEdges.length; i++) {
            if (selectedEdges == n - 1) {
                break;
            }
            int[] edge = allEdges[i];
            if (unionFindSet.union(edge[1], edge[2])) {
                totalCost += edge[0];
                selectedEdges++;
            }
        }
        return totalCost;

    }

    private int cost(int[] point1, int[] point2) {
        return Math.abs(point2[0] - point1[0]) + Math.abs(point2[1] - point1[1]);
    }
}
