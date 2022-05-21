package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution1763 {
    public static void main(String[] args) {
        Solution1763 solution1763 = new Solution1763();
        System.out.println(solution1763.longestNiceSubstring("YazaAay"));
    }

    public String longestNiceSubstring(String s) {
        int longest = 0, from = 0, to = 0, len = s.length();
        Map<Character, Integer> counter = new HashMap<>();
        int start = 0;

        for (int end = 0; end < len; end++) {
            char c = s.charAt(end);
            counter.putIfAbsent(c, 0);
            counter.put(c, counter.get(c) + 1);
            if (nice(counter)) {
                longest = Math.max(longest, end - start + 1);
                from = start;
                to = end;
            } else {
                while (!nice(counter)) {
                    char t = s.charAt(start);
                    counter.put(t, counter.get(t) - 1);
                    if (counter.get(t) == 0) {
                        counter.remove(t);
                    }
                    start++;
                }
            }
        }
        return s.substring(from, to + 1);
    }

    private boolean nice(Map<Character, Integer> counter) {
        for (Character key : counter.keySet()) {
            if (!(counter.containsKey(Character.toLowerCase(key)) && counter.containsKey(Character.toUpperCase(key)))) {
                return false;
            }
        }
        return true;
    }
}
