package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution224 {

    public static void main(String[] args) {
        System.out.println(new Solution224().calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    public int calculate(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();
        s = "(" + s + ")";
        int num = 0;
        boolean preDigit = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                preDigit = true;
            } else {
                if (preDigit || (c == '-' && s.charAt(i - 1) == '(')) {
                    numStack.push(num);
                    num = 0;
                }
                while (!opStack.isEmpty() && prior(opStack.peekFirst(), c)) {
                    char topOp = opStack.pop();
                    if (topOp == '(' && c == ')') {
                        break;
                    }
                    int num2 = numStack.pop(), num1 = numStack.pop();
                    numStack.push(cal(num1, num2, topOp));
                }
                if (c != ')') {
                    opStack.push(c);
                }
                preDigit = false;
            }
        }
        return numStack.pop();
    }

    private int cal(int num1, int num2, char op) {
        if (op == '-') {
            return num1 - num2;
        } else if (op == '+') {
            return num1 + num2;
        }
        return 0;
    }

    //op1的优先级是否高于op2
    private boolean prior(char op1, char op2) {
        switch (op1) {
            case '+':
            case '-':
                switch (op2) {
                    case '+':
                    case '-':
                        return true;
                    case '(':
                        return false;
                    case ')':
                        return true;
                }
                break;
            case '(':
                switch (op2) {
                    case '+':
                    case '-':
                        return false;
                    case '(':
                        return false;
                    case ')':
                        return true;
                }
                break;
            case ')':
                return true;
        }
        return true;
    }
}
