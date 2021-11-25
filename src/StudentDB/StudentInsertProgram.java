package StudentDB;

import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentInsertProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);

		StudentDAO studentDAO = new StudentDAO();

		
		System.out.println("=== Add student (DAO version) === \n");

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
		Student insertStudent = new Student(studentId, firstname, lastname, street, postcode, postoffice); 
		
		int checkInsertStudent = studentDAO.insertStudent(insertStudent); 
		
		if(checkInsertStudent == -1 ) {
			System.out.println("insertStudent() failed");
		} else if (checkInsertStudent == 0) {
			System.out.println("Student data added!");
		} else if (checkInsertStudent == 1) {
			System.out.println("Cannot add the student. " + "The student id (" + studentId + ") is already in use.");
		}
		
	}

}
