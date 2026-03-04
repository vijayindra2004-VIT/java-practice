/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.sql.DriverManager;
import java.sql.*;
public class DBConnection {
    public static Connection getConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/customer_management","root","Vijay@777");
        } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }
}
