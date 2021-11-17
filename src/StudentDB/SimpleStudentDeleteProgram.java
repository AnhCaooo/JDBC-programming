package StudentDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameters;

public class SimpleStudentDeleteProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("===  Delete student ===");

		System.out.print("Enter student id: ");
		int studentId = Integer.parseInt(input.nextLine());

		try {
			// 1. Open a database connection
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);

			// 2. Define the SQL query text
			String sqlText = "DELETE FROM Student WHERE id = ?";

			// 3. Create a prepared statement based on the SQL query text
			preparedStatement = connection.prepareStatement(sqlText);

			// 4. Set the query parameter value(s) based on the place-holder number(s)
			preparedStatement.setInt(1, studentId);

			// 5. Execute the SQL query with the PreparedStatement object
			int result = preparedStatement.executeUpdate();

			// 6. If the result equals 1, student deleted
			// Otherwise, student id cannot found.

			if (result == 1) {
				System.out.println("Student deleted!");
			} else {

				System.out.println("Nothing deleted. Unknown student id (" + studentId + ")");
			}

		} catch (SQLException sqle) {
			// 7. If any JDBC operation fails, we display an error message here
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		}

	}

}
