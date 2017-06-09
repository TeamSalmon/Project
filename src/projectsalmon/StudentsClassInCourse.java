package projectsalmon;

import java.util.ArrayList;
import java.util.Calendar;

public class StudentsClassInCourse
{
	Course course;
	StudentsClass studentsClass;
	ArrayList<Calendar> classSchedule;
	Teacher teacher;
	
	public StudentsClassInCourse(Course course,StudentsClass studentsClass,ArrayList<Calendar> classSchedule,Teacher teacher)
	{
		this.course = course;
		this.studentsClass = studentsClass;
		this.classSchedule = classSchedule;
		this.teacher = teacher;
	}
	
	public void setCourse(Course course){this.course = course;}
	public void setStudentsClass(StudentsClass studentsCass){this.studentsClass = studentsClass;}
	public void setClassSchedule(ArrayList<Calendar> classSchedule){this.classSchedule = classSchedule;}
	public void setTeacher(Teacher teacher){this.teacher = teacher;}
	
	public Course getCourse(){return course;}
	public StudentsClass getStudentsClass(){return studentsClass;}
	public ArrayList<Calendar> getClassSchedule(){return classSchedule;}
	public Teacher getTeacher(){return teacher;}
}
