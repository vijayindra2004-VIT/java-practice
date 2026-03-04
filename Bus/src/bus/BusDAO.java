package bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusDAO {
	public void createCustAcc(String name,String mail,String phNo,String pass) throws ClassNotFoundException, SQLException {
		try(Connection con = BusJDBC.getConnection()){
			String q1= "insert into BusCostomers (Name,Email,PhoneNo,Password) VALUES(?,?,?,?)";
			PreparedStatement ps1 = con.prepareStatement(q1,PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps1.setString(1, name);
			ps1.setString(2, mail);
			ps1.setString(3, phNo);
			ps1.setString(4, pass);
			
			int rows= ps1.executeUpdate();
			if(rows>0) {
				ResultSet rs = ps1.getGeneratedKeys();
				if(rs.next()) {
					int custID=rs.getInt(1);
					System.out.println("Account created! with CustomerID: "+custID);
				}
				
			}			
		}catch(SQLException e) {
			if(e.getErrorCode()==1062) {
				System.out.println("Account creation failed! Email already exists");
			}else {
				e.printStackTrace();
			}
			
		}
	}
	
	public boolean userLogin(String mail,String pass) throws ClassNotFoundException, SQLException {
		try(Connection con = BusJDBC.getConnection()){
			String q2= "Select * from BusCostomers where Email=? AND Password=?";
			PreparedStatement ps2 = con.prepareStatement(q2);
			ps2.setString(1, mail);
			ps2.setString(2, pass);
			
			ResultSet rs = ps2.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}
	}
	public void bookTickets(int custId,int busID,String source,String dest) throws ClassNotFoundException {

	    try(Connection con = BusJDBC.getConnection()) {

	        String q3 = "INSERT INTO Bookings (CustId,BusID,SourcePlace,Destination,PayStatus) VALUES(?,?,?,?,?)";

	        PreparedStatement ps3 = con.prepareStatement(q3,
	                PreparedStatement.RETURN_GENERATED_KEYS);

	        ps3.setInt(1, custId);
	        ps3.setInt(2, busID);
	        ps3.setString(3, source);
	        ps3.setString(4, dest);
	        ps3.setString(5, "Not Paid");

	        int rows = ps3.executeUpdate();

	        if(rows > 0) {

	            ResultSet rs = ps3.getGeneratedKeys();

	            if(rs.next()) {
	                int bookingID = rs.getInt(1);

	                System.out.println("Ticket Booked Successfully!");
	                System.out.println("Your Booking ID: " + bookingID);

	                showBookingDetails(bookingID, con);
	            }
	        }

	    } catch(SQLException e) {
	        e.printStackTrace();
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
	
	public void showYourTickets(int custid) throws SQLException, ClassNotFoundException {
		try(Connection con = BusJDBC.getConnection()){
			String query = "Select * from Bookings where CustID=?";
			PreparedStatement psq = con.prepareStatement(query);
			psq.setInt(1, custid);
			
			ResultSet rs = psq.executeQuery();
			boolean f = false;
			System.out.println("CustID | BusID | Source | Destination | Payment");
			while(rs.next()) {
				f=true;
	
				int busNum=rs.getInt("BusID");
				String sourceplace = rs.getString("SourcePlace");
				String destin=rs.getString("Destination");
				String paystat="Not Paid";
				System.out.printf("%-2d | %-2d  | %-5s | %-5s | %-5s%n",custid,busNum,sourceplace,destin,paystat);
			}
			if(!f) {
				System.out.println("You haven't Booked yet ");
			}
		}
	}
	
	public void cancelTicket(int bookingID, int custID)
	        throws ClassNotFoundException {

	    try(Connection con = BusJDBC.getConnection()) {

	        String q = "DELETE FROM Bookings WHERE BookID = ? AND CustID = ?";

	        PreparedStatement ps = con.prepareStatement(q);

	        ps.setInt(1, bookingID);
	        ps.setInt(2, custID);

	        int rows = ps.executeUpdate();

	        if(rows > 0) {
	            System.out.println("Ticket Cancelled Successfully!");
	        } else {
	            System.out.println("Invalid Booking ID!");
	        }

	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean busCheck(String source,String dest) throws ClassNotFoundException, SQLException {
		try(Connection con = BusJDBC.getConnection()){
			String qx = "SELECT * FROM BusDetails where Source=? and Destination=?";
			PreparedStatement psx = con.prepareStatement(qx);
			psx.setString(1, source);
			psx.setString(2, dest);
			
			boolean found = false;
			
			ResultSet rsx = psx.executeQuery();
			
			System.out.println("BusID | BusNo | BusName | Source | Destination");
			while(rsx.next()) {
				found = true;
				
				int busid = rsx.getInt("BusID");
				String busNum=rsx.getString("BusNo");
				String busname = rsx.getString("BusName");
				String sourceplace = rsx.getString("Source");
				String destin=rsx.getString("Destination");
				System.out.printf("%-2d | %-10s | %-5s | %-5s | %-5s ",busid,busNum,busname,sourceplace,destin);
			}
			if(!found) {
				System.out.println("Sorry! No Buses for your route");
			}
			
			return found;
			
		}
	}
}