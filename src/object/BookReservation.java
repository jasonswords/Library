package object;

public class BookReservation {
	private int reserverationId;
	private int bookId;
	private int userId;

	public BookReservation(int reserverationId, int userId,int bookId) {
		this.reserverationId = reserverationId;
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

}
