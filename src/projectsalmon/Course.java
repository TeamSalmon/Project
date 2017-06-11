package projectsalmon;

import java.util.ArrayList;

public class Course
{
	private String courseNumber;
	private String name;
	private String description;
	private float weeklyHours;
	private ArrayList<Course> preCondition;
	
	public Course(String courseNumber, String name, String description, float weeklyHours, String courseName)
	{
		this.courseNumber = courseNumber;
		this.name = courseName;
		this.description = description;
		this.weeklyHours = weeklyHours;
	}
	public Course(String courseNumber, String name, float weeklyHours, String courseName)
	{
		this.courseNumber = courseName;
		this.name = courseName;
		this.weeklyHours = weeklyHours;
		this.description = "";
	}
	
	public String getCourseNumber(){return courseNumber;}
	public String getName(){return name;}
	public String getDescription(){return description;}
	public float getWeeklyHours(){return weeklyHours;}
	public ArrayList<Course> getPreCondition(){return preCondition;}
	
	public void setCourseNumber(String number){this.courseNumber = number;}
	public void setName(String name){this.name = name;}
	public void setDescription(String description){this.description = description;}
	public void setWeeklyHours(float hours){this.weeklyHours = hours;}
	public void setPreCondition(ArrayList<Course> preCondition){this.preCondition = preCondition;}
}
