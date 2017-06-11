package projectsalmon;

import java.util.ArrayList;
import java.util.Calendar;

public class StudentsClassInCourse
{
	private Course course;
	private StudentsClass studentsClass;
	private ArrayList<Student> singleStudentsAdded;
	private ArrayList<Student> singleStudentsRemoved;
	private ArrayList<Class> classSchedule;
	private Teacher teacher;
	private int studentsAmount;
	
	public StudentsClassInCourse(Course course,StudentsClass studentsClass,ArrayList<Class> classSchedule,Teacher teacher)
	{
		this.course = course;
		this.studentsClass = studentsClass;
		this.classSchedule = classSchedule;
		this.teacher = teacher;
		singleStudentsAdded = new ArrayList<Student>();
		singleStudentsRemoved = new ArrayList<Student>();
		classSchedule = new ArrayList<Class>();
		studentsAmount = 0;
	}
	
	public boolean addClass(Class Aclass)
	{
		classSchedule.add(Aclass);
		teacher.addClass(Aclass);
		return true;
	}
	
	public boolean addSingleStudent(Student student)
	{
		if(studentsAmount<=30 )
		{
			singleStudentsAdded.add(student);
			studentsAmount++;
			return true;
		}
		return false;
	}
	public boolean removeSingleStudent(Student student)
	{
		int i = singleStudentsRemoved.indexOf(student);
		
		if(i == -1)
			return false;
		
		singleStudentsRemoved.remove(i);
		studentsAmount--;
		return true;
	}
	public void setCourse(Course course){this.course = course;}
	public void setStudentsClass(StudentsClass studentsClass){this.studentsClass = studentsClass;}
	public void setClassSchedule(ArrayList<Class> classSchedule){this.classSchedule = classSchedule;}
	public void setTeacher(Teacher teacher){this.teacher = teacher;}
	
	public Course getCourse(){return course;}
	public StudentsClass getStudentsClass(){return studentsClass;}
	public ArrayList<Class> getClassSchedule(){return classSchedule;}
	public Teacher getTeacher(){return teacher;}
}
