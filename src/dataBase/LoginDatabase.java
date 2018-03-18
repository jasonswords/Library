package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import object.Login;

public class LoginDatabase {
	private static String password = "toor";
	private static String username = "root";

	static ArrayList<Login> loginTrans;

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
	public int createTableLogin() {
		int i = 0;
		try {
			String sql = "CREATE TABLE IF NOT EXISTS Login" 
					+ "("
					+ "loginId  INT AUTO_INCREMENT,"
					+ "userName varchar(255),"
					+ "password varchar(255),"
					+ "privilege INT,"
					+ " PRIMARY KEY(loginId)"
					+ ");";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} 
	}

	// method for add login details to login table
	public int addLogin(String username, String password, int privilege) throws Exception {
		this.createTableLogin();// create table if it does not already exist.
		int i = 0;
		try {
			String sql = "INSERT INTO Login (username, password, privilege)" + " VALUES (?,?,?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, privilege);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch data from login table
	public ArrayList<Login> getAllLogin() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Login";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			loginTrans = null;
			loginTrans = new ArrayList<Login>();
			while (rs.next()) {
				loginTrans.add(new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return loginTrans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch a specific piece of data by login id
	public ArrayList<Login> getOneByLoginId(int loginId) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Login WHERE loginId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, loginId);
			rs = ps.executeQuery();
			loginTrans = null;
			loginTrans = new ArrayList<Login>();
			while (rs.next()) {
				loginTrans.add(new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return loginTrans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch a specific piece of data by user name
	public ArrayList<Login> getOneByPrivilege(int privilege) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Login WHERE privilege=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, privilege);
			rs = ps.executeQuery();
			rs = ps.executeQuery();
			loginTrans = null;
			loginTrans = new ArrayList<Login>();
			while (rs.next()) {
				loginTrans.add(new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return loginTrans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch a specific piece of data by user name
	public ArrayList<Login> getOneByUsername(String username) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Login WHERE username=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			rs = ps.executeQuery();
			loginTrans = null;
			loginTrans = new ArrayList<Login>();
			while (rs.next()) {
				loginTrans.add(new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return loginTrans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch a specific piece of data by user name
	public ArrayList<Login> getOneByPassword(String password) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Login WHERE password=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, password);
			rs = ps.executeQuery();
			loginTrans = null;
			loginTrans = new ArrayList<Login>();
			while (rs.next()) {
				loginTrans.add(new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return loginTrans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to update Login information
	public int updateLoginDetailsById(String username, String password, int privilege, int loginId)
			throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			String sql = "UPDATE Login SET username=?,password=?, privilege=? WHERE loginId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, privilege);
			ps.setInt(4, loginId);

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

	// method to delete login by id
	public int deleteLoginDetails(int loginId) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			String sql = "DELETE FROM Login WHERE loginId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, loginId);
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

	public boolean isPasswordValid(String passwd) throws Exception {
		ArrayList<Login> log = new ArrayList<Login>();
		log = this.getOneByPassword(passwd);
		for (Login l : log) {
			if (l.getPassword().equals(passwd)) {
				return true;
			}
		}
		return false;
	}

}
