package testers;
import java.sql.SQLException;
import java.util.ArrayList;
import dataBase.BookReservationDatabase;
import object.BookReservation;

public class ReservationTest {
	
	public static void main(String []args) throws SQLException, Exception {
		BookReservationDatabase bookData = new BookReservationDatabase();
		ArrayList<BookReservation> br = new ArrayList<BookReservation>();
		
		
		
		/*
		 * 1-createTableReservation()
		 * 2-reserveBook(int userId, int bookId)
		 * 3-getAllReservations()
		 * 4-getOneByReservationId(int reservationId)
		 * 5-getOneByuserId(int userId) 
		 * 6-getOneByBookId(int bookId)
		 * 7-updateLoan(int userId, int bookId, int reservationId)
		 * 8-deleteUserDetails(int reservationId)
		 *  
		 */
		
		
		
		//METHOD 1
//		int n = bookData.createTableReservation();
//		if(n == 1) {
//			System.out.println("Successful");
//		}
//		else System.out.println("Un - Successful");
		
		//METHOD 2
//		int n = bookData.reserveBook(2, 4);
//		if(n == 1) {
//			System.out.println("Successful");
//		}
//		else System.out.println("Un - Successful");
		
		//METHOD 3
//		br = bookData.getAllReservations();
//		if(br == null) {
//			System.out.println("The return value is Null");
//		}else {
//			for(BookReservation re: br) {
//				System.out.println("\nReservation id: "+re.getReserverationId()+"\nReservation Book Id: "+re.getBookId()+"\nReservation User Id: "+re.getUserId());
//			}
//		}
		
		//METHOD 4
//		br = bookData.getOneByReservationId(2);
//		if(br == null) {
//			System.out.println("The return value is Null");
//		}else {
//			for(BookReservation re: br) {
//				System.out.println("\nReservation id: "+re.getReserverationId()+"\nReservation Book Id: "+re.getBookId()+"\nReservation User Id: "+re.getUserId());
//			}
//		}
		
		//METHOD 5
//		br = bookData.getOneByuserId(4);
//		if(br == null) {
//			System.out.println("The return value is Null");
//		}else {
//			for(BookReservation re: br) {
//				System.out.println("\nReservation id: "+re.getReserverationId()+"\nReservation Book Id: "+re.getBookId()+"\nReservation User Id: "+re.getUserId());
//			}
//		}
		
		//METHOD 6
//		br = bookData.getOneByBookId(2);
//		if(br == null) {
//			System.out.println("The return value is Null");
//		}else {
//			for(BookReservation re: br) {
//				System.out.println("\nReservation id: "+re.getReserverationId()+"\nReservation Book Id: "+re.getBookId()+"\nReservation User Id: "+re.getUserId());
//			}
//		}
		
		//METHOD 7
//		int n = bookData.updateLoan(2, 1, 2);
//		if(n == 1) {
//			System.out.println("Successful");
//		}
//		else System.out.println("Un - Successful");
		
		//METHOD 8
		int n = bookData.deleteReservation(1);
		if(n == 1) {
			System.out.println("Successful");
		}
		else System.out.println("Un - Successful");
	}

}
