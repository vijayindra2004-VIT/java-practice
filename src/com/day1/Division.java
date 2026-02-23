package com.day1;

public class Division {
 public static void main(String [] args) {
	int a = 10;
	int b = 0;
	int arr[] = {3,4,5};
	try {
	int c= a/b;
	System.out.println(c);
	System.out.println(arr[3]);
	}
	catch(ArithmeticException e) {
		e.printStackTrace();
} catch(ArrayIndexOutOfBoundsException e) {
	e.printStackTrace();
}
	finally {
		System.out.println("finally block");
	}
	System.out.println("hello");
	}
}
