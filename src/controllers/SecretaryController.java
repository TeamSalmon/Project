package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import ClientGui.AssignClassToCourseController2;
import ClientGui.Main;
import javafx.scene.Scene;
import projectsalmon.*;
import projectsalmon.Class;
import ServerClient.ClientConsole;

/**
 * This controller is responsible for methods and scenes that
 * are used as part of the secretary's functionalities.
 * 
 * @see AssignClassToCourseController1
 * @see AssignClassToCourseController2
 * @see AssignClassToCourseController3 
 * @see DefineClass1Controller
 * @see DefineClass2Controller
 * @see searchStudentInCourseCurrentSemester
 * @see searchStudentNameByID
 * @see sendAssignRequest
 * 
 */
public abstract class SecretaryController {

	
	static Main myMain = Main.getInstance();

	private static Object received_object;
	
	
	
	/* Assign Class to Course*/
	
	private static ArrayList<Object> list_of_objects;

	private static ArrayList<Course> list_of_courses = new ArrayList<Course>();
	private static ArrayList<StudentsClass> list_of_classes = new ArrayList<StudentsClass>();
	private static ArrayList<Teacher> list_of_teachers = new ArrayList<Teacher>();
	private static ArrayList<Student> all_students_of_class = new ArrayList<Student>();
	private static ArrayList<String> preconditions = new ArrayList<String>();
	private static ArrayList<String> student_former_courses = new ArrayList<String>();
	private static ArrayList<Student> approved_students = new ArrayList<Student>();
	
	private static Course chosen_course;
	private static StudentsClass chosen_class;
	private static Teacher chosen_teacher;
	
	private static boolean save_changes = false;
	
	private static StudentsClassInCourse new_class_in_course;
		
	private static String courseClassID; 
	
	

	/**
	 * Returns optional courses to assign classes to.
	 * @return ArrayList<Course> list of optional courses.
	 * @throws IOException
	 */
	public static ArrayList<Course> getOptionalCourses() throws IOException
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
		list_of_objects = (ArrayList<Object>)received_object;
		
		
		// extract 'list_of_objects' into 'list_of_courses' 
		
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
		
