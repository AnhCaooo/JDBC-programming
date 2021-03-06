package StudentDB;

import java.util.List;

import data_access.StudentDAO;
import model.Student;

public class StudentListProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("=== Print students (DAO version) ===");

		StudentDAO studentDAO = new StudentDAO();

		List<Student> studentList = studentDAO.getAllStudents();

		if (studentList == null) {
			System.out.println("The database is temporarily unavailable. Please try again later.");
		} else {
			for (Student student : studentList) {
				System.out.println(student.getId() + ": " + student.getFirstname() + " " + student.getLastname() + ", " + student.getStreetaddress() + ", " + student.getPostcode() + " " + student.getPostoffice());
			}
		}
	}

}
