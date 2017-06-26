package controllers;

import java.util.ArrayList;
import java.util.Arrays;

import projectsalmon.*;

public abstract class AdminController {

	private static String courseNumber;
	private static String teachingUnit;
	private static ArrayList<String> preconditions;
	
	
	private static ArrayList<TeachingUnit> tUnits;
	
	
	public static String setNewCourse(String received_courseName, String received_weeklyHours, String received_teachingUnit, String received_preconditions) 
	{
		TeachingUnit tu = check_teachingUnit(received_teachingUnit);
		if(tu == null)
		{
			// report problem - this teaching unit doesn't exist
			return "teaching unit";
		}
		
		float weeklyHours = Float.valueOf(received_weeklyHours);
		
		// split to number of courses and save as an ArrayList
		String[] splited = received_preconditions.split("\\s*(=>|,|\\s)\\s*");		
		preconditions = (ArrayList<String>) Arrays.asList(splited);
		
		courseNumber = generateCourseNumber();
		
		
		Course new_course = new Course(courseNumber, tu, weeklyHours, received_courseName);
		System.out.println(new_course.toString());
		
		return "ok";
	}

	
	// check if the teaching unit number exists in DB
	private static TeachingUnit check_teachingUnit(String received_teachingUnit)
	{
		//ask DB: list of teaching units existing in DB
		
		////////////////////////////////////////////////////////////////
		tUnits = new ArrayList<TeachingUnit>();
		tUnits.add(new TeachingUnit("01","Math"));
		tUnits.add(new TeachingUnit("02","Physics"));
		tUnits.add(new TeachingUnit("01","History"));
		////////////////////////////////////////////////////////////////

		for(TeachingUnit tu : tUnits)
		{
			// the received "
			if(tu.getNumber() == received_teachingUnit || tu.getName() == received_teachingUnit)
				return tu;
		}
		return null;
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
