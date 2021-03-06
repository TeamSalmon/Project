package projectsalmon;

import java.util.ArrayList;

public class Student extends User
{
	private float gpa;
	private ArrayList<StudentAssignment> personalFile;
	private StudentsClass sClass;
	private String classId;
	
	public Student(String id, String first, String last, String password)
	{
		super(id,first,last,password);
		gpa = 0;
		personalFile = new ArrayList<StudentAssignment>();
	}
	
	public Student(String id, String first, String last, String password, String classId)
	{
		super(id,first,last,password);
		this.classId = classId;
	}
	
	public void setGpa(float gpa){this.gpa = gpa;}
	
	public void setClass(StudentsClass sClass)
	{
		this.sClass = sClass;		
		
		/*if(sClass.addStudent())
		{
		this.sClass = sClass;
		return true;
		}
		return false;*/
	}
	
	public float getGpa(){return gpa;}
	public ArrayList<StudentAssignment> getPersonalFile(){return personalFile;}
	public StudentsClass getStudentClass(){return sClass;}
	
	public void AddToPersonalFile(StudentAssignment sa){personalFile.add(sa);}
}
