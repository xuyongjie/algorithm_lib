package unknown;

/**
 * 给一个数如23333，给定一个集合如{2,9}，找到小于23333的最大值，且该值的数字必须都在集合{2,9}中
 */
public class Solution1 {

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.dfs(0, new int[]{8, 9}, 23333);
        System.out.println(solution1.max);
    }

    int max = Integer.MIN_VALUE;

    public void dfs(int cur, int[] s, int limit) {
        if (cur >= limit) {
            return;
        }
        max = Math.max(max, cur);
        for (int i = 0; i < s.length; i++) {
            dfs(cur * 10 + s[i], s, limit);
        }
    }
}
