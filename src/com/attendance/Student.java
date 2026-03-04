package attendance;

import java.sql.*;

public class Student extends User {

    private final String url = "jdbc:mysql://localhost:3306/companydb";
    private final String user = "root";
    private final String password = "Vijay@777";

    public Student(String name) {
        super(name);
    }

    // MARK ATTENDANCE
    public void markAttendance(String subject, String status) {

        String query = "INSERT INTO attendance (name, subject, status, date) VALUES (?, ?, ?, CURDATE())";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, this.getName());
                ps.setString(2, subject);
                ps.setString(3, status);

                ps.executeUpdate();
                System.out.println("Attendance inserted successfully.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // VIEW ATTENDANCE
    public void viewAttendance() {

        String query = "SELECT * FROM attendance WHERE name = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, this.getName());
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Attendance Records ---");
            System.out.println("ID | Subject | Status | Date");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("subject") + " | " +
                        rs.getString("status") + " | " +
                        rs.getDate("date")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // UPDATE
    public void updateAttendance(int id, String newStatus) {

        String query = "UPDATE attendance SET status = ? WHERE id = ? AND name = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, newStatus);
            ps.setInt(2, id);
            ps.setString(3, this.getName());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Attendance updated successfully.");
            } else {
                System.out.println("Record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteRecord(int id) {

        String query = "DELETE FROM attendance WHERE id = ? AND name = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.setString(2, this.getName());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // SUMMARY
    public void generateSummary() {

        String query = "SELECT status, COUNT(*) AS total FROM attendance WHERE name = ? GROUP BY status";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, this.getName());
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Attendance Summary ---");

            while (rs.next()) {
                System.out.println(rs.getString("status") + " : " + rs.getInt("total"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}