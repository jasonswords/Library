package windowBuilder.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import object.Book;
import testers.GUICommunication;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearchByTitle;
	JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 45, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("WELCOME");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("AR DARLING", Font.BOLD | Font.ITALIC, 65));
		label.setBounds(241, 179, 667, 64);
		getContentPane().add(label);

		txtSearchByTitle = new JTextField();
		txtSearchByTitle.setFont(new Font("AR BLANCA", Font.PLAIN, 29));
		txtSearchByTitle.setText("Search by Title of the Book,Author or Genre");
		txtSearchByTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchByTitle.setBackground(Color.LIGHT_GRAY);
		txtSearchByTitle.setBounds(241, 267, 656, 41);
		contentPane.add(txtSearchByTitle);
		txtSearchByTitle.setColumns(10);

		JButton Button1 = new JButton("Search");
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = txtSearchByTitle.getText();
				try {
					ArrayList<Book> book = GUICommunication.searchMethod(key);
					if (book.isEmpty()) {
						label.setText("No Results Found");
					} else {
						SearchResults results = new SearchResults(book);
						results.setVisible(true);
						dispose();
					}
				} catch (Exception e1) {
					label.setText("Problem occured");
					e1.printStackTrace();
				}
			}
		});
		Button1.setFont(new Font("AR BLANCA", Font.PLAIN, 29));
		Button1.setBounds(517, 335, 111, 41);
		getContentPane().add(Button1);

		JButton Button2 = new JButton("Login");
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		Button2.setFont(new Font("AR BLANCA", Font.PLAIN, 26));
		Button2.setBounds(490, 524, 165, 41);
		getContentPane().add(Button2);

		JLabel Label3 = new JLabel("New label");
		Label3.setIcon(new ImageIcon(Main.class.getResource("/images/Untitled.jpg")));
		Label3.setBounds(0, 0, 1100, 650);
		contentPane.add(Label3);
	}
}
