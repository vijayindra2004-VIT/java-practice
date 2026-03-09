package busbookingmanagement.dao;

import busbookingmanagement.util.BusJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BusAdmin {
	
	private boolean adminLog(String mail,String pass) throws ClassNotFoundException, SQLException {
		try(Connection con = BusJDBC.getConnection()){
			String q3= "SELECT * from BusAdmin where Email=? AND Password=?";
			PreparedStatement ps3 = con.prepareStatement(q3);
			ps3.setString(1, mail);
			ps3.setString(2, pass);
			
			ResultSet rs = ps3.executeQuery();
			
			if(rs.next()) { //instead of this if else simply return rs.next()
				return true;
			}else {
				return false;
			}
		}
	}
	
	
	public void addBus(String bname,String busno,String source,String dest) throws ClassNotFoundException, SQLException {
		try(Connection con = BusJDBC.getConnection()){
			String q1= "INSERT INTO BusDetails (BusName,BusNo,Source,Destination) VALUES(?,?,?,?)";
			PreparedStatement ps1 = con.prepareStatement(q1);
			ps1.setString(1, bname);
			ps1.setString(2, busno);
			ps1.setString(3, source);
			ps1.setString(4, dest);
			
			ps1.executeUpdate();
			
			System.out.println("Bust Added");
			
		}catch(SQLException e) {
			System.out.println("Bus with this RegisterNumber already exists!");
			e.printStackTrace();
		}
	}
	public void delBus(String busno) throws ClassNotFoundException, SQLException {
		try(Connection con = BusJDBC.getConnection()){
			String q2= "DELETE FROM BusDetails where BusNo=?";
			PreparedStatement ps2 = con.prepareStatement(q2);
			
			ps2.setString(1, busno);
			
			int rows = ps2.executeUpdate();

			if(rows > 0) {
			    System.out.println("Bus Deleted");
			} else {
			    System.out.println("Bus with this Register no does not exist!");
			}
			
		}
	}
	public void showBookingDetails(int bookingID, Connection con) throws SQLException {

	    String query = "SELECT b.BookID, c.Name, d.BusName, d.BusNo, " +
	                   "b.SourcePlace, b.Destination, b.PayStatus " +
	                   "FROM Bookings b " +
	                   "JOIN BusCostomers c ON b.CustID = c.CustID " +
	                   "JOIN BusDetails d ON b.BusID = d.BusID " +
	                   "WHERE b.BookID = ?";

	    PreparedStatement ps = con.prepareStatement(query);
	    ps.setInt(1, bookingID);

	    ResultSet rs = ps.executeQuery();

	    if(rs.next()) {

	        System.out.println("------ Booking Details ------");
	        System.out.println("Booking ID : " + rs.getInt("BookID"));
	        System.out.println("Customer   : " + rs.getString("Name"));
	        System.out.println("Bus Name   : " + rs.getString("BusName"));
	        System.out.println("Bus No     : " + rs.getString("BusNo"));
	        System.out.println("From       : " + rs.getString("SourcePlace"));
	        System.out.println("To         : " + rs.getString("Destination"));
	        System.out.println("Payment    : " + rs.getString("PayStatus"));
	    }
	}
        
        public void updateBookings(String source,String dest,double fare) throws ClassNotFoundException, SQLException{
            try(Connection con = BusJDBC.getConnection()){
                String upq = "update Bookings set fare=? where SourcePlace=? and Destination=?";
                PreparedStatement ups = con.prepareStatement(upq);
                ups.setDouble(1,fare);
                ups.setString(2, source);
                ups.setString(3, dest);
                
                int rows = ups.executeUpdate();
                if(rows>0){
                    System.out.println(rows +"Updated");
                            
                }else{
                    System.out.println("Something wrong");
                }
            }
        }
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		BusAdmin badmin = new BusAdmin();
		
		System.out.println("----Welcome to Admin Login----");
		System.out.println("Enter Email:");
		String mail = sc.nextLine();
		System.out.println("Enter the Password");
		String pass = sc.nextLine();
		
		
		if(badmin.adminLog(mail, pass)) {
			System.out.printf("Enter %n1.Add new Bus %n2.Delete Bus Data%n");
			int ch = sc.nextInt();
			sc.nextLine();
			if(ch==1) {
				System.out.println("To Add Bus details");
				System.out.println("Enter the Bus name(ex: Sugama).");
				String bname = sc.nextLine();
				System.out.println("Enter the Bus Registration number (ex:KA06HP2285)");
				String bNo1 = sc.nextLine();
				System.out.println("enter source palce");
				String source= sc.nextLine();
				System.out.println("Enter Destination");
				String dest = sc.nextLine();
				
				badmin.addBus(bname, bNo1,source,dest);
				
			}else if(ch==2) {
				System.out.println("To Delete Bus details");
				System.out.println("Enter the Bus Registration number (ex:KA06HP2285)");
				String bNo2 = sc.nextLine();
				badmin.delBus(bNo2);

			}else {
				System.out.println("Invalid!");
			}
		}else {
			System.out.println("Ivalid Admin Credentials Try again!");
		}
		
		sc.close();

	}

}