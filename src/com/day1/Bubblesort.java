package com.day1;

import java.util.Scanner;

class Sorting {
    public static void sortArray(int[] numList, int n) {

        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - 1 - i; j++) {
                if(numList[j] > numList[j + 1]) {
                    int temp = numList[j];
                    numList[j] = numList[j + 1];
                    numList[j + 1] = temp;
                }
            }
        }

        System.out.print("Sorted Array: ");
        for(int i = 0; i < n; i++) {
            System.out.print(numList[i] + " ");
        }
    }
}

public class Bubblesort {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of array(n):");
        int n = sc.nextInt();

        int[] numList = new int[n];

        System.out.printf("Enter %d elements(Integer):%n", n);
        for(int i = 0; i < n; i++) {
            numList[i] = sc.nextInt();
        }

        sc.close();

        Sorting.sortArray(numList, n);
    }
}
