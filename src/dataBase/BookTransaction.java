package dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookTransaction {
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
		
		//create the book tale if not already created
		public static void createTableBook() {
			int i=0;
			try {
		String sql = "CREATE TABLE IF NOT EXISTS Book" + 
				"(" + 
				"bookId INT AUTO_INCREMENT," + 
				"bookName varchar(255)," + 
				"author varchar(255)," + 
				"genre varchar(255)," + 
				"numOfCopies INT," + 
				"PRIMARY KEY(bookId)" + 
				");";
		
				PreparedStatement ps = getConnection().prepareStatement(sql);
				i = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(i == -1) {System.out.println("The result returned is "+i);}
			}
		}

		// method for add book data in database
		public static int addBook(String bookName, String author, String genre, int numOfCopies) throws Exception {
		
		createTableBook();//create table if it does not already exist.
		
		int i = 0 ;
			try {
				String sql = "INSERT INTO User (bookName, author, genre, numOfCopies)"
						+ " VALUES (?,?,?,?,?,?)";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, bookName);
				ps.setString(2, author);
				ps.setString(3, genre);
				ps.setInt(4, numOfCopies);
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

		// method to fetch data from book
		public ResultSet getAllBooks() throws SQLException, Exception {
			ResultSet rs = null;
			try {
				String sql = "SELECT * FROM Book";
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

		// method to fetch a specific piece of data by book name
		public ResultSet getOneByName(String bookName) throws SQLException, Exception {
			ResultSet rs = null;
			try {
				String sql = "SELECT * FROM Book WHERE bookName=?";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, bookName );
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
		
		// method to fetch a specific piece of data by author
				public ResultSet getOneByAuthor(String author) throws SQLException, Exception {
					ResultSet rs = null;
					try {
						String sql = "SELECT * FROM Book WHERE author=?";
						PreparedStatement ps = getConnection().prepareStatement(sql);
						ps.setString(1, author );
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
						String sql = "SELECT * FROM Book WHERE bookId=?";
						PreparedStatement ps = getConnection().prepareStatement(sql);
						ps.setInt(1, bookId );
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

		// method to update book information
		public int updateBookDetails(String bookName, String author, String genre, int numOfCopies, int bookId )
				throws SQLException, Exception {
			getConnection().setAutoCommit(false);
			int i = 0;
			try {
				String sql = "UPDATE User SET bookName=?,author=?,genre=?,numOfCopies=? WHERE bookId=?";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, bookName);
				ps.setString(2, author);
				ps.setString(3, genre);
				ps.setInt(4, numOfCopies);
				ps.setInt(5, bookId);
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

		// method to delete book by id
		public int deleteBook(int bookId) throws SQLException, Exception {
			getConnection().setAutoCommit(false);
			int i = 0;
			try {
				String sql = "DELETE FROM Book WHERE bookId=?";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, bookId);
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




