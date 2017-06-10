package projectsalmon;

import java.util.ArrayList;
import java.util.Calendar;

public class StudentsClassInCourse
{
	Course course;
	StudentsClass studentsClass;
	private ArrayList<Class> classSchedule;
	Teacher teacher;
	
	public StudentsClassInCourse(Course course,StudentsClass studentsClass,ArrayList<Class> classSchedule,Teacher teacher)
	{
		this.course = course;
		this.studentsClass = studentsClass;
		this.classSchedule = classSchedule;
		this.teacher = teacher;
	}
	
	public boolean addClass(int day, int startHour, int startMinutes, int endHour, int endMinute)
	{
		if(studentsClass.getStudentsAmount() >= 30)
			return false;
		
		Calendar cStart = Calendar.getInstance();
		cStart.set(Calendar.DAY_OF_WEEK, day);
		cStart.set(Calendar.HOUR, startHour);
		cStart.set(Calendar.MINUTE, startMinutes);
		
		Calendar cEnd = Calendar.getInstance();
		cEnd.set(Calendar.DAY_OF_WEEK, day);
		cEnd.set(Calendar.HOUR, endHour);
		cEnd.set(Calendar.MINUTE, endMinute);
		
		classSchedule.add(new Class(cStart, cEnd));
		return true;
	}
	public boolean removeClass(){return false;}
	
	public void setCourse(Course course){this.course = course;}
	public void setStudentsClass(StudentsClass studentsClass){this.studentsClass = studentsClass;}
	public void setClassSchedule(ArrayList<Class> classSchedule){this.classSchedule = classSchedule;}
	public void setTeacher(Teacher teacher){this.teacher = teacher;}
	
	public Course getCourse(){return course;}
	public StudentsClass getStudentsClass(){return studentsClass;}
	public ArrayList<Class> getClassSchedule(){return classSchedule;}
	public Teacher getTeacher(){return teacher;}
}
