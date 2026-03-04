class StudentProfile {

    String name;
    int age;
    double cgpa;
    boolean isEnrolled;

   public static void main(String[] args) {

        StudentProfile student = new StudentProfile();

        student.name = "Nikhita";
        student.age = 22;
        student.cgpa = 8.5;
        student.isEnrolled = true;

        System.out.println("Student Profile");
        System.out.println("Name: " + student.name);
        System.out.println("Age: " + student.age);
        System.out.println("CGPA: " + student.cgpa);
        System.out.println("Enrollment Status: " + student.isEnrolled);
    }
}