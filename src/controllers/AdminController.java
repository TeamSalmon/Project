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
 * 
 * @author Elia
 */
public abstract class AdminController {

	static Main myMain = Main.getInstance();
	private static Object received_object;
	static ArrayList<Object> list_of_objects = new ArrayList<Object>();
	
	
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
		
		try {
			myMain.getConnection().sendToServer((Object) query_searchUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		synchronized (myMain.getConnection()) {
			try {
				myMain.getConnection().wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
//		received_object = myMain.con.getMessage();
//		list_of_objects = (ArrayList<Object>) received_object;

		received_object = myMain.getConnection().getMessage();
		if (((ArrayList<String>)received_object).size() == 0)
		{
			return false;
		}
		
		received_object = myMain.getConnection().getMessage();
		Integer permission = Integer.valueOf( (((ArrayList<String>)received_object).get(5)) );
		
		return ((permission & LoginUser.SystemAdminPER)!=0);
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
				
		try{
			myMain.getConnection().sendToServer((Object)query_block);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			synchronized(myMain.getConnection()){
				try {
					myMain.getConnection().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		received_object = myMain.con.getMessage();
				
		String answer = ((ArrayList<String>)received_object).get(0);
			
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
					
		try{
			myMain.getConnection().sendToServer((Object)query_unblock);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			synchronized(myMain.getConnection()){
				try {
					myMain.getConnection().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		received_object = myMain.con.getMessage();
				
		String answer = ((ArrayList<String>)received_object).get(0);
			
		return answer;
	}
	

	
	
	
	
	/* Define Course */
	
	private static String courseNumber;
	private static String teachingUnit;

	private static ArrayList<String> preconditionsNames = new ArrayList<String>();
	private static ArrayList<Course> preconditionsCourses = new ArrayList<Course>();
	private static ArrayList<TeachingUnit> list_of_teaching_units = new ArrayList<TeachingUnit>();	
	
	
	/**
	 * Checks that the received parameters are valid and updates the DB
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
		//  *@param [1]=courseNumber,[2]=name,[3]=weeklyHours,[4.....n]=precourses.

		ArrayList<String> query_defineNewCourse = new ArrayList<String>();
		query_defineNewCourse.add("defineNewCourse");
		query_defineNewCourse.add(courseNumber);
		query_defineNewCourse.add(received_courseName);
		query_defineNewCourse.add(((Float)weeklyHours).toString());
		query_defineNewCourse.add(teachingUnit);
		for (Course precondition : preconditionsCourses){
			query_defineNewCourse.add(precondition.getCourseNumber());
		}


		try {
			myMain.getConnection().sendToServer((Object) query_defineNewCourse);
		} catch (IOException e) {
			e.printStackTrace();
		}
		synchronized (myMain.getConnection()) {
			try {
				myMain.getConnection().wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		received_object = myMain.getConnection().getMessage();
		if (received_object.equals("null")) {
			System.out.println("Couldn't define new course (DB error");
			return null;
		}
		String answer1 = ((ArrayList<String>) received_object).get(0);

		if (answer1.equals("false")) {
			System.out.println("Couldn't define new course (DB error");
			return null;
		}
		
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
		
		ArrayList<String> query_allTeachingUnits = new ArrayList<String>();
		query_allTeachingUnits.add("allTeachingUnits");
		
		try{
			myMain.getConnection().sendToServer((Object)query_allTeachingUnits);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			synchronized(myMain.getConnection()){
				try {
					myMain.getConnection().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			received_object = myMain.getConnection().getMessage();
			ArrayList<Object> list_of_objects = (ArrayList<Object>)received_object;
		
		
		// extract 'list_of_objects' into 'list_of_teaching_units' 
				
		for(Object obj : list_of_objects)
		{
			String number = null;
			String name=null;
			
			for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
			{
				switch (i)
				{
					case 0:
						number = ((ArrayList<String>)obj).get(i);
						break;
					case 1:
						name = ((ArrayList<String>)obj).get(i);
						break;
				}
			
			}
			TeachingUnit tu = new TeachingUnit(number, name);
			list_of_teaching_units.add(tu);
		}	
		
		
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

		ArrayList<String> query_getAllCourses = new ArrayList<String>();
		query_getAllCourses.add("getAllCourses");
	
		try{
			myMain.getConnection().sendToServer((Object)query_getAllCourses);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			synchronized(myMain.getConnection()){
				try {
					myMain.getConnection().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		received_object = myMain.con.getMessage();
		ArrayList<Object> list_of_objects = (ArrayList<Object>)received_object;
		
		
		// extract 'list_of_objects' into 'list_of_courses' 
		
		ArrayList<Course> list_of_courses = new ArrayList<Course>();
		
		for(Object obj : list_of_objects)
		{
			String name = null;
			String id=null;
			float weekly_hours=0;
			String year=null;
			String semester=null;
			int current_semester=0;
			String teachingunitID = null;
			
			for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
			{
				switch (i)
				{
					case 0:
						id = ((ArrayList<String>)obj).get(i);
						break;
					case 1:
						weekly_hours = Float.valueOf(((ArrayList<String>)obj).get(i));
						break;
					case 2:
						name = ((ArrayList<String>)obj).get(i);
						break;
					case 3:
						year = ((ArrayList<String>)obj).get(i);
						break;
					case 4:
						semester = ((ArrayList<String>)obj).get(i);
						break;
					case 5:
						if(((ArrayList<String>)obj).get(i) == null)
						{
							current_semester = -1;
							break;
						}
						current_semester = Integer.valueOf(((ArrayList<String>)obj).get(i));
						break;
					case 6:
						teachingunitID = ((ArrayList<String>)obj).get(i);
						break;
				}
			
			}
			Course onecourse = new Course(id, weekly_hours, name, year, semester,teachingunitID);
			list_of_courses.add(onecourse);
		}	
		
		
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
		ArrayList<String> query_getAllCourses = new ArrayList<String>();
		query_getAllCourses.add("getAllCourses");
	
		try{
			myMain.getConnection().sendToServer((Object)query_getAllCourses);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			synchronized(myMain.getConnection()){
				try {
					myMain.getConnection().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		received_object = myMain.con.getMessage();
		ArrayList<Object> list_of_objects = (ArrayList<Object>)received_object;
		
		
		// extract 'list_of_objects' into 'list_of_courses' 
		
		ArrayList<Course> list_of_courses = new ArrayList<Course>();
		
		for(Object obj : list_of_objects)
		{
			String name = null;
			String id=null;
			float weekly_hours=0;
			String year=null;
			String semester=null;
			int current_semester=0;
			String teachingunitID = null;
			
			for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
			{
				switch (i)
				{
					case 0:
						id = ((ArrayList<String>)obj).get(i);
						break;
					case 1:
						weekly_hours = Float.valueOf(((ArrayList<String>)obj).get(i));
						break;
					case 2:
						name = ((ArrayList<String>)obj).get(i);
						break;
					case 3:
						year = ((ArrayList<String>)obj).get(i);
						break;
					case 4:
						semester = ((ArrayList<String>)obj).get(i);
						break;
					case 5:
						if(((ArrayList<String>)obj).get(i) == null)
						{
							current_semester = -1;
							break;
						}
						current_semester = Integer.valueOf(((ArrayList<String>)obj).get(i));
						break;
					case 6:
						teachingunitID = ((ArrayList<String>)obj).get(i);
						break;
				}
			
			}
			Course onecourse = new Course(id, weekly_hours, name, year, semester,teachingunitID);
			list_of_courses.add(onecourse);
		}	
	
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
