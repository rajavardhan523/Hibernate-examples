package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProcedureComplexSample {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {

		Class.forName("org.postgresql.Driver"); // load database interface
		// connect to the database
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/rajavardhan", "postgres",
				"1841");

		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM get_user_credentials(?,?)");

		ps.setString(1, "testname");
		ps.setString(2, "test@test.com");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getString(1) + " : " + rs.getString(2)
					+ " : " + rs.getString(3));
		}

		conn.close();
	}
}
