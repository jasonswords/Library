package windowBuilder.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dataBase.BookDatabase;
import dataBase.UserDatabase;
import object.Book;
import object.User;
import testers.GUICommunication;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AdminHomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model, model2;
	Object[] rowData, rowData2;
	ArrayList<User> user;
	ArrayList<Book> book;
	User u;
	Book b;
	UserDatabase ud;
	BookDatabase bd;
	JButton internalFrameButton;
	JInternalFrame internalFrame;
	private JTextField textField;
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
	private JTextField textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7;
	private String[] userLabels = { "Member ID", "First Name", "Sur-name", "Address", "Phone Number", "UserName","Password", "Privilege" };
	private String[] bookLabels = { "Book ID", "Title", "Author", "Genre", "Number Available" };
	private JButton btnViewAllBooks;
	private JTable table_1;
	private JScrollPane scrollPane_2;
	private JButton btnCheckLateReturns;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomeScreen frame = new AdminHomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					errorMessage("Error opening application");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminHomeScreen() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 45, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

////////////////////////////////////////////////////////////////////////////////////OUTER LOG OUT BUTTON  /////////////////////////////
		JButton btnBack = new JButton("Log Out");// main frame button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUICommunication.logOut();
				Main main = new Main();
				main.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(23, 27, 146, 29);
		contentPane.add(btnBack);

////////////////////////////////////////////////////////////////////////////////////OUTER ADD MEMBER BUTTON  /////////////////////////////
		JButton btnAddMember = new JButton("Add member");// main frame button
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseUserLabel(userLabels, "Add Member");
				textField.setVisible(false);
				label1.setVisible(false);
				clearTextFields();	
			}
		});
		btnAddMember.setBounds(23, 195, 146, 29);
		contentPane.add(btnAddMember);

////////////////////////////////////////////////////////////////////////////////////OUTER EDIT MEMBER BUTTON  /////////////////////////////
		JButton btnEditMember = new JButton("Edit Member");// main frame button
		btnEditMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseUserLabel(userLabels, "Edit Member");
				try {
					displayMemberInformation("edited", "Edit Member");
				} catch (Exception e1) {
					//errorMessage("Error, problem opening internal window");
				}
			}
		});
		btnEditMember.setBounds(23, 236, 146, 29);
		contentPane.add(btnEditMember);

////////////////////////////////////////////////////////////////////////////////////OUTER DELETE MEMBER BUTTON  /////////////////////////////
		JButton btnDeleteMember = new JButton("Delete Member");// main frame button
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextFields();
				initialiseUserLabel(userLabels, "Delete Member");
				try {
					displayMemberInformation("deleted", "Delete Member");
				} catch (Exception e1) {
					//errorMessage("Error, Problem opening internal window");
				}
			}
		});
		btnDeleteMember.setBounds(23, 277, 146, 29);
		contentPane.add(btnDeleteMember);

////////////////////////////////////////////////////////////////////////////////////OUTER VIEW ALL MEMBERS BUTTON  /////////////////////////////
		JButton btnViewAllMembers = new JButton("View all members");
		btnViewAllMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAllUsers();
			}
		});
		btnViewAllMembers.setBounds(23, 154, 146, 29);
		contentPane.add(btnViewAllMembers);
////////////////////////////////////////////////////////////////////////////////////  INNER FRAME  /////////////////////////////
		internalFrame = new JInternalFrame();
		internalFrame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setResizable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		internalFrame.setBounds(250, 83, 618, 364);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
