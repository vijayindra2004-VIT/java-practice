package com.jdbcsample;

import java.util.Scanner;

// Parent Class
class Calculator {
    int a;
    int b;

    Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    void add() {
        System.out.println("Addition: " + (a + b));
    }

    void subtract() {
        System.out.println("Subtraction: " + (a - b));
    }
}

// Interface
interface AdvancedOperations {
    void multiply();
    void divide();
}

// Hybrid Inheritance
class Result extends Calculator implements AdvancedOperations {

    Result(int a, int b) {
        super(a, b);
    }

    public void multiply() {
        System.out.println("Multiplication: " + (a * b));
    }

    public void divide() {
        if (b != 0)
            System.out.println("Division: " + (a / b));
        else
            System.out.println("Cannot divide by zero");
    }
}

// Main Class
public class HybridInheritanceValues{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int num1 = sc.nextInt();

        System.out.print("Enter second number: ");
        int num2 = sc.nextInt();

        Result obj = new Result(num1, num2);

        int choice;

        do {
            System.out.println("\n1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    obj.add();
                    break;
                case 2:
                    obj.subtract();
                    break;
                case 3:
                    obj.multiply();
                    break;
                case 4:
                    obj.divide();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}