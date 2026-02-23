package com.day1;

interface A{
	default void show() {
		System.out.println("This is Default method");
	}
	void display();
	
	static void showit() {
		System.out.println("This is static");
	}
}

interface B{
	void show();
	
	static void showthis() {
		System.out.println("This is B");
	}
	
}

class C implements A,B{
	public void show1() {
		System.out.println("This is child");
		A.super.show();
		A.showit();
		B.showthis();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

}

public class Abstractmethods {
	public static void main(String[] args) {
		
		C ob1 = new C();
		ob1.show1();
		
	}
}