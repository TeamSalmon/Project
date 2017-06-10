package projectsalmon;
import java.io.File;

public class StudentAssignment
{
	Student student;
	Assignment assignment;
	File submission;
	
	public StudentAssignment(Student student, Assignment assignment)
	{
		this.student = student;
		this.assignment = assignment;
	}
	
	public File getSubmission(){return submission;}
}
