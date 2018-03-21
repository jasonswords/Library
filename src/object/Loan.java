package object;

import java.util.Date;

public class Loan {
	private int loanId;
	private int bookId;
	private int userId;
	private Date date;
	
	public Loan() {}

	public Loan(int loanId, int bookId, int userId, Date date) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.userId = userId;
		this.date = date;
	}
	
	public Loan(int bookId, int userId) {
		super();
		this.bookId = bookId;
		this.userId = userId;
	}

	public int getLoanId() {
		return loanId;
	}

	public int getBookId() {
		return bookId;
	}

	public int getUserId() {
		return userId;
	}

	public Date getDate() {
		return date;
	}

}
