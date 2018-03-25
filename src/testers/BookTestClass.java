package testers;

import java.util.ArrayList;
import java.util.Arrays;

import dataBase.BookDatabase;
import object.Book;

public class BookTestClass {

	public static void main(String[] args) throws Exception {
		ArrayList<Book> book = new ArrayList<Book>();
		BookDatabase bd = new BookDatabase();
		
		int[] r = {2,4,6,8,12,45};
		for(int i=0;i<r.length;i++) {
		book.add(bd.getOneBookByBookId(r[i]));
		}
		
		for(Book b: book) {
			System.out.println("\n"+b.getBookName());
		}
		/*
		 * 1-createTableBook()
		 * 2-addBook(String bookName, String author, String genre,int numOfCopies) 
		 * 3-getAllBooks() 
		 * 4-getOneByName(String bookName)
		 * 5-getOneByAuthor(String author)
		 * 6-getOneByBookId(int bookId)
		 * 7-updateBookDetails(String bookName, String author, String genre, int numOfCopies, int bookId )
		 * 8-deleteBook(int bookId)
		 * 9-searchBooks(String key)
		 * 
		 */

		// WORKING CODE (SEARCH BOOK DATABASE USING KEY VALUE)
		//BookDatabase bd = new BookDatabase();
		
		//METHOD 1
//		int n = bd.createTableBook();
//		if (n == 0) {
//			System.out.println("Affected");
//		} else
//			System.out.println("Not Affected");
//		
		
		//METHOD 2
//		int n = bd.addBook("book1", "author1", "genre1", 5);
//		if (n == 0) {
//			System.out.println("Un- successful");
//		} else
//			System.out.println("successful"+n);
		
		//METHOD 3
//		book = bd.getAllBooks();
//		for (Book b : book) {
//			System.out.println("Book id: " + b.getBookId() + "\nBook name: " + b.getBookName() + "\nBook author: "
//					+ b.getAuthor() + "\nBook genre: " + b.getGenre() + "\nBook number of copies: " + b.getNumOfCopies()
//					+ "\n" + "\n");
//		}
		
		//METHOD 4
//		book = bd.getOneByName("beauty");
//		for (Book b : book) {
//			System.out.println("Book id: " + b.getBookId() + "\nBook name: " + b.getBookName() + "\nBook author: "
//					+ b.getAuthor() + "\nBook genre: " + b.getGenre() + "\nBook number of copies: " + b.getNumOfCopies()
//					+ "\n" + "\n");
//		}
		
	    //METHOD 5
//		book = bd.getOneByAuthor("har");
//		for (Book b : book) {
//			System.out.println("Book id: " + b.getBookId() + "\nBook name: " + b.getBookName() + "\nBook author: "
//					+ b.getAuthor() + "\nBook genre: " + b.getGenre() + "\nBook number of copies: " + b.getNumOfCopies()
//					+ "\n" + "\n");
//		}
		
		//METHOD 6
//		book = bd.getOneByBookId(2);
//		for (Book b : book) {
//		System.out.println("Book id: " + b.getBookId() + "\nBook name: " + b.getBookName() + "\nBook author: "
//				+ b.getAuthor() + "\nBook genre: " + b.getGenre() + "\nBook number of copies: " + b.getNumOfCopies()
//				+ "\n" + "\n");
//	}
		
		//METHOD 7
//		int n = bd.updateBookDetails("update1", "authorUpdate", "genreUpdate", 2, 1);
//		if (n == 0) {
//			System.out.println("Un- successful");
//		} else
//			System.out.println("successful   =  "+n);
		
		//METHOD 8
//		int n = bd.deleteBook(3);
//		if (n == 0) {
//			System.out.println("Un- successful");
//		} else
//			System.out.println("successful   =  "+n);
		
		//METHOD 9
		book = bd.searchBooks("black");

		for (Book b : book) {
			System.out.println("Book id: " + b.getBookId() + "\nBook name: " + b.getBookName() + "\nBook author: "
					+ b.getAuthor() + "\nBook genre: " + b.getGenre() + "\nBook number of copies: " + b.getNumOfCopies()
					+ "\n" + "\n");
		 }

	}// END MAIN

}// END CLASS
