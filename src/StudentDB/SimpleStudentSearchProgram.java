package StudentDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DBUtils;

public class SimpleStudentSearchProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in); 
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		

		System.out.println("===  Find student by id ===");

		System.out.print("Enter student by id: ");
		int studentId = Integer.parseInt(input.nextLine()); 
		
		try {
			// 1. Open a database connection
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);

			// 2. Define the SQL query text (NB! Exclamation mark is used as a place-holder
			// for a parameter value)
			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice  FROM Student WHERE id = ? ";

			// 3. Create a prepared statement based on the SQL query text
			preparedStatement = connection.prepareStatement(sqlText);

			// 4. Set the query parameter value(s) based on the place-holder number(s)
			preparedStatement.setInt(1, studentId);

			// 5. Execute the SQL query with the PreparedStatement object
			resultSet = preparedStatement.executeQuery();

			// 6. Iterate the results
			// NB! The next() method moves the cursor to the next available row in the
			// results. Initially, the cursor is 'before the first row'.
			// The next() method returns false if there are no more rows.
			boolean rowFound = false;

			while (resultSet.next()) {
				rowFound = true;

				// 7. Each column value has to be retrieved separately as below
				int id = resultSet.getInt("id"); 
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				System.out.println(id + ", " + firstname + " " + lastname + ", " + streetaddress + ", " + postcode + " " + postoffice);
			}

			if (rowFound == false) {
				System.out.println("Unknown student id (" + studentId + ")") ;
			}

		} catch (SQLException sqle) {
			// 8. If any JDBC operation fails, we display an error message here
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		} finally {
			// 9. The resources should be closed as soon as we don't need them anymore
			DBUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

	}

}
