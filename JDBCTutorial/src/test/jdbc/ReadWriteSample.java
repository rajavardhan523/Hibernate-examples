package test.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class ReadWriteSample {
	/*
     * Exercise 1: Read values from a file which contains comma seperated values
     * 
     * values.csv 
     *   1,Rakesh,4567 
     *   2,XYZ,1234 
     *   3,ABC,7891
     * 
     * Tip: Use StringTokenizer to split the values
     * http://crunchify.com/java-stringtokenizer-and-string-split-example/
     * 
     * Insert the data into a table
     */
	public static void main(String[] args) {
		List<Person> persons = readPersonsFromFile();
		
		savePersons(persons);
		
		System.out.println("Finished");
	}

	private static void savePersons(List<Person> persons) {
		Connection conn = null;
		PreparedStatement preStatement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			preStatement = conn.prepareStatement("insert into people (id,name,details) values (?,?,?)");
			
			for (Person person : persons) {
				System.out.println("Saving Person " + person);
				preStatement.setString(1, person.id);
				preStatement.setString(2, person.name);
				preStatement.setString(3, person.details);
				preStatement.executeUpdate();
			}
			
			//commit transaction
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//rollback transaction
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			//rollback transaction
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			closeResources(conn, preStatement);
		}

	}

	private static void closeResources(Connection conn,
			PreparedStatement preStatement) {

		try {
			if (preStatement != null)
				preStatement.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		// 1. Get Connection
		Class.forName("org.postgresql.Driver"); // load database interface
		// connect to the database
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/rajavardhan", "postgres", "1841");
		return conn;
	}

	private static List<Person> readPersonsFromFile() {
		File file = new File("/Users/rajavardhan/Desktop/Person");
		List<Person> persons = new ArrayList<Person>();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);

			String data = null;
			
			
			while ((data = buffer.readLine()) != null) {
				Person person = convertStringToPerson(data);
				persons.add(person);
			}
			
			buffer.close();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return persons;
	}

	private static Person convertStringToPerson(String data) {
		StringTokenizer tokenizer = new StringTokenizer(data, ",");
		String id = tokenizer.nextToken();
		String name = tokenizer.nextToken();
		String details = tokenizer.nextToken();

		return new Person(id, name, details);
	}

}
