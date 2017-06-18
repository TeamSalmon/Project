package projectsalmon;
import java.util.ArrayList;


public class StudentsClass
{
	private ArrayList<Student> students;
	private String studentsClassId;
	private int grade;
	private int classNum;
	private int studentsAmount;
	
	public StudentsClass(int classNum, int grade, String studentsClassId)
	{
		students=null;
		this.classNum = classNum;
		this.grade = grade;
		this.studentsAmount = 0;
		this.studentsClassId = studentsClassId;
	}
	
	public void setGrade(int grade){this.grade = grade;}
	public void setClassNum(int classNum){this.classNum = classNum;}
	public boolean addStudent()
	{
		if(studentsAmount>=30)
			return false;
		studentsAmount++;
		return true;
	}
	public boolean removeStudent()
	{
		if(studentsAmount == 0)
			return false;
		studentsAmount--;
		return true;
	}
	
	public int getGrade(){return grade;}
	public int getClassNum(){return classNum;}
	public int getStudentsAmount(){return studentsAmount;}
	public String getClassId(){return studentsClassId;}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
}