		return list_of_courses;
	}
	
	
	/**
	 * Returns optional classes to assign to courses.
	 * @return ArrayList<StudentsClass> list of optional classes.
	 * @throws IOException
	 */
	public static ArrayList<StudentsClass> getOptionalClasses() throws IOException
	{	
		// ask DB: Get list of all the classes in the DB (sorted by ID)
		// save it to 'list_of_courses'
	
		ArrayList<String> query_studentClassesByID = new ArrayList<String>();
		query_studentClassesByID.add("studentClassesByID");
		try{
		myMain.getConnection().sendToServer((Object)query_studentClassesByID);
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
		list_of_objects = (ArrayList<Object>)received_object;

		
		// extract 'list_of_objects' into 'list_of_courses' 
		
		for(Object obj : list_of_objects)
		{
			int amount = 0;
			String level=null;
			String id = null;
			String name=null;
			
			
			for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
			{
				switch (i)
				{
					case 0:
						amount = Integer.valueOf(((ArrayList<String>)obj).get(i));
						break;
					case 1:
						level = ((ArrayList<String>)obj).get(i);
						break;
					case 2:
						id = ((ArrayList<String>)obj).get(i);
						break;
					case 3:
						name = ((ArrayList<String>)obj).get(i);
						break;
				}
			
			}

			StudentsClass oneclass = new  StudentsClass(name, level, id, amount);
			if(! oneclass.getClassId().equals("NULL"))
				list_of_classes.add(oneclass);
		}	
		
		return list_of_classes;

	}
	
	
	/**
	 * Updates the chosen class and course.
	 * @param courseName
	 * @param className
	 */
	public static void updateChosenCourseAndClass(String courseName, String className)
	{
		for (Course course : list_of_courses)
    	{
			if (course.getName() == courseName)
			{
				chosen_course = course;
				break;
			}
		}
		
		for (StudentsClass sclass : list_of_classes)
    	{
			if (sclass.getClassName() == className)
			{
				chosen_class = sclass;
				break;
			}
    	}
	}
	
	
	/**
	 * Checks if the students of the class has the course's preconditions and returns the results.
	 * @return ArrayList<Student> list of misfit students
	 * @throws IOException
	 */
	public static ArrayList<Student> getMisfitStudents() throws IOException
	{
		// ask DB: Get list of all the students in a specified class (sorted ? ? ?)
		//ArrayList<Student> all_students_of_class = DB.studentsOfClass(StudentsClass);
		
		ArrayList<String> query_studentsOfClass = new ArrayList<String>();
		query_studentsOfClass.add("studentsOfClass");
		query_studentsOfClass.add(chosen_class.getClassId());
		
		try{
			myMain.getConnection().sendToServer((Object)query_studentsOfClass);
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
			list_of_objects = (ArrayList<Object>)received_object;

			
			// extract 'list_of_objects' into 'all_students_of_class' 
			//	 * @return [0]=id,[1]=first_name,[2]=last_name,[3]=birthday.

			for(Object obj : list_of_objects)
			{
				String id = null;
				String first=null;
				String last = null;
				String bd=null;
				
				
				for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
				{
					switch (i)
					{
						case 0:
							id = ((ArrayList<String>)obj).get(i);
							break;
						case 1:
							first = ((ArrayList<String>)obj).get(i);
							break;
						case 2:
							last = ((ArrayList<String>)obj).get(i);
							break;
						case 3:
							bd = ((ArrayList<String>)obj).get(i);
							break;
					}
				
				}

				Student onestudent = new  Student(id, first, last, null);
				all_students_of_class.add(onestudent);
			}	
					
		
		
		// ask DB: Get list the courses that are the preconditions for the specified course
		// preconditions = coursePreconditions(chosen_course);
		
		ArrayList<String> query_coursePreconditions = new ArrayList<String>();
		query_coursePreconditions.add("coursePrecondition");
		query_coursePreconditions.add(chosen_course.getCourseNumber());

		try{
			myMain.getConnection().sendToServer((Object)query_coursePreconditions);
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
		preconditions = (ArrayList<String>)received_object;
		 
		
		// List of 'Student's that lack the preconditions of the course
		ArrayList<Student> misfit_students = new ArrayList<Student>();
		
		// List of 'Student's that has the preconditions of the course
		approved_students = new ArrayList<Student>();
					
		
		
		// Check for each 'Student' from the 'chosen_class' if they have the 'preconditions' of the 'chosen_course' 
		for (Student student : all_students_of_class)
		{
			int i = 0;
			i++;

			int exists = 0;

			// ask DB: Get list of all the courses that the student ALREADY FINISHED (not including the current semester)
			// save it to 'student_former_courses'
			//student_former_courses = DB.studentOldCourses(student);
			
			ArrayList<String> query_studentOldCourses = new ArrayList<String>();
			query_studentOldCourses.add("studentOldCourses");
			query_studentOldCourses.add(student.getId().toString());
			
			try{
				myMain.getConnection().sendToServer((Object)query_studentOldCourses);
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
				list_of_objects = (ArrayList<Object>)received_object;
				
				for(Object obj : list_of_objects)
				{
					String id = null;
					
					
					for(int j = 0 ; j < ((ArrayList<String>)obj).size() ; j++ )
					{
						switch (j)
						{
							case 0:
								id =  ((ArrayList<String>)obj).get(j);
								break;
						}
					
					}

					student_former_courses.add(id);
				}
				
							
			
			for (String condition : preconditions)
			{
				if(i == 1)
				for (String course : student_former_courses)
				{	
					if( condition.equals(course) )
					{	
						// count the precondition
						exists++;
						break;
					}
				}
			}
		
			// counted all the preconditions? -> add student to fit/misfit list
			if ( exists == preconditions.size() )
			{
				approved_students.add(student);
			}
			else
			{
				misfit_students.add(student);
			}
		}
		
		return misfit_students;		
		
	}
	
	/**
	 * Checks for optional teachers shows the user.
	 * @return ArrayList<Teacher> list of optional teachers for the class in this course.
	 * @throws IOException
	 */
	public static ArrayList<Teacher> getOptionalTeachers() throws IOException {

		// ask DB: Get list of all the teachers that teach this course + have ENOUGH FREE WEEKLY HOURS
		//list_of_teachers = DB.optionalTeachers(chosen_course.getCourseNumber());	
		
		ArrayList<String> query_optionalTeachersForCourse = new ArrayList<String>();
		query_optionalTeachersForCourse.add("optionalTeachersForCourse");
		query_optionalTeachersForCourse.add(chosen_course.getCourseNumber());
		
		try{
			myMain.getConnection().sendToServer((Object)query_optionalTeachersForCourse);
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
			list_of_objects = (ArrayList<Object>)received_object;

			//	 * @return [0]=weeklyHours,[1]=id,[2]=first_name,[3]=last_name,[4]=Password.

			// extract 'list_of_objects' into 'list_of_teachers' 
			
			for(Object obj : list_of_objects)
			{
				float maxweeklyhours = 0;
				float weeklyhours = 0;
				String id=null;
				String first = null;
				String last=null;
				String pass = null;
				
				for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
				{
					switch (i)
					{
						case 0:
							weeklyhours = Float.valueOf(((ArrayList<String>)obj).get(i));
							break;
						case 1:
							id = ((ArrayList<String>)obj).get(i);
							break;
						case 2:
							first = ((ArrayList<String>)obj).get(i);
							break;
						case 3:
							last= ((ArrayList<String>)obj).get(i);
							break;
						case 4:
							pass= ((ArrayList<String>)obj).get(i);
							break;
						case 5:
							maxweeklyhours= Float.valueOf(((ArrayList<String>)obj).get(i));
							break;
					}
				
				}

				Teacher oneteacher = new  Teacher(weeklyhours,id, first, last, pass);
				oneteacher.setWeekly_hours(maxweeklyhours);
				list_of_teachers.add(oneteacher);
			}	
		
			
		// Keep only the teachers that have enough free teaching hours
		for (Teacher teacher : list_of_teachers)
    	{
			if( teacher.getMax_maximal_weekly_hours() - teacher.getWeekly_hours() < chosen_course.getWeeklyHours())
				list_of_teachers.remove(teacher);
    	}
	
		return list_of_teachers;
	}

	/**
	 * Updates the chosen teacher and assigns all the gathered data to the DB.
	 * @param teacherName
	 * @throws IOException 
	 */
	public static String updateChosenTeacher(String teacherName) throws IOException {
		
		// find the selected teacher in the list
		for (Teacher teacher : list_of_teachers)
    	{
			String name = teacher.getFirst_name() + " " + teacher.getLast_name() + " " + teacher.getId();
			if ( name.equals(teacherName))
			{
				chosen_teacher = teacher;
				break;
			}
		}
		
		// Add weekly hours of 'chosen_course' to 'chosen_teacher''s weekly hours
		chosen_teacher.setWeekly_hours( chosen_teacher.getWeekly_hours() + chosen_course.getWeeklyHours());
		
		ArrayList<Class> schedule = new ArrayList<Class>();
		schedule.add(new Class());
		
		new_class_in_course  = new StudentsClassInCourse("something", chosen_course, chosen_class, schedule, chosen_teacher);

////////////////////////////////////////////////////
		// send 'new_class_in_course' to DB:
		// public static ArrayList<String> CreateClassInCourse(String studentsClassID,String courseID,String teacherID,String studentsAmount,String ClassCourseID){
		
		ArrayList<String> query_CreateClassInCourse = new ArrayList<String>();
		query_CreateClassInCourse.add("CreateClassInCourse");
		query_CreateClassInCourse.add(chosen_class.getClassId());
		query_CreateClassInCourse.add(chosen_course.getCourseNumber());
		query_CreateClassInCourse.add(chosen_teacher.getId());
		query_CreateClassInCourse.add(((Integer)approved_students.size()).toString());
		
		courseClassID = generateCourseClassId();
		query_CreateClassInCourse.add(courseClassID);

		try{
			myMain.getConnection().sendToServer((Object)query_CreateClassInCourse);
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
		
		String answer1 = ((ArrayList<String>) received_object).get(0);

		if (answer1.equals("false")) {
			System.out.println("Couldn't define new course (DB error");
			return null;
		}
		
		// update 'chosen_teacher' in DB
			
		ArrayList<String> query_updateTeacher = new ArrayList<String>();
		query_updateTeacher.add("updateTeacher");
		query_CreateClassInCourse.add(chosen_teacher.getId());
		query_CreateClassInCourse.add(((Float)chosen_teacher.getWeekly_hours()).toString());

		try{
			myMain.getConnection().sendToServer((Object)query_CreateClassInCourse);
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
			
			String answer2 = ((ArrayList<String>) received_object).get(0);

			if (answer2.equals("false")) {
				System.out.println("Couldn't define new course (DB error");
				return null;
			}
		
		
		for(Student student: approved_students)
		{
			 //add to new class in course
			 //update 'student' in DB
			ArrayList<String> query_updateStudentCourse = new ArrayList<String>();
			query_updateStudentCourse.add("updateStudentCourse");
			query_updateStudentCourse.add(chosen_course.getCourseNumber());
			query_updateStudentCourse.add(chosen_teacher.getId());
			query_updateStudentCourse.add(chosen_class.getClassId());
			query_updateStudentCourse.add(courseClassID);
			
			//public static ArrayList<String> updateStudentCourse(String studentID,String CourseId,String teasherId,String StudentsClassID,String classCourseID){

			try{
				myMain.getConnection().sendToServer((Object)query_updateStudentCourse);
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
				
				answer2 = ((ArrayList<String>) received_object).get(0);

				if (answer2.equals("false")) {
					System.out.println("Couldn't define new course (DB error");
					return null;
				}
		}
		return answer2;
		
////////////////////////////////////////////////////
	}

	
	/**
	 * Returns optional courses to assign classes to.
	 * @return ArrayList<Course> list of optional courses.
	 * @throws IOException
	 */
	public static String generateCourseClassId() throws IOException
	{
		ArrayList<String> list_of_ids=new ArrayList<String>(); 		
		
			ArrayList<String> query_groupsOfCourse = new ArrayList<String>();
			query_groupsOfCourse.add("groupsOfCourse");
			query_groupsOfCourse.add(chosen_course.getCourseNumber());
			
			try{
				myMain.getConnection().sendToServer((Object)query_groupsOfCourse);
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
				list_of_objects = (ArrayList<Object>)received_object;

				//	 * @return [0]=weeklyHours,[1]=id,[2]=first_name,[3]=last_name,[4]=Password.

				// extract 'list_of_objects' into 'list_of_teachers' 
				
				for(Object obj : list_of_objects)
				{
					String ccID=null;
				

					for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
					{
						switch (i)
						{
							case 0:
								ccID = ((ArrayList<String>)obj).get(i);
								break;
						}
					
					}

					list_of_ids.add(ccID);
				}	
			
			
			
			// List is empty, this s_class will be the first
			if (list_of_ids.isEmpty() == true)
				return "1";

			String newid = "1";
			int numerical_value;

			// Scan the SORTED list of existing classes and return the
			// smallest unused studentsClassId (starting at 1)
			for (String sclass : list_of_ids) {
				if (sclass.equals(newid)) {
					// Increment 'newid'
					numerical_value = Integer.valueOf(newid);
					numerical_value++;
					newid = String.valueOf(numerical_value);
				}
				if ( ! sclass.equals(newid)) {
					break;
				}

			}
			//set 'newid' to have a length of 3 digits
			if (newid.length() < 2)
				newid = "00000000" + newid; 
			else if (newid.length() < 3)
				newid = "0000000" + newid; 
			else if (newid.length() < 4)
				newid = "000000" + newid; 
			else if (newid.length() < 5)
				newid = "00000" + newid; 
			else if (newid.length() < 6)
				newid = "0000" + newid; 
			else if (newid.length() < 7)
				newid = "000" + newid; 
			else if (newid.length() < 8)
				newid = "00" + newid; 
			else if (newid.length() < 9)
				newid = "0" + newid; 
			
			return newid;
		}
		
	
	
	
	/**
	 * (Getter)
	 * @return Course chosen_course.
	 */
	public static Course get_chosen_course()
	{
		return chosen_course;
	}

	
	/**
	 * (Getter)
	 * @return StudentsClass chosen class.
	 */
	public static StudentsClass get_chosen_class()
	{
		return chosen_class;
	}

	/**
	 * (Getter)
	 * @return boolean value of save_changes flag.
	 */
	public static boolean isSave_changes() {
		return save_changes;
	}

	/**
	 * (Setter)
	 * @param save_changes
	 */
	public static void setSave_changes(boolean save_changes) {
		SecretaryController.save_changes = save_changes;
	}


	/**
	 * Closes the and returns from the open windows back to the user's main menu.
	 * @param n
	 * @throws IOException
	 */
	public static void assignClassToCourseEXIT(int n) throws IOException {
		for(int i = 0 ; i < n-1 ; i++)
		{
			myMain.getMange().myStack.pop();
		}
		myMain.getMange().changeScene((Scene)myMain.getMange().myStack.pop());
	}

	
	
	
	
	
	/* Define Class*/
	
	
	private static ArrayList<Student> OptionalStudents = new ArrayList<Student>();
	//private static ArrayList<StudentsClass> list_of_classes; -> Using the 'list_of_classes'
	// from the Assign Class to Course scope
	
	private static StudentsClass new_class;
	private static String new_grade;

	public static String getGrade()
	{
		return new_grade;
	}
	
	
	/**
	 * (Setter)
	 * @param grade
	 */
	public static void setNewGrade(String grade) 
	{
		new_grade = grade;
	}

	
	/**
	 * Gets a list of students with a specified level/grade without a class to be assigned to the new class
	 * @param level 
	 * @return ArrayList<Student>
	 * @throws IOException
	 */
	public static ArrayList<Student> getOptionalStudents(String level) throws IOException 
	{
		// ask DB: get a list of students in this grade that are not attached to
		// 'studentsClass' instance already
		// ArrayList<Student> OptionalStudents = DB.studnetsWithoutClass(grade);
		
		  ArrayList<String> query_studentsWithoutClass = new ArrayList<String>();
		  query_studentsWithoutClass.add("studentsWithoutClass");
		  query_studentsWithoutClass.add(level);

		  try{
				myMain.getConnection().sendToServer((Object)query_studentsWithoutClass);
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
		list_of_objects = (ArrayList<Object>)received_object;
  

			// extract 'list_of_objects' into 'OptionalStudents' 
			
			for(Object obj : list_of_objects)
			{
				String id = null;
				String first = null;
				String last =null;
				String pass = null;
				
				
				for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
				{
					switch (i)
					{
						case 0:
							id = ((ArrayList<String>)obj).get(i);
							break;
						case 1:
							first = ((ArrayList<String>)obj).get(i);
							break;
						case 2:
							last = ((ArrayList<String>)obj).get(i);
							break;
						case 3:
							pass = ((ArrayList<String>)obj).get(i);
							break;
					}
				
				}

				Student onestudent = new  Student(id,first,last,pass);
					OptionalStudents.add(onestudent);
			}	
		 
		return OptionalStudents;
	}

	
	/**
	 * Generates a new StudentsClass
	 * @param chosen_students
	 * @return StudentsClass new StudentsClass
	 * @throws IOException
	 */
	public static StudentsClass createNewClass(ArrayList<Student> chosen_students) throws IOException 
	{

		// ask DB: Get list of all the classes in the DB - SORTED BY
		// 'studentsClassId'
		// ArrayList<StudentsClass> list_of_classes = DB.classesByID();

		
		  ArrayList<String> query_studentClassesByID = new ArrayList<String>();
		  query_studentClassesByID.add("studentClassesByID");
				  
				  
				  try{
						myMain.getConnection().sendToServer((Object)query_studentClassesByID);
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
						list_of_objects = (ArrayList<Object>)received_object;

						
						// extract 'list_of_objects' into 'list_of_courses' 
						
						for(Object obj : list_of_objects)
						{
							int amount = 0;
							String level=null;
							String id = null;
							String name=null;
							
							
							for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
							{
								switch (i)
								{
									case 0:
										amount = Integer.valueOf(((ArrayList<String>)obj).get(i));
										break;
									case 1:
										level = ((ArrayList<String>)obj).get(i);
										break;
									case 2:
										id = ((ArrayList<String>)obj).get(i);
										break;
									case 3:
										name = ((ArrayList<String>)obj).get(i);
										break;
								}
							
							}

							StudentsClass oneclass = new  StudentsClass(name, level, id, amount);
							if(! oneclass.getClassId().equals("NULL"))
								list_of_classes.add(oneclass);
						}	
				  
				  
		String className = generateClassName(list_of_classes, new_class);
		String classID = generateClassID(list_of_classes);

		StudentsClass new_class = new StudentsClass(className, new_grade, classID);

		// Add chosen students to the new class
		new_class.setStudents(chosen_students);
		new_class.setStudentsAmount(chosen_students.size());

		// Send to DB: the instance 'new_class' to add to DB
		
		  ArrayList<String> query_createStudentsClass = new ArrayList<String>();
		  query_createStudentsClass.add("createStudentsClass"); 
		  query_createStudentsClass.add(classID);
		  query_createStudentsClass.add(((Integer)new_class.getStudentsAmount()).toString());
		  query_createStudentsClass.add(new_grade); 
		  query_createStudentsClass.add(className);
		 
			try{
				myMain.getConnection().sendToServer((Object)query_createStudentsClass);
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
		  
		  
		  String answer1 = ((ArrayList<String>)received_object).get(0);
	  
		  if (answer1.equals("false"))
		  {
			  System.out.println("Couldn't define new class (DB error");
			  return null;
		  }
		  
		  for (Student student : chosen_students)
		  { 
			  // Connect class to student and increase amount of students in class student.setClass(new_class);
			  new_class.setStudentsAmount( new_class.getStudentsAmount() + 1 );
		  
		  
			  // Send to DB: the instance 'new_class' to add to DB
			  ArrayList<String> query_updateClassOfStudent = new
			  ArrayList<String>();
			  query_updateClassOfStudent.add("updateClassOfStudent");
			  query_updateClassOfStudent.add(student.getId());
			  query_updateClassOfStudent.add(new_class.getClassId());
			  
					  try{
						myMain.getConnection().sendToServer((Object)query_updateClassOfStudent);
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
				String answer2 = ((ArrayList<String>)received_object).get(0);

			  
			  if (answer2.equals("false"))
			  {
				  System.out.println("Couldn't assign student to new class (DB error");
				  return null;
			  }
		  }
		 
		return new_class;
	}

	
	/**
	 * Generate an ID for the new class based on existing classes in the DB
	 * @param list_of_classes
	 * @return
	 */
	private static String generateClassID(ArrayList<StudentsClass> list_of_classes) 
	{
		// List is empty, this s_class will be the first
		if (list_of_classes.isEmpty() == true)
			return "1";

		String newid = "1";
		int numerical_value;

		// Scan the SORTED list of existing classes and return the
		// smallest unused studentsClassId (starting at 1)
		for (StudentsClass sclass : list_of_classes) {
			if (sclass.getClassId() == newid) {
				// Increment 'newid'
				numerical_value = Integer.valueOf(newid);
				numerical_value++;
				newid = String.valueOf(numerical_value);
			}
			if (sclass.getClassId() != newid) {
				break;
			}

		}
		//set 'newid' to have a length of 3 digits
		if (newid.length() < 2)
			newid = "00" + newid; 
		else if (newid.length() < 3)
			newid = "0" + newid; 
		
		return newid;
	}

	
	/**
	 * Generate a name for the new class based on existing classes in the DB
	 * @param list_of_classes
	 * @return
	 */
	private static String generateClassName(ArrayList<StudentsClass> list_of_classes, StudentsClass new_class) 
	{
		// List is empty, this s_class will be the first
		if (list_of_classes.isEmpty() == true)
			return new_grade + "A";

		char c = 'A';
		String name = new_grade + c;

		// Scan the SORTED list of existing classes and return the
		// smallest unused className (starting at 1)
		for (StudentsClass sclass : list_of_classes) 
		{
			// Check only the classes with the same grade
			if (sclass.getlevel().equals(new_grade)) 
			{
				if (sclass.getClassName().equals(name)) 
				{
					// passed on all the ABC and couldn't find a free letter
					if (c == 'Z') return null;
					
					// Increment 'name'
					c++;
					name = new_grade + c;
				}
				else if ( ! sclass.getClassId().equals(name) ) 
				{
					// Found an unused name
					break;
				}
			}
		}
		return name;
	}
	
	
	/**
	 * Return to the main menu of the actor. 
	 * @param n
	 * @throws IOException
	 */
	public static void defineClassEXIT(int n) throws IOException {
		for (int i = 0; i < n; i++) {
			myMain.getMange().myStack.pop();
		}
		myMain.getMange().changeScene((Scene) myMain.getMange().myStack.pop());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*Mariya*/

	//	static String searchStudentID;
	

	public static boolean sendAppointTeacherRequest(String teacherID,String classCourse, String description){
		ArrayList<String> arrsend  =  new ArrayList<String>();
				arrsend.clear();
				 arrsend.add("sendAppointTeacherRequest");
				 arrsend.add(teacherID);
				 arrsend.add(classCourse);
				 arrsend.add(description);
				 arrsend.add("2");


					try {
						Main.con.sendToServer(arrsend);
					} catch (IOException e) {
						
						e.printStackTrace();
					}		
				 synchronized (Main.con) {
			    		
			    		try {
							Main.con.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				 ArrayList<String> ans = (ArrayList<String>)  myMain.con.getMessage();
				 if(ans.get(0).equals("true"))
					 return true;
				 else return false;
	}
	
	
	public static boolean removeTeacherfromCourseRequest(String courseNumber,String teacherID, String description){
		boolean sendRequestSucceded = false;
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.clear();
		arrsend.add("removeTeacherfromCourseRequest");
		arrsend.add(courseNumber);
		arrsend.add(teacherID);
		arrsend.add(description);
		arrsend.add("3");


		try {
			Main.con.sendToServer(arrsend);
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	 synchronized (Main.con) {
    		
    		try {
				Main.con.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 ArrayList<String> ans = (ArrayList<String>)  myMain.con.getMessage();
	 if(ans.get(0).equals("true"))
		 return true;
	 else return false;
	}
	
			
/**
 * fill tuple in requests table
 * @param courseNumber
 * @param studentID
 * @param description
 * @return true of false
 */

		public static boolean  removeStudentfromCourseRequest(String courseNumber, String userID,String description){
			boolean sendRequestSucceded=false;
			 String typeOfRequest = "1"; //assignStudent
				String checkStatus = "0";
				//get year and semester
				 ArrayList<String> arrsend  =  new ArrayList<String>();
				 arrsend.clear();
				 arrsend.add("CurrentSemester");
				 try {
						Main.con.sendToServer(arrsend);
					} catch (IOException e) {
						
						e.printStackTrace();
					}		
				 synchronized (Main.con) {
			    		
			    		try {
							Main.con.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				ArrayList<String> currSemester= (ArrayList<String>) myMain.con.getMessage();
				//call to sendAssignRequest in server
				 arrsend.clear();
				 arrsend.add("sendRemoveRequest");
				 arrsend.add(userID);
				// arrsend.add(classCourse);
				 arrsend.add(description);
				 arrsend.add(checkStatus);
				 arrsend.add(typeOfRequest);
				 arrsend.add(courseNumber);
				 arrsend.add(currSemester.get(0));
				 arrsend.add(currSemester.get(1));
				 try {
						Main.con.sendToServer(arrsend);
					} catch (IOException e) {
						
						e.printStackTrace();
					}		
				 synchronized (Main.con) {
			    		
			    		try {
							Main.con.wait();
						} catch (InterruptedException e) {
							ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
							e.printStackTrace();
						}
					}
			
		ArrayList<String> ans = (ArrayList<String>)  myMain.getConnection().getMessage();
			 if(ans.get(0).equals("true"))
				 return true;
			 else return false;
			 

		}
		
		public static float  getCourseWHours(String courseNum){
			ArrayList<String> arrsend  =  new ArrayList<String>();
			 arrsend.clear();
			 arrsend.add("getCourseWHours");
			 arrsend.add(courseNum);

			 try {
					Main.con.sendToServer(arrsend);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			    	synchronized (Main.con) {
			    		
			    		try {
							Main.con.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 		
			  float wHours = (float)Main.con.getMessage();
			 return wHours;
		}
		
		
		/**
		 * send to DB teacherID and expected to accept object of teacher
		 * @param TeacherID
		 * @return object Teacher
		 */
		public static ArrayList<String>  searchTeacherID(String TeacherID){
			
			 ArrayList<String> arrsend  =  new ArrayList<String>();
			 arrsend.clear();
			 arrsend.add("searchTeacherID");
			 arrsend.add(TeacherID);

			 try {
					Main.con.sendToServer(arrsend);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			    	synchronized (Main.con) {
			    		
			    		try {
							Main.con.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			    		
			   ArrayList<String> newTeacher = (ArrayList<String>)Main.con.getMessage();

			
			if (newTeacher == null)
				return (ArrayList<String>)null;
			return newTeacher;
		}

		

/**
 * This method send ID of student to server and gets his F_name and L_name if he is exists	
 * @param studentID
 * @return ArrayList with student name or null if the student isn't exists
 * @author Mariya Portnoy
 */
		@SuppressWarnings("unchecked")
		public static ArrayList<String> searchStudentNameByID(String studentID){	
			 ArrayList<String> arrsend  =  new ArrayList<String>();
			 ArrayList<String> newStudent = new ArrayList<String>();
			 arrsend.clear();

			 arrsend.add("searchStudentNameByID");
			 arrsend.add(studentID);		  
			 try {
				Main.con.sendToServer(arrsend);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		    	synchronized (Main.con) {
		    		
		    		try {
						Main.con.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		    	newStudent=	(ArrayList<String>)Main.con.getMessage();						
			if (newStudent==null){
				return (ArrayList<String>)null;
			}
			return newStudent;
		}
		


	
	@SuppressWarnings("unchecked")
	public static ArrayList<String>  searchCourseNum(String courseNum){
		
	//	ArrayList<String> classCourseArr = new ArrayList<String>();

		ArrayList<String> StudentsClassInCourseArr= new ArrayList<String>();
		 ArrayList<String> arrsend  =  new ArrayList<String>();
		 arrsend.clear();
		 arrsend.add("searchCourseNum");
		 arrsend.add(courseNum);
		 try {
				Main.con.sendToServer(arrsend);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		 synchronized (Main.con) {
	    		
	    		try {
					Main.con.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 StudentsClassInCourseArr =  (ArrayList<String>) myMain.con.getMessage();
		
		if (StudentsClassInCourseArr==null){
			return (ArrayList<String>)null;
		}
			return StudentsClassInCourseArr;
		}

	/**
	 * sendAssignRequest will save the report in DB
	 * @param studentIDTB
	 * @param classCourse
	 * @param description
	 * @return true if report data successfully saved in DB, else return false
	 */
		public static boolean sendAssignRequest(String userID,String classCourse,String description,String courseNum){	
			String typeOfRequest = "0"; //assignStudent
			String checkStatus = "0";
			//get year and semester
			 ArrayList<String> arrsend  =  new ArrayList<String>();
			 arrsend.clear();
			 arrsend.add("CurrentSemester");
			 try {
					Main.con.sendToServer(arrsend);
				} catch (IOException e) {
					
					e.printStackTrace();
				}		
			 synchronized (Main.con) {
		    		
		    		try {
						Main.con.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			ArrayList<String> currSemester= (ArrayList<String>) myMain.con.getMessage();
			//call to sendAssignRequest in server
			 arrsend.clear();
			 arrsend.add("sendAssignRequest");
			 arrsend.add(userID);
			 arrsend.add(classCourse);
			 arrsend.add(description);
			 arrsend.add(checkStatus);
			 arrsend.add(typeOfRequest);
			 arrsend.add(courseNum);
			 arrsend.add(currSemester.get(0));
			 arrsend.add(currSemester.get(1));
			 try {
					Main.con.sendToServer(arrsend);
				} catch (IOException e) {
					
					e.printStackTrace();
				}		
			 synchronized (Main.con) {
		    		
		    		try {
						Main.con.wait();
					} catch (InterruptedException e) {
						ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
						e.printStackTrace();
					}
				}
			 ArrayList<String> ans = (ArrayList<String>)  myMain.con.getMessage();
			 if(ans.get(0).equals("true"))
				 return true;
			 else return false;

		}
		
	/**
	 * This method send to server course number and student ID and checks is the student participated the course in current semester	
	 * @param courseNum
	 * @param StudentID
	 * @return true or false
	 * @author Mariya Portnoy
	 */
	public static boolean searchStudentInCourseCurrentSemester(String courseNum, String StudentID) {
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.clear();
		arrsend.add("searchStudentInCourse");
		arrsend.add(courseNum);
		arrsend.add(StudentID);
		
		 try {
				Main.con.sendToServer(arrsend);
			} catch (IOException e) {
				
				e.printStackTrace();
			}		
		 synchronized (Main.con) {
	    		
	    		try {
					Main.con.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		 ArrayList<String> ans = (ArrayList<String>)  myMain.con.getMessage();
		 if(ans.get(0).equals("true"))
			 return true;
		 else return false;
	}

		/**
		 * function that asking from DB to check if the teacher already participates the course current semester
		 * @param courseNum
		 * @param teacherID
		 * @return true if the teacher participates this course this semester, else will return false
		 */
		public static boolean searchTeacherInClassCourseCurrentSemester(String classCourseNum,String teacherID){
			ArrayList<String> teacherInTheClassCourse= new ArrayList<String>();
			 ArrayList<String> arrsend  =  new ArrayList<String>();
				arrsend.clear();
				 arrsend.add("searchTeacherInCourseCurrentSemester");
				 arrsend.add(classCourseNum);
				 arrsend.add(teacherID);
			try {
				Main.con.sendToServer(arrsend);
			} catch (IOException e) {
				
				e.printStackTrace();
			}		
		 synchronized (Main.con) {
	    		
	    		try {
					Main.con.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 ArrayList<ArrayList> teacherInTheCourse = (ArrayList<ArrayList>)myMain.con.getMessage();
			
		 if(teacherInTheCourse.get(0).equals("true"))
			 return true;
		 else return false;
			
		}

		/**
		 * check if group of course belongs to current semester
		 * @param classCourseNum 
		 * @return true if yes or false if not
		 * @author Mariya Portnoy
		 */
		public static boolean classCourseBelongToCurrSemester(String classCourseNum){
			//get year and semester
			 ArrayList<String> arrsend  =  new ArrayList<String>();
			 arrsend.clear();
			 arrsend.add("CurrentSemester");
			 try {
					Main.con.sendToServer(arrsend);
				} catch (IOException e) {
					
					e.printStackTrace();
				}		
			 synchronized (Main.con) {
		    		
		    		try {
						Main.con.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			ArrayList<String> currSemester= (ArrayList<String>) myMain.con.getMessage();
			 arrsend.clear();
			 arrsend.add("classCourseBelongToSemester");
			 arrsend.add(classCourseNum);
			 arrsend.add(currSemester.get(0));
			 arrsend.add(currSemester.get(1));
			 try {
					Main.con.sendToServer(arrsend);
				} catch (IOException e) {
					
					e.printStackTrace();
				}		
			 synchronized (Main.con) {
		    		
		    		try {
						Main.con.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			 
		ArrayList<String> ans = (ArrayList<String>)  myMain.con.getMessage();
			 if(ans.get(0).equals("true"))
				 return true;
			 else return false;
 
		}
		/**
		 * check is specific student have no pre-courses of specific course
		 * @param studentID
		 * @param courseNumber
		 * @return true if student don't have required pre-courses or false if he have
		 * @author Mariya Portnoy
		 */
		@SuppressWarnings({ "static-access", "unchecked" })
		public static boolean haveNoPreconditions(String studentID,String courseNumber){
		
			ArrayList<String> coursePreconditions = new ArrayList<String>();
			ArrayList<String> arrsend = new ArrayList<String>();
			arrsend.add("coursePrecondition");
			arrsend.add(courseNumber);
			try {
				Main.con.sendToServer(arrsend);
			} catch (IOException e) {
				
				e.printStackTrace();
			}		
		 synchronized (Main.con) {
	    		
	    		try {
					Main.con.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 coursePreconditions = (ArrayList<String>)myMain.con.getMessage();
		//get year and semester
		
		 arrsend.clear();
		 arrsend.add("CurrentSemester");
		 try {
				Main.con.sendToServer(arrsend);
			} catch (IOException e) {
				
				e.printStackTrace();
			}		
		 synchronized (Main.con) {
	    		
	    		try {
					Main.con.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		ArrayList<String> currSemester= (ArrayList<String>) myMain.con.getMessage();

		 //get student old courses
		 	arrsend.clear();
		 	arrsend.add("getStudentPrevCourses");
			arrsend.add(studentID);
			arrsend.add(currSemester.get(0));
			arrsend.add(currSemester.get(1));
			try {
				Main.con.sendToServer(arrsend);
			} catch (IOException e) {
				
				e.printStackTrace();
			}		
		 synchronized (Main.con) {
	    		
	    		try {
					Main.con.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 ArrayList<ArrayList> studentOldCourses = (ArrayList<ArrayList>)myMain.con.getMessage();
		 
		int precoursesCounter = 0;

		for (int i = 0; i < coursePreconditions.size(); i++) {
			for (int j = 0; j < studentOldCourses.size(); j++)
				if ((coursePreconditions.get(i)).equals((studentOldCourses.get(j))))
					precoursesCounter++;
		}
		if(coursePreconditions.size()==precoursesCounter)
			return false;
		else
			return true;
	}
}
