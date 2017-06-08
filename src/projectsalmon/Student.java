package projectsalmon;

import java.util.ArrayList;

public class Student extends User
{
	private float gpa;
	private String semesterSchedule;
	private ArrayList<StudentAssignment> personalFile;
	
	public Student(String id,String first, String last, String password)
	{
		super(id,first,last,password);
		gpa = 0;
		personalFile = new ArrayList<StudentAssignment>();
	}
	public void setGpa(float gpa){this.gpa = gpa;}
	public void setSemesterScheduke(String s){this.semesterSchedule = s;}
	
	public float getGpa(){return gpa;}
	public String getSemesterSchedule(){return semesterSchedule;}
	public ArrayList<StudentAssignment> getPersonalFile(){return personalFile;}
}
