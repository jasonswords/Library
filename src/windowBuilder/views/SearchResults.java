package windowBuilder.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import object.Book;
import testers.GUICommunication;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;

public class SearchResults extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtSearchAgain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchResults frame = new SearchResults(null);
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
	public SearchResults(ArrayList<Book> book) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 45, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 117, 913, 450);
		contentPane.add(scrollPane);
		for (Book b : book) {
			System.out.println(b.getBookName());
		}

		String[] col = { "Book ID", "Title", "Author", "Genre", "Number Available" };
		DefaultTableModel model = new DefaultTableModel(col, 0);
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(5);

		// table.getSelectedRow();

		Object[] rowData = new Object[5];
		for (int i = 0; i < book.size(); i++) {
			rowData[0] = book.get(i).getBookId();
			rowData[1] = book.get(i).getBookName();
			rowData[2] = book.get(i).getAuthor();
			rowData[3] = book.get(i).getGenre();
			rowData[4] = book.get(i).getNumOfCopies();

			model.addRow(rowData);
		}
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String key = txtSearchAgain.getText();
				try {
					ArrayList<Book> book = GUICommunication.searchMethod(key);
					if (book.isEmpty()) {
						txtSearchAgain.setText("No Results Found");
					} else {
						SearchResults results = new SearchResults(book);
						results.setVisible(true);
						dispose();
					}
				} catch (Exception e1) {
					txtSearchAgain.setText("Problem occured");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("AR BLANCA", Font.PLAIN, 29));
		btnNewButton.setBounds(703, 18, 111, 41);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("AR BLANCA", Font.PLAIN, 29));
		btnNewButton_1.setBounds(69, 18, 111, 41);
		contentPane.add(btnNewButton_1);

		txtSearchAgain = new JTextField();
		txtSearchAgain.setFont(new Font("AR BLANCA", Font.PLAIN, 29));
		txtSearchAgain.setText("Search Again");
		txtSearchAgain.setBackground(Color.LIGHT_GRAY);
		txtSearchAgain.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchAgain.setBounds(414, 17, 288, 41);
		contentPane.add(txtSearchAgain);
		txtSearchAgain.setColumns(10);

		JButton btnNewButton_2 = new JButton("Login");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("AR BLANCA", Font.PLAIN, 29));
		btnNewButton_2.setBounds(957, 16, 117, 43);
		contentPane.add(btnNewButton_2);
		
		JLabel Label3 = new JLabel("New label");
		Label3.setIcon(new ImageIcon(Main.class.getResource("/images/Untitled.jpg")));
		Label3.setBounds(0, 0, 1100, 650);
		contentPane.add(Label3);

	}
}
