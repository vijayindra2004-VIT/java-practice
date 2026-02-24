package com.jdbcsample;

import java.util.Scanner;

public class StudentPerformance {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter marks for Subject 1: ");
        int m1 = sc.nextInt();

        System.out.print("Enter marks for Subject 2: ");
        int m2 = sc.nextInt();

        System.out.print("Enter marks for Subject 3: ");
        int m3 = sc.nextInt();
        
        System.out.print("Enter marks for subject 4: ");
        int m4 = sc.nextInt();
        
        System.out.print("Enter marks for subject 5: ");
        int m5 = sc.nextInt();

        double average = (m1 + m2 + m3 + m4 + m5) / 5.0;

        String grade;
        String result;

        if (average >= 90) {
            grade = "A+";
            result = "Pass";
        } 
        else if (average >= 70 && average < 90) {
            grade = "A";
            result = "Pass";
        } 
        else if (average >= 50 && average < 70) {
            grade = "B";
            result = "Pass";
        } 
        else if (average >= 35 && average < 50) {
            grade = "C";
            result = "Pass";
        } 
        else {
            grade = "*****3";
            result = "Fail";
        }

        System.out.println("\nAverage Marks: " + average);
        System.out.println("Grade: " + grade);
        System.out.println("Result: " + result );

        sc.close();
    }
}