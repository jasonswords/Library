package object;

import java.util.Date;

public class BookReservation {
	private int reserverationId;
	private int bookId;
	private int userId;
	private Date date;

	public BookReservation() {}

	public BookReservation(int reserverationId, int userId,int bookId) {
		this.reserverationId = reserverationId;
		this.bookId = bookId;
		this.userId = userId;
	}
	
	public BookReservation(int reserverationId, int userId,int bookId, Date date) {
		this.reserverationId = reserverationId;
		this.bookId = bookId;
		this.userId = userId;
		this.date = date;
	}
	
	public BookReservation( int userId,int bookId) {
		this.bookId = bookId;
		this.userId = userId;
	}

	public int getReserverationId() {
		return reserverationId;
	}

	public void setReserverationId(int reserverationId) {
		this.reserverationId = reserverationId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
