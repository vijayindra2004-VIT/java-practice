package com.day1;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(20);
        list.add(0, 20);

        System.out.println(list);
        
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("mango");
        fruits.add("orange");
        System.out.println(fruits);
        System.out.println(fruits.contains("orange"));
        fruits.clear();
        System.out.println(fruits);
        
    }
}
