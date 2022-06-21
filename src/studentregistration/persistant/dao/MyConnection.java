package studentregistration.persistant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	static Connection con = null;
	
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/student_registration","root","root");
			System.out.println("Connected to database");
		} catch (ClassNotFoundException e) {			
			System.out.println("Class not found.");
		} catch (SQLException e) {
			System.out.println("Database not found");
		}
		return con;
	}	
	
}
