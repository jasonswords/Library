package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanTransaction {
	private static String password = "toor";
	private static String username = "root";

		
		// method for create connection
		public static Connection getConnection() throws Exception {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Library?autoReconnect=true&useSSL=false", username, password);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public static void createTableLoan() {
			int i=0;
			try {
		String sql = "CREATE TABLE IF NOT EXISTS Loan" + 
				"		(" + 
				"		loanId INT AUTO_INCREMENT," + 
				"		bookId INT," + 
				"		userId INT," + 
				"		date DATE," + 
				"		PRIMARY KEY (loanId)" + 
				"		);";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				i = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(i == -1) {System.out.println("The result returned is "+i);}
			}
		}

		// method for add user data in database
		public static int loanBook(int bookId, int userId) throws Exception {
		
		createTableLoan();//create table if it does not already exist.
		
		int i = 0 ;
			try {
				String sql = "INSERT INTO Loan (bookId, userId)"
						+ " VALUES (?,?)";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, bookId);
				ps.setInt(2, userId);
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

		// method to fetch data from loan table
		public ResultSet getAllLoans() throws SQLException, Exception {
			ResultSet rs = null;
			try {
				String sql = "SELECT * FROM Loan";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				rs = ps.executeQuery();
				return rs;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				if (getConnection() != null) {
					getConnection().close();
				}
			}
		}

		// method to fetch a specific piece of data by loan id
		public ResultSet getOneById(int loanId) throws SQLException, Exception {
			ResultSet rs = null;
			try {
				String sql = "SELECT * FROM Loan WHERE loanId=?";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, loanId);
				rs = ps.executeQuery();
				return rs;
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
				public ResultSet getOneByuserId(int userId) throws SQLException, Exception {
					ResultSet rs = null;
					try {
						String sql = "SELECT * FROM Loan WHERE userId=?";
						PreparedStatement ps = getConnection().prepareStatement(sql);
						ps.setInt(1, userId);
						rs = ps.executeQuery();
						return rs;
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
				public ResultSet getOneByBookId(int bookId) throws SQLException, Exception {
					ResultSet rs = null;
					try {
						String sql = "SELECT * FROM Loan WHERE bookId=?";
						PreparedStatement ps = getConnection().prepareStatement(sql);
						ps.setInt(1, bookId);
						rs = ps.executeQuery();
						return rs;
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					} finally {
						if (getConnection() != null) {
							getConnection().close();
						}
					}
				}

		// method to update loan information
		public int updateLoan(int bookId, int userId, int loanId)
				throws SQLException, Exception {
			getConnection().setAutoCommit(false);
			int i = 0;
			try {
				String sql = "UPDATE Loan SET bookId=?,userId=? WHERE loanId=?";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, bookId);
				ps.setInt(2, userId);
				ps.setInt(3, loanId);
				
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

		// method to delete loan with its id
		public int deleteUserDetails(int loanId) throws SQLException, Exception {
			getConnection().setAutoCommit(false);
			int i = 0;
			try {
				String sql = "DELETE FROM Loan WHERE loanId=?";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, loanId);
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
