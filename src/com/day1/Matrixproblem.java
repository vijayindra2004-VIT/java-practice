package com.day1;

public class Matrixproblem {

	public static void main(String[] args) {
		int[][] matrix1 = {
				{5,3,4},
				{3,4,2},
				{4,8,2}
		};
		
		int[][] matrix2 = {
				{5,3,1},
				{3,4,1},
				{4,8,1}
		};
		
		int[][] sumArr = new int[3][3];
		
		for(int i=0;i<matrix1.length;i++) {
			for(int j=0;j<matrix1[i].length;j++) {
				sumArr[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		System.out.println("Sum of two matrix is: ");
		for(int i=0;i<sumArr.length;i++) {
			for(int j= 0;j<sumArr[i].length;j++) {
				
				System.out.print(sumArr[i][j]+ " ");
			}
			System.out.println();
		}

	}

}