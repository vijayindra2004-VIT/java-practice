package com.day1;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.add(0, 20);
        list.addLast(60);

        System.out.println(list);
        
        LinkedList<String> fruits = new LinkedList<>();
        fruits.add("mango");
        fruits.add("orange");
        fruits.addFirst("peach");
        System.out.println(fruits);
        System.out.println(fruits.contains("peach"));
        System.out.println(fruits.get(0));
        System.out.println(fruits.getLast());
        
        Iterator<String> it =  fruits.iterator();
        System.out.println("Iterating:");
        while (it.hasNext()) {
        	System.out.println(it.next()+" ");
        }
        System.out.println("Using foreach:");
        fruits.forEach(item -> System.out.println(item + " "));
        System.out.println(      );
        fruits.clear();
    }
}
