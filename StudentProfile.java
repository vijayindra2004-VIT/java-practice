import java.util.Scanner;

class StudentProfile {

    String name;
    int age;
    double cgpa;
    boolean isEnrolled;

    public static void main(String[] args) {

        StudentProfile student = new StudentProfile();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        student.name = sc.nextLine();

        System.out.println("Enter your age:");
        student.age = sc.nextInt();

        System.out.println("Enter your CGPA:");
        student.cgpa = sc.nextDouble();

        System.out.println("Enter enrollment status (true/false):");
        student.isEnrolled = sc.nextBoolean();

        System.out.println("\nStudent Profile");
        System.out.println("Name: " + student.name);
        System.out.println("Age: " + student.age);
        System.out.println("CGPA: " + student.cgpa);
        System.out.println("Enrollment Status: " + student.isEnrolled);

        sc.close();
    }
}
