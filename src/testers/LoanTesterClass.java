package testers;

import java.util.ArrayList;

import dataBase.LoanDatabase;

import object.Loan;

public class LoanTesterClass {


	public static void main(String[] args) throws Exception {
		ArrayList<Loan> loan = new ArrayList<Loan>();
		LoanDatabase ld = new LoanDatabase();
		
		/*
		 * 
		 * 1-createTableLoan()
		 * 2-addLoanOfBook(int bookId, int userId)
		 * 3-getAllLoans()
		 * 4-getOneById(int loanId)
		 * 5-getOneByuserId(int userId)
		 * 6-getOneByBookId(int bookId)
		 * 7-updateLoan(int bookId, int userId, int loanId)
		 * 8-deleteUserDetails(int loanId)
		 * 
		 */
		
		//METHOD 1
//		int n = ld.createTableLoan();
//		if(n == 1) {
//			System.out.println("Successful");
//		}
//		else System.out.println("Un - Successful");
		
		//METHOD 2
//		int n = ld.addLoanOfBook(2, 1);
//		if(n == 1) {
//			System.out.println("Successful");
//		}
//		else System.out.println("Un - Successful");
		
		//METHOD 3
//		loan = ld.getAllLoans();
//		if(loan == null) {
//			System.out.println("The return value is Null");
//		}else {
//			for(Loan l: loan) {
//				System.out.println("\nLoan Id: "+l.getLoanId()+"\nLoan Book Id: "+l.getBookId()+"\nLoan User Id: "+l.getUserId()+"\nLoan Date: "+l.getDate());
//			}
//		}
		
		//METHOD 4
//		loan = ld.getOneById(2);
//		if(loan == null) {
//			System.out.println("The return value is Null");
//		}else {
//			for(Loan l: loan) {
//				System.out.println("\nLoan Id: "+l.getLoanId()+"\nLoan Book Id: "+l.getBookId()+"\nLoan User Id: "+l.getUserId()+"\nLoan Date: "+l.getDate());
//			}
//		}
		
		//METHOD 5
//		loan = ld.getOneByuserId(1);
//		if(loan == null) {
//			System.out.println("The return value is Null");
//		}else {
//			for(Loan l: loan) {
//				System.out.println("\nLoan Id: "+l.getLoanId()+"\nLoan Book Id: "+l.getBookId()+"\nLoan User Id: "+l.getUserId()+"\nLoan Date: "+l.getDate());
//			}
//		}
		
		//METHOD 6
//		loan = ld.getOneByBookId(2);
//		if(loan == null) {
//			System.out.println("The return value is Null");
//		}else {
//			for(Loan l: loan) {
//				System.out.println("\nLoan Id: "+l.getLoanId()+"\nLoan Book Id: "+l.getBookId()+"\nLoan User Id: "+l.getUserId()+"\nLoan Date: "+l.getDate());
//			}
//		}
		
		//METHOD 7
//		int n = ld.updateLoan(2, 2, 3);
//		if(n == 1) {
//			System.out.println("Successful");
//		}
//		else System.out.println("Un - Successful");
		
		//METHOD 8
		int n = ld.deleteUserDetails(2);
		if(n == 1) {
			System.out.println("Successful");
		}
		else System.out.println("Un - Successful");


	}

}
