/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package bank;

/**
 *
 * @author guruv
 */


import java.util.*;

public class Bank {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        BankDAO bankOps= new BankDAO();
        System.out.println("===Welcome To Secure Bank Services===");
        
        while(true){
            System.out.println("1.Deposite");
            System.out.println("2.Withdraw money");
            System.out.println("3.View and Exit");
            int choice = sc.nextInt();
            
            switch(choice){
                case 1:
                    System.out.println("ENter account number");
                    int accNo = sc.nextInt();
                    System.out.println("Enter tamount to deposite");
                    double amount = sc.nextDouble();
                    
                    bankOps.deposit(amount,accNo); 
                    bankOps.viewAccount(accNo);
                    break;
                    
                case 2:
                    System.out.println("Enter account number");
                    int accNo2 = sc.nextInt();
                    System.out.println("Enter the amount to withdraw");
                    double amount2 = sc.nextDouble();
                    bankOps.withdraw(amount2, accNo2);
                    System.out.println("Withdrawl");
                    bankOps.viewAccount(accNo2);
                    break;
                    
                case 3:
                    System.out.println("Enter the account number:");
                    int accNo3 = sc.nextInt();
                    bankOps.viewAccount(accNo3);
                    System.out.println("Thank you for using the banking servises");
                    sc.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid entry please try again");
            }
        }
    }
    
}