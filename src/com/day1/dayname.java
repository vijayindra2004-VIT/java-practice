package com.day1;

public class dayname {

    public static void main(String[] args) {

        int day = 3;
        String dayname;

        switch (day) {
            case 1: dayname = "Monday"; break;
            case 2: dayname = "Tuesday"; break;
            case 3: dayname = "Wednesday"; break;
            case 4: dayname = "Thursday"; break;
            case 5: dayname = "Friday"; break;
            default: dayname = "Weekend";
        }

        System.out.println("Day: " + dayname);
    }
}
