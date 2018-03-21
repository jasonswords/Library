package object;

public class Book extends Product {
	private int bookId;
	private String bookName;
	private String author;
	private String genre;
	private int numOfCopies;

	public Book() {
	}

	public Book( String bookName, String author, String genre, int numOfCopies) {
		this.bookName = bookName;
		this.author = author;
		this.genre = genre;
		this.numOfCopies = numOfCopies;
	}
	
	public Book(int bookId,  String bookName, String author, String genre, int numOfCopies) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.genre = genre;
		this.numOfCopies = numOfCopies;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNumOfCopies() {
		return numOfCopies;
	}

	public void setNumOfCopies(int numOfCopies) {
		this.numOfCopies = numOfCopies;
	}

}
