package com.day1;

import java.util.Scanner;

public class conditionalStetementDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        if (num > 0) {
            System.out.println("The number is Positive");
        } else {
            System.out.println("The number is Not Positive");
        }

        sc.close();
    }
}
