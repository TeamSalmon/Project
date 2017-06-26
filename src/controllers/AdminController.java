package controllers;

import java.util.ArrayList;
import java.util.Arrays;

import projectsalmon.*;

public abstract class AdminController {

	private static String courseNumber;
	private static String courseName;
	private static String teachingUnit;
	private static int weeklyHours;
	private static ArrayList<String> preconditions;
	
	
	
	public static void setNewCourse(String received_courseName, String received_weeklyHours, String received_teachingUnit, String received_preconditions) 
	{
		if(check_teachingUnit(received_teachingUnit))
		{
			teachingUnit = received_teachingUnit;
		}
		else
		{
			//return problem with teaching unit!!!
		}
		
		courseName = received_courseName;
		
		weeklyHours = Integer.valueOf(received_weeklyHours);
		
		// split to number of courses and save as an ArrayList
		String[] splited = received_preconditions.split("\\s*(=>|,|\\s)\\s*");		
		preconditions = (ArrayList<String>) Arrays.asList(splited);
		
		courseNumber = generateCourseNumber();
	}

	
	// check if the teaching unit number exists in DB
	private static boolean check_teachingUnit(String received_teachingUnit)
	{
		return false;
	}
	
	
	private static String generateCourseNumber ()
	{		
		//get all the courses in the DB
		// ask DB: ArrayList<Course> courseByID();
		ArrayList<Course> list_of_courses = new ArrayList<Course>();
		
		// List is empty, this s_class will be the first
		if(list_of_courses.isEmpty() == true)
			return teachingUnit + "001";
				
		String id = "001";
				
		// Scan the SORTED list of existing courses and return the 
		// smallest unused id (starting at "001")
		for (Course course : list_of_courses)
		{
			// Check only the classes with the same teaching unit
			if( course.getTeachingUnit().getNumber() == teachingUnit )
			{
				if (course.getCourseNumber() == teachingUnit + id)
				{
					// Increment 'id'
					Integer id_as_number = Integer.valueOf(id);
					if(id_as_number < 10) id = "00" + id_as_number.toString();
					if(id_as_number < 100) id = "0" + id_as_number.toString();
				}
				if (course.getCourseNumber() != teachingUnit + id)
				{
				// Found an unused name
					break;
				}
			}
		}
		return teachingUnit + id;
	}
}
