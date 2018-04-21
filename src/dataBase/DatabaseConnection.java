package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import object.BookReservation;

public class DatabaseConnection {
	private static String password = "toor";
	private static String username = "root";

	ArrayList<BookReservation> bookRes;

	// method for create connection
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library?autoReconnect=true&useSSL=false",
					username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
