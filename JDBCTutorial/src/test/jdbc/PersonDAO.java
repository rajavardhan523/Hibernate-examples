package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

	private static final String JDBC_PASSWORD = "1841";
	private static final String JDBC_USERNAME = "postgres";
	private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/rajavardhan";

	public static void main(String[] args) {
		//create
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(new Person("1","Tom","tom details"));
		persons.add(new Person("2","Joe","Joe details"));
		
		PersonDAO personDAO = new PersonDAO();
		
		personDAO.createPersons(persons);
		
		personDAO.readPerson("Joe");
		
		personDAO.updatePersons(persons);
		
		personDAO.deletePerson("Tom");
	}
	
	
	public void createPersons(List<Person> persons) {
		System.out.println("Creating Persons");
		Connection conn = null;
		try {
			conn = getConnection();
			
			for (Person person : persons) {
				String sqlQuery = "insert into people (id,name,details) values ('"+person.id+"','"+person.name+"','"+person.details+"')";
				System.out.println("Saving Person " + person);
				Statement st = conn.createStatement();
				st.executeUpdate(sqlQuery);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

	}
	
	public void updatePersons(List<Person> persons) {
		System.out.println("Creating Persons");
		Connection conn = null;
		try {
			conn = getConnection();
			
			for (Person person : persons) {
				String sqlQuery = "update people set name='"+person.name+"',details='"+person.details+"' where id='"+person.id+"'";
				System.out.println("Updating Person " + person);
				Statement st = conn.createStatement();
				st.executeUpdate(sqlQuery);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}

	public List<Person> readPerson(String name) {
		System.out.println("Reading Persons");
		List<Person> persons = new ArrayList<Person>();
		Connection conn = null;
		try {
			conn = getConnection();
			
			String sqlQuery = "select id,name,details from people where name = ('"+name+"')";
			System.out.println("Reading Person " + name);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while(rs.next()){
				persons.add(new Person(rs.getString("id"),rs.getString("name"),rs.getString("details")));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		System.out.println("Returning "+persons);
		return persons;
	}

	public void deletePerson(String name) {
		System.out.println("Deleting Persons");
		Connection conn = null;
		try {
			conn = getConnection();
			
			String sqlQuery = "delete from people where name = ('"+name+"')";
			System.out.println("Deleting Person " + name);
			Statement st = conn.createStatement();
			st.executeUpdate(sqlQuery);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}

	private Connection getConnection() throws ClassNotFoundException,SQLException {
		// 1. Get Connection
		Class.forName("org.postgresql.Driver"); // load database interface
		// connect to the database
		Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
		return conn;
	}
	
	private void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
