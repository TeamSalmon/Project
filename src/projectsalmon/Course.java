package projectsalmon;

import java.util.ArrayList;

public class Course
{
	private String courseNumber;
	private String name;
	private String description;
	private float weeklyHours;
	private String teachingUnitId;

	// to return 
	private String year;
	private String semester;

	private ArrayList<Course> preCondition;
	private ArrayList<Integer> grades;
	private TeachingUnit teachingUnit;

// TeachingUnit teachingUnit from constrcutor
	public Course(String courseNumber, float weeklyHours, String courseName, String year, String semester, String teachingUnitId)
	{
		this.year = year;
		this.semester = semester;
		this.courseNumber = courseNumber;
		this.name = courseName;
		this.weeklyHours = weeklyHours;
		this.description = "";
		this.teachingUnit = new TeachingUnit(teachingUnitId,"");
	}
	
	public Course(String courseNumber, String courseName,String description, float weeklyHours,String teachingUnit)
{
		this.courseNumber = courseNumber;
		this.name = courseName;
		this.description = description;
		this.weeklyHours = weeklyHours;
		this.teachingUnitId = teachingUnit;
	}

	public Course(String courseNumber, String teachingUnit, float weeklyHours, String courseName)
	{
		this.courseNumber = courseNumber;
		this.name = courseName;
		this.weeklyHours = weeklyHours;
		this.description = "";
		this.teachingUnitId = teachingUnit;
	}
	
	public Course(String courseNumber)
	{
		this.courseNumber = courseNumber;
	}
	
	public String getCourseNumber(){return courseNumber;}
	public String getName(){return name;}
	public String getDescription(){return description;}
	public float getWeeklyHours(){return weeklyHours;}
	public ArrayList<Course> getPreCondition(){return preCondition;}
	public String getTeachingUnit(){return teachingUnitId;}
	
	public void setCourseNumber(String number){this.courseNumber = number;}
	public void setName(String name){this.name = name;}
	public void setDescription(String description){this.description = description;}
	public void setWeeklyHours(float hours){this.weeklyHours = hours;}
	public void setPreCondition(ArrayList<Course> preCondition){this.preCondition = preCondition;}
	public void setTeachingUnit(String teachingUnit){this.teachingUnitId = teachingUnit;}

	public ArrayList<Integer> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	public String toString()
	{
		return name + " ," + courseNumber;
	}
}
