package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ClientGui.AssignClassToCourseController2;
import ClientGui.Main;
import projectsalmon.*;

/**
 * This controller is responsible for methods and scenes that are used 
 * as part of the system administrator's functionalities.
 * 
 * @see DefineCourseController
 * @see BlockOrUnblockUserController

 * @author Elia
 */
public abstract class AdminController {

	static Main myMain = Main.getInstance();
	private static Object received_object;
	
	
	/* Block or Unblock User */
						
	
	/**
	 * Checks if the given user has an administrator permission.
	 * @param id
	 * @return boolean answer
	 * @throws IOException
	 */
	public static boolean checkIfAdmin (String id) throws IOException {
		
		ArrayList<String> query_searchUser = new ArrayList<String>();
		query_searchUser.add("searchUser");
		query_searchUser.add(id);
		
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_searchUser);
		received_object = myMain.getConnection().getMessage();
		LoginUser user = (LoginUser)received_object;
			
		if (user.getPermission() == 16)
		{
			//The user is an administrator
			return true;
		}
		return false;
	}
	
	
	/**
	 * Attempting to block the specified user
	 * @param id
	 * @return String response from DB
	 * @throws IOException
	 */
	public static String blockUser (String id) throws IOException {
				
		ArrayList<String> query_block = new ArrayList<String>();
		query_block.add("block");
		query_block.add(id);
		
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_block);
		received_object = myMain.getConnection().getMessage();
		String answer = (String)received_object;
			
		return answer;
	}
	
	
	/**
	 * Attempting to unblock the specified user
	 * @param id
	 * @return String response from DB
	 * @throws IOException
	 */
	public static String unblockUser (String id) throws IOException {
		
		ArrayList<String> query_unblock = new ArrayList<String>();
		query_unblock.add("unblock");
		query_unblock.add(id);
		
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_unblock);
		received_object = myMain.getConnection().getMessage();
		String answer = (String)received_object;
			
		return answer;
	}

	
	
	
	
	/* Define Course */
	
	private static String courseNumber;
	private static String teachingUnit;

	private static ArrayList<String> preconditionsNames = new ArrayList<String>();
	private static ArrayList<Course> preconditionsCourses = new ArrayList<Course>();
	private static ArrayList<TeachingUnit> list_of_teaching_units;	
	
	
	/**
	 * Checks that the received parameters are valid an
	 * @param received_courseName
	 * @param received_weeklyHours
	 * @param received_teachingUnit
	 * @param received_preconditions
	 * @return warnings to deliver to the user
	 * @throws IOException
	 */
	public static String setNewCourse(String received_courseName, String received_weeklyHours, String received_teachingUnit, String received_preconditions) throws IOException 
	{
		TeachingUnit tu = check_teachingUnit(received_teachingUnit);
		if(tu == null)
		{
			// report problem - this teaching unit doesn't exist
			return "teaching unit";
		}
		
		float weeklyHours = Float.valueOf(received_weeklyHours);
		
		// split to number of courses and save as an ArrayList
		// set only if the user sent any
		if( ! received_preconditions.equals(""))
		{
			String[] splited = received_preconditions.split("\\s*(=>|,|\\s)\\s*");
			List<String> list = (List<String>) Arrays.asList(splited);
			for(String name : list){
				String str = name;
				preconditionsNames.add(str); 
			}
			 
			if (set_preconditions() == false)
			{
				// report problem - at least one precondition doesn't exist
				return "preconditions";
			}
		}
		
		teachingUnit = received_teachingUnit;
		courseNumber = generateCourseNumber();
		
		Course new_course = new Course(courseNumber, tu, weeklyHours, received_courseName);
		new_course.setPreCondition(preconditionsCourses);
		
		// Send new course to DB
		
		return "ok";
	}

		
	/**
	 * Checks if the teaching unit number exists in DB and provides it
	 * @param received_teachingUnit
	 * @returnTeachingUnit The specified teaching unit
	 * @throws IOException
	 */
	private static TeachingUnit check_teachingUnit(String received_teachingUnit) throws IOException
	{
		//ask DB: list of teaching units existing in DB
		
		///////////////////////////////////////////////////////////////////
		//ArrayList<String> query_allTeachingUnits = new ArrayList<String>();
		//query_allTeachingUnits.add("allTeachingUnits");
		//myMain.getConnection().getClient().handleMessageFromClientUI((Object) query_allTeachingUnits);
		//received_object = myMain.getConnection().getMessage();
		//list_of_teaching_units = (ArrayList<TeachingUnit>) received_object;
		///////////////////////////////////////////////////////////////////
		
		
		
		////////////////////////////////////////////////////////////////
		list_of_teaching_units = new ArrayList<TeachingUnit>();
		list_of_teaching_units.add(new TeachingUnit("01","Math"));
		list_of_teaching_units.add(new TeachingUnit("02","Physics"));
		list_of_teaching_units.add(new TeachingUnit("03","History"));
		////////////////////////////////////////////////////////////////

		for(TeachingUnit tu : list_of_teaching_units)
		{
			// Pick the chosen teaching unit (by number or name)
			if(tu.getNumber().equals(received_teachingUnit) || tu.getName().equals(received_teachingUnit))
				return tu;
		}
		return null;
	}
	
	
	/**
	 * Generates a new ID for the new course based on the IDs that are in the DB
	 * @return New course's ID
	 */
	private static String generateCourseNumber ()
	{		
		//get all the courses in the DB
		// ask DB: ArrayList<Course> coursesByID();
		///////////////////////////////////////////////////////////////////
		//ArrayList<Course> list_of_courses;
		//ArrayList<String> query_coursesByID = new ArrayList<String>();
		//query_coursesByID.add("courseByID");
		//myMain.getConnection().getClient().handleMessageFromClientUI((Object) query_coursesByID);
		//received_object = myMain.getConnection().getMessage();
		//list_of_courses = (ArrayList<Course>) received_object;
		///////////////////////////////////////////////////////////////////
		
		
		/////////////////////////////////////////////////////////////////////////////
		ArrayList<Course> list_of_courses = new ArrayList<Course>();
		list_of_courses.add(new Course("01001",new TeachingUnit("01","thing"), 2, "poetry"));
		list_of_courses.add(new Course("01002",new TeachingUnit("01","thing"), 2, "french"));
		list_of_courses.add(new Course("02003",new TeachingUnit("02","thing"), 2, "Literature"));
		/////////////////////////////////////////////////////////////////////////////
		
		// List is empty, this s_class will be the first
		if(list_of_courses.isEmpty() == true)
			return teachingUnit + "001";
				
		String id = "001";
				
		// Scan the SORTED list of existing courses and return the 
		// smallest unused id (starting at "001")
		for (Course course : list_of_courses)
		{
			// Check only the classes with the same teaching unit
			if( course.getTeachingUnit().getNumber().equals(teachingUnit) )
			{
				// Note: the 'list_of_courses' is sorted by ID
				if (course.getCourseNumber().equals( teachingUnit + id) )
				{
					// Increment 'id'
					Integer id_as_number = Integer.valueOf(id);
					if(id_as_number < 10) 
						id = "00" + ((Integer)(id_as_number+1)).toString();
					else if(id_as_number < 100) 
						id = "0" + ((Integer)(id_as_number+1)).toString();
				}
				else if (! (course.getCourseNumber().equals(teachingUnit + id)))
				{
				// Found an unused name
					break;
				}
			}
		}
		return teachingUnit + id;
	}


	/**
	 * Checks if the preconditions exist in the DB
	 * @return boolean answer
	 * @throws IOException
	 */
	private static boolean set_preconditions() throws IOException 
	{
		// ask DB: Get list of all the courses in the DB (sorted by anything???)
		// save it to 'list_of_courses'
		///////////////////////////////////////////////////////////////////
		//ArrayList<Course> list_of_courses;
		//ArrayList<String> query_coursesByID = new ArrayList<String>();
		//query_coursesByID.add("courseByID");
		//myMain.getConnection().getClient().handleMessageFromClientUI((Object) query_coursesByID);
		//received_object = myMain.getConnection().getMessage();
		//list_of_courses = (ArrayList<Course>) received_object;
		///////////////////////////////////////////////////////////////////
		
		
		/////////////////////////////////////////////////////////////////////////////
		ArrayList<Course> list_of_courses = new ArrayList<Course>();
		list_of_courses.add(new Course("01001",new TeachingUnit("some","thing"), 2, "poetry"));
		list_of_courses.add(new Course("01002",new TeachingUnit("some","thing"), 2, "french"));
		list_of_courses.add(new Course("02003",new TeachingUnit("some","thing"), 2, "Literature"));
		/////////////////////////////////////////////////////////////////////////////
				
		// Get reference to the courses that their names were received
		for (String precondition : preconditionsNames) 
		{
			for (Course course : list_of_courses) 
			{
				if ( precondition.equals(course.getCourseNumber()) ) 
				{
					preconditionsCourses.add(course);
					break;
				}
			}
		}
		
		// Check if all the preconditions that were asked really exist
		if (preconditionsCourses.size() < preconditionsNames.size())
			return false;
		else
			return true;
	}


	

}
