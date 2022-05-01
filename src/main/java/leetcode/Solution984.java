package leetcode;

public class Solution984 {

    public static void main(String[] args) {
        Solution984 solution984 = new Solution984();
        System.out.println(solution984.strWithout3a3b(4, 1));
    }

    public String strWithout3a3b(int a, int b) {
        StringBuilder builder = new StringBuilder();
        while (a > 0 || b > 0) {
            boolean writeA;
            int len = builder.length();
            if (len >= 2 && builder.charAt(len - 1) == builder.charAt(len - 2)) {
                writeA = builder.charAt(len - 1) == 'b';
            } else {
                writeA = a >= b;
            }
            if (writeA) {
                builder.append('a');
                a--;
            } else {
                builder.append('b');
                b--;
            }
        }
        return builder.toString();
    }
}
