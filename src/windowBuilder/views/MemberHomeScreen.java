package windowBuilder.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
import javax.swing.SwingConstants;

public class MemberHomeScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	DefaultTableModel model;
	JLabel lblNewLabel;
	Object[] rowData;
	JButton btnThisBook;
	ArrayList<Book> book;
	ArrayList<Loan> l;
	LoanDatabase ld;
	BookDatabase bd;

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */
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
		table.getModel().addTableModelListener(new TableModelListener() {

			public void tableChanged(TableModelEvent e) {
			}
		});

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
				int id = GUICommunication.getLoggedIn();
				ArrayList<Book> book = new ArrayList<Book>();
				try {
					book = GUICommunication.getBookFromLoan(id);
					if (book.isEmpty()) {
						JOptionPane.showConfirmDialog(null, "You currently have no books to return", "Information",
								JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
					} else if (book.size() == 1) {
						displayBooks(book);
						if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							LoanDatabase ld = new LoanDatabase();
							ld.deleteLoanDetails(book.get(0).getBookId());
						} else {
							JOptionPane.showConfirmDialog(null, "The book was not removed", "Information",
									JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						}

					} else {
						displayBooks(book);
						String s = "";
						do {
							s = JOptionPane.showInputDialog(null, "Please input the book ID?");
							if (s == null) {
								break;
							}
						} while (!parseString(s));
						int n = Integer.parseInt(s);
						LoanDatabase ld = new LoanDatabase();
						l = new ArrayList<>();
						l = ld.getOneByBookId(n);
						if (l.isEmpty()) {
							JOptionPane.showConfirmDialog(null, "Invalid ID", "Invalid ID", JOptionPane.DEFAULT_OPTION,
									JOptionPane.PLAIN_MESSAGE);
						} else {
							if (JOptionPane.showConfirmDialog(null, "Return this book ID " + n, "WARNING",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								ld = new LoanDatabase();
								ld.deleteLoanDetails(n);
								book = GUICommunication.getBookFromLoan(id);
								displayBooks(book);
							} else {
								JOptionPane.showConfirmDialog(null, "The book was not returned", "Information",
										JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
							}

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
				String s = "";
				do {
					s = JOptionPane.showInputDialog(null, "Please input the book ID?");
					if (s == null) {
						break;
					}
				} while (!parseString(s));
				int n = Integer.parseInt(s);
				bd = new BookDatabase();
				try {
					if (!bd.searchIfIdExists(n)) {
						System.out.println(bd.searchIfIdExists(n));
						ld = new LoanDatabase();
						Book b = new Book();
						try {
							b = bd.getOneByBookId(n);
							displayBooks(b);
							if (b.getNumOfCopies() > 0) {
								try {
									ArrayList<Loan> l = new ArrayList<>();
									l = ld.getAllLoansByuserId(GUICommunication.getLoggedIn());
									boolean alreadyOnLoan = true;
									for (Loan loan : l) {
										if (loan.getBookId() == n) {
											alreadyOnLoan = false;
										}
									}
									if (alreadyOnLoan) {
										ld.addNewLoan(b.getBookId(), GUICommunication.getLoggedIn());
									} else {
										JOptionPane.showConfirmDialog(null, "Book already on loan", "Loan",
												JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
									}
								} catch (Exception e1) {

								}
							} else {
								if (JOptionPane.showConfirmDialog(null,
										"This book is currently unavailable\nWould you like to reserve it ? ") == JOptionPane.YES_OPTION) {
									BookReservationDatabase br = new BookReservationDatabase();
									ArrayList<BookReservation> r = new ArrayList<>();
									r = br.getOneByuserId(GUICommunication.getLoggedIn());
									if (r.isEmpty()) {
										br.reserveBook(GUICommunication.getLoggedIn(), b.getBookId());
										JOptionPane.showConfirmDialog(null,
												"The book " + b.getBookName() + " has been reserved");
									} else {
										boolean alreadyReserved = false;
										for (BookReservation v : r) {
											if (n == v.getBookId()) {
												alreadyReserved = true;
											}
										}
										if (alreadyReserved) {
											// JOptionPane.(null, "This book is already reserved for you");
											JOptionPane.showConfirmDialog(null, "Book already reserved", "Reservation",
													JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
										} else {
											br.reserveBook(GUICommunication.getLoggedIn(), n);
										}

									}
								} else {
									JOptionPane.showConfirmDialog(null, "Please choose another book", "Unavailable",
											JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
								}
							}

						} catch (Exception e2) {
						}
					} else {
						JOptionPane.showConfirmDialog(null, "The id does not exist", "Invalid ID",
								JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
					}

				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "Invalid ID", "Invalid ID", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE);
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
					if (!key.equals("Name/Author/Genre")) {
						ArrayList<Book> book = GUICommunication.searchMethod(key);
						if (book.isEmpty()) {
							JOptionPane.showConfirmDialog(null, "No results found", "Information",
									JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						} else
							displayBooks(book);
					} else {
						ArrayList<Book> book = bd.getAllBooks();
						displayBooks(book);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "Unknown problem occurred", "Information",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
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

		JLabel Label3 = new JLabel("New label");
		Label3.setIcon(new ImageIcon(Main.class.getResource("/images/Untitled.jpg")));
		Label3.setBounds(0, 0, 1100, 650);
		contentPane.add(Label3);
	}

	/**
	 * 
	 * @param book
	 *            .. Takes in ArrayList of Book objects and displays in JTable
	 */
	public void displayBooks(ArrayList<Book> book) {
		model.setRowCount(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(5);
		rowData = new Object[5];
		for (int i = 0; i < book.size(); i++) {
			rowData[0] = book.get(i).getBookId();
			rowData[1] = book.get(i).getBookName();
			rowData[2] = book.get(i).getAuthor();
			rowData[3] = book.get(i).getGenre();
			rowData[4] = book.get(i).getNumOfCopies();
			model.addRow(rowData);
		}
	}

	/**
	 * 
	 * @param book
	 *            ..Takes in Book object and displays in JTable
	 */
	public void displayBooks(Book book) {
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

	/**
	 * 
	 * @param s
	 *            String. Method tests if string can be parsed to an integer
	 * @return true if possible, false if not
	 */
	public boolean parseString(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
