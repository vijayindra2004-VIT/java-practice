package com.day1;

interface Car {
	public static void show() {
		System.out.println("This is car");
	}
}

interface Bus{
	public static void show() {
		System.out.println("This is Bus");
	}
}

class Car2 implements Car,Bus{
	public  void display() {
		
		Car.show();
		Bus.show();
		System.out.println("This is car2");
	}
}
public class MultipleInheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println();
		Car2 obj1 = new Car2();
		obj1.display();
		
		
	}

}