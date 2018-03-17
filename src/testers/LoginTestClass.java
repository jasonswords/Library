package testers;
import java.util.ArrayList;
import dataBase.LoginDatabase;
import object.Login;

public class LoginTestClass {

	public static void main(String[] args) throws Exception {

		ArrayList<Login> login = new ArrayList<Login>();
		LoginDatabase ld = new LoginDatabase();

	/*
	 * 1-createTableLogin() 
	 * 2-addLogin(String username, String password, int privilege, int userId)
	 * 3-getAllLogin() 
	 * 4-getOneByLoginId(int loginId)
	 * 5-getOneByPrivilege(int privilege)
	 * 6-getOneByUsername(String username) 
	 * 7-getOneByPassword(String password)
	 * 8-updateLoginDetailsById(String username, String password, int privilege, int userId, int loginId)
	 * 9-deleteLoginDetails(int loginId)
	 * 10-isPasswordValid(String passwd)
	 *
	 */

		//METHOD 1
//		int n = ld.createTableLogin();
//		if (n == 1) {
//			System.out.println("Successful");
//		} else
//			System.out.println("Un - Successful");
		
		//METHOD 2
//		int n = ld.addLogin("jason", "jason", 1);
//		if (n == 1) {
//			System.out.println("Successful");
//		} else
//			System.out.println("Un - Successful");
		
		//METHOD 3
//		login = ld.getAllLogin();
//		for (Login log : login) {
//			System.out.println("\nLogin id: " + log.getLoginId() + "\nLogin user name: " + log.getUserName()
//					+ "\nLogin Password: " + log.getPassword() + "\nLogin Privilege: " + log.getPrivilege());
//		}
		
		//METHOD 4
//		login = ld.getOneByLoginId(2);
//		for (Login log : login) {
//			System.out.println("\nLogin id: " + log.getLoginId() + "\nLogin user name: " + log.getUserName()
//					+ "\nLogin Password: " + log.getPassword() + "\nLogin Privilege: " + log.getPrivilege());
//		}
		
		//METHOD 5
//		login = ld.getOneByPrivilege(1);
//		for (Login log : login) {
//			System.out.println("\nLogin id: " + log.getLoginId() + "\nLogin user name: " + log.getUserName()
//					+ "\nLogin Password: " + log.getPassword() + "\nLogin Privilege: " + log.getPrivilege());
//		}
		
		//METHOD 6
//		login = ld.getOneByUsername("jason");
//		for (Login log : login) {
//			System.out.println("\nLogin id: " + log.getLoginId() + "\nLogin user name: " + log.getUserName()
//					+ "\nLogin Password: " + log.getPassword() + "\nLogin Privilege: " + log.getPrivilege());
//		}
		
		//METHOD 7
//		login = ld.getOneByPassword("jason");
//		for (Login log : login) {
//			System.out.println("\nLogin id: " + log.getLoginId() + "\nLogin user name: " + log.getUserName()
//					+ "\nLogin Password: " + log.getPassword() + "\nLogin Privilege: " + log.getPrivilege());
//		}
		
		//METHOD 8
//		int n = ld.updateLoginDetailsById("Jason", "12345", 0, 2);
//		if (n == 1) {
//			System.out.println("Successful");
//		} else
//			System.out.println("Un - Successful");
		
		//METHOD 9
//		int n = ld.deleteLoginDetails(1);
//		if (n == 1) {
//			System.out.println("Successful");
//		} else
//			System.out.println("Un - Successful");
		
		//METHOD 10
		System.out.println(ld.isPasswordValid("12345"));
		
		

//		if (!login.isEmpty()) {
//
//			for (Login log : login) {
//				System.out.println("Login id: " + log.getLoginId() + "\nLogin user name: " + log.getUserName()
//						+ "\nLogin Password: " + log.getPassword() + "\nLogin Privilege: " + log.getPrivilege());
//			}
//		} else {
//			System.out.println("The return is empty");
//		}

	}

}
