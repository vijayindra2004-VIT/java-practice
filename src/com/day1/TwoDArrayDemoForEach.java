package com.day1;

public class TwoDArrayDemoForEach {

    public static void main(String[] args) {

        int[][] arr = { {10, 20, 30}, {40, 50, 60}, {70, 80, 90}
        };
        for (int[] row : arr) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
