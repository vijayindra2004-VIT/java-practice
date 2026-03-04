/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

/**
 *
 * @author guruv
 */
import java.sql.*;

public class BankDAO {
    public void deposit(double amount, int acc_no) {
        try (Connection con = DBConnection.getConnection()) {
            // Update balance in accounts table
            String updateSQL = "UPDATE accounts SET balance = balance + ? WHERE acc_no = ?";
            PreparedStatement ps = con.prepareStatement(updateSQL);
            ps.setDouble(1, amount);
            ps.setInt(2, acc_no);
            
            int count = ps.executeUpdate();
            if (count > 0) {
                recordTransaction(acc_no, "Deposit", amount);
                System.out.println("Successfully deposited: " + amount);
            } else {
                System.out.println("Account Number not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Withdraw Method
    public void withdraw(double amount, int acc_no) {
        try (Connection con = DBConnection.getConnection()) {
            // Check current balance first
            String checkSQL = "SELECT balance FROM accounts WHERE acc_no = ?";
            PreparedStatement psCheck = con.prepareStatement(checkSQL);
            psCheck.setInt(1, acc_no);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                if (currentBalance >= amount) {
                    // Perform withdrawal
                    String withdrawSQL = "UPDATE accounts SET balance = balance - ? WHERE acc_no = ?";
                    PreparedStatement psUpdate = con.prepareStatement(withdrawSQL);
                    psUpdate.setDouble(1, amount);
                    psUpdate.setInt(2, acc_no);
                    psUpdate.executeUpdate();
                    
                    recordTransaction(acc_no, "Withdrawal", amount);
                    System.out.println("Successfully withdrawn: " + amount);
                } else {
                    System.out.println("Insufficient funds!");
                }
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Account Details
    public void viewAccount(int acc_no) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT c.name, a.acc_type, a.balance FROM customers c " +
                           "JOIN accounts a ON c.cust_id = a.cust_id WHERE a.acc_no = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, acc_no);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Account Details ---");
                System.out.println("Owner: " + rs.getString("name"));
                System.out.println("type: " + rs.getString("acc_type"));
                System.out.println("balance: Rs." + rs.getDouble("balance"));
            } else {
                System.out.println("Account details not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    // Helper method to log transactions
    private void recordTransaction(int accNo, String type, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO transactions(acc_no, tran_type, amount) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accNo);
        ps.setString(2, type);
        ps.setDouble(3, amount);
        ps.executeUpdate();
    }
    
}