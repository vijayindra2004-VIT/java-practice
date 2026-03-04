import java.util.Scanner;

class PayrollCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Hours Worked: ");
        double hoursWorked = sc.nextDouble();

        System.out.print("Enter Hourly Wage: ");
        double hourlyWage = sc.nextDouble();

        double grossPay = hoursWorked * hourlyWage;
        double taxDeduction = grossPay * 0.10;   
        double netPay = grossPay - taxDeduction;

        System.out.println("\n----- Payroll Details -----");
        System.out.println("Employee Name: " + name);
        System.out.println("Gross Pay: " + grossPay);
        System.out.println("Tax Deduction (10%): " + taxDeduction);
        System.out.println("Net Pay: " + netPay);

        sc.close();
    }
}