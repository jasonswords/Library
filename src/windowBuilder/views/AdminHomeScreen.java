package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import testers.GUICommunication;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomeScreen extends JFrame {

	private JPanel contentPane;

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
					e.printStackTrace();
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
		btnLogOut.setBounds(6, 6, 181, 47);
		contentPane.add(btnLogOut);
		
		JButton btnAddNewMember = new JButton("Members");
		btnAddNewMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAddNewMember.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnAddNewMember.setBounds(743, 47,  181, 47);
		contentPane.add(btnAddNewMember);
		
		JButton btnAddNewLibrarian = new JButton("Librarians");
		btnAddNewLibrarian.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnAddNewLibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddNewLibrarian.setBounds(482, 47,  181, 47);
		contentPane.add(btnAddNewLibrarian);
		
		JButton btnBooks = new JButton("Books");
		btnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBooks.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnBooks.setBounds(244, 47,  181, 47);
		contentPane.add(btnBooks);
		
		JButton btnLoans = new JButton("Loans");
		btnLoans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoans.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnLoans.setBounds(294, 194,  181, 47);
		contentPane.add(btnLoans);
		
		JButton btnReservations = new JButton("Reservations");
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReservations.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnReservations.setBounds(715, 194,  181, 47);
		contentPane.add(btnReservations);
		
//		JLabel Label3 = new JLabel("New label");
//		Label3.setIcon(new ImageIcon(Main.class.getResource("/images/Untitled.jpg")));
//		Label3.setBounds(0, 0, 1100, 650);
//		contentPane.add(Label3);
	}
}
