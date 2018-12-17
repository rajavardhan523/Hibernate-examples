package test.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class StoredProcedureExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver"); // load database interface
		// connect to the database
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/rajavardhan", "postgres",
				"1841");
		
		CallableStatement upperProc = conn
				.prepareCall("{ ? = call upper( ? ) }");
		upperProc.registerOutParameter(1, Types.VARCHAR);
		upperProc.setString(2, "lowercase to uppercase");
		upperProc.execute();
		
		String upperCased = upperProc.getString(1);
		System.out.println("Result is :"+upperCased);
		
		upperProc.close();
	}
}
