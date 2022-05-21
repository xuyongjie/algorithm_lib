package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution1297 {

    public static void main(String[] args) {
        System.out.println(new Solution1297().maxFreq("aaaa", 1, 3, 3));
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> occurCounter = new HashMap<>();

        Map<Character, Integer> window = new HashMap<>();
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            window.put(c, window.getOrDefault(c, 0) + 1);
            if (end - start + 1 == minSize) {
                if (window.size() <= maxLetters) {
                    String sub = s.substring(start, end + 1);
                    occurCounter.put(sub, occurCounter.getOrDefault(sub, 0) + 1);
                }
                char t = s.charAt(start);
                window.put(t, window.get(t) - 1);
                if (window.get(t) == 0) {
                    window.remove(t);
                }
                start++;
            }
        }
        int max = 0;
        for (String key : occurCounter.keySet()) {
            max = Math.max(max, occurCounter.get(key));
        }
        return max;
    }
}
