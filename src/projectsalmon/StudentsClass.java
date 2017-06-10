package projectsalmon;

import java.util.ArrayList;

public class StudentsClass
{
	private int grade;
	private int classNum;
	private int studentsAmount;
	private ArrayList<Student> students;
	
	public StudentsClass(int classNum, int grade, ArrayList<Student> students)
	{
		this.classNum = classNum;
		this.grade = grade;
		this.studentsAmount = 0;
		this.students = students;
	}
	public StudentsClass(int classNum, int grade)
	{
		this.classNum = classNum;
		this.grade = grade;
		this.studentsAmount = 0;
	}
	
	public void setGrade(int grade){this.grade = grade;}
	public void setClassNum(int classNum){this.classNum = classNum;}
	
	public int getGrade(){return grade;}
	public int getClassNum(){return classNum;}
	public int getStudentsAmount(){return studentsAmount;}	
	public ArrayList<Student> getStudents(){return students;}
}
