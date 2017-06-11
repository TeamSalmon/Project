package Controllers;

import java.util.ArrayList;

import projectsalmon.Student;
import projectsalmon.StudentAssignment;

public class StudentController
{
	//preventing from creating more than one instance of a controller type
	private static boolean limit = false;
	private ArrayList<Student> students;
	
	private StudentController()
	{
		students = new ArrayList<Student>();
	}
	public static synchronized StudentController getInstance()
	{
		if(!limit)
			return new StudentController();
		return null;
	}
	
	public void viewInfo()
	{
		
	}
	public void addAssignmentToFile(String id, StudentAssignment st)
	{
		for(Student s : students)
			if(s.getId().equals(id))
				s.getPersonalFile().add(st);
	}
}
