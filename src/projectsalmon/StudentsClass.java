package projectsalmon;
import java.util.ArrayList;

import java.util.ArrayList;

/**
 * 
 * @author Elia
 */
public class StudentsClass
{
	private ArrayList<Student> students;
	private String studentsClassId;
	private String level;
	private String className;
	private int studentsAmount;
	
	public StudentsClass(String className, String level, String studentsClassId)
	{
		this.className = className;
		students=null;
		this.level = level;		
		this.studentsAmount = 0;
		this.studentsClassId = studentsClassId;
	}
	
	
	public StudentsClass(String className, String level, String studentsClassId, int studentsAmount)
	{
		this.className = className;
		students=null;
		this.level = level;		
		this.studentsAmount = studentsAmount;
		this.studentsClassId = studentsClassId;
	}
	
	
	public void setlevel(String level){this.level = level;}

	public void setStudentsAmount(int amount)
	{
		studentsAmount = amount;
	}

	public void setClassId(String studentsClassId)
	{this.studentsClassId = studentsClassId;}
	
	public String getClassName() {
		return className;
	}
	
	public String getlevel(){return level;}
	public int getStudentsAmount(){return studentsAmount;}
	public String getClassId(){return studentsClassId;}
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
