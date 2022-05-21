package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution871 {

    public static void main(String[] args) {
        Solution871 solution871 = new Solution871();
        System.out.println(solution871.minRefuelStops(100, 25, new int[][]{{25, 25}, {50, 25}, {75, 25}}));
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int curPosition = 0;
        int curGas = startFuel;
        int stop = 0;
        PriorityQueue<Integer> gasQ = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < stations.length; i++) {
            if (curPosition + curGas >= target) {
                return stop;
            }
            if (curPosition + curGas >= stations[i][0]) {
                gasQ.add(stations[i][1]);
                continue;
            }
            while (curPosition + curGas < stations[i][0] && !gasQ.isEmpty()) {
                curGas += gasQ.poll();
                stop++;
            }
            if (gasQ.isEmpty() && curPosition + curGas < stations[i][0]) {
                return -1;
            }
            gasQ.add(stations[i][1]);
        }
        while (curPosition + curGas < target && !gasQ.isEmpty()) {
            curGas += gasQ.poll();
            stop++;
        }
        if (curPosition + curGas < target && gasQ.isEmpty()) {
            return -1;
        }
        return stop;
    }
}
