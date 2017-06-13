package Controllers;

import java.util.ArrayList;

import projectsalmon.Student;
import projectsalmon.StudentAssignment;

public abstract class StudentController
{
	public int calaculateGpa(ArrayList<StudentAssignment> arr)
	{
		int gpa=0;
		
		for(StudentAssignment a : arr)
			gpa += a.getGrade() * a.getAssignment().getPrecentagesOfFinalGrade();
		
		return gpa;
	}
}
