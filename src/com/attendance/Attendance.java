package attendance;

public class Attendance {

    public static void main(String[] args) {

        Student s1 = new Student("manoj");

        s1.markAttendance("Java Programming", "absent");
        s1.viewAttendance();
        s1.updateAttendance(1, "Absent");  // Change ID if needed
        s1.generateSummary();
    }
}