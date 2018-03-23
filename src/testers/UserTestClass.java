package testers;

import java.util.ArrayList;
import dataBase.UserDatabase;
import object.User;

public class UserTestClass {

	public static void main(String[] args) throws Exception {
		ArrayList<User> user = new ArrayList<>();

		// WORKING CODE (GET ALL USERS IN DATABASE
		UserDatabase userDB = new UserDatabase();
		
		/* 1-createTableUser()
		 * 2-addUser(String firstName, String surName, String address1, String address2, String address3, String phone)
		 * 3-getAllUsers()
		 * 4-getOneByName(String firstName)
		 * 5-getOneBySurName(String surName)
		 * 6-getOneById(int userId)
		 * 7-updateUserDetails(String firstName, String surName, String address1, String address2, String address3, String phone, int userId)
		 * 8-deleteUserDetails(int userId)
		 * 
		 */
		
		//METHOD 1
		userDB.createTableUser();
		
		//METHOD 2
		//userDB.addUser("Betty", "Swords", "road1", "road2", "road3", "1187372637");
		
		//METHOD 3
		user = userDB.getAllUsers();

		for (User person : user) {
			System.out.println( "\nUser Id: " + person.getUserId() + "\nUser first name: " + person.getFirstName()	+ 
					"\nUser sur name: " + person.getSurName() + "\nUser Address 1: " +person.getAddress1() + "\nUser Address 2: " 
					+ person.getAddress2() + "\nUser Address 3: " + person.getAddress3()+ "\nUser phone: " + person.getPhone());
		}
		
		//METHOD 4
//		user = userDB.getOneByName("betty");
//		for (User person : user) {
//			System.out.println( "\nUser Id: " + person.getUserId() + "\nUser first name: " + person.getFirstName()	+ 
//					"\nUser sur name: " + person.getSurName() + "\nUser Address 1: " +person.getAddress1() + "\nUser Address 2: " 
//					+ person.getAddress2() + "\nUser Address 3: " + person.getAddress3()+ "\nUser phone: " + person.getPhone());
//		}
		
		//METHOD 5
//		user = userDB.getOneBySurName("saunders");
//		for (User person : user) {
//			System.out.println( "\nUser Id: " + person.getUserId() + "\nUser first name: " + person.getFirstName()	+ 
//					"\nUser sur name: " + person.getSurName() + "\nUser Address 1: " +person.getAddress1() + "\nUser Address 2: " 
//					+ person.getAddress2() + "\nUser Address 3: " + person.getAddress3()+ "\nUser phone: " + person.getPhone());
//		}
		
		//METHOD 6
//		user = userDB.getOneById(2);
//		for (User person : user) {
//			System.out.println( "\nUser Id: " + person.getUserId() + "\nUser first name: " + person.getFirstName()	+ 
//					"\nUser sur name: " + person.getSurName() + "\nUser Address 1: " +person.getAddress1() + "\nUser Address 2: " 
//					+ person.getAddress2() + "\nUser Address 3: " + person.getAddress3()+ "\nUser phone: " + person.getPhone());
//		}
		
		//METHOD 7
		int n = userDB.updateUserDetails("dylan", "swords", "add1", "add2", "add3", "123421321", "dylan", "dylan", 1, 2);
		if(n == 0) 
			System.out.println("The update was unsuccessful");
		
		else System.out.println("The update was a success");
		
		//MRTHOD 8
//		int n = userDB.deleteUserDetails(3);
//		if(n == 0) 
//			System.out.println("The update was unsuccessful");
//		
//		else System.out.println("The update was a success");
//		
		
		

	}

}
