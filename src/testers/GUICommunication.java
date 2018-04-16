package testers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import dataBase.BookDatabase;
import dataBase.BookReservationDatabase;
import dataBase.LoanDatabase;
import dataBase.UserDatabase;
import object.Book;
import object.BookReservation;
import object.Loan;
import object.User;

public class GUICommunication {

	static int isLoggedIn =1;
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
		bd = new BookDatabase();
		ud = new UserDatabase();
		String password = String.valueOf(pass);
		if (username != null && pass != null) {
			if (ud.isUserNameValid(username)) {
				u = new User();
				u = ud.getOneByUserName(username);
				if (password.equals(u.getPassword())) {
					isLoggedIn = u.getUserId();
					privilege = u.getPrivilege();
				}
				return u.getPrivilege();
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
		if (isStringValid(array[array.length - 1])) {
			if (isNullValues(array)) {
				if (isNumberOfCopiesValid(Integer.parseInt(array[array.length - 1]))) {
					b = new Book(array[0], array[1], array[2], Integer.parseInt(array[array.length - 1]));
					int c = addBookToDatabase(b);
					if (c == 1) {
						return 0;
					} else
						return -1;
				} else
					return -2;
			} else
				return -3;
		} else
			return -4;
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
		if (isStringValid(array[array.length - 1])) {
			if (isNullValues(array)) {
				if (isNumberOfCopiesValid(Integer.parseInt(array[array.length - 1]))) {
					bd = new BookDatabase();
					b = new Book(Integer.parseInt(array[0]), array[1], array[2], array[3],
							Integer.parseInt(array[array.length - 1]));
					int v = bd.updateBookDetails(b);
					if (v == 1) {
						return 0;
					} else
						return -1;
				} else
					return -2;
			} else
				return -3;
		} else
			return -4;
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
				} else
					return -1;
			} else
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

	// CHECK RESERVATION TABLE FOR LATE RETURNS
	public static int[] lateBookReturn() throws SQLException, Exception {
		int[] bookIds = {};
		ArrayList<Loan> loan = new ArrayList<>();
		LoanDatabase ld = new LoanDatabase();
		loan = ld.getAllLoansByuserId(getLoggedIn());
		for (int i = 0; i < loan.size(); i++) {
			if (checkDateLimit(loan.get(i).getDate())) {
				bookIds[i] = loan.get(i).getBookId();
			}
		}
		return bookIds;
	}

	static boolean checkDateLimit(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate today = LocalDate.now();
		long days = ChronoUnit.DAYS.between(localDate, today);
		return days > 3;
	}

	public static void errorMessage(String error) {
		JOptionPane.showConfirmDialog(null, error, "Information", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}

	public static void displayMessageIfBooksAreLate() throws SQLException, Exception {
		int[] bookIds = {};
		String booksLate = "";
		bd = new BookDatabase();
		bookIds = lateBookReturn();
		if (bookIds != null) {
			for (int s : bookIds) {
				booksLate += bd.getOneByBookId(s) + "\n";
			}
			errorMessage(booksLate);
		}
	}

	public static boolean validateUnreserveBookId(String input) throws NumberFormatException, SQLException, Exception {
		if (isStringValid(input)) {
			if (isReserveIdValid(Integer.parseInt(input))) {
				return true;
			} else
				return false;
		}
		return false;
	}

	public static boolean isReserveIdValid(int n) throws SQLException, Exception {
		BookReservationDatabase brd = new BookReservationDatabase();
		ArrayList<BookReservation> br = new ArrayList<>();
		br = brd.getOneByBookId(n);
		if (!br.isEmpty()) {
			for (BookReservation b : br) {
				if (b.getBookId() == n) {
					return true;
				}
			}
		}
		return false;
	}
}
