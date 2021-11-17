package StudentDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import data_access.ConnectionParameters;
import data_access.DBUtils;

public class CreateStudentTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement statement = null;

		try {
			// 1. Open a database connection
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);

			System.out.println("=== Creating and populating the Student table in "
					+ connection.getMetaData().getDatabaseProductName() + " ===");

			// 2. Create a statement object
			statement = connection.createStatement();

			// 3. Drop the Student table
			String sqlDropStudentTable = "DROP TABLE Student";
			try {
				statement.executeUpdate(sqlDropStudentTable);
				System.out.println("The existing Student table is dropped.");

			} catch (SQLException sqle) {
				// This time, no message here. If the table does not exist yet, DROP TABLE
				// fails.
			}

			// 4. Create the Movie table
			String sqlCreateStudentTable =
					"CREATE TABLE Student (" 
					+ " id INTEGER NOT NULL,"
					+ " firstname VARCHAR(100) NOT NULL,"
					+ " lastname VARCHAR(100) NOT NULL," 
					+ " streetaddress VARCHAR(100) NOT NULL,"
					+ " postcode CHAR(5) NOT NULL," 
					+ " postoffice VARCHAR(100) NOT NULL,"
					+ " CONSTRAINT pk_student PRIMARY KEY(id) )";

			statement.executeUpdate(sqlCreateStudentTable);
			System.out.println("The Student table is created.");

			// 5. Populate the Movie table
			String sqlInsertStudent =
					"INSERT INTO Student (id, firstname, lastname, streetaddress, postcode," 
							+ "postoffice) VALUES"
							+ "(30, 'Diana', 'Doe', 'Kuusikuja 6', '01200', 'VANTAA'),"
							+ "(20, 'Eric', 'Clapp', 'Luuttutie 4', '54120', 'PULP'),"
							+ "(10, 'Jack', 'Bruce', 'Asematori 3', '00520', 'HELSINKI'),"
							+ "(40, 'Ginger', 'Baker', 'Rumputie 10', '54120', 'PULP')";

			statement.executeUpdate(sqlInsertStudent);
			System.out.println("The Student table is populated with test data.");

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		} finally {
			DBUtils.closeQuietly(connection);
		}
	}

}
