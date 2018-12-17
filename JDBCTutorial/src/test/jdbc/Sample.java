package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sample {

	public static void main(String args[]) {
		Connection conn; // holds database connection
		Statement stmt; // holds SQL statement

		try {
			//1. Get Connection
			//conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","password");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/rajavardhan", "postgres","1841");
			//2. Get Statement
			stmt = conn.createStatement();
			 // send the query
			
			//3. Handle Result
			/*
			//Read Data
			ResultSet res = stmt.executeQuery("select id,name,location from cities");
			while(res.next()){
				String id = res.getString("id");
				String name = res.getString("name");
				String location = res.getString("location");
				
				System.out.println("Details: Id= "+id+" ,Name= "+name+" ,Location= "+location);
			}
			*/
			/*
			//Insert
            String city = "pune";
            String location = "mh";
            String query = "insert into cities (name, location) values ('"+city+"','"+location+"')";
            System.out.println("Query is "+query);
            
            int numberOfRowsInserted = stmt.executeUpdate(query);
            System.out.println("Inserted "+numberOfRowsInserted);
            */
            /*
            //update data
            String query = "update cities set location = 'US'";
            System.out.println("Query is "+query);
            int numberOfRowsUpdated = stmt.executeUpdate(query);
            System.out.println("Updated "+numberOfRowsUpdated);
            */
			/*            
			//delete data
            String query = "delete from cities where name like 'pune%'";
            System.out.println("Query is "+query);
            int numberOfRowsDeleted = stmt.executeUpdate(query);
            System.out.println("Deleted "+numberOfRowsDeleted);            
            */	
			
			//4. Close resources
			//res.close();
			stmt.close();
			conn.close();
			System.out.println("Finished");	
		} catch (Exception exc) {
			System.err.println("Exception caught.\n" + exc);
			exc.printStackTrace();
		}
	}
}
