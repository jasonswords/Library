package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import object.User;

public class UserDatabase {
	private static String password = "toor";
	private static String username = "root";
	static ArrayList<User> user;

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

	public void createTableUser() {
		int i = 0;
		try {
			String sql = "	CREATE TABLE IF NOT EXISTS User " 
					+ "( " 
					+ "userId INT AUTO_INCREMENT NOT NULL,"
					+ "firstName VARCHAR(255),"
					+ "surName VARCHAR(255)," 
					+ "address1 VARCHAR(255), "
					+ "address2 VARCHAR(255)," 
					+ "address3 VARCHAR(255)," 
					+ "phone VARCHAR(255),"
					+ " PRIMARY KEY (userId)"
					+ ");";

			PreparedStatement ps = getConnection().prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	// method for add user data in database
	public int addUser(String firstName, String surName, String address1, String address2, String address3,
			String phone) throws Exception {
		this.createTableUser();// create table if it does not already exist.

		int i = 0;
		try {
			String sql = "INSERT INTO User (firstName, surName, address1, address2, address3, phone)"
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, surName);
			ps.setString(3, address1);
			ps.setString(4, address2);
			ps.setString(5, address3);
			ps.setString(6, phone);
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

	// method to fetch data from tables
	public ArrayList<User> getAllUsers() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM User";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			user = new ArrayList<User>();
			while (rs.next()) {
				user.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch a specific piece of data by firstName
	public ArrayList<User> getOneByName(String firstName) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM User WHERE firstName=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, firstName);
			rs = ps.executeQuery();
			user = new ArrayList<User>();
			while (rs.next()) {
				user.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	public ArrayList<User> getOneBySurName(String surName) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM User WHERE surName=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, surName);
			rs = ps.executeQuery();
			user = new ArrayList<User>();
			while (rs.next()) {
				user.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	public ArrayList<User> getOneById(int userId) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM User WHERE userId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			user = new ArrayList<User>();
			while (rs.next()) {
				user.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to update user information
	public int updateUserDetails(String firstName, String surName, String address1, String address2, String address3,
			String phone, int userId) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			String sql = "UPDATE User SET firstName=?,surName=?,address1=?,address2=?, address3=?, phone=? WHERE userId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, surName);
			ps.setString(3, address1);
			ps.setString(4, address2);
			ps.setString(5, address3);
			ps.setString(6, phone);
			ps.setInt(7, userId);
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

	// method to delete user by his id
	public int deleteUserDetails(int userId) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			String sql = "DELETE FROM User WHERE userId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
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
