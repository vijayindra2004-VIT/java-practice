package com.day1;

import java.util.Scanner;

class Bike{
	public void bikeFair(double distance) {
		System.out.printf("The charge for the %.2fkm will be: ",distance);
		System.out.println(distance*2);
	}
}
class Cars{
	public void carFair(double distance) {
		System.out.printf("The charge for the %.2fkm will be: ",distance);
		System.out.println(distance*5);
	}
}

public class VehicleCostCalculator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int vehicle;
		double dist;
		int decision;
		Cars obj1 = new Cars();
		Bike obj2 = new Bike();
		
		boolean exit = false;
		while(!exit) {
			System.out.println("Enter the Vehicle you want to Travel: 1.Car 2.Bike 3.Exit:");
			vehicle = sc.nextInt();
			
			if(vehicle==3) {
				System.out.println("Exiting.... Thank you for using our service!");
				break;
			}
			
			System.out.println("Enter the distance:");
			dist = sc.nextDouble();
			
			switch (vehicle) {
			case 1:
				System.out.println("For Cars we put 5.Rs/km proceed? 1.Ok 2.No ");
				decision=sc.nextInt();
				if(decision==1) {
					obj1.carFair(dist);
				}
				break;
				
			case 2:
				System.out.println("For Bikes we put 2.Rs/km proceed? 1.Ok 2.No ");
				decision=sc.nextInt();
				if(decision==1) {
					obj2.bikeFair(dist);
				}
				break;
				
			default:
				System.out.println("Invalid entry..");
				break;
			}
			
		}
		sc.close();
		
	}

}