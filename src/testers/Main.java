package testers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dataBase.BookDatabase;
import dataBase.LoanDatabase;
import dataBase.UserDatabase;
import object.Book;
import object.Loan;
import object.Transaction;
import object.User;

public class Main {

	static int isLoggedIn;
	static int privilege;

	// ADMIN USER
	// USERNAME admin
	// PASSWORD admin

	// STANDARD USER
	// USERNAME user
	// PASSWORD user
	static UserDatabase ud;
	static BookDatabase bd;
	static LoanDatabase ld;
	static ArrayList<Book> book;
	static ArrayList<User> user;
	static ArrayList<Loan> l;
	static User u;
	static Book bk;

	public static void main(String[] args) throws Exception {
		char character = '_';
		// User u = new User();
		Scanner scan = new Scanner(System.in);
		/////////////////////////////////////////////////////////////////////////////////////////////////
		boolean b = true;////// ------CHANGE TO TRUE TO USER PASSWORD AUTHENTICATION---------///////////
		////////////////////////////////////////////////////////////////////////////////////////////////

		do {
			System.out.println("Login in");
			System.out.println("User name");
			String user = scan.next();
			System.out.println("Password");
			String pass = scan.next();

			boolean val = validatePassword(pass, user);
			if (val)
				b = false;

		} while (b);
		{
			while (character != 'q') {

				System.out.println("");
				System.out.println("-----Main Menu------");
				System.out.println("");
				System.out.println(" 	(B) for book");
				System.out.println(" 	(U) for user");
				System.out.println(" 	(Q) for quit");
				character = scan.next().toLowerCase().charAt(0);

				switch (character) {
				case 'b': {
					boolean bo = true;
					while (bo) {

						System.out.println("");
						System.out.println("-------Book Menu----------");
						System.out.println("");
						System.out.println(" 	(V) to view books");
						System.out.println(" 	(U) to update book");
						System.out.println(" 	(D) to delete book");
						System.out.println(" 	(A) to add book");
						System.out.println("	(B) to borrow a book");
						System.out.println("	(R) to return a book");
						System.out.println(" 	(S) to search books");
						System.out.println("	(Q) to quit");
						char v = scan.next().toLowerCase().charAt(0);

						switch (v) {
						case 'v': {
							printAllBooks();
						}
							break;
						case 'u': {
							updateBookMethod();
						}
							break;
						case 'd': {
							deleteBook();
						}
							break;
						case 'a': {
							addToBooks();
						}
							break;
						case 'b': {
							borrowBookMethod();
						}
							break;
						case 'r': {
							returnBook();
						}
							break;

						case 's': {
							searchMethod();
						}
							break;
						case 'q': {
							bo = false;
						}
							break;

						default: {
							System.out.println("The character selected was invalid!!!");
						}
							break;
						}

					} // END BOOK MAIN MENU WHILE LOOP

				} // END BOOK SWITCH CASE

				case 'u': {
					char v = '_';
					while (v != 'q') {
						System.out.println("");
						System.out.println("---------User Menu--------------");// WILL BE ADMIN ONLY
						System.out.println("");
						System.out.println(" 	(V) to view users");
						System.out.println(" 	(U) to update users");
						System.out.println(" 	(D) to delete users");
						System.out.println(" 	(A) to add users");
						System.out.println(" 	(Q) to quit");
						v = scan.next().toLowerCase().charAt(0);

						switch (v) {
						case 'v': {
							printUsers();
						}
							break;
						case 'u': {
							updateUsers();
						}
							break;
						case 'd': {
							deleteUser();
						}
							break;
						case 'a': {
							addUsers();
						}
							break;

						default: {
							System.out.println("The character selected was invalid!!!");
						}
							break;

						} // end user switch case
					}
				}
				}
			}
		}

		

	}// END MAIN

	public static boolean validatePassword(String username, String password) throws Exception {
		ud = new UserDatabase();
		boolean bool = ud.isUserNameValid(username);
		if (bool) {
			u = ud.getOneByUserName(username);
			if (u.getPassword().equals(password)) {
				isLoggedIn = u.getUserId();
				privilege = u.getPrivilege();
				return true;
			}
		}
		return false;
	}

	// ADD BOOK OBJECT TO DATABASE
	public static int addBookToDatabase(Book b) throws Exception {
		BookDatabase bd = new BookDatabase();
		int x = bd.addBook(b);
		return x;
	}

	public static void printAllBooks() throws SQLException, Exception {
		bd = new BookDatabase();
		book = new ArrayList<>();
		book = bd.getAllBooks();
		System.out.println("------- Available Books---------");
		for (Book bk : book) {
			System.out.print("\nBook Name: " + bk.getBookName() + "\nBook Author: " + bk.getAuthor() + "\nBook Genre: "
					+ bk.getGenre() + "\nNumber available: " + bk.getNumOfCopies());
			System.out.println("");
		}
	}

