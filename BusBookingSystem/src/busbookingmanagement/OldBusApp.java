package busbookingmanagement;



import busbookingmanagement.dao.BusDAO;
import java.util.Scanner;

public class OldBusApp {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        BusDAO dao = new BusDAO();

        while(true) {

            System.out.println("------ Welcome to Bus Booking ------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:
                    System.out.println("Enter Name:");
                    String name = sc.nextLine();

                    System.out.println("Enter Email:");
                    String email = sc.nextLine();

                    System.out.println("Enter Phone:");
                    String phone = sc.nextLine();

                    System.out.println("Enter Password:");
                    String pass = sc.nextLine();

                    dao.createCustAcc(name, email, phone, pass);
                    break;

                case 2:
                    System.out.println("Enter Email:");
                    String logMail = sc.nextLine();

                    System.out.println("Enter Password:");
                    String logPass = sc.nextLine();

                    if(dao.userLogin1(logMail, logPass)) {

                        System.out.println("Login Successful!");

                        //After login menu
                        System.out.println("Enter Your Customer ID:");
                        int custId = sc.nextInt();
                        sc.nextLine();

                        while(true) {

                            System.out.println("1. Book Ticket");
                            System.out.println("2. Cancel Ticket");
                            System.out.println("4. View Your Tickets");
                            System.out.println("3. Logout");

                            int userChoice = sc.nextInt();
                            sc.nextLine();

                            if(userChoice == 1) {

                                System.out.println("Enter Source:");
                                String source = sc.nextLine();

                                System.out.println("Enter Destination:");
                                String dest = sc.nextLine();
                                boolean available=dao.busCheck(source, dest);
                                
                                if(available) {
                                	System.out.println("Enter BusID:");
                                    int busID = sc.nextInt();
                                    sc.nextLine();
                                    dao.bookTickets1(custId, busID, source, dest);
                                }else {
                                	System.out.println("Sorry cannot proceed!");
                                }

                            } 
                            else if(userChoice == 2) {

                                System.out.println("Enter Booking ID:");
                                int bookingID = sc.nextInt();
                                sc.nextLine();

                                dao.cancelTicket(bookingID, custId);

                            } 
                            else if(userChoice == 3) {
                                System.out.println("Logged out!");
                                break;
                            }
                            else if(userChoice==4) {
                            	dao.showYourTickets(custId);
                            }
                            else {
                                System.out.println("Invalid Option!");
                            }
                        }

                    } else {
                        System.out.println("Invalid Login!");
                    }
                    break;

                case 3:
                    System.out.println("Thank You for using GBUS!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}