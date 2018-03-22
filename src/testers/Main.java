package testers;

import java.util.ArrayList;

import dataBase.BookDatabase;
import dataBase.BookReservationDatabase;
import dataBase.LoginDatabase;
import dataBase.UserDatabase;
import object.Book;
import object.Login;
import object.Transaction;
import object.User;

public class Main {
	
	static int loggedIn;
	static int privilege;

	// ADMIN USER
	static String username = "testAdmin";// ADMIN USERS
	static String password = "testAdmin";// ADMIN USERS

	// STANDARD USER
	// static String username = "testUser";//STANDARD USERS
	// static String password = "testUser";//STANDARD

	static ArrayList<Login> login;

	public static void main(String[] args) throws Exception {
		User user = new User("nadine", "Flavin", "bally", "kilm", "naas", "2736t");
		Book b1 = new Book("Harry Potter", "J.K Rowling", "Fantasy", 3);
		Book b = new Book();
		BookDatabase bd = new BookDatabase();
		b = bd.getOneBookByBookId(2);
		Transaction t = new Transaction(b, user);
		
		
		
		
		//ADD BOOK TO DATABASE
		addBookToDatabase(b1);

		//CHECK IF SOMEONE IS LOGGED IN
		isloggedIn();
		
		//PRINT INFO OF WHO IS LOGGED IN
		printInfo();

		

		
		
		
		

	}// END MAIN
	
	//ADD BOOK OBJECT TO DATABASE
	public static void addBookToDatabase(Book b) throws Exception {
		BookDatabase bd = new BookDatabase();
		bd.addBook(b);
	}

	//CHECK IF SOMEONE IS LOGGED IN AND RETEIEVE THAT INFORMATION IF THEY ARE LOGGED IN
	public static void isloggedIn() throws Exception {
		// CRATAE LOGIN DATABASE INSTANCE
		LoginDatabase log = new LoginDatabase();
		//INITIALISE ARRAYLIST
		login = new ArrayList<Login>();
		//TEST IF PASSWORD MATCHES PASSWORDS IN DATABASE
		boolean b = log.isPasswordValid(password);
		//IF VALID, TEST IF USERNAME IS ATTACHED TO THAT PASSWORD
		if (b) {
			//RETRIEVE LOGIN OBJECT USING THE VALID PASSWORD
			login = log.getOneByPassword(password);
			//LOOP THROUGH ALL LOGIN OBJECTS WITH THAT PASSWORD
			for (Login l : login) {
				//CHECK IF PASSWORD IS ASSOCIATED WITH USERNAME ENTERED
				if (l.getUserName().equals(username)) {
					//IF VALID, INITIALISE LOGGED IN WITH LOGIN ID AND PRIVILEGE WITH SETTINGS
					loggedIn = l.getLoginId();
					privilege = l.getLoginId();
					//PRINT WHO IS LOGGED IN
					System.out.println(
							"The user " + l.getUserName() + " with privilege " + l.getPrivilege() + " is logged in");
				} // end if
				else {
					//PRINT IF USERNAME DOES NOT MATCH PASSWORD IN DATABASE
					System.out.println("The user name is invalid");
				} // end else
			} // end for
		} // end if
		else {
			//PRINT OUT IF PASSWORD DOES NOT EXIST IN DATABASE
			System.out.println("The password is invalid");
		} // end else
	}//END IS LOGGED IN

	public static void printInfo() throws Exception{
		// CREATE USER
		Login l = new Login();
		//CREATE LOGIN DATABASE INSTANCE
		LoginDatabase ld = new LoginDatabase();
		//CREATE USER DATABASE INSTANCE
		UserDatabase ud = new UserDatabase();
		//CREATE USER INSTANCE
		User u = new User();
		//RETRIEVE LOGIN OBJECT USING LOGIN ID
		l = ld.getOneLoginByLoginId(loggedIn);
		
		if(l == null) {
			System.out.println("Null Null");
		}else {
		
		//RETRIEVE USER OBJECT USING USER ID
		
		u = ud.getOneUserById(l.getUserId());
		
		System.out.println("\nUser name: " + l.getUserName() + "\nPassword: " + l.getPassword() + "\nPrivilege: "
				+ l.getPrivilege() + "\nUser Id: " + l.getUserId());
		System.out.println("\nFirst Nae: " + u.getFirstName() + "\nSur Name: " + u.getSurName());
		System.out.println("\nThe print out of loggedIn is : " + loggedIn);
		}
//		PRINT OUT DETAILS
		
		

//		System.out.println(u.getClass());
	
//		System.out.println("\nUser name: " + l.getUserName() + "\nPassword: " + l.getPassword() + "\nPrivilege: "
//				+ l.getPrivilege() + "\nUser Id: " + l.getUserId());
//		System.out.println("\nFirst Nae: " + u.getFirstName() + "\nSur Name: " + u.getSurName());
//		System.out.println("\nThe print out of loggedIn is : " + loggedIn);
		
	}

}// END CLASS
