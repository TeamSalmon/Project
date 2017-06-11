package projectsalmon;

import java.util.ArrayList;

public class StudentInCourse
{
	private Student student;
	private Course course;
	private ArrayList<StudentAssignment> submissions;
	
	public StudentInCourse(Student student, Course course)
	{
		this.student = student;
		this.course = course;
		submissions = new ArrayList<StudentAssignment>();
	}
	
	public Student getStudent(){return student;}
	public Course getCourse(){return course;}
	public ArrayList<StudentAssignment> getSubmissionts(){return submissions;}
}

