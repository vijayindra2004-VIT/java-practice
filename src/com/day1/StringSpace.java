package com.day1;

import java.util.Scanner;

public class StringSpace {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        String s = sc.nextLine();

        int spaces = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                spaces++;
        }

        System.out.println("Total gaps(spaces): " + spaces);

        sc.close();
    }
}
