package com.margoLobova.oop;

import java.util.Scanner;


/**
 * Engineer calculator for real numbers. Prefix terms. Сonsole input and output.
 */
public class Calculator {
    private final String string;
    private final Stack<Double> stack = new Stack<Double>();

    Calculator(String string) {
        this.string = string;
    }

    /**
     * @return Double value - the result of computation
     */
    public Double calculate() {
        if (string.equals("")) {
            throw new IllegalArgumentException("Empty string!");
        }
        String[] strings = string.split(" ");
        for (int i = strings.length - 1; i >= 0; i--) {
            if (!isOperation(strings[i])) {
                try {
                    stack.push(Double.valueOf(strings[i]));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                switch (strings[i]) {
                    case ("+"):
                        stack.push(add());
                        break;
                    case ("-"):
                        stack.push(subtract());
                        break;
                    case ("*"):
                        stack.push(multiply());
                        break;
                    case ("/"):
                        stack.push(divide());
                        break;
                    case ("sin"):
                        stack.push(sin());
                        break;
                    case ("cos"):
                        stack.push(cos());
                        break;
                    case ("pow"):
                        stack.push(pow());
                        break;
                    case ("log"):
                        stack.push(log());
                        break;
                    case ("sqrt"):
                        stack.push(sqrt());
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

    private Double add() {
        return stack.pop() + stack.pop();
    }

    public Double subtract() {
        return stack.pop() - stack.pop();
    }

    private Double multiply() {
        return stack.pop() * stack.pop();
    }

    private Double divide() {
        return stack.pop() / stack.pop();
    }

    private Double log() {
        return Math.log(stack.pop());
    }

    private Double sqrt() {
        return Math.sqrt(stack.pop());
    }

    private Double pow() {
        return Math.pow(stack.pop(), stack.pop());
    }

    private Double sin() {
        return Math.sin(stack.pop());
    }

    private Double cos() {
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



