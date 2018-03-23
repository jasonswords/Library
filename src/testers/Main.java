package testers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dataBase.BookDatabase;
import dataBase.UserDatabase;
import object.Book;
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
	static UserDatabase ud = new UserDatabase();
	static BookDatabase bd = new BookDatabase();
	static ArrayList<Book> book;
	static ArrayList<User> user;
	static User u;
	static Book bk;

	public static void main(String[] args) throws Exception {

		User u = new User();
		Scanner scan = new Scanner(System.in);
		boolean b = false;

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
			System.out.println(" 	(1) for book");
			System.out.println(" 	(2) for user");
			int n = scan.nextInt();

			switch (n) {
			case 1: {
				System.out.println(" 	(1) to view books");
				System.out.println(" 	(2) to update book");
				System.out.println(" 	(3) to delete book");
				System.out.println(" 	(4) to add book");
				System.out.println(" 	(5) to search books");
				int v = scan.nextInt();
				switch (v) {
				case 1: {
					printAllBooks();
				}
					break;
				case 2: {
						updateBookMethod();
				}
					break;
				case 3: {
					deleteBook();
				}
					break;
				case 4: {
					addToBooks();
				}
					break;
				case 5: {
					searchMethod();
				}
					break;
				default: {
					System.out.println("Incorrect entry, start again");
				}
					break;
				}

			} // end book sitch case
				break;

			case 2: {
				System.out.println(" 	(1) to view users");
				System.out.println(" 	(2) to update users");
				System.out.println(" 	(3) to delete users");
				System.out.println(" 	(4) to add users");
				int v = scan.nextInt();

				switch (v) {
				case 1: {
					printUsers();
				}
					break;
				case 2: {
					updateUsers();
				}
					break;
				case 3: {
					deleteUser();
				}
					break;
				case 4: {
					addUsers();
				}
					break;
				}

			} // end user switch case
				break;
			default:
				break;
			}

		}

		scan.close();

	}// END MAIN

	public static boolean validatePassword(String password, String username) throws Exception {
		boolean bool = ud.isPasswordValid(password);
		if (bool) {
			u = ud.getOneByPassword(password);
			if (u.getUsername().equals(username)) {
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

	public static void printBook() {

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
			System.out.print("\nBook Name: " + b.getBookName() + "\nBook Author: " + b.getAuthor() + "\nBook Genre: "
					+ b.getGenre() + "\nNumber available: " + b.getNumOfCopies());
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
		scan.close();
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
		scan.close();
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
		book = getBook(key);
		if (!book.isEmpty()) {
			int index = displayBook(book);
		}
	}

	public static void printUsers() throws SQLException, Exception {
		System.out.println("------ All Users----------");
		u = new User();
		user = new ArrayList<>();
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
		scan.close();
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

		ud.addUser(firstname, surname, add1, add2, add3, phone, username, password, privilege);

		printUsers();
	}

}// END CLASS
