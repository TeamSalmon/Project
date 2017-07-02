package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentInCourse implements Serializable
{
	private Student student;
	private Course course;
	private int grade;
	private ArrayList<StudentAssignment> submissions;
	
	public StudentInCourse(Student student, Course course)
	{
		this.student = student;
		this.course = course;
		submissions = new ArrayList<StudentAssignment>();
		grade = -1;
	}
	
	public Student getStudent(){return student;}
	public Course getCourse(){return course;}
	public ArrayList<StudentAssignment> getSubmissionts(){return submissions;}
	public int getGrade(){return grade;}
	
	public void setGrade(int grade){this.grade = grade;}
}

