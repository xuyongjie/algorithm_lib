package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution6094 {

    public static void main(String[] args) {
        System.out.println(new Solution6094().distinctNames(new String[]{"coffee", "donuts", "time", "toffee"}));
    }

    public long distinctNames(String[] ideas) {
        Map<String, Set<Character>> groups = new HashMap<>();
        for (String idea : ideas) {
            String key = idea.substring(1);
            groups.putIfAbsent(key, new HashSet<>());
            groups.get(key).add(idea.charAt(0));
        }
        int[][] cnt = new int[26][26];//定义cnt[i][j] 表示组中首字母不包含 i 但包含 j 的组的个数
        long res = 0;
        for (Set<Character> group : groups.values()) {
            for (int i = 0; i < 26; i++) {
                if (!group.contains((char) (i + 'a'))) {
                    for (int j = 0; j < 26; j++) {
                        if (group.contains((char) (j + 'a'))) {
                            cnt[i][j]++;
                        }
                    }
                } else {
                    for (int j = 0; j < 26; j++) {
                        if (!group.contains((char) (j + 'a'))) {
                            res += cnt[i][j];
                        }
                    }
                }
            }
        }
        return res * 2;
    }
}