////////////////////////////////////////////////////////////////////////////////////  INNER TEXT FIELDS AND LABELS  /////////////////////////////
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(225, 11, 130, 26);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(225, 49, 130, 26);
		internalFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(225, 87, 130, 26);
		internalFrame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(225, 125, 130, 26);
		internalFrame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(225, 163, 130, 26);
		internalFrame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(225, 201, 130, 26);
		internalFrame.getContentPane().add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(225, 239, 130, 26);
		internalFrame.getContentPane().add(textField_6);
		textField_6.setColumns(10);

		label1 = new JLabel();
		label1.setBounds(37, 11, 127, 26);
		internalFrame.getContentPane().add(label1);

		label2 = new JLabel();
		label2.setBounds(37, 49, 127, 25);
		internalFrame.getContentPane().add(label2);

		label3 = new JLabel();
		label3.setBounds(37, 87, 127, 25);
		internalFrame.getContentPane().add(label3);

		label4 = new JLabel();
		label4.setBounds(47, 125, 117, 25);
		internalFrame.getContentPane().add(label4);

		label5 = new JLabel();
		label5.setBounds(37, 163, 127, 25);
		internalFrame.getContentPane().add(label5);

		label6 = new JLabel();
		label6.setBounds(47, 202, 117, 25);
		internalFrame.getContentPane().add(label6);

		label7 = new JLabel();
		label7.setBounds(53, 239, 111, 25);
		internalFrame.getContentPane().add(label7);

		label8 = new JLabel();
		label8.setBounds(47, 280, 117, 23);
		internalFrame.getContentPane().add(label8);

		textField_7 = new JTextField();
		textField_7.setBounds(225, 277, 130, 26);
		internalFrame.getContentPane().add(textField_7);
		textField_7.setColumns(10);

////////////////////////////////////////////////////////////////////////////////////INNER CLEAR FIELDS BUTTON /////////////////////////////
		JButton btnCancel = new JButton("Clear Fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextFields();
			}
		});
		btnCancel.setBounds(449, 17, 117, 29);
		internalFrame.getContentPane().add(btnCancel);
		internalFrameButton = new JButton();
		internalFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
   /////////////////////////////////////////////////////////////////////////////////////  INNER ADD MEMBER BUTTON  /////////////////////////////
				if (e.getActionCommand().equals("Add Member")) {
					addMemberMethod();
   /////////////////////////////////////////////////////////////////////////////////////  INNER DELETE MEMBER BUTTON  /////////////////////////////
				} else if (e.getActionCommand().equals("Delete Member")) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure") == JOptionPane.YES_OPTION) {
						ud = new UserDatabase();
						try {
							int id = Integer.parseInt(textField.getText());
							ud.deleteUserDetails(id);
							internalFrame.doDefaultCloseAction();
							user = new ArrayList<>();
							user = ud.getAllUsers();
							displayUsers(user);
						} catch (SQLException e1) {
							errorMessage("A problem occured with the database");
						} catch (Exception e1) {
							errorMessage("A problem occured");
						}
					} else
						internalFrame.doDefaultCloseAction();
    /////////////////////////////////////////////////////////////////////////////////////  INNER EDIT MEMBER BUTTON  /////////////////////////////
				} else if (e.getActionCommand().equals("Edit Member")) {
						String array[] = { textField.getText(), textField_1.getText(), textField_2.getText(),
								textField_3.getText(), textField_4.getText(), textField_5.getText(),
								textField_6.getText(), textField_7.getText() };
					try {
						int b = GUICommunication.editUser(array);
						displayReturn(b);
					}catch (Exception e1) {
							errorMessage("No changes were saved");
						}
     /////////////////////////////////////////////////////////////////////////////////////  INNER ADD BOOK BUTTON  /////////////////////////////
				} else if (e.getActionCommand().equals("Add Book")) {
					String array[] = { textField_1.getText(), textField_2.getText(), textField_3.getText(),
							textField_4.getText() };
					try {
						int b = GUICommunication.addBook(array);
						displayReturn(b);
					} catch (Exception e1) {
						errorMessage("Error adding new book to database");
					}
    /////////////////////////////////////////////////////////////////////////////////////  INNER EDIT BOOK BUTTON  /////////////////////////////
				}else if (e.getActionCommand().equals("Edit Book")) {
					String array[] = { textField.getText(),textField_1.getText(), textField_2.getText(), textField_3.getText(),
							textField_4.getText() };
					try {
						int b = GUICommunication.editBook(array);
						displayReturn(b);
					} catch (Exception e1) {
						errorMessage("Error, Problem writing new data to database");
					}			
	/////////////////////////////////////////////////////////////////////////////////////  INNER DELETE BOOK BUTTON  /////////////////////////////				
				} else if (e.getActionCommand().equals("Delete Book")) {
					String s = textField.getText();
					try {
						int b = GUICommunication.deleteBook(s);
						displayReturn(b);
					} catch (Exception e1) {
						errorMessage("Error, Problem deleting book in database");
					}
				} 
				else
					internalFrame.doDefaultCloseAction();
			}
		});
		internalFrameButton.setBounds(449, 137, 117, 29);
		internalFrame.getContentPane().add(internalFrameButton);
