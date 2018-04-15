package testers;

import java.sql.SQLException;
import java.util.ArrayList;

import dataBase.BookDatabase;
import dataBase.LoanDatabase;
import dataBase.UserDatabase;
import object.Book;
import object.Loan;
import object.User;

public class GUICommunication {

	static int isLoggedIn = 1;
	static int privilege;
	static UserDatabase ud;
	static BookDatabase bd;
	static LoanDatabase ld;
	static ArrayList<Book> book;
	static ArrayList<User> user;
	static ArrayList<Loan> l;
	static User u;
	static Book b;

	public static int getLoggedIn() {
		return isLoggedIn;
	}

	public static void logOut() {
		isLoggedIn = 0;
	}

	// VALIDATE LOGIN DETAILS
	public static int validatePassword(String username, char[] pass) throws Exception {
		String password = String.valueOf(pass);
		if (username != null && pass != null) {
			ud = new UserDatabase();
			boolean bool = ud.isUserNameValid(username);
			if (bool) {
				u = new User();
				u = ud.getOneByUserName(username);
				if (password.equals(u.getPassword())) {
					isLoggedIn = u.getUserId();
					privilege = u.getPrivilege();
					return u.getPrivilege();
				}
			}
		}
		return -1;
	}

	////////////////////////////////////////////////////////////////////////// BOOKS
	////////////////////////////////////////////////////////////////////////// ///////////////////////////

	// ADD BOOK OBJECT TO DATABASE
	public static int addBookToDatabase(Book b) throws Exception {
		BookDatabase bd = new BookDatabase();
		int x = bd.addBook(b);
		return x;
	}

	// VALIDATE ARRAY BEFORE ADDING TO DATABASE
	public static int addBook(String[] array) throws Exception {
		int numOfCopies = 0;
		try {
			numOfCopies = Integer.parseInt(array[array.length - 1]);
			if (isNullValues(array)) {
				b = new Book(array[0], array[1], array[2], numOfCopies);
				int c = addBookToDatabase(b);
				if (c == -1) {
					return 0;
				} else
					return -1;
			} else
				return -1;

		} catch (Exception e) {
			return -1;
		}
	}

