package com.jdbcsample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Createtable {
public static void main(String[] args) throws SQLException, ClassNotFoundException {
	// load the drive
	Class.forName("com.mysql.cj.jdbc.Driver");
	// get the connection
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/companydb","root","Vijay@777");
	// create statement
	Statement st=con.createStatement();
	String q="delete from student where roll=1";
	// execute the query
	int a=st.executeUpdate(q);
	System.out.println(a+" row is deleted");
	// close connection 
	st.close();
	con.close();
}
}
