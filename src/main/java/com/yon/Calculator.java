package com.yon;

import java.util.LinkedList;

public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String expression = "5+4*3-24/3+12/5";
        System.out.println(expression + "=" + calculator.calculate(expression));
    }

    private int calculate(String expression) {
        LinkedList<Integer> numStack = new LinkedList<>();
        LinkedList<Character> opStack = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else {
                numStack.push(num);
                num = 0;
                if (opStack.isEmpty()) {
                    opStack.push(c);
                } else {
                    while (!opStack.isEmpty() && prior(opStack.peek(), c)) {
                        int num2 = numStack.pop();
                        int num1 = numStack.pop();
                        numStack.push(calculateOp(num1, num2, opStack.pop()));
                    }
                    opStack.push(c);
                }
            }
        }
        numStack.push(num);
        while (!opStack.isEmpty()) {
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            numStack.push(calculateOp(num1, num2, opStack.pop()));
        }
        return numStack.pop();
    }

    private int calculateOp(int num1, int num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return 0;
    }

    private boolean prior(char op1, char op2) {
        switch (op1) {
            case '+':
            case '-':
                return op2 == '+' || op2 == '-';
            case '*':
            case '/':
                return true;
        }
        return true;
    }
}
