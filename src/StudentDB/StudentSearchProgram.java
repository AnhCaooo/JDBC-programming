package StudentDB;

import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentSearchProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in); 
		System.out.println("=== Find student by id (DAO version) ===");

		System.out.print("Enter student id: ");
		int studentId = Integer.parseInt(input.nextLine()); 
		
		StudentDAO studentDAO = new StudentDAO();

		Student studentCheck = studentDAO.getStudentByID(studentId);

		if(studentCheck == null) {
			System.out.println("Unknown student id (" + studentId + ")") ;
		} else {
			System.out.println(studentDAO.getStudentByID(studentId).toString());
		}
		
	}
}
