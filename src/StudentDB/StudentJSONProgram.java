package StudentDB;

import java.util.ArrayList;

import com.google.gson.Gson;

import data_access.StudentDAO;

public class StudentJSONProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Gson gson = new Gson();

		System.out.println("=== Print the JSON string (DAO version) ===");

		StudentDAO studentDAO = new StudentDAO();

		ArrayList<String> JSONstudent = studentDAO.getAllStudentsJSON();

		String jsonString = gson.toJson(JSONstudent); 
		
		if (JSONstudent == null) {
			System.out.println("The database is temporarily unavailable. Please try again later.");
		} else {
			System.out.println(jsonString);

		}
	}

}
