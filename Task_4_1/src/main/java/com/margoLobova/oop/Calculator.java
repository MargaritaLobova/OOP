package com.margoLobova.oop;

import java.util.Scanner;
import java.util.Stack;

/**
 * Engineer calculator for real numbers. Prefix terms. Сonsole input and output.
 */
public class Calculator {
    private final String string;

    Calculator(String string) {
        this.string = string;
    }

    /**
     * @return Double value - the result of computation
     */
    public Double calculate() {
        Stack<Double> stack = new Stack<>();
        String[] strings = string.split(" ");
        for (int i = strings.length - 1; i >= 0; i--) {
            if (!isOperation(strings[i])) {
                stack.add(Double.valueOf(strings[i]));
            } else {
                switch (strings[i]) {
                    case ("+"):
                        stack.add(add(stack));
                        break;
                    case ("-"):
                        stack.add(subtract(stack));
                        break;
                    case ("*"):
                        stack.add(multiply(stack));
                        break;
                    case ("/"):
                        stack.add(divide(stack));
                        break;
                    case ("sin"):
                        stack.add(sin(stack));
                        break;
                    case ("cos"):
                        stack.add(cos(stack));
                        break;
                    case ("pow"):
                        stack.add(pow(stack));
                        break;
                    case ("log"):
                        stack.add(log(stack));
                        break;
                    case ("sqrt"):
                        stack.add(sqrt(stack));
                        break;
                    default:
                        throw new IllegalArgumentException("Use proper operations.");
                }
            }
        }
        return stack.pop();
    }

    /**
     * @param string - part of input string that is supposed to be either operation or double value
     * @return true if the string is permitted operation, false in other case.
     */
    private boolean isOperation(String string) {
        return string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/") ||
                string.equals("sin") || string.equals("log") || string.equals("sqrt") ||
                string.equals("cos") || string.equals("pow");
    }

    private Double add(Stack<Double> stack) {
        return stack.pop() + stack.pop();
    }

    private Double subtract(Stack<Double> stack) {
        return stack.pop() - stack.pop();
    }

    private Double multiply(Stack<Double> stack) {
        return stack.pop() * stack.pop();
    }

    private Double divide(Stack<Double> stack) {
        return stack.pop() / stack.pop();
    }

    private Double log(Stack<Double> stack) {
        return Math.log(stack.pop());
    }

    private Double sqrt(Stack<Double> stack) {
        return Math.sqrt(stack.pop());
    }

    private Double pow(Stack<Double> stack) {
        return Math.pow(stack.pop(), stack.pop());
    }

    private Double sin(Stack<Double> stack) {
        return Math.sin(stack.pop());
    }

    private Double cos(Stack<Double> stack) {
        return Math.cos(stack.pop());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a prefix term:");
        String string = in.nextLine();
        Calculator calculator = new Calculator(string);
        System.out.println("Result is: ");
        System.out.println(calculator.calculate());
        in.close();
    }
}



