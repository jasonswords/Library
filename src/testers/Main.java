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

	public static void main(String[] args) throws Exception {

		ArrayList<Login> login = new ArrayList<Login>();
		UserDatabase ud = new UserDatabase();
		Book book = new Book();

		BookDatabase bd = new BookDatabase();
		bd.addBook("Lord of the rings", "J.R.R Tolkien", "Fiction", 4);

		// USER LOGS IN
		// String username = "testUser";//STANDARD USERS
		// String password = "testUser";//STANDARD

		String username = "testAdmin";// ADMIN USERS
		String password = "testAdmin";// ADMIN USERS

		// CHECK LOGIN CREDENTIALS
		LoginDatabase log = new LoginDatabase();
		boolean b = log.isPasswordValid(password);

		if (b) {
			login = log.getOneByPassword(password);
			for (Login l : login) {
				if (l.getUserName().equals(username)) {
					loggedIn = l.getLoginId();
					privilege = l.getLoginId();
					System.out.println(
							"The user " + l.getUserName() + " with privilege " + l.getPrivilege() + " is logged in");
				} // end if
				else {
					System.out.println("The user name is invalid");
				} // end else
			} // end for
		} // end if
		else {
			System.out.println("The password is invalid");
		} // end else
		
		
		//CREATE TRANSACTION
		Transaction t = new Transaction();
		t.setBook(bd.getOneBookByBookId(1));
		t.setUser(ud.getOneUserByLoginId(loggedIn));
		
		//CREATE RESERVATION
		BookReservationDatabase br = new BookReservationDatabase();
		br.reserveBook(t.getUserId(), t.getBookId());
		
		
		//CREATE USER
		
		Login l = new Login();
		LoginDatabase ld = new LoginDatabase();
		User u = new User();
		l = ld.getOneLoginByLoginId(loggedIn);
		u = ud.getOneUserById(l.getUserId());
		
		
		
		System.out.println("\nUser name: " + l.getUserName() + "\nPassword: " + l.getPassword()+"\nPrivilege: "+l.getPrivilege()+"\nUser Id: "+l.getUserId());
		System.out.println("\nFirst Nae: "+u.getFirstName()+"\nSur Name: "+u.getSurName());
		System.out.println("\nThe print out of loggedIn is : " + loggedIn);


	}// END MAIN
}// END CLASS