////////////////////////////////////////////////////////////////////////////////////INNER VIEW ALL BOOKS BUTTON  /////////////////////////////
		btnViewAllBooks = new JButton("View all books");
		btnViewAllBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAllBooks();
			}
		});
		btnViewAllBooks.setBounds(933, 154, 140, 29);
		contentPane.add(btnViewAllBooks);
////////////////////////////////////////////////////////////////////////////////////INNER CANCEL BUTTON  /////////////////////////////
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.doDefaultCloseAction();
			}
		});
		btnCancel_1.setBounds(449, 273, 117, 29);
		internalFrame.getContentPane().add(btnCancel_1);
////////////////////////////////////////////////////////////////////////////////////OUTER ADD BOOK BUTTON  /////////////////////////////
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextFields();
				initialiseBookLabel(bookLabels,"Add Book");
			}
		});
		btnAddBook.setBounds(933, 195, 140, 29);
		contentPane.add(btnAddBook);
/////////////////////////////////////////////////////////////////////////////////////  OUTER EDIT BOOK BUTTON  /////////////////////////////
		JButton btnEditBook = new JButton("Edit Book");
		btnEditBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAllBooks();
				clearTextFields();
				initialiseBookLabel(bookLabels,"Edit Book");
				textField.setVisible(true);
				label1.setVisible(true);
				try {
					getValidInformation("edited", "Edit Book");
				} catch (Exception e1) {
//					errorMessage("Error, Problem reading the inputed information");
				}
			}
		});
		btnEditBook.setBounds(933, 236, 140, 29);
		contentPane.add(btnEditBook);