	public static ArrayList<Book> getBook(String key) throws SQLException, Exception {
		book = new ArrayList<>();
		bd = new BookDatabase();
		book = bd.searchBooks(key);
		return book;
	}

	public static int displayBook(ArrayList<Book> bk) {
		for (Book b : bk) {
			System.out.print("\nBook Id: " + b.getBookId() + "\nBook Name: " + b.getBookName() + "\nBook Author: "
					+ b.getAuthor() + "\nBook Genre: " + b.getGenre() + "\nNumber available: " + b.getNumOfCopies());
			System.out.println("");
			return b.getBookId();
		}
		return -1;
	}

	public static void displayBook(Book b) {

		System.out.print("\nBook Name: " + b.getBookName() + "\nBook Author: " + b.getAuthor() + "\nBook Genre: "
				+ b.getGenre() + "\nNumber available: " + b.getNumOfCopies());
		System.out.println("");
	}

	public static void updateBookMethod() throws SQLException, Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("------- Update Books---------");
		printAllBooks();
		System.out.println("");
		System.out.println("Which book would you like to update");
		System.out.println("Please enter Title, Genre or Author");
		String key = scan.next();
		bk = new Book();
		book = new ArrayList<>();
		bd = new BookDatabase();
		book = getBook(key);
		if (!book.isEmpty()) {
			int index = displayBook(book);

			System.out.println("");
			System.out.println("-----Enter new Values ");
			System.out.println("");
			System.out.println("Enter Book Name");
			String name = scan.next();
			scan.nextLine();
			System.out.println("Enter Genre");
			String gen = scan.next();
			scan.nextLine();
			System.out.println("Enter Author");
			String author = scan.next();
			scan.nextLine();
			System.out.println("Enter Number of copies");
			int copies = scan.nextInt();
			scan.nextLine();

			int i = bd.updateBookDetails(name, author, gen, copies, index);
			if (i == -1) {
				System.out.println("Un - successful");
			} else
				System.out.println("Successful changed");

			bk = new Book();
			bk = bd.getOneBookByBookId(index);

			displayBook(bk);

		} else
			System.out.println("No search results found");
		
	}

	public static void deleteBook() throws SQLException, Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("------- Delete Book---------");
		printAllBooks();
		System.out.println("");
		System.out.println("Which book would you like to delete");
		System.out.println("Please enter Title, Genre or Author");
		String key = scan.next();
		bk = new Book();
		book = new ArrayList<>();
		bd = new BookDatabase();
		book = getBook(key);
		if (!book.isEmpty()) {
			int index = displayBook(book);
			System.out.println("");
			System.out.println("Are you sure you want to delete this book");
			System.out.println("------- 1 for Yes----");
			System.out.println("------- 2 for No----");
			int ch = scan.nextInt();
			if (ch == 1) {
				bd = new BookDatabase();
				int i = bd.deleteBook(index);
				if (i == 0) {
					System.out.println("		Book has NOT been deleted");
				} else
					System.out.println("		Book has been deleted");
			} else
				System.out.println("		Book has NOT been deleted");

			System.out.println("");
			printAllBooks();

		} else {
			System.out.println("The search returned 0 results");
		}
		
	}

	public static void addToBooks() throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("------- Add Book---------");
		System.out.println("");
		System.out.println("Enter Book Name");
		String name = scan.next();
		scan.nextLine();
		System.out.println("Enter Genre");
		String gen = scan.next();
		scan.nextLine();
		System.out.println("Enter Author");
		String author = scan.next();
		scan.nextLine();
		System.out.println("Enter Number of copies");
		int copies = scan.nextInt();
		scan.nextLine();

		bk = new Book(name, author, gen, copies);
		int z = addBookToDatabase(bk);
		if (z == -1) {
			System.out.println("The book was not added");
		} else
			System.out.println("The book was added successfully");
		System.out.println("");
		printAllBooks();
		
	}

	public static void searchMethod() throws SQLException, Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("------- Search Books---------");
		System.out.println("");
		System.out.println("Please enter Title, Genre or Author");
		String key = scan.next();
		bk = new Book();
		book = new ArrayList<>();
		book = getBook(key);
		if (!book.isEmpty()) {
			displayBook(book);
		} else {

		}
		
	}

	public static void printUsers() throws SQLException, Exception {
		System.out.println("------ All Users----------");
		u = new User();
		user = new ArrayList<>();
		ud = new UserDatabase();
		user = ud.getAllUsers();
		for (User o : user) {
			System.out.println("\nUser first name: " + o.getFirstName() + "\nUser sur- name " + o.getSurName()
					+ "\nUser first address1: " + o.getAddress2() + "\nUser first address3: " + o.getAddress3()
					+ "\nUser phone number: " + o.getPhone());
		}
	}

	public static void printSingleUsers(User o) {
		System.out.println("\nUser first name: " + o.getFirstName() + "\nUser sur- name " + o.getSurName()
				+ "\nUser first address1: " + o.getAddress2() + "\nUser first address3: " + o.getAddress3()
				+ "\nUser phone number: " + o.getPhone());
	}

	public static void updateUsers() throws SQLException, Exception {
		System.out.println("------ Update Users----------");
		Scanner scan = new Scanner(System.in);
		printUsers();
		System.out.println("");
		System.out.println("Which user would you like to update");
		System.out.println("Please enter their first name");
		String key = scan.next();
		u = new User();
		ud = new UserDatabase();
		u = ud.getUserByFirstName(key);
		if (u != null) {
			int index = u.getUserId();

			System.out.println("");
			System.out.println("-----Enter new Values ");
			System.out.println("");
			System.out.println("Enter first Name");
			String firstname = scan.next();
			scan.nextLine();
			System.out.println("Enter sur name");
			String surname = scan.next();
			scan.nextLine();
			System.out.println("Enter address 1");
			String add1 = scan.next();
			scan.nextLine();
			System.out.println("Enter address 2");
			String add2 = scan.next();
			scan.nextLine();
			System.out.println("Enter address 3");
			String add3 = scan.next();
			scan.nextLine();
			System.out.println("Enter phone number");
			String phone = scan.next();
			scan.nextLine();
			System.out.println("Enter username");
			String username = scan.next();
			scan.nextLine();
			System.out.println("Enter password");
			String password = scan.next();
			scan.nextLine();
			System.out.println("Enter privilege");
			int privilege = scan.nextInt();
			scan.nextLine();

			int i = ud.updateUserDetails(firstname, surname, add1, add2, add3, phone, username, password, privilege,
					index);
			if (i == 0) {
				System.out.println("Un - successful");
			} else
				System.out.println("Successful changed");

			u = new User();
			u = ud.getOneUserById(index);

			printSingleUsers(u);
		} else
			System.out.println("No search results found");
		
	}

	public static void deleteUser() throws SQLException, Exception {
		System.out.println("------ Delete Users----------");
		Scanner scan = new Scanner(System.in);
		printUsers();
		System.out.println("");
		System.out.println("Which user would you like to update");
		System.out.println("Please enter their first name");
		String key = scan.next();
		u = new User();
		ud = new UserDatabase();
		u = ud.getUserByFirstName(key);
		u.getUserId();
		ud.deleteUserDetails(u.getUserId());
		System.out.println("");
		printUsers();
		
	}

	public static void addUsers() throws Exception {
		System.out.println("------ Add Users----------");
		Scanner scan = new Scanner(System.in);
		System.out.println("");

		System.out.println("");
		System.out.println("-----Enter new Values ");
		System.out.println("");
		System.out.println("Enter first Name");
		String firstname = scan.next();
		scan.nextLine();
		System.out.println("Enter sur name");
		String surname = scan.next();
		scan.nextLine();
		System.out.println("Enter address 1");
		String add1 = scan.next();
		scan.nextLine();
		System.out.println("Enter address 2");
		String add2 = scan.next();
		scan.nextLine();
		System.out.println("Enter address 3");
		String add3 = scan.next();
		scan.nextLine();
		System.out.println("Enter phone number");
		String phone = scan.next();
		scan.nextLine();
		System.out.println("Enter username");
		String username = scan.next();
		scan.nextLine();
		System.out.println("Enter password");
		String password = scan.next();
		scan.nextLine();
		System.out.println("Enter privilege");
		int privilege = scan.nextInt();
		scan.nextLine();
		ud = new UserDatabase();
		ud.addUser(firstname, surname, add1, add2, add3, phone, username, password, privilege);

		printUsers();
		
	}

	public static void addBookToLoanTable(int id) throws SQLException, Exception {
		ld = new LoanDatabase();
		bd = new BookDatabase();
		ud = new UserDatabase();
		Transaction t = new Transaction();
		t.setBook(bd.getOneBookByBookId(id));
		t.setUser(ud.getOneUserById(isLoggedIn));
		int[] arr = ld.addNewLoan(t);
		if (arr[0] == 1 || arr[1] == 1) {
			System.out.println("");
			System.out.println("The book is added to your account successfully !!");
		} else {
			System.out.println("");
			System.out.println("An error occured while writing to the database");
		} // END ELSE STATEMENT
	}

	public static void borrowBookMethod() throws SQLException, Exception {
		Scanner scan = new Scanner(System.in);
		boolean bol = true;
		while (bol) {

			System.out.println("");
			System.out.println("---- Borrow Book --------");
			System.out.println("");
			printAllBooks();
			System.out.println("");
			System.out.println("Please choose a book to borrow");
			System.out.println("You can search by title, genre or author");
			System.out.println("Enter search value");
			String key = scan.next();
			bd = new BookDatabase();
			book = new ArrayList<>();
			book = bd.searchBooks(key);
			if (book.size() == 1) {
				bk = new Book();
				bk = book.get(0);
				System.out.println("");
				displayBook(bk);
				System.out.println("");
				System.out.println("Is this the book ??");
				System.out.println("		Y for yes");
				System.out.println("		N for no");
				String zx = scan.next().toLowerCase();
				char xc = zx.charAt(0);
				if (xc == 'y') {

					addBookToLoanTable(bk.getBookId());

				} // END IF STATEMENT

			} // END IF STATEMENT
			else {
				boolean y = true;

				while (y) {
					displayBook(book);
					System.out.println("");
					System.out.println(book.size());
					System.out.println(" ----- Multiple results-----------");
					System.out.println("Plaese choose which book you want");
					System.out.println("Please input the id of the book");
					for (Book b1 : book) {
						System.out.println("\nBook name: " + b1.getBookName() + "\nBook id: " + b1.getBookId());
						System.out.println("");
					}

					System.out.println("Please input book id of book you want");
					int id = scan.nextInt();
					bk = new Book();
					System.out.println("");
					bk = bd.getOneBookByBookId(id);
					displayBook(bk);
					System.out.println("");
					System.out.println("Is this the book you want ?");
					System.out.println("");
					System.out.println(" 	Y for yes");
					System.out.println(" 	N for no");
					String c = scan.next().toLowerCase();
					char m = c.charAt(0);
					if (m == 'y') {
						addBookToLoanTable(id);
						y = false;
					} else {
						y = true;

					} // END ELSE STATEMENT
				} // END INNER WHILE LOOP
			} // END IF STATEMENT

			System.out.println("Loan another book");
			System.out.println("		Y for yes");
			System.out.println("		N for no");
			String s = scan.next().toLowerCase();
			char k = s.charAt(0);
			bol = (k == 'y') ? true : false;
			
		}
	}

	public static void returnBook() throws SQLException, Exception {
		Scanner scan = new Scanner(System.in);
		char k = 'y';
		while (k != 'n') {
			System.out.println("");
			System.out.println("------Return Book----------");
			System.out.println("");
			System.out.println("");
			l = new ArrayList<Loan>();
			ld = new LoanDatabase();
			bd = new BookDatabase();
			book = new ArrayList<>();
			char s = 'n';
			while (s != 'y') {
				l = ld.getAllLoansByuserId(isLoggedIn);

				displayBookByIndex(l);

				System.out.println("");
				System.out.println("Please input the id of the book to return");
				int id = scan.nextInt();
				bk = new Book();
				bk = bd.getOneBookByBookId(id);
				displayBook(bk);
				System.out.println("");
				System.out.println("Is this the book you are retyrning ?");
				System.out.println("		Y for yes");
				System.out.println("		N for no");
				s = scan.next().toLowerCase().charAt(0);
			} // end while loop

			if (s == 'y') {
				int d = bk.getBookId();
				int n = ld.deleteLoanDetails(d);
				bk = new Book();
				bk = bd.getOneBookByBookId(d);
				bk.setNumOfCopies(bk.getNumOfCopies() +1);
				bd.updateBookDetails(bk);
				System.out.println("");
				if (n == 1) {
					System.out.println("The book has been removed from the account");
					System.out.println("");
				} else {
					System.out.println("An error occured while removing the book from the database");
					System.out.println("");
				}
			} else {
				System.out.println("");
				System.out.println("An error has occured");
				System.out.println("");
			}

			System.out.println("Return another book");
			System.out.println("		Y for yes");
			System.out.println("		N for no");
			k = scan.next().toLowerCase().charAt(0);
		} // END WHILE LOOP
		

	}

	public static int[] getBookIndexes(ArrayList<Loan> l) {
		int[] index = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			index[i] = l.get(i).getBookId();
		}
		return index;
	}

	public static void displayBookByIndex(ArrayList<Loan> l) throws SQLException, Exception {
		bd = new BookDatabase();
		int[] index = getBookIndexes(l);
		for (int i = 0; i < index.length; i++) {
			book.add(bd.getOneBookByBookId(index[i]));
		}
		for (Book b : book) {
			System.out.println("\nBookName" + b.getBookName() + "\nBook Id: " + b.getBookId());
			System.out.println("");
		}
	}

}// END CLASS
