package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable 
{
	private String courseNumber;
	private String name;
	private String description;
	private float weeklyHours;
	private TeachingUnit teachingUnit;
	private ArrayList<Course> preCondition;
	private ArrayList<Integer> grades;
	public Course(String courseNumber, String courseName,TeachingUnit teachingUnit, String description, float weeklyHours)
{
		this.courseNumber = courseNumber;
		this.name = courseName;
		this.description = description;
		this.weeklyHours = weeklyHours;
		this.teachingUnit = teachingUnit;
	}

	public Course(String courseNumber, TeachingUnit teachingUnit, float weeklyHours, String courseName)
	{
		this.courseNumber = courseNumber;
		this.name = courseName;
		this.weeklyHours = weeklyHours;
		this.description = "";
		this.teachingUnit = teachingUnit;
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
	public TeachingUnit getTeachingUnit(){return teachingUnit;}
	
	public void setCourseNumber(String number){this.courseNumber = number;}
	public void setName(String name){this.name = name;}
	public void setDescription(String description){this.description = description;}
	public void setWeeklyHours(float hours){this.weeklyHours = hours;}
	public void setPreCondition(ArrayList<Course> preCondition){this.preCondition = preCondition;}
	public void setTeachingUnit(TeachingUnit teachingUnit){this.teachingUnit = teachingUnit;}

	public ArrayList<Integer> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}
}
