package com.day1;

public class throwDemo {
public static void checkAge(int age) {
	if(age<18) {
		throw new ArithmeticException("not eligible for vote");
	}
	else {
		System.out.println("eligible for vote");
	}
}
public static void main(String[] args) {
	try {
		checkAge(17);
	}
	catch(ArithmeticException e) {
		System.out.println(e+ " "+e.getMessage());
	}
	System.out.println("hello");
}
}
