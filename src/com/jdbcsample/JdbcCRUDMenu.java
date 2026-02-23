package com.jdbcsample;

import java.sql.*;
import java.util.Scanner;

public class JdbcCRUDMenu {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Load Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create Connection
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/companydb",
                "root",
                "Vijay@777");

        System.out.println("1. Create");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. Read");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();

        switch (choice) {

            case 1: // CREATE
                System.out.print("Enter roll: ");
                int roll = sc.nextInt();
                sc.nextLine(); // clear buffer

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                String insertQuery = "insert into student values(?,?)";
                PreparedStatement ps1 = con.prepareStatement(insertQuery);

                ps1.setInt(1, roll);
                ps1.setString(2, name);
                
                int insert = ps1.executeUpdate();
                System.out.println(insert + " row inserted");
                ps1.close();
                break;

            case 2: // UPDATE
            	System.out.println("Enter Update for 1.Name 2.roll");
                int decision = sc.nextInt();
                sc.nextLine(); // clear buffer

                if (decision == 1) {

                    System.out.print("Enter roll to update: ");
                    int uroll = sc.nextInt();
                    sc.nextLine(); // clear buffer

                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine();

                    String updateQuery = "update student set name=? where roll=?";
                    PreparedStatement ps2 = con.prepareStatement(updateQuery);

                    ps2.setString(1, newName);
                    ps2.setInt(2, uroll);

                    int update = ps2.executeUpdate();
                    System.out.println(update + " row updated");
                    ps2.close();

                } else if (decision == 2) {

                    System.out.print("Enter Name to update roll: ");
                    name = sc.nextLine();

                    System.out.print("Enter new roll: ");
                    int newroll = sc.nextInt();

                    String updateQuery = "update student set roll=? where name=?";
                    PreparedStatement ps2 = con.prepareStatement(updateQuery);

                    ps2.setInt(1, newroll);
                    ps2.setString(2, name);

                    int update = ps2.executeUpdate();
                    System.out.println(update + " row updated");
                    ps2.close();

                } else {
                    System.out.println("Invalid choice");
                }

                break;
            case 3: // DELETE
                System.out.print("Enter roll to delete: ");
                int droll = sc.nextInt();

                String deleteQuery = "delete from student where roll=?";
                PreparedStatement ps3 = con.prepareStatement(deleteQuery);

                ps3.setInt(1, droll);

                int delete = ps3.executeUpdate();
                System.out.println(delete + " row deleted");
                ps3.close();
                break;

            case 4: // READ
                String selectQuery = "select * from student";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(selectQuery);

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("roll") + " " +
                            rs.getString("name") );
                }

                rs.close();
                st.close();
                break;

            default:
                System.out.println("Invalid Choice");
        }

        con.close();
        sc.close();
    }
}