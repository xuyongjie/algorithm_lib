package leetcode;

import java.util.Arrays;

public class Solution43 {
    public static void main(String[] args) {
        System.out.println(new Solution43().multiply("2", "3"));
    }

    public String multiply(String num1, String num2) {
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();
        char[] res = new char[s1.length + s2.length];
        Arrays.fill(res, '0');
        char[] t = new char[s1.length + 1];
        Arrays.fill(t, '0');
        for (int i = s2.length - 1; i >= 0; i--) {
            multiOneDigit(s1, s2[i] - '0', t);
            add(res, t, res.length - s2.length + i);
        }
        int c = 0;
        while (c < res.length && res[c] == '0') {
            c++;
        }
        if (c == res.length) {
            return "0";
        }
        return new String(Arrays.copyOfRange(res, c, res.length));
    }

    private void multiOneDigit(char[] s1, int i, char[] t) {
        int pre = 0;
        for (int j = s1.length - 1, k = t.length - 1; k >= 0; j--, k--) {
            int tmp = (j >= 0 ? (s1[j] - '0') : 0) * i + pre;
            t[k] = (char) (tmp % 10 + '0');
            pre = tmp / 10;
        }
    }

    private void add(char[] res, char[] t, int index) {
        int mov = 0;
        for (int i = index, j = t.length - 1; i >= 0; i--, j--) {
            int tmp = res[i] - '0' + (j >= 0 ? t[j] - '0' : 0) + mov;
            res[i] = (char) (tmp % 10 + '0');
            mov = tmp / 10;
        }
    }
}
