package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactDAO {
	private static final String JDBC_PASSWORD = "1841";
	private static final String JDBC_USERNAME = "postgres";
	private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/phonebook";

	public void createContacts(List<Contact> contacts) throws SQLException {
		System.out.println("Creating Contacts");
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			try {
				for (Contact contact : contacts) {

					createPhoneBookContact(contact);
					createPhoneBookAddress(contact.getId(), contact.getAddress());
					createPhoneBookPhoneNumber(contact.getId(), contact.getPhoneNumbers());
					conn.commit();

				}
			} catch (SQLException e) {
				conn.rollback();
				e.printStackTrace();
				System.err.println("Error while saving data... rolling back the transaction");
			}
			
		} catch (Exception exc) {
			System.err.println("Exception caught.\n" + exc);
			exc.printStackTrace();
			
		}
		finally {
			conn.close();
		}
	}

	private void createPhoneBookAddress(String Id, Address address) {
		System.out.println("Creating Address");
		Connection conn = null;
		try {
			conn = getConnection();

			String sqlQuery = "insert into address (id,street,city,state,zipcode) values (?,?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			st.setInt(1, Integer.parseInt(Id));
			st.setString(2, address.getStreet());
			st.setString(3, address.getCity());
			st.setString(4, address.getState());
			st.setString(5, address.getZipCode());

			st.executeUpdate();

			System.out.println("Saving address " + address);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}

	private void createPhoneBookPhoneNumber(String Id, Set<PhoneNumber> phoneNumbers) {
		System.out.println("Creating PhoneNumber");
		Connection conn = null;
		try {
			conn = getConnection();
			String sqlQuery = "insert into phonenumber (id,phonetype,phonenumber) values (?,?,?);";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			for (PhoneNumber phoneNumber : phoneNumbers) {
				st.setInt(1, Integer.parseInt(Id));
				st.setString(2, phoneNumber.getPhoneType());
				st.setString(3, phoneNumber.getPhoneType());

				st.executeUpdate();

				System.out.println("Saving PhoneNumber " + phoneNumber);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

	}

	public void createPhoneBookContact(Contact contact) {
		System.out.println("Creating Contacts");
		Connection conn = null;
		try {
			conn = getConnection();

			String sqlQuery = "insert into contact (id,firstname,lastname) values (?,?,?)";
			PreparedStatement st = conn.prepareStatement(sqlQuery);

			st.setInt(1, Integer.parseInt(contact.getId()));
			st.setString(2, contact.getFirstName());
			st.setString(3, contact.getLastName());

			st.executeUpdate();
			System.out.println("Saving Contact " + contact);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

	}

	public void updateContacts(List<Contact> contacts) {
		System.out.println("Creating contacts");
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			try {
				for (Contact contact : contacts) {

					updatePhoneBookContact(contact);
					updatePhoneBookAddress(contact.getAddress(), contact.getId());
					updatePhoneBookPhoneNumber(contact.getPhoneNumbers(), contact.getId());
					conn.commit();

				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Error while saving data... rolling back the transaction");

				conn.rollback();
			}
			conn.close();
		} catch (Exception exc) {
			System.err.println("Exception caught.\n" + exc);
			exc.printStackTrace();
		}
	}

	private void updatePhoneBookAddress(Address address, String contactId) {
		System.out.println("Creating Address");
		Connection conn = null;
		try {
			conn = getConnection();

			String sqlQuery = "update address set street=?,city=?,state=?,zipcode=? where id=?";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			st.setString(1, address.getStreet());
			st.setString(2, address.getCity());
			st.setString(3, address.getState());
			st.setString(4, address.getZipCode());
			st.setInt(5, Integer.parseInt(contactId));
			st.executeUpdate();

			System.out.println("Updating address " + address);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}

	private void updatePhoneBookPhoneNumber(Set<PhoneNumber> phoneNumbers, String contactId) {
		System.out.println("Creating PhoneNumber");
		Connection conn = null;
		try {
			conn = getConnection();
			String sqlQuery = "update phonenumber set phonetype=?,phonenumber=? where id=?";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			for (PhoneNumber phoneNumber : phoneNumbers) {
				st.setString(1, phoneNumber.getPhoneType());
				st.setString(2, phoneNumber.getPhoneType());
				st.setInt(3, Integer.parseInt(contactId));
				st.executeUpdate();

				System.out.println("Updating PhoneNumber " + phoneNumber);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

	}

	public void updatePhoneBookContact(Contact contact) {
		System.out.println("Creating Contacts");
		Connection conn = null;
		try {
			conn = getConnection();

			String sqlQuery = "update contact set firstname=?,lastname=? where id=?";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			st.setString(1, contact.getFirstName());
			st.setString(2, contact.getLastName());
			st.setInt(3, Integer.parseInt(contact.getId()));
			st.executeUpdate();
			System.out.println("Updating Contact " + contact);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

	}

	public Contact readContact(String contactLastName) {
		System.out.println("Reading contact from phonebook");
		Contact contact = new Contact();
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			try {

				contact = readPhoneBookContact(contactLastName);
				contact.setAddress(readPhoneBookAddress(contact.getId()));
				contact.setPhoneNumbers(readPhoneBookPhoneNumber(contact.getId()));
				conn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Error while saving data... rolling back the transaction");

				conn.rollback();
			}
			conn.close();
		} catch (Exception exc) {
			System.err.println("Exception caught.\n" + exc);
			exc.printStackTrace();
		}
		return contact;
	}

	public Contact readPhoneBookContact(String contactLastName) {

		Connection conn = null;
		Contact contact = new Contact();
		try {
			conn = getConnection();

			String sqlQuery = "select * from contact where lastname =?";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			st.setString(1, contactLastName);
			System.out.println("Reading Contact  of " + contactLastName);
			ResultSet rs = st.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				contact.setId(id.toString());
				contact.setFirstName(rs.getString("firstname"));
				contact.setLastName(rs.getString("firstname"));

				System.out.println(rs.getInt("id") + "-" + rs.getString("firstname") + "-" + rs.getString("firstname"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		System.out.println("Returning " + contact);
		return contact;
	}

	private Address readPhoneBookAddress(String contactId) {
		System.out.println("Reading Address");
		Connection conn = null;
		Address address = new Address();
		try {
			conn = getConnection();

			String sqlQuery = "Select * from address where id=?";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			st.setInt(1, Integer.parseInt(contactId));

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				address.setStreet(rs.getString("street"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setZipCode(rs.getString("zipCode"));
				System.out.println("Reading address" + address);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);

		}
		return address;
	}

	private Set<PhoneNumber> readPhoneBookPhoneNumber(String contactId) {
		System.out.println("Reading PhoneNumber");
		Connection conn = null;
		Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		try {
			conn = getConnection();
			String sqlQuery = "select * from phonenumber where id=?";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			st.setInt(1, Integer.parseInt(contactId));

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				PhoneNumber phno = new PhoneNumber();
				phno.setPhoneNumber(rs.getString("phonenumber"));
				phno.setPhoneType(rs.getString("phonetype"));
				phoneNumbers.add(phno);
			}
			System.out.println("Reading PhoneNumbers" + phoneNumbers);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return phoneNumbers;

	}

	public void deleteContact(String contactLastName) {
		System.out.println("Deleting Contact from phonebook");
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			try {
				Contact contact = readPhoneBookContact(contactLastName);
				deletePhoneBookContact(contactLastName);
				deletePhoneBookAddress(contact.getId());
				deletePhoneBookPhoneNumber(contact.getId());
				conn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Error while saving data... rolling back the transaction");

				conn.rollback();
			}
			conn.close();
		} catch (Exception exc) {
			System.err.println("Exception caught.\n" + exc);
			exc.printStackTrace();
		}

	}

	private void deletePhoneBookContact(String contactLastName) {

		Connection conn = null;
		try {
			conn = getConnection();

			String sqlQuery = "delete from contact where lastname =?";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			st.setString(1, contactLastName);
			System.out.println("Deleting Contact " + contactLastName);

			st.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

	}

	private void deletePhoneBookPhoneNumber(String contactId) {
		System.out.println("Deleting phone number");
		Connection conn = null;
		try {
			conn = getConnection();

			String sqlQuery = "delete from phonenumber where id =?";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			st.setInt(1, Integer.parseInt(contactId));

			st.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}

	private void deletePhoneBookAddress(String contactId) {
		System.out.println("Deleting Address");
		Connection conn = null;
		try {
			conn = getConnection();

			String sqlQuery = "delete from phonenumber where id =?";
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			st.setInt(1, Integer.parseInt(contactId));

			st.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");

		Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
		return conn;
	}

	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
