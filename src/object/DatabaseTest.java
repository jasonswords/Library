package object;
import java.sql.*;
import java.util.Properties;

public class DatabaseTest {

	//private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/softwareTesting";

	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		
		Properties p = new Properties();
	    p.put("user","root");
	    p.put("password","toor");
	    
		//System.out.println(dbClassName);
		
		//Class.forName(dbClassName);
		
		Connection c = DriverManager.getConnection(CONNECTION,p);
		
		//System.out.println("It works !");
		
		//-------------------------------------------------
		
		Statement m_Statement = c.createStatement();
		
		//String insertInfo = "CREATE TABLE IF NOT EXISTS book (bookId varchar(255),bookName varchar(255), author varchar(255), genre varchar(255)); ";
		//m_Statement.execute(insertInfo);
		
		String insertInfo = "INSERT INTO Book(bookId, bookName, author, genre)" + " VALUES('1','Love Hate', 'Nadine Flavin', 'Porn');";
		m_Statement.executeUpdate(insertInfo);
		
		
		
	    String query = "SELECT * FROM Book";

	    ResultSet m_ResultSet = m_Statement.executeQuery(query);

	    while (m_ResultSet.next()) {
	      System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2) + ", "
	          + m_ResultSet.getString(3)+", "+ m_ResultSet.getString(4));

	    }
		
		
		
		c.close();

	}

}
