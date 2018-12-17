package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionSample {
	
	public static void main(String[] args) {
		Connection conn; // holds database connection
		try {
			Class.forName("org.postgresql.Driver"); // load database interface
			// connect to the database
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/rajavardhan", "postgres",
					"1841");
			//set auto commit to false
			conn.setAutoCommit(false);
			try{
				saveCity(conn);
				saveUser(conn,false);
				System.out.println("Data saved successfully.. commiting the trasaction");
				conn.commit();
			} catch(SQLException ex){
				ex.printStackTrace();
				System.err.println("Error while saving data... rolling back the transaction");
				conn.rollback();
			}
			
			
			conn.close();

		} catch (Exception exc) {
			System.err.println("Exception caught.\n" + exc);
			exc.printStackTrace();
		}
	}

	private static void saveUser(Connection conn,boolean generateError) throws SQLException {
		PreparedStatement pst = conn.prepareStatement("insert into user_table(username,first_name,last_name,email_address,postal_code) values (?,?,?,?,?)");
		pst.setString(1, "newuser");
		pst.setString(2, "test first name");
		pst.setString(3, "test first name");
		pst.setString(4, "test email");
		if(generateError)
			pst.setString(5, "123456789");
		else
			pst.setString(5, "12345");
		
		int rowsInsertedOrUpdated = pst.executeUpdate();

		System.out.println(rowsInsertedOrUpdated+" were inserted in user");

	}

	private static void saveCity(Connection conn) throws SQLException {
		PreparedStatement pst = conn.prepareStatement("insert into cities (name,location) values(?,?)");
		pst.setString(1, "Test City");
		pst.setString(2, "Test Location");
		
		int rowsInsertedOrUpdated = pst.executeUpdate();

		System.out.println(rowsInsertedOrUpdated+" were inserted in city");

	}
}
