package testers;
import object.Transaction;
import java.sql.ResultSet;
import java.util.ArrayList;
import dataBase.LoginTransaction;
import object.Book;
import object.Login;
import object.User;

public class Main {

	static ResultSet rs;

	public static void main(String[] args) throws Exception {
		ArrayList<User> user = new ArrayList<User>();
		ArrayList<Book> book = new ArrayList<Book>();
		ArrayList<Login> login = new ArrayList<Login>();
		
		Transaction trans = new Transaction(1, new Book(1, "Black Beauty", "Author", "Adventure", 10), new User(1, "nadine", "Flavin", "013413", "add 1", "add 2","add 3"));
		

		
		
		
		
		
		
		
		
		
		
		
		

		// new User("Jason","Swords","0879272092", "address1", "address2", "address2");
		// UserTransaction.addUser("Jason","Swords", "address1", "address2",
		// "address2","0879272092");
		// UserTransaction.addUser("dylan","Flavin", "address1", "address2",
		// "address2","087737373");
		// LoginTransaction.addLogin("jason", "swords", 1, 1);
		// LoginTransaction.updateLoginDetailsById("Nadine", "nadinePassword", 2, 2, 1);

		login = LoginTransaction.getOneByUsername("username1");

		for (Login log : login) {
			System.out.println("User name: " + log.getUserName() + "\nPassword: " + log.getPassword() + "\nPrivilege: "
					+ log.getPrivilege());
		}

		/*
		 * user = UserTransaction.getAllUsers(); for(User person: user) {
		 * System.out.println(person.getUserId()+
		 * " "+person.getFirstName()+" "+person.getSurName()+" "+person.getAddress1()
		 * +" "+person.getAddress2()+" "+person.getAddress3()+" "+person.getPhone()); }
		 */

	}
}
