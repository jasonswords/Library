package object;

public class Transaction {

	private Book book;
	private User user;

	public Transaction() {
		this.book = new Book();
		this.user = new User();
	}

	public Transaction(Book book, User user) {
		this.book = book;
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	////////////////////////////
	// BOOK WRAPPER METHODS
	///////////////////////////
	public int getBookId() {
		return book.getBookId();
	}

	public String getBookName() {
		return book.getBookName();
	}

	public String getAuthor() {
		return book.getAuthor();
	}

	public String getGenre() {
		return book.getGenre();
	}

	public int getNumOfCopies() {
		return book.getNumOfCopies();
	}

	/////////////////////////////////////
	// USER WRAPPER METHODS
	/////////////////////////////////////

	public int getUserId() {
		return user.getUserId();
	}

	public String getFirstName() {
		return user.getFirstName();
	}

	public String getSurName() {
		return user.getSurName();
	}

	public String getPhone() {
		return user.getPhone();
	}

	public String getAddress() {
		return user.getAddress();
	}
	
	public void executeTransaction(Book b, User u) {
		Loan l = new Loan();
		l.setBookId(b.getBookId());
		l.setUserId(u.getUserId());
	}

}
