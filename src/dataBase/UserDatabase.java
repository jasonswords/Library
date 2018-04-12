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
	User u;

	// CREATE CONNECTION TO DATABASE
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

	// CREATE TABLE IN DATABASE FOR USER OBJECTS
	public void createTableUser() throws SQLException, Exception {
		try {
			String sql = "	CREATE TABLE IF NOT EXISTS User " 
					+ "( " + "userId INT AUTO_INCREMENT,"
					+ "firstName VARCHAR(255)," 
					+ "surName VARCHAR(255)," 
					+ "address VARCHAR(255), "
					+ "phone VARCHAR(255),"
					+ "userName varchar(255),"
					+ "password varchar(255)," 
					+ "privilege INT,"
					+ "PRIMARY KEY (userId)" 
					+ ");";

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// ADD USER OBJECTS TO DATABASE -- RETURN INT FOR SUCCESS/FAILURE
	public int addUser(String firstName, String surName, String address, String phone, String userName, String password,
			int privilege) throws Exception {
		this.createTableUser();// create table if it does not already exist.

		int i = 0;
		try {
			String sql = "INSERT INTO User (firstName, surName, address, phone, userName, password, privilege)"
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, surName);
			ps.setString(3, address);
			ps.setString(4, phone);
			ps.setString(5, userName);
			ps.setString(6, password);
			ps.setInt(7, privilege);
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
	
	// ADD USER OBJECTS TO DATABASE -- RETURN INT FOR SUCCESS/FAILURE
		public int addUser(User u) throws Exception {
			this.createTableUser();// create table if it does not already exist.

			int i = 0;
			try {
				String sql = "INSERT INTO User (firstName, surName, address, phone, userName, password, privilege)"
						+ " VALUES (?,?,?,?,?,?,?)";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, u.getFirstName());
				ps.setString(2, u.getSurName());
				ps.setString(3, u.getAddress());
				ps.setString(4, u.getPhone());
				ps.setString(5, u.getUsername());
				ps.setString(6, u.getPassword());
				ps.setInt(7, u.getPrivilege());
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

	// RETURN ARRAYLIST OF ALL USER OBJECTS IN DATABASE
	public ArrayList<User> getAllUsers() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM User";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			user = new ArrayList<User>();
			while (rs.next()) {
				user.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8)));
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

	// ????????
	// RETURN ARRAYLIST OF USER OBJECTS WHERE FIRSTNAME IS
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
						rs.getString(6), rs.getString(7), rs.getInt(8)));
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

	public User getUserByFirstName(String firstName) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM User WHERE firstName=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, firstName);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8)));
			}
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// RETURN ARRAYLIST OF USER OBJECTS WHERE SURNAME IS
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
						rs.getString(6), rs.getString(7), rs.getInt(8)));
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

	// RETURN ARRAYLIST OF USER OBJECTS WHERE USERID IS
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
						rs.getString(6), rs.getString(7), rs.getInt(8)));
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

	// RETURN
	public User getOneUserById(int userId) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM User WHERE userId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
			}
			return u;
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
	public int updateUserDetails(String firstName, String surName, String address, String phone, String userName, String password, int privilege, int userId) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			String sql = "UPDATE User SET firstName=?,surName=?,address=?, phone=?, userName=?, password=?, privilege=? WHERE userId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, surName);
			ps.setString(3, address);
			ps.setString(4, phone);
			ps.setString(5, userName);
			ps.setString(6, password);
			ps.setInt(7, privilege);
			ps.setInt(8, userId);
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

	// RETRUN ARRAYLIST OF LOGIN OBJECTS WHERE PASSWORD IS GIVEN
	public User getOneByUserName(String username) throws SQLException, Exception {
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM User WHERE userName=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
			}
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	public User getOneByPassword(String password) throws SQLException, Exception {
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM User WHERE password=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
			}
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// MEHTOD TO CHECK IF PASSWORD IS VALID
	public boolean isUserNameValid(String user) throws Exception {

		u = this.getOneByUserName(user);

		if (u != null) {
			return true;
		}

		return false;
	}

}
