package leetcode;

import java.util.*;

public class Solution2250 {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int[] count = new int[points.length];
        Map<Integer, List<int[]>> heightMap = new HashMap<>();
        for (int i = 0; i < rectangles.length; i++) {
            if (!heightMap.containsKey(rectangles[i][1])) {
                heightMap.put(rectangles[i][1], new ArrayList<>());
            }
            heightMap.get(rectangles[i][1]).add(rectangles[i]);
        }
        for (List<int[]> each : heightMap.values()) {
            each.sort(Comparator.comparingInt(o -> o[0]));
        }
        for (int i = 0; i < points.length; i++) {
            for (Integer h : heightMap.keySet()) {
                if (points[i][1] <= h) {
                    List<int[]> sortedList = heightMap.get(h);
                    int firstBiggerOrEqualIndex = findFirst(sortedList, 0, sortedList.size() - 1, points[i][0]);
                    count[i] += sortedList.size() - firstBiggerOrEqualIndex;
                }
            }
        }
        return count;
    }

    private int findFirst(List<int[]> sortedList, int start, int end, int target) {
        if (start > end) {
            return start;
        }
        int mid = (start + end) / 2;
        if (sortedList.get(mid)[0] == target) {
            return mid;
        } else if (sortedList.get(mid)[0] > target) {
            return findFirst(sortedList, start, mid - 1, target);
        } else {
            return findFirst(sortedList, mid + 1, end, target);
        }
    }
}
