package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1286 {

    public static void main(String[] args) {
        Solution1286 solution1286 = new Solution1286("abc", 2);
    }

    List<String> all;
    int pointer;

    public Solution1286(String characters, int combinationLength) {
        char[] C = characters.toCharArray();
        Arrays.sort(C);
        all = new ArrayList<>();
        pointer = 0;
        backTracking(C, new ArrayList<>(), 0, combinationLength);
    }

    private void backTracking(char[] chars, List<Character> path, int index, int target) {
        if (path.size() == target) {
            all.add(getString(path));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            path.add(chars[i]);
            backTracking(chars, path, i + 1, target);
            path.remove(path.size() - 1);
        }
    }

    private String getString(List<Character> path) {
        StringBuilder sb = new StringBuilder();
        for (Character character : path) {
            sb.append(character);
        }
        return sb.toString();
    }

    public String next() {
        return all.get(pointer++);
    }

    public boolean hasNext() {
        return pointer < all.size();
    }
}
