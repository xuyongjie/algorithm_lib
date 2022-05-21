package leetcode;

public class Solution6058 {

    public static void main(String[] args) {
        Solution6058 solution6058 = new Solution6058();
        System.out.println(solution6058.countTexts("22233"));
    }

    int MOD = 1000000007;

    public int countTexts(String pressedKeys) {
        int len = pressedKeys.length();
        char[] keys = pressedKeys.toCharArray();
        long[] dp = new long[len + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            dp[i] = dp[i - 1];
            if (i >= 2 && keys[i - 1] == keys[i - 2]) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
            if (i >= 3 && keys[i - 1] == keys[i - 2] && keys[i - 2] == keys[i - 3]) {
                dp[i] = (dp[i] + dp[i - 3]) % MOD;
            }
            if (i >= 4 && keys[i - 1] == keys[i - 2] && keys[i - 2] == keys[i - 3] && keys[i - 3] == keys[i - 4]) {
                if (keys[i - 1] == '7' || keys[i - 1] == '9') {
                    dp[i] = (dp[i] + dp[i - 4]) % MOD;
                }
            }
        }
        return (int) dp[len];
    }
}
