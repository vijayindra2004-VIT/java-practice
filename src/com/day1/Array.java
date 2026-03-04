package com.day1;

public class Array {
    public static void main(String[] args) {

       int[] arr = {10,20,30,40};
       try {
    	   int a = arr[4];
       System.out.println(a);
    } catch(Exception e) {
    	e.printStackTrace();
       System.out.println(e);
}
} }

