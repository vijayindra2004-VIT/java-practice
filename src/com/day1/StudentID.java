package com.day1;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "ID: " + id + " , Name: " + name;
    }
}

public class StudentID {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Student CRUD Menu ---");
            System.out.println("1. Create (Add Student)");
            System.out.println("2. Read (Display Students)");
            System.out.println("3. Update (Edit Student)");
            System.out.println("4. Delete (Remove Student)");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1: // CREATE
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    students.add(new Student(id, name));
                    System.out.println("Student Added!");
                    break;

                case 2: // READ
                    if (students.isEmpty()) {
                        System.out.println("No students found!");
                    } else {
                        System.out.println("\n--- Student List ---");
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3: // UPDATE
                    System.out.print("Enter Student ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    boolean updated = false;

                    for (Student s : students) {
                        if (s.id == updateId) {
                            System.out.print("Enter new name: ");
                            s.name = sc.nextLine();
                            System.out.println("Student Updated!");
                            updated = true;
                            break;
                        }
                    }

                    if (!updated) {
                        System.out.println("Student ID not found!");
                    }
                    break;

                case 4: // DELETE
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = sc.nextInt();

                    boolean deleted = false;

                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).id == deleteId) {
                            students.remove(i);
                            System.out.println("Student Deleted!");
                            deleted = true;
                            break;
                        }
                    }

                    if (!deleted) {
                        System.out.println("Student ID not found!");
                    }
                    break;

                case 5: // EXIT
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
