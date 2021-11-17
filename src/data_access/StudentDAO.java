package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;



public class StudentDAO {
	public StudentDAO() {
		// In Tomcat 8 environment, the JDBC driver must be explicitly loaded as below
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}

	/**
	 * Open a new database connection
	 * 
	 * @throws SQLException
	 */
	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
				ConnectionParameters.password);
	}

	/**
	 * Retrieve all students from the database
	 * 
	 * @return List<Student>
	 * @throws SQLException
	 */
	public List<Student> getStudents() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student ORDER BY firstname, lastname";

			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode"); 
				String postoffice = resultSet.getString("postoffice"); 

				studentList.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));
			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getStudents() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DBUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return studentList;
	}

	/**
	 * Retrieve all students from the given postcode from the database
	 * 
	 * @param postcode - the postcode to be used as the filter in the query
	 * @return List<Student>
	 * @throws SQLException
	 */
	public List<Student> getStudentsFromTheGivenPostcode(String postcode) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student WHERE postcode = ? ORDER BY firstname";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setString(1, postcode);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postoffice = resultSet.getString("postoffice"); 

				studentList.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));
			}

		} catch (SQLException sqle) {
			System.out.println("[ERROR] StudentDAO: getStudentsFromTheGivenPostcode() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DBUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return studentList;
	}

	/**
	 * Insert a student into the database
	 * 
	 * @return 0 = Ok | 1 = Duplicate id | -1 = Other error
	 * @throws SQLException
	 */
	public int insertStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "INSERT INTO  (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstname());
			preparedStatement.setString(3, student.getLastname());
			preparedStatement.setString(4, student.getStreetaddress());
			preparedStatement.setString(5, student.getPostcode());
			preparedStatement.setString(6, student.getPostoffice());
			System.out.println(student);

			preparedStatement.executeUpdate();
			errorCode = 0;

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				errorCode = 1;
			} else {
				System.out.println("\n[ERROR] StudentDAO: insertStudent() failed. " + sqle.getMessage() + "\n");
				errorCode = -1;
			}

		} finally {
			DBUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}
}
