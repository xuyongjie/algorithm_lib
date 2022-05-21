package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution1876 {

    public static void main(String[] args) {
        System.out.println(countGoodSubstrings("aababcabc"));
    }

    public static int countGoodSubstrings(String s) {
        int num = 0, len = s.length(), target = 3;
        Map<Character, Integer> counter = new HashMap<>();
        int start = 0;
        for (int end = 0; end < len; end++) {
            char c = s.charAt(end);
            counter.putIfAbsent(c, 0);
            counter.put(c, counter.get(c) + 1);
            if (end - start + 1 == target) {
                if (counter.size() == end - start + 1) {
                    num++;
                    counter.remove(s.charAt(start));
                    start++;
                } else {
                    while (counter.size() != end - start + 1) {
                        char t = s.charAt(start);
                        counter.put(t, counter.get(t) - 1);
                        if (counter.get(t) == 0) {
                            counter.remove(t);
                        }
                        start++;
                    }
                }
            }
        }
        return num;
    }
}
