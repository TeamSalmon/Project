package projectsalmon;
import java.util.ArrayList;


public class StudentsClass
{
	private ArrayList<Student> students;
	private String studentsClassId;
	private String level;
	private String classNum;
	private int studentsAmount;
	
	public StudentsClass(String classNum, String level, String studentsClassId)
	{
		students=null;
		this.classNum = classNum;
		this.level = level;
		this.studentsAmount = 0;
		this.studentsClassId = studentsClassId;
	}
	
	public void setlevel(String level){this.level = level;}
	public void setClassNum(String classNum){this.classNum = classNum;}
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
	
	public String getlevel(){return level;}
	public String getClassNum(){return classNum;}
	public int getStudentsAmount(){return studentsAmount;}
	public String getClassId(){return studentsClassId;}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
}
