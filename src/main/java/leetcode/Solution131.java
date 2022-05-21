package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution131 {

    public static void main(String[] args) {
        Solution131 solution131 = new Solution131();
        System.out.println(solution131.partition("aab"));
    }

    List<List<String>> result;
    boolean[][] flag;

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        char[] chars = s.toCharArray();
        flag = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                flag[i][j] = isCycle(chars, i, j);
            }
        }
        back(chars, 0, new ArrayList<>());
        return result;
    }

    private void back(char[] s, int index, List<String> path) {
        if (index == s.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < s.length; i++) {
            if (flag[index][i]) {
                path.add(new String(Arrays.copyOfRange(s, index, i + 1)));
                back(s, i + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isCycle(char[] s, int i, int j) {
        if (i == j) {
            return true;
        }
        if (i + 1 == j) {
            return s[i] == s[j];
        }
        return s[i] == s[j] && isCycle(s, i + 1, j - 1);
    }
}