	// RETURN ARRAY OF BOOK BOOKIDS
	public static int[] getBookIndexes(ArrayList<Loan> l) {
		int[] index = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			index[i] = l.get(i).getBookId();
		}
		return index;
	}

	// RETURN ARRAYLIST OF BOOKS ON LOAN TABLE
	public static ArrayList<Book> getBookFromLoan() throws SQLException, Exception {
		ld = new LoanDatabase();
		bd = new BookDatabase();
		book = new ArrayList<>();
		l = new ArrayList<>();
		l = ld.getAllLoansByuserId(isLoggedIn);
		int[] index = getBookIndexes(l);
		for (int i = 0; i < index.length; i++) {
			book.add(bd.getOneByBookId(index[i]));
		}
		return book;
	}

	// SEARCH BOOK TABLE FOR KEY VALUE
	public static ArrayList<Book> searchMethod(String key) throws SQLException, Exception {
		b = new Book();
		book = new ArrayList<>();
		bd = new BookDatabase();
		book = bd.searchBooks(key);
		if (!book.isEmpty()) {
			return book;
		}
		return book;
	}

	// VALIDATE INFORMATION BEFORE ASKING IF THEY WANT TO CONTINUE
	public static int editBook(String[] array) throws SQLException, Exception {
		int id = Integer.parseInt(array[0]), numberOfCopies = 0;
		if (isStringValid(array[array.length - 1])) {
			if (isNumberOfCopiesValid(numberOfCopies = Integer.parseInt(array[array.length - 1]))) {
				if (isNullValues(array)) {
					bd = new BookDatabase();
					b = new Book(id, array[1], array[2], array[3], numberOfCopies);
					int v = bd.updateBookDetails(b);
					if (v == 1) {
						return 0;
					} else
						return -1;
				} else// end if no null values
					return -1;
			} else
				return -1;
		} else// end if is string valid
			return -1;
	}

	// DELETE BOOK FROM DATABASE TABLE
	public static int deleteBook(String s) throws SQLException, Exception {
		if (isStringValid(s)) {
			if (isBookIdVallid(s)) {
				int id = Integer.parseInt(s);
				bd = new BookDatabase();
				bd.deleteBook(id);
				return 0;
			} else
				return -1;
		} else
			return -1;
	}

	///////////////////////////////////////////////////////////////////////////////// USERS
	///////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////

	// //ADD NEW USER TO DATABASE TABLE
	// public static int addUsers(String[] array) throws Exception {
	// int privilege = 0,x = 0;
	// try {// try parse string to integer
	// privilege = Integer.parseInt(array[array.length - 1]);
	// if (privilege == 1 || privilege == 2) {// check range of privilege value
	// if (eliminateNullValues(array)) {
	// ud = new UserDatabase();
	// u = new User(array[0], array[1], array[2], array[3], array[4], array[5],
	// privilege);
	// x = ud.addUser(u);
	// // check added to database ok
	// if (x == 1) {
	// return 0;// all ok
	// } else
	// return -1;// problem adding to database
	// } else {
	// return -1;// null value found
	// }
	// } else {
	// return -1;// value out of range
	// }
	// } catch (Exception xx) {
	// return -1;// invalid value
	// }
	// }

	// ADD NEW USER TO DATABASE TABLE
	public static int addUsers(String[] array) throws Exception {
		int privilege = 0;
		if (isStringValid(array[array.length - 1])) {
			if (validatePrivilege(Integer.parseInt(array[array.length - 1]))) {
				if (isNullValues(array)) {
					privilege = Integer.parseInt(array[array.length - 1]);
					ud = new UserDatabase();
					u = new User(array[0], array[1], array[2], array[3], array[4], array[5], privilege);
					if ((ud.addUser(u)) == 1) {
						return 0;// all ok
					} else
						return -4;// problem adding to database
				} else
					return -3;
			} else
				return -2;
		}
		return -1;
	}

	// EDIT USER DETAILS
	public static int editUser(String[] array) throws Exception {
		if (isStringValid(array[array.length - 1])) {
			if (validatePrivilege(Integer.parseInt(array[array.length - 1]))) {
				if (isNullValues(array)) {
					return 0;
				}else 
					return -1;
			}else
				return -2;
		}
		return -3;
	}

	// WRITE EDITED CHANGES TO THE DATABASE
	public static int proceedWithEdit(String[] array) throws SQLException, Exception {
		int userId = Integer.parseInt(array[0]), privilege = Integer.parseInt(array[array.length - 1]);
		ud = new UserDatabase();
		int x = ud.updateUserDetails(array[1], array[2], array[3], array[4], array[5], array[6], privilege, userId);
		return x;
	}

	// VALIADTE USER ID
	public static boolean validateUserIdString(String s) throws SQLException, Exception {
		if (isStringValid(s)) {
			ud = new UserDatabase();
			if ((ud.getOneUserById(Integer.parseInt(s))) == null) {
				return false;
			} else
				return true;
		} else
			return false;

	}

	///////////////////////////////////////////////////////////////////////////////////////// GENERAL
	///////////////////////////////////////////////////////////////////////////////////////// METHODS
	///////////////////////////////////////////////////////////////////////////////////////// /////////////////////

	// VALIADE ARRAY TO ELIMINATE NULL VALUES
	public static boolean isNullValues(String[] array) {
		for (String x : array) {
			System.out.println(x);
			if (x == null || (x != null && ("".equals(x)))) {
				return false;
			}
		}
		return true;
	}

	// VALIADATE PRIVILEGE UPPER AND LOWER BOUNDS
	public static boolean validatePrivilege(int n) {
		if (n == 1 || n == 2) {
			return true;
		} else
			return false;
	}

	// VALIADTE INTEGER
	public static boolean isStringValid(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// VALIADTE IF BOOK EXISTS IN DATABASE
	public static boolean isBookIdVallid(String s) throws SQLException, Exception {
		if (isStringValid(s)) {
			int n = Integer.parseInt(s);
			book = new ArrayList<>();
			bd = new BookDatabase();
			book = bd.getAllBooks();
			for (Book b : book) {
				if (b.getBookId() == n) {
					return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	// VALIDATE IF NUMBER OF COPIES IS WITHIN THE UPPER AND LOWER BOUNDS
	public static boolean isNumberOfCopiesValid(int n) {
		if (n > 0 && n < 100) {
			return true;
		} else
			return false;
	}
}
