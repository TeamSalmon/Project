package projectsalmon;

import java.util.ArrayList;

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
	
	
}
