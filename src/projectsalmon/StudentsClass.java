package projectsalmon;

import java.util.ArrayList;

/**
 * 
 * @author Elia
 */
public class StudentsClass
{
	private ArrayList<Student> students;
	private String studentsClassId;
	private String grade;
	private String className;
	private int studentsAmount;
	
	public StudentsClass(String className, String grade, String studentsClassId)
	{
		this.setClassName(className);
		this.grade = grade;
		this.studentsAmount = 0;
		this.studentsClassId = studentsClassId;
	}
	

	public void setGrade(String grade){this.grade = grade;}
	public String getGrade(){return grade;}
	public int getStudentsAmount(){return studentsAmount;}
	public void setStudentsAmount(int amount)
	{
		studentsAmount = amount;
	}
	public String getClassId(){return studentsClassId;}
	public void setClassId(String studentsClassId)
	{this.studentsClassId = studentsClassId;}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}


	public ArrayList<Student> getStudents() {
		return students;
	}


	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
}
