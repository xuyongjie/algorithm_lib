package leetcode;

import java.util.HashSet;
import java.util.Set;

public class Solution433 {

    public static void main(String[] args) {
        Solution433 solution433 = new Solution433();
        System.out.println(solution433.minMutation("AAAACCCC", "CCCCCCCC", new String[]{"AAAACCCA", "AAACCCCA", "AACCCCCA", "AACCCCCC", "ACCCCCCC", "CCCCCCCC", "AAACCCCC", "AACCCCCC"}));
    }

    int result;

    public int minMutation(String start, String end, String[] bank) {
        result = Integer.MAX_VALUE;
        Set<String> visit = new HashSet<>();
        back(start, end, bank, visit, 0);
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }

    private void back(String start, String end, String[] bank, Set<String> visit, int steps) {
        if (start.equals(end)) {
            result = Math.min(result, steps);
            return;
        }
        for (int i = 0; i < bank.length; i++) {
            if (!visit.contains(bank[i])) {
                if (canTransfer(start, bank[i])) {
                    visit.add(bank[i]);
                    back(bank[i], end, bank, visit, steps + 1);
                    visit.remove(bank[i]);
                }
            }
        }
    }

    private boolean canTransfer(String from, String to) {
        int differCount = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                differCount++;
            }
            if (differCount > 1) {
                return false;
            }
        }
        return differCount == 1;
    }
}
