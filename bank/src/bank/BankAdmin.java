package bank;

import java.sql.*;
import java.util.Scanner;

public class BankAdmin {
    
    // Method to create a new account in the database
    public void createAccount(int custId, String type, double balance) {
        String sqlQ = "INSERT INTO accounts(Cust_ID, Acc_Type, Balance) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlQ)) {
            
            ps.setInt(1, custId);
            ps.setString(2, type);
            ps.setDouble(3, balance);
            
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Account created successfully for Customer ID: " + custId);
            }
        } catch (Exception e) {
            System.err.println("Error creating account: " + e.getMessage());
        }
    }

    // Method to delete an account using Account Number
    public void deleteAccount(int accNo) {
        String sqlQ = "DELETE FROM accounts WHERE Acc_NO = ?";
        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlQ)){
            
            ps.setInt(1, accNo);
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Account " + accNo + " deleted successfully.");
            } else {
                System.out.println("Account Number not found.");
            }
        } catch (Exception e) {
            System.err.println("Error deleting account: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAdmin admin = new BankAdmin();
        
        System.out.println("=== Secure Bank Admin Portal ===");
        
        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int cid = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Account Type (Savings/Current): ");
                    String type = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double bal = sc.nextDouble();
                    admin.createAccount(cid, type, bal);
                    break;
                    
                case 2:
                    System.out.print("Enter Account Number to Delete: ");
                    int accNo = sc.nextInt();
                    admin.deleteAccount(accNo);
                    break;
                    
                case 3:
                    System.out.println("Exiting Admin Portal...");
                    return;

                default:
                    System.out.println("Invalid entry, please try again.");
            }
        }
    }
}