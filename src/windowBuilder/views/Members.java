package windowBuilder.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import dataBase.UserDatabase;
import object.User;
import testers.GUICommunication;

public class Members extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	Object[] rowData;
	ArrayList<User> user;
	User u;
	UserDatabase ud;
	JButton internalFrameButton;
	JInternalFrame internalFrame;
	private JTextField textField;
	JLabel lblMemberId;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	int id;
	String[] popUpMessage = { "Error Occured", "Privilege must be numerical value\n1 for member\n2 for admin staff",
			"The privilege value is not in the correct range", "All fields must be completed",
			"A problem arose adding the data to the database", "The new user was added successfully" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Members frame = new Members();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Members() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 45, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBack = new JButton("Back");// main frame button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHomeScreen admin = new AdminHomeScreen();
				admin.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(19, 200, 117, 29);
		contentPane.add(btnBack);

		JButton btnAddMember = new JButton("Add member");// main frame button
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setTitle("Add Member");
				internalFrame.setVisible(true);
				internalFrameButton.setText("Add Member");
				textField.setVisible(false);
				lblMemberId.setVisible(false);
			}
		});
		btnAddMember.setBounds(38, 310, 117, 29);
		contentPane.add(btnAddMember);

		JButton btnEditMember = new JButton("Edit member");// main frame button
		btnEditMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editMemberMethod();
			}
		});
		btnEditMember.setBounds(38, 370, 117, 29);
		contentPane.add(btnEditMember);

		JButton btnDeleteMember = new JButton("Delete member");// main frame button
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMemberMethod();
			}
		});
		btnDeleteMember.setBounds(38, 339, 117, 29);
		contentPane.add(btnDeleteMember);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 447, 800, 122);
		contentPane.add(scrollPane);

		table = new JTable();
		String[] col = { "Member ID", "First Name", "SurName", "Address", "Phone Number", "User Name", "Password",
				"Privilege" };
		model = new DefaultTableModel(col, 0);
		table = new JTable(model);
		table.getModel().addTableModelListener(new TableModelListener() {

			public void tableChanged(TableModelEvent e) {
			}
		});
		scrollPane.setViewportView(table);

		JButton btnViewAllMembers = new JButton("View all members");
		btnViewAllMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAllUsers();

			}
		});
		btnViewAllMembers.setBounds(38, 269, 117, 29);
		contentPane.add(btnViewAllMembers);

		internalFrame = new JInternalFrame();
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setResizable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		internalFrame.setBounds(167, 69, 482, 353);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(107, 6, 130, 26);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(107, 45, 130, 26);
		internalFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(107, 83, 130, 26);
		internalFrame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(107, 121, 130, 26);
		internalFrame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(107, 159, 130, 26);
		internalFrame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(107, 197, 130, 26);
		internalFrame.getContentPane().add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(107, 235, 130, 26);
		internalFrame.getContentPane().add(textField_6);
		textField_6.setColumns(10);

		lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(16, 11, 61, 16);
		internalFrame.getContentPane().add(lblMemberId);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(16, 50, 61, 16);
		internalFrame.getContentPane().add(lblFirstName);

		JLabel lblSurname = new JLabel("Sur-name");
		lblSurname.setBounds(16, 88, 61, 16);
		internalFrame.getContentPane().add(lblSurname);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(16, 126, 61, 16);
		internalFrame.getContentPane().add(lblAddress);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(16, 164, 61, 16);
		internalFrame.getContentPane().add(lblPhoneNumber);

		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(16, 202, 61, 16);
		internalFrame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(16, 240, 61, 16);
		internalFrame.getContentPane().add(lblPassword);

		textField_7 = new JTextField();
		textField_7.setBounds(107, 275, 130, 26);
		internalFrame.getContentPane().add(textField_7);
		textField_7.setColumns(10);

		JLabel lblPrivilege = new JLabel("Privilege");
		lblPrivilege.setBounds(16, 280, 61, 16);
		internalFrame.getContentPane().add(lblPrivilege);

		JButton btnCancel = new JButton("Clear");// internal frame button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextFields();
			}
		});
		btnCancel.setBounds(320, 17, 117, 29);
		internalFrame.getContentPane().add(btnCancel);

		internalFrameButton = new JButton();// internal frame button
		internalFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Add Member")) {
					addMemberMethod();
				} else if (e.getActionCommand().equals("Delete Member")) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure") == JOptionPane.YES_OPTION) {
						ud = new UserDatabase();
						try {
							ud.deleteUserDetails(id);
							internalFrame.doDefaultCloseAction();
							user = new ArrayList<>();
							user = ud.getAllUsers();
							displayUsers(user);
						} catch (SQLException e1) {
							JOptionPane.showConfirmDialog(null, "A problem occured with the database", "Information",
									JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						} catch (Exception e1) {
							JOptionPane.showConfirmDialog(null, "A problem occured", "Information",
									JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						}
					} else
						internalFrame.doDefaultCloseAction();

				} else if (e.getActionCommand().equals("Edit Member")) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure") == JOptionPane.YES_OPTION) {
						String array[] = { textField.getText(),textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(),
								textField_5.getText(), textField_6.getText(), textField_7.getText() };
						int b = 0;
						try {
							b = GUICommunication.editUser(array);
							if (b == 5) {
								displayAllUsers();
								internalFrame.doDefaultCloseAction();
							}
						} catch (Exception e1) {
							b = 0;
						}
						JOptionPane.showConfirmDialog(null, popUpMessage[b], "Information", JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE);
						}
					} else
						internalFrame.doDefaultCloseAction();
				}
		});
		internalFrameButton.setBounds(320, 98, 117, 29);
		internalFrame.getContentPane().add(internalFrameButton);

		JButton btnCancel_1 = new JButton("Cancel");// internal frame button
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.doDefaultCloseAction();
			}
		});
		btnCancel_1.setBounds(320, 159, 117, 29);
		internalFrame.getContentPane().add(btnCancel_1);

	}

	//display all users to j table
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
		int b = 0;
		try {
			b = GUICommunication.addUsers(array);
			if (b == 5) {
				displayAllUsers();
				internalFrame.doDefaultCloseAction();
			}
		} catch (Exception e1) {
			b = 0;
		}
		JOptionPane.showConfirmDialog(null, popUpMessage[b], "Information", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}

	// delete member from system
	public void deleteMemberMethod() {
		displayAllUsers();
		clearTextFields();
		JOptionPane pane;
		String input = "";
		id = 0;
		do {
			pane = new JOptionPane();
			input = pane.showInputDialog(null, "Please input the ID of member to be deleted", "Delete Member");
			if (input == null) {
				pane.setEnabled(false);
			} else
				id = GUICommunication.validateIdString(input);
		} while (id == -1);
		if (pane.isEnabled() == true) {
			try {
				id = Integer.parseInt(input);
				ud = new UserDatabase();
				u = new User();

				u = ud.getOneUserById(id);
				textField.setText(String.valueOf(u.getUserId()));
				textField_1.setText(u.getFirstName());
				textField_2.setText(u.getSurName());
				textField_3.setText(u.getAddress());
				textField_4.setText(u.getPhone());
				textField_5.setText(u.getUsername());
				textField_6.setText(u.getPassword());
				textField_7.setText(String.valueOf(u.getPrivilege()));
				internalFrameButton.setText("Delete Member");
				internalFrame.setTitle("Delete Member");
				internalFrame.setVisible(true);
				internalFrameButton.setText("Delete Member");
			} catch (Exception e1) {
				JOptionPane.showConfirmDialog(null, "Error occurred with retrieving user information", "Information",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
		}

	}

	// edit member details
	public void editMemberMethod() {
		displayAllUsers();
		clearTextFields();
		JOptionPane pane;
		String input = "";
		id = 0;
		do {
			pane = new JOptionPane();
			input = pane.showInputDialog(null, "Please input the ID of member to edit", "Delete Member");
			if (input == null) {
				pane.setEnabled(false);
			} else
				id = GUICommunication.validateIdString(input);
		} while (id == -1);
		if (pane.isEnabled() == true) {
			try {
				id = Integer.parseInt(input);
				ud = new UserDatabase();
				u = new User();

				u = ud.getOneUserById(id);
				textField.setText(String.valueOf(u.getUserId()));
				textField_1.setText(u.getFirstName());
				textField_2.setText(u.getSurName());
				textField_3.setText(u.getAddress());
				textField_4.setText(u.getPhone());
				textField_5.setText(u.getUsername());
				textField_6.setText(u.getPassword());
				textField_7.setText(String.valueOf(u.getPrivilege()));

				internalFrame.setTitle("Edit Member");
				internalFrame.setVisible(true);
				internalFrameButton.setText("Edit Member");
			} catch (Exception e1) {
				JOptionPane.showConfirmDialog(null, "Error occurred with retrieving user information", "Information",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
		}

	}

	public void displayAllUsers() {
		user = new ArrayList<>();
		ud = new UserDatabase();
		try {
			user = ud.getAllUsers();
			displayUsers(user);
		} catch (Exception e1) {
			JOptionPane.showConfirmDialog(null, "Unknown problem occurred", "Information", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
		}
	}

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
}
