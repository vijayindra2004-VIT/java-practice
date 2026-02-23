package com.day1;

public class privateClassDemo {

    private class First {
        int a = 10;
    }

    private class Second {
        String b = "Hello";

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    public static void main(String[] args) {

        privateClassDemo obj = new privateClassDemo();

        First obj1 = obj.new First() ;
        Second obj2 = obj.new Second();

        System.out.println("a = " + obj1.a);
        System.out.println("b = " + obj2.b);

        // using getter
        System.out.println("b using getter = " + obj2.getB());
    }
}
