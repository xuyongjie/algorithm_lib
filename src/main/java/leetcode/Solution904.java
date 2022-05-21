package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution904 {
    public static void main(String[] args) {
        Solution904 solution904 = new Solution904();
        System.out.println(solution904.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
    }

    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> typeCount = new HashMap<>();
        int i = 0, j = 0;
        int length = fruits.length;
        int max = 0;
        while (i < length && j < length) {
            if (typeCount.size() == 3) {
                max = Math.max(max, j - i - 1);
                do {
                    typeCount.put(fruits[i], typeCount.get(fruits[i]) - 1);
                } while (typeCount.get(fruits[i++]) > 0);
                typeCount.remove(fruits[i - 1]);
            } else {
                typeCount.putIfAbsent(fruits[j], 0);
                typeCount.put(fruits[j], typeCount.get(fruits[j]) + 1);
                j++;
            }
        }
        max = Math.max(max, j - i);
        return max;
    }
}
