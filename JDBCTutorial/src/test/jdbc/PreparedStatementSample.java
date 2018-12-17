package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PreparedStatementSample {
	public static void main(String[] args) {
		Connection conn; // holds database connection
		Statement stmt; // holds SQL statement

		try {
			//1. Get Connection
			Class.forName("org.postgresql.Driver"); // load database interface
			// connect to the database
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/rajavardhan", "postgres",
					"1841");
			//stmt = conn.createStatement();
			//2. Get Statement
			/*
			String city = "DFW";
			String location = "Dalls";
			String query = "insert into cities (name, location) values ('"+city+"','"+location+"')";
			//execute Query 1
			
			city = "TX";
			location = "Texas";
			query = "insert into cities (name, location) values ('"+city+"','"+location+"')";
			//execute Query 2
			*/
			
			PreparedStatement pst = conn.prepareStatement("insert into cities (name,location) values(?,?)");
			
			for(int i = 0; i < 10; i++){
				pst.setString(1, "new test 77 "+i);
				pst.setString(2, "77777");
				
				//3. Execute Query
				int rowsInsertedOrUpdated = pst.executeUpdate();
	
				System.out.println(rowsInsertedOrUpdated+" were inserted");
			}
			
			//4. Close resources
			pst.close();
			conn.close();

		} catch (Exception exc) {
			System.err.println("Exception caught.\n" + exc);
			exc.printStackTrace();
		}
	}
}
