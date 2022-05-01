package com.yon;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        XmlParser xmlParser = new XmlParser();
        XmlParser.XmlNode parsed = xmlParser.parse("<root><sub1>2</sub1><sub2>3</sub2></root>");
        System.out.println(parsed);
    }


    public int mctFromLeafValues(int[] arr) {
        int len = arr.length;
        int[][] m = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    m[i][j] = arr[i];
                    continue;
                }
                m[i][j] = Math.max(m[i][j - 1], arr[j]);
            }
        }
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                    continue;
                }
                if (j == i + 1) {
                    dp[i][j] = arr[i] * arr[j];
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + m[i][k] * m[k + 1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }

    public List<String> getAll(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            dfs(i, n, result);
        }
        return result;
    }

    private void dfs(int parent, int n, List<String> result) {
        if (parent > n) {
            return;
        }
        result.add(parent + "");
        for (int i = 0; i <= 9; i++) {
            dfs(parent * 10 + i, n, result);
        }
    }
}
