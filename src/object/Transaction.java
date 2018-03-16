package object;

public class Transaction {

	private int transId;
	private Book book;
	private User user;

	public Transaction() {
		this.transId = 0; 
		this.book = new Book();
		this.user = new User();
	}

	public Transaction(int transId, Book book, User user) {
		super();
		this.transId = transId;
		this.book = book;
		this.user = user;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
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

}
