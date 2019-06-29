package calculator;

import java.util.Scanner;
/**
 * @author calc.Roman Minevich
 * @version June 29, 2019
 */
public class Calculator implements Arabic, Roman {
    String expression, operator;
    String[] operands;
    int operand1, operand2;
    Object result;

    Calculator() {
        System.out.println("\nThis calculator can add, subtract, multiply or divide two numbers.\n" +
                "It accepts integers from 0 to 10 and roman numerals from I to X.\n" +
                "The input must be in the format <operand> <operator> <operand> (e.g. 2 + 2).\n" +
                "The operands must be of the same numeral system.\n");
    }

    void setExpression() {
        System.out.println("Enter the expression:");
        expression = new Scanner(System.in).nextLine();
        expression = expression.replace(" ", "");
    }

    void setOperator() {
        for (String operator : new String[]{"+", "-", "*", "/"}) {
            if (expression.contains(operator)) {
                this.operator = operator;
            }
        }
    }

    void setOperands() {
        operands = expression.split(String.format("\\%s", operator), 2);
    }

    void calculate() {
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
        }
    }

    void print() {
        System.out.println(operands[0] + " " + operator + " " + operands[1] + " = " + result + "\n");
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        while (true) {
            calculator.setExpression();
            calculator.setOperator();
            calculator.setOperands();
            if (calculator.operands.length > 1
                    && calculator.checkArabic(calculator.operands[0])
                    && calculator.checkArabic(calculator.operands[1])) {
                try {
                    calculator.operand1 = calculator.parseArabic(calculator.operands[0]);
                    calculator.operand2 = calculator.parseArabic(calculator.operands[1]);
                    calculator.calculate();
                    calculator.print();
                } catch (ArithmeticException e) {
                    System.out.println("Can't divide by zero");
                }
            } else if (calculator.operands.length > 1
                    && calculator.checkRoman(calculator.operands[0])
                    && calculator.checkRoman(calculator.operands[1])) {
                calculator.operand1 = calculator.parseRoman(calculator.operands[0]);
                calculator.operand2 = calculator.parseRoman(calculator.operands[1]);
                calculator.calculate();
                calculator.result = calculator.resultToRoman(calculator.result);
                calculator.print();
            } else {
                System.out.println("Invalid expression");
            }
        }
    }
}
