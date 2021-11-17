package StudentDB;

import java.util.Scanner; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import data_access.ConnectionParameters;
import data_access.DBUtils;

public class SimpleStudentInsertProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in); 
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		System.out.println("=== Add student === \n");
		
 		System.out.print("Id: ");
 		int studentId = Integer.parseInt(input.nextLine()); 
 		
 		System.out.print("First name: ");
 		String firstname = input.nextLine(); 
 		
 		System.out.print("Last name: ");
 		String lastname = input.nextLine();
 		
 		System.out.print("Street: ");
 		String street = input.nextLine();
 		
 		System.out.print("Postcode: ");
 		String postcode = input.nextLine(); 
 		
 		System.out.print("Post office: ");
 		String postoffice = input.nextLine(); 
 		
 		System.out.println();
		try {
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);
							
			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);
			preparedStatement.setString(2, firstname);
			preparedStatement.setString(3, lastname);
			preparedStatement.setString(4, street);
			preparedStatement.setString(5, postcode);
			preparedStatement.setString(6, postoffice);
			
			preparedStatement.executeUpdate();

			System.out.println("Student data added!");
			
		} catch (SQLException sqle) {
			// First, check if the problem is primary key violation (the error code is vendor-dependent)
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				System.out.println("Cannot add the student. " + "The student id (" + studentId + ") is already in use.");
			} else {
				System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
			}
			
		} finally {
			DBUtils.closeQuietly(preparedStatement, connection);
		}
	}

}
