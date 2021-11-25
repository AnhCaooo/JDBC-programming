package StudentDB;

import java.util.Scanner;

import data_access.StudentDAO;

public class StudentDeleteProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		StudentDAO studentDAO = new StudentDAO();
		
		System.out.println("===  Delete student (DAO version) ===");

		System.out.print("Enter student id: ");
		int studentId = Integer.parseInt(input.nextLine());
		
		int deleteId = studentDAO.deleteStudent(studentId); 
		
		if(deleteId == 0) {
			System.out.println("Delete operation is completed successfully");
		} else if(deleteId == 1) {
			System.out.println("The student id is not found");
		} else if (deleteId == -1) {
			System.out.println("The operation fails");
		}
		
	}

}