////////////////////////////////////////////////////////////////////////////////////OUTER DELETE BOOK BUTTON  /////////////////////////////
		JButton btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAllBooks();
				clearTextFields();
				initialiseBookLabel(bookLabels, "Delete Book");
				textField.setVisible(true);
				label1.setVisible(true);
				try {
					getValidInformation("deleted", "Delete Book");
				} catch (Exception e1) {
//					errorMessage("Error, Problem reading the inputed information");
				}
			}
		});
		btnDeleteBook.setBounds(933, 277, 140, 29);
		contentPane.add(btnDeleteBook);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 447, 918, 122);
		contentPane.add(scrollPane);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(195, 307, 722, 122);
		contentPane.add(scrollPane_2);

		table = new JTable();
		String[] col = { "Member ID", "First Name", "SurName", "Address", "Phone Number", "User Name", "Password",
				"Privilege" };
		model = new DefaultTableModel(col, 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		String[] col2 = { "Book ID", "Book Title", "Author", "Genre", "Copies Available" };
		model2 = new DefaultTableModel(col2, 0);
		table_1 = new JTable(model2);
		scrollPane_2.setViewportView(table_1);
		
		btnCheckLateReturns = new JButton("Check late returns");
		btnCheckLateReturns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					try {
						GUICommunication.displayLateReturnBooksForAllUsers();
//						GUICommunication.displayLateReturnBooksForSingleUser();
					} catch (Exception e1) {
						e1.printStackTrace();
//						errorMessage("Problem in late book return modeule");
					}
			}
		});
		btnCheckLateReturns.setBounds(933, 318, 140, 29);
		contentPane.add(btnCheckLateReturns);
		
		JLabel Label3 = new JLabel("New label");
		Label3.setIcon(new ImageIcon(Main.class.getResource("/images/Untitled.jpg")));
		Label3.setBounds(0, 0, 1100, 650);
		contentPane.add(Label3);
	}

	/////////////////////////////////////////////////////////////////////////   BOOKS ///////////////////////////////////////////
	
	
	// display all users to j table
	public void displayBooks(ArrayList<Book> book) {
		model2.setRowCount(0);
		rowData = new Object[5];
		for (int i = 0; i < book.size(); i++) {
			rowData[0] = book.get(i).getBookId();
			rowData[1] = book.get(i).getBookName();
			rowData[2] = book.get(i).getAuthor();
			rowData[3] = book.get(i).getGenre();
			rowData[4] = book.get(i).getNumOfCopies();
			model2.addRow(rowData);
		}
	}


	//get and validate information for editing and deleting books
	public void getValidInformation(String state, String buttonState) throws SQLException, Exception {
		internalFrame.setVisible(false);
		clearTextFields();
		String input = "";
		int id =0;
		do {
			displayAllBooks();
			input = JOptionPane.showInputDialog(null, "Please input the ID of book to be "+state, buttonState);
			if(input == null || (input != null && ("".equals(input))))   
			{
			    throw new Exception();
			}
		} while (!GUICommunication.isBookIdVallid(input));
			try {
				id = Integer.parseInt(input);
				bd = new BookDatabase();
				b = new Book();
				b = bd.getOneByBookId(id);
				textField.setText(String.valueOf(b.getBookId()));
				textField_1.setText(b.getBookName());
				textField_2.setText(b.getAuthor());
				textField_3.setText(b.getGenre());
				textField_4.setText(String.valueOf(b.getNumOfCopies()));
				internalFrameButton.setText(buttonState);
				label1.setVisible(true);
				internalFrame.setTitle(buttonState);
				internalFrame.setVisible(true);
				internalFrameButton.setText(buttonState);
			} catch (Exception e1) {
				errorMessage("Error occurred with retrieving user information");
			}
	}
	
	//display all books in j table
	public void displayAllBooks() {
		book = new ArrayList<>();
		bd = new BookDatabase();
		try {
			book = bd.getAllBooks();
			displayBooks(book);
		} catch (Exception e1) {
			errorMessage("Unknown problem occurred");
		}
	}
	
	/////////////////////////////////////////////////////////////////////////   USERS   ///////////////////////////////////////////

	// display arraylist of users in j table
		public void displayUsers(ArrayList<User> user) {
			model.setRowCount(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
			table.getColumnModel().getColumn(1).setPreferredWidth(50);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.getColumnModel().getColumn(4).setPreferredWidth(50);
			table.getColumnModel().getColumn(5).setPreferredWidth(50);
			table.getColumnModel().getColumn(6).setPreferredWidth(50);
			table.getColumnModel().getColumn(7).setPreferredWidth(10);
			rowData = new Object[8];
			for (int i = 0; i < user.size(); i++) {
				rowData[0] = user.get(i).getUserId();
				rowData[1] = user.get(i).getFirstName();
				rowData[2] = user.get(i).getSurName();
				rowData[3] = user.get(i).getAddress();
				rowData[4] = user.get(i).getPhone();
				rowData[5] = user.get(i).getUsername();
				rowData[6] = user.get(i).getPassword();
				rowData[7] = user.get(i).getPrivilege();
				model.addRow(rowData);
			}
		}
	
	
	// add new member to database
		public void addMemberMethod() {
			String array[] = { textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(),
					textField_5.getText(), textField_6.getText(), textField_7.getText() };
			try {
				int b = GUICommunication.addUsers(array);
				switch(b) {
				case 0:{
					errorMessage("New member successfully added to database");
					internalFrame.doDefaultCloseAction();break;
				}	case -1:{
					errorMessage("Privilege must an integer value\n1 for member\n2 for admin");break;
				}	case -2:{
					errorMessage("Privilege must be\n1 for member\n2 for admin");break;
				}	case -3:{
					errorMessage("Please fill in all fields");break;
				}	case -4:{
					errorMessage("Error, Problem occured writing to database");break;
				}	default:{
					errorMessage("Privilege must be\n1 for member\n2 for admin");break;
				}
				}//end switch
			}catch(Exception ex) {
				errorMessage("Error, Problem occured");
			}		
			displayAllUsers();
		}
	
	
	// delete member from system
		public void displayMemberInformation(String state, String buttonState) throws SQLException, Exception {
			displayAllUsers();//display all users in table
			internalFrame.setVisible(false);
			clearTextFields();//clear all text fields
			String input = "";
			int id = 0;//Initialize values 
			do {//display j input box to get id from user
				input = JOptionPane.showInputDialog(null, "Please input the ID of member to be "+state, buttonState);
				if(input == null || (input != null && ("".equals(input))))   
				{
				    throw new Exception();
				}
			} while (!GUICommunication.validateUserIdString(input));//check if inputed id exists in database
				try {
					id = Integer.parseInt(input);//parse to integer
					ud = new UserDatabase();
					u = new User();
					u = ud.getOneUserById(id);
					textField.setText(String.valueOf(u.getUserId()));//set the textfields to the user details
					textField_1.setText(u.getFirstName());
					textField_2.setText(u.getSurName());
					textField_3.setText(u.getAddress());
					textField_4.setText(u.getPhone());
					textField_5.setText(u.getUsername());
					textField_6.setText(u.getPassword());
					textField_7.setText(String.valueOf(u.getPrivilege()));
					internalFrameButton.setText(buttonState);
					label1.setVisible(true);
					internalFrame.setTitle(buttonState);
					internalFrame.setVisible(true);
					internalFrameButton.setText(buttonState);
				} catch (Exception e1) {
					errorMessage("Error occurred with retrieving user information");
				}

		}

		//display all the users
	public void displayAllUsers() {
		user = new ArrayList<>();
		ud = new UserDatabase();
		try {
			user = ud.getAllUsers();
			displayUsers(user);
		} catch (Exception e1) {
			errorMessage("Unknown problem occurred");
		}
	}

	/////////////////////////////////////////////////////////////////////////   METHODS ///////////////////////////////////////////
	//clear all text fields
	public void clearTextFields() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
	}

	//initialize labels with user text values
	public void initialiseUserLabel(String[] arr, String s) {
		label1.setText(arr[0]);
		label2.setText(arr[1]);
		label3.setText(arr[2]);
		label4.setText(arr[3]);
		label5.setText(arr[4]);
		label6.setText(arr[5]);
		label7.setText(arr[6]);
		label8.setText(arr[7]);
		internalFrame.setTitle(s);
		internalFrame.setVisible(true);
		internalFrameButton.setText(s);
		textField.setVisible(false);
		textField_5.setVisible(true);
		textField_6.setVisible(true);
		textField_7.setVisible(true);
		textField.setVisible(true);
		label1.setVisible(true);
		label6.setVisible(true);
		label7.setVisible(true);
		label8.setVisible(true);
	}

	//initialize labels with book text values
	public void initialiseBookLabel(String[] arr, String s) {
		label1.setText(arr[0]);
		label2.setText(arr[1]);
		label3.setText(arr[2]);
		label4.setText(arr[3]);
		label5.setText(arr[4]);
		textField_5.setVisible(false);
		textField_6.setVisible(false);
		textField_7.setVisible(false);
		label6.setVisible(false);
		label7.setVisible(false);
		label8.setVisible(false);
		internalFrame.setTitle(s);
		internalFrame.setVisible(true);
		internalFrameButton.setText(s);
		textField.setVisible(false);
		label1.setVisible(false);
	}
	
	public static void errorMessage(String error) {
		JOptionPane.showConfirmDialog(null, error, "Information", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}
	
	public void displayReturn(int b) {
		switch(b) {
		case 0:{
			displayAllBooks();
			errorMessage("The operation has been successfull !!!");
			internalFrame.doDefaultCloseAction();
			break;
		}case -1:{
			errorMessage("Error occurred writing to database");break;
		}case -2:{
			errorMessage("Please input valid number of bookd\nMust be greater than 0\nLess than 100");break;
		}case -3:{
			errorMessage("Error, Please complete all fields");break;
		}case -4:{
			errorMessage("Please eneter a valid integer value");break;
		}default:{
			errorMessage("Error, Problem has occurred");break;
		}
		}
	}
}
