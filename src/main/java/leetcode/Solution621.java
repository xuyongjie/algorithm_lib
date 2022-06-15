package leetcode;

public class Solution621 {
    public static void main(String[] args) {
        System.out.println(new Solution621().leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }

    public int leastInterval(char[] tasks, int n) {
        int[] wait = new int[26];
        int[] taskCount = new int[26];
        for (char c : tasks) {
            taskCount[c - 'A']++;
        }
        int cost = 0;
        while (hasTask(taskCount)) {
            while (mustWait(wait, taskCount)) {
                for (int i = 0; i < 26; i++) {
                    wait[i]--;
                }
                cost++;
            }
            processOne(taskCount, wait, n);
            cost++;
        }
        return cost;
    }

    private boolean hasTask(int[] taskCount) {
        for (int count : taskCount) {
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    private boolean mustWait(int[] wait, int[] taskCount) {
        for (int i = 0; i < 26; i++) {
            if (wait[i] <= 0 && taskCount[i] > 0) {
                return false;
            }
        }
        return true;
    }

    private void processOne(int[] taskCount, int[] wait, int n) {
        int selected = -1, maxCount = 0;
        for (int i = 0; i < taskCount.length; i++) {
            if (wait[i] <= 0 && taskCount[i] > maxCount) {
                selected = i;
                maxCount = taskCount[i];
            }
        }
        taskCount[selected]--;
        for (int i = 0; i < wait.length; i++) {
            if (i == selected) {
                wait[i] = n;
            } else {
                wait[i]--;
            }
        }
    }
}
