package JAVA_SYNTAX;
import java.util.Scanner;

 class Calci {
    static String Operator;
    static int num1;
    static int num2;
}

public class Calculator extends Calci {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter the operation you want to perform (+, -, /, *, %): ");
        Operator = obj.nextLine();

        if (Operator.equals("+")) {
            System.out.println("You selected Addition " + Operator);
        } else if (Operator.equals("-")) {
            System.out.println("You selected Subtraction " + Operator);
        } else if (Operator.equals("/")) {
            System.out.println("You selected Division " + Operator);
        } else if (Operator.equals("*")) {
            System.out.println("You selected Multiplication " + Operator);
        } else if (Operator.equals("%")) {
            System.out.println("You selected Modulus " + Operator);
        } else {
            System.out.println("Invalid operator selected.");
            return;
        }

        System.out.print("Enter num1: ");
        num1 = obj.nextInt();
        System.out.print("Enter num2: ");
        num2 = obj.nextInt();

        switch (Operator) {
            case "+":
                System.out.println("Value of " + num1 + " " + Operator + " " + num2 + " = " + (num1 + num2));
                break;
            case "-":
                System.out.println("Value of " + num1 + " " + Operator + " " + num2 + " = " + (num1 - num2));
                break;
            case "*":
                System.out.println("Value of " + num1 + " " + Operator + " " + num2 + " = " + (num1 * num2));
                break;
            case "/":
                if (num2 != 0) {
                    System.out.println("Value of " + num1 + " " + Operator + " " + num2 + " = " + (num1 / num2));
                } else {
                    System.out.println("Cannot divide by zero.");
                }
                break;
            case "%":
                if (num2 != 0) {
                    System.out.println("Value of " + num1 + " " + Operator + " " + num2 + " = " + (num1 % num2));
                } else {
                    System.out.println("Cannot divide by zero.");
                }
                break;
            default:
                System.out.println("Invalid operator.");
                break;
        }
    }
}
