package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import object.Book;

public class BookDatabase {
	private static String password = "toor";
	private static String username = "root";

	ArrayList<Book> book;
	Book b;

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

	// create the book tale if not already created
	public int createTableBook() throws SQLException, Exception {
		int i = 0;
		try {
			String sql = "CREATE TABLE IF NOT EXISTS Book"
					+ "(" 
					+ "bookId INT AUTO_INCREMENT NOT NULL,"
					+ "bookName varchar(255)," 
					+ "author varchar(255)," 
					+ "genre varchar(255),"
					+ "numOfCopies INT,"
					+ "PRIMARY KEY(bookId)" 
					+ ");";

			PreparedStatement ps = getConnection().prepareStatement(sql);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method for add book data in database
	public int addBook(String bookName, String author, String genre, int numOfCopies) throws Exception {
		this.createTableBook();// create table if it does not already exist.
		int i = 0;
		try {
			String sql = "INSERT INTO Book (bookName, author, genre, numOfCopies)" + " VALUES (?,?,?,?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, bookName);
			ps.setString(2, author);
			ps.setString(3, genre);
			ps.setInt(4, numOfCopies);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// ADD BOOK OBJECT TO DATABASE-- METHOD TAKES IN BOOK OBJECT
	public int addBook(Book b) throws Exception {
		String bookName = b.getBookName();
		String author = b.getAuthor();
		String genre = b.getGenre();
		int numOfCopies = b.getNumOfCopies();
		// CREATE TABLE IF NOT ALREADY CREATED
		this.createTableBook();
		int i = 0;
		try {
			String sql = "INSERT INTO Book (bookName, author, genre, numOfCopies)" + " VALUES (?,?,?,?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, bookName);
			ps.setString(2, author);
			ps.setString(3, genre);
			ps.setInt(4, numOfCopies);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to fetch data from book
	public ArrayList<Book> getAllBooks() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Book";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			book = new ArrayList<>();
			while (rs.next()) {
				book.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			return book;
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
	public ArrayList<Book> getOneByName(String key) throws SQLException, Exception {
		String bookName = "%" + key + "%";
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Book WHERE bookName LIKE ?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, bookName);
			rs = ps.executeQuery();
			book = new ArrayList<>();
			while (rs.next()) {
				book.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			return book;
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
	public ArrayList<Book> getOneByAuthor(String key) throws SQLException, Exception {
		String author = "%" + key + "%";
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Book WHERE author LIKE ?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, author);
			rs = ps.executeQuery();
			book = new ArrayList<>();
			while (rs.next()) {
				book.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			return book;
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
	public Book getOneByBookId(int bookId) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Book WHERE bookId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			b = new Book();
			while (rs.next()) {
				b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}
	
	

	public Book getOneBookByBookId(int bookId) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Book WHERE bookId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();

			while (rs.next()) {
				b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}
			return b;
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
	public int updateBookDetails(String bookName, String author, String genre, int numOfCopies, int bookId)
			throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			String sql = "UPDATE Book SET bookName=?,author=?,genre=?,numOfCopies=? WHERE bookId=?";
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
			return 1;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method to update book information
	public int updateBookDetails(Book b) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			String sql = "UPDATE Book SET bookName=?,author=?,genre=?,numOfCopies=? WHERE bookId=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());
			ps.setString(3, b.getGenre());
			ps.setInt(4, b.getNumOfCopies());
			ps.setInt(5, b.getBookId());
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
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
			return -1;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// SEARCH BOOK TABLE
	public ArrayList<Book> searchBooks(String key) throws SQLException, Exception {
		String key1 = "%" + key + "%";
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Book WHERE bookName LIKE ?" + "OR author LIKE ?" + "OR genre LIKE ?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, key1);
			ps.setString(2, key1);
			ps.setString(3, key1);
			rs = ps.executeQuery();
			book = new ArrayList<>();
			while (rs.next()) {
				book.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
			return book;
		} catch (Exception e) {
			System.out.println("were in the catch");
			;
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}
	
	// method to search table for specific book ids
		public boolean searchIfIdExists(int bookId) throws SQLException, Exception {
			ResultSet rs = null;
			try {
				String sql = "SELECT * FROM Book WHERE bookId=?";
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, bookId);
				rs = ps.executeQuery();
				b = new Book();
				while (rs.next()) {
					b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				}
				if(b != null) {
					return true;
				}
				else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (getConnection() != null) {
					getConnection().close();
				}
			}
		}
}
