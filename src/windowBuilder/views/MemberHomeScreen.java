package windowBuilder.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dataBase.BookDatabase;
import dataBase.BookReservationDatabase;
import dataBase.LoanDatabase;
import dataBase.UserDatabase;
import object.Book;
import object.BookReservation;
import object.Loan;
import object.User;
import testers.GUICommunication;

public class MemberHomeScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	private JTextField textField;
	private JOptionPane pane;
	static DefaultTableModel model;
	JLabel lblNewLabel;
	static Object[] rowData;
	JButton btnThisBook;
	ArrayList<Book> book;
	static ArrayList<Loan> l;
	static LoanDatabase ld;
	static BookDatabase bd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberHomeScreen frame = new MemberHomeScreen(null);
					frame.setVisible(true);
				} catch (Exception e) {
					errorMessage("Error, Problem opening the application");
				}
			}
		});
	}

	public MemberHomeScreen(ArrayList<Book> book) throws SQLException, Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 45, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 231, 766, 391);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		String[] col = { "Book ID", "Title", "Author", "Genre", "Number Available" };
		model = new DefaultTableModel(col, 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUICommunication.logOut();
				Main main = new Main();
				main.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnLogOut.setBounds(31, 24, 248, 61);
		contentPane.add(btnLogOut);

		JButton btnNewButton = new JButton("Return Book");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ArrayList<Book> book = new ArrayList<Book>();
				try {
					book = GUICommunication.getBookFromLoan();
					if (book.isEmpty()) {
						errorMessage("Currently no books to return");
					} else if (book.size() == 1) {
						displayBooks(book, 0);
						if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							LoanDatabase ld = new LoanDatabase();
							ld.removeLoanOfBook(book.get(0).getBookId(), GUICommunication.getLoggedIn());
							book = new ArrayList<>();
							book = GUICommunication.getBookFromLoan();
							displayBooks(book, 0);
							errorMessage("The book was successfully removed");
						} else {
							errorMessage("Error, the book was not removed \nfrom the database");
							pane.setVisible(false);
						}
					} else {
						displayBooks(book, 0);
						String s = "";
						do {
							s = JOptionPane.showInputDialog(null, "Please input a valid book ID?");
							if (s == null || (s != null && ("".equals(s)))) {
//								throw new Exception();
								break;
							}
						} while (!parseString(s) || !isBookAlreadyOnLoanTable(Integer.parseInt(s)));
						int n = Integer.parseInt(s);
						LoanDatabase ld = new LoanDatabase();
						int[] val = ld.removeLoanOfBook(n, GUICommunication.getLoggedIn());
						if (val[0] == 1 && val[1] == 1) {
							displayBooks(GUICommunication.getBookFromLoan(), 0);
							errorMessage("The book was successfully removed");
						} else {
							errorMessage("Error, Problem writing to the database");
						}
					}
				} catch (Exception e1) {
				}
			}
		});
		btnNewButton.setBounds(17, 296, 248, 70);
		contentPane.add(btnNewButton);

		JButton btnBorrowBook = new JButton("Loan a Book");
		btnBorrowBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loanBookMethod();
				} catch (Exception e1) {
					
				}
			}
		});
		btnBorrowBook.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnBorrowBook.setBounds(17, 378, 248, 61);
		contentPane.add(btnBorrowBook);

		textField = new JTextField("Name/Author/Genre");
		textField.setFont(new Font("AR BLANCA", Font.PLAIN, 29));
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(368, 143, 311, 64);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSearchBooks = new JButton("Search Books");
		btnSearchBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = textField.getText();
				try {
					if (!key.isEmpty()) {
						ArrayList<Book> book = new ArrayList<>();
						book = GUICommunication.searchMethod(key);
						if (book.isEmpty()) {
							errorMessage("No results found");
						} else
							displayBooks(book, 0);
					} else {
						displayBooks(null, 1);
					}
				} catch (Exception e1) {
					errorMessage("Unknown problem occurred");
				}
			}
		});
		btnSearchBooks.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnSearchBooks.setBounds(689, 146, 292, 61);
		contentPane.add(btnSearchBooks);

		UserDatabase ud = new UserDatabase();
		User u = new User();
		u = ud.getOneUserById(GUICommunication.getLoggedIn());
		if (u != null) {
			lblNewLabel = new JLabel("Welcome " + u.getFirstName());
		} else {
			lblNewLabel.setText("Unknown User");
		}
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(778, 29, 316, 54);
		contentPane.add(lblNewLabel);

		JLabel lblMembersArea = new JLabel("Members Area");
		lblMembersArea.setOpaque(true);
		lblMembersArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblMembersArea.setForeground(Color.BLACK);
		lblMembersArea.setBackground(Color.WHITE);
		lblMembersArea.setFont(new Font("Calibri", Font.PLAIN, 30));
		lblMembersArea.setBounds(454, 6, 235, 61);
		contentPane.add(lblMembersArea);

		JButton btnReservedBooks = new JButton("Reserved Books");
		btnReservedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayReservedBooks();
			}
		});
		btnReservedBooks.setBounds(72, 486, 160, 47);
		contentPane.add(btnReservedBooks);

		JButton btnNewButton_1 = new JButton("Un-reserve book");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					unReserveBook();
				}catch(Exception ev) {
					
				}
			}
		});
		btnNewButton_1.setBounds(72, 545, 160, 44);
		contentPane.add(btnNewButton_1);
	}

	// display arraylist of books on j table
	public static void displayBooks(ArrayList<Book> bookList, int n) throws SQLException, Exception {
		ArrayList<Book> b1 = new ArrayList<>();
		if (n == 1) {
			bd = new BookDatabase();
			b1 = bd.getAllBooks();
		} else
			b1 = bookList;
		model.setRowCount(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(5);
		rowData = new Object[5];
		for (int i = 0; i < b1.size(); i++) {
			rowData[0] = b1.get(i).getBookId();
			rowData[1] = b1.get(i).getBookName();
			rowData[2] = b1.get(i).getAuthor();
			rowData[3] = b1.get(i).getGenre();
			rowData[4] = b1.get(i).getNumOfCopies();
			model.addRow(rowData);
		}
	}

	// display a single book on j table
	public static void displayBook(Book book) {
		model.setRowCount(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(5);
		rowData = new Object[5];
		rowData[0] = book.getBookId();
		rowData[1] = book.getBookName();
		rowData[2] = book.getAuthor();
		rowData[3] = book.getGenre();
		rowData[4] = book.getNumOfCopies();
		model.addRow(rowData);
	}

	// validate integer
	public static boolean parseString(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	// display error message
	public static void errorMessage(String s) {
		JOptionPane.showConfirmDialog(null, s, "Information", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	// validate bookid exists in array
	public static boolean isBookIdValid(ArrayList<Book> b, int bookId) {
		for (Book book : b) {
			if (book.getBookId() == bookId) {
				return true;
			}
		}
		return false;
	}

	// return arraylist of all the books in database
	public static ArrayList<Book> allBooks() {
		bd = new BookDatabase();
		try {
			return bd.getAllBooks();
		} catch (SQLException e) {
			errorMessage("Error, Problem reading from the database");
		} catch (Exception e) {
			errorMessage("Error, Something went wrong");
		}
		return null;
	}

	// check if book is already on loan table
	public static boolean isBookAlreadyOnLoanTable(int bookId) throws SQLException, Exception {
		ld = new LoanDatabase();
		ArrayList<Loan> loan = new ArrayList<>();
		loan = ld.getAllLoansByuserId(GUICommunication.getLoggedIn());
		for (Loan l : loan) {
			if (l.getBookId() == bookId) {
				return true;
			}
		}
		return false;
	}

	public static boolean displayReservedBooks() {
		ArrayList<BookReservation> reserved = new ArrayList<>();
		ArrayList<Book> book = new ArrayList<>();
		BookReservationDatabase brd = new BookReservationDatabase();
		BookDatabase bd = new BookDatabase();
		try {
			reserved = brd.getAllReservationsByUserId(GUICommunication.getLoggedIn());
			if (!reserved.isEmpty()) {
				for (BookReservation br : reserved) {
					book.add(bd.getOneByBookId(br.getBookId()));
				}
				displayBooks(book, 0);
				return true;
			} else {
				errorMessage("Currnetly no books reserved");
				return false;
			}

		} catch (Exception e1) {
			errorMessage("Error, Problem reading reserved books from database");
		}
		return false;
	}

	public static void loanBookMethod() throws Exception {
		ArrayList<Book> book = new ArrayList<>();
		BookDatabase bd = new BookDatabase();
		try {
			book = bd.getAllBooks();
			displayBooks(book, 1);
		} catch (Exception e3) {
			errorMessage("Error, Problem reading from the database");
		}
		String s = "";
		do {
			s = JOptionPane.showInputDialog(null, "Please input the book ID?");
			if (s == null || (s != null && ("".equals(s)))) {
				throw new Exception();
			}
		} while (!parseString(s) || !isBookIdValid(allBooks(), Integer.parseInt(s)));
		int n = Integer.parseInt(s);
		bd = new BookDatabase();
		ld = new LoanDatabase();
		Book b = new Book();
		try {
			b = bd.getOneByBookId(n);
			displayBook(b);
			if (b.getNumOfCopies() > 0) {
				if (!isBookAlreadyOnLoanTable(n)) {
					int[] n1 = ld.addNewLoan(b.getBookId(), GUICommunication.getLoggedIn());
					if (n1[0] == 1 && n1[1] == 1) {
						errorMessage("The book was successfully added");
					} else
						errorMessage("Error, Problem reading from the database");
				} else {
					errorMessage("Book already on loan");
				}
			} else {
				if (JOptionPane.showConfirmDialog(null,
						"This book is currently unavailable\nWould you like to reserve it ? ") == JOptionPane.YES_OPTION) {
					BookReservationDatabase br = new BookReservationDatabase();
					ArrayList<BookReservation> r = new ArrayList<>();
					r = br.getOneByuserId(GUICommunication.getLoggedIn());
					if (r.isEmpty()) {
						br.reserveBook(GUICommunication.getLoggedIn(), b.getBookId());
						errorMessage("The book " + b.getBookName() + " has been reserved");
					} else {
						boolean alreadyReserved = false;
						for (BookReservation v : r) {
							if (n == v.getBookId()) {
								alreadyReserved = true;
							}
						}
						if (alreadyReserved) {
							errorMessage("Book already reserved");
						} else {
							br.reserveBook(GUICommunication.getLoggedIn(), n);
						}
					}
				}
			}
		} catch (Exception e2) {
		}
	}

	public static void unReserveBook(){
		if (displayReservedBooks()) {
			String input = "";
			try {
				do {
					input = JOptionPane.showInputDialog(null, "Please input the ID of book to un-reserve",
							"Un-Reserve Book");
					if (input == null || (input != null && ("".equals(input)))) {
							throw new Exception();
					}
				} while (!GUICommunication.validateUnreserveBookId(input));
				int bookId = Integer.parseInt(input);
				BookReservationDatabase brd = new BookReservationDatabase();
				brd.deleteReservation(bookId, GUICommunication.getLoggedIn());
				displayReservedBooks();
			} catch (Exception e1) {
			
			}
		}
	}
}
