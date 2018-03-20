package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import object.BookReservation;

public class BookReservationDatabase {
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

	// create table if not already created
	public int createTableReservation() {
		int i = 0;
		try {
			String sql = "CREATE TABLE IF NOT EXISTS Reservation"
					+ "("
					+ "reservationId INT AUTO_INCREMENT NOT NULL,"
					+ "userId INT,"
					+ "bookId INT," 
					+ "PRIMARY KEY(reservationId)"
					+ ");";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} 
	}

	// method for add reservation to table
	public int reserveBook(int userId, int bookId) throws Exception {
		this.createTableReservation();// create table if it does not already exist.
		int i = 0;
		try {
			String sql = "INSERT INTO Reservation (bookId, userId)" + " VALUES (?,?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, bookId);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return i;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch data from reservation table
	public ArrayList<BookReservation> getAllReservations() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Reservation";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			bookRes = new ArrayList<BookReservation>();
			while(rs.next()) {
				bookRes.add(new BookReservation(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			return bookRes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch a specific piece of data by reservation id
	public ArrayList<BookReservation> getOneByReservationId(int reservationId) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Reservation WHERE reservationId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, reservationId);
			rs = ps.executeQuery();
			bookRes = new ArrayList<BookReservation>();
			while(rs.next()) {
				bookRes.add(new BookReservation(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			return bookRes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch a specific piece of data by user id
	public ArrayList<BookReservation> getOneByuserId(int userId) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Reservation WHERE userId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			bookRes = new ArrayList<BookReservation>();
			while(rs.next()) {
				bookRes.add(new BookReservation(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			return bookRes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch a specific piece of data by book id
	public ArrayList<BookReservation> getOneByBookId(int bookId) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Reservation WHERE bookId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			bookRes = new ArrayList<BookReservation>();
			while(rs.next()) {
				bookRes.add(new BookReservation(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			return bookRes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to update reservation information
	public int updateLoan(int userId, int bookId, int reservationId) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			String sql = "UPDATE Reservation SET userId=?,bookId=? WHERE reservationId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setInt(2, userId);
			ps.setInt(3, reservationId);

			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			getConnection().rollback();
			return 0;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to delete reservation by id
	public int deleteReservation(int reservationId) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			String sql = "DELETE FROM Reservation WHERE reservationId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, reservationId);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			getConnection().rollback();
			return 0;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

}
