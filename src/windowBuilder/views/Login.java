package windowBuilder.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import testers.GUICommunication;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEnterUsername;
	private JPasswordField passwordField;
	private JButton Button2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 45, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Label1 = new JLabel("Library Login");
		Label1.setForeground(Color.WHITE);
		Label1.setFont(new Font("AR DARLING", Font.BOLD, 65));
		Label1.setHorizontalAlignment(SwingConstants.CENTER);
		Label1.setBounds(295, 120, 549, 91);
		getContentPane().add(Label1);

		JLabel Label2 = new JLabel("Enter username and password");
		Label2.setForeground(Color.WHITE);
		Label2.setFont(new Font("AR DELANEY", Font.BOLD, 50));
		Label2.setHorizontalAlignment(SwingConstants.CENTER);
		Label2.setBounds(130, 214, 875, 58);
		getContentPane().add(Label2);

		JButton Button1 = new JButton("Login");
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Login")) {
					String user = txtEnterUsername.getText();
					char[] pass = passwordField.getPassword();
					try {
						int n = GUICommunication.validatePassword(user, pass);
						if (n == 1) {
							MemberHomeScreen memberHomeScreen = new MemberHomeScreen(null);
							memberHomeScreen.setVisible(true);
							dispose();
						} else if (n == 2) {
							AdminHomeScreen adminHomeScreen = new AdminHomeScreen();
							adminHomeScreen.setVisible(true);
							dispose();
						} else
							Label2.setText("Invalid Login");
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "Username or Password were invalid", "Information",
								JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
					}
				}

			}
		});
		Button1.setForeground(Color.BLACK);
		Button1.setFont(new Font("AR BLANCA", Font.PLAIN, 28));
		Button1.setBounds(598, 410, 136, 43);
		getContentPane().add(Button1);

		Button2 = new JButton("Home");
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				dispose();
			}
		});
		Button2.setForeground(Color.BLACK);
		Button2.setFont(new Font("AR BLANCA", Font.PLAIN, 28));
		Button2.setBounds(442, 410, 136, 43);
		getContentPane().add(Button2);

		txtEnterUsername = new JTextField();
		txtEnterUsername.setFont(new Font("AR BLANCA", Font.PLAIN, 29));
		txtEnterUsername.setBackground(Color.LIGHT_GRAY);
		txtEnterUsername.setText("Enter Username");
		txtEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterUsername.setBounds(351, 283, 446, 43);
		getContentPane().add(txtEnterUsername);
		txtEnterUsername.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter Password");
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(351, 355, 446, 43);
		getContentPane().add(passwordField);

		JLabel Label3 = new JLabel("New label");
		Label3.setIcon(new ImageIcon(Login.class.getResource("/images/Untitled.jpg")));
		Label3.setBounds(0, 0, 1100, 650);
		contentPane.add(Label3);
	}
}
