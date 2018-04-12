package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import object.Book;

public class Borrow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	JLabel lblNewLabel, displayLabel;
	Object[] rowData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Borrow frame = new Borrow();
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
	public Borrow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 45, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

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
	}

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

}
