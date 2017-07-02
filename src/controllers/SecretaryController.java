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
 * @author Mariya and Elia
 */
public abstract class SecretaryController {

	
	static Main myMain = Main.getInstance();

	private static Object received_object;
	
		
	
	/* Assign Class to Course*/
	
	
	private static ArrayList<Course> list_of_courses;
	private static ArrayList<StudentsClass> list_of_classes;
	private static ArrayList<Teacher> list_of_teachers;
	private static ArrayList<Student> all_students_of_class;
	private static ArrayList<Course> preconditions;
	private static ArrayList<Course> student_former_courses;
	private static ArrayList<Student> approved_students;
	
	private static Course chosen_course;
	private static StudentsClass chosen_class;
	private static Teacher chosen_teacher;
	
	private static boolean save_changes = false;
	
	private static StudentsClassInCourse new_class_in_course;
		
	

	/**
	 * Returns optional courses to assign classes to.
	 * @return ArrayList<Course> list of optional courses.
	 * @throws IOException
	 */
	public static ArrayList<Course> getOptionalCourses() throws IOException
	{
		
		// ask DB: Get list of all the courses in the DB (sorted by anything???)
		// save it to 'list_of_courses'
		
		ArrayList<String> query_coursesByID = new ArrayList<String>();
		query_coursesByID.add("coursesByID");
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_coursesByID);
		received_object = myMain.getConnection().getMessage();
		list_of_courses = (ArrayList<Course>)received_object;
		
		/////////////////////////////////////////////////////////////////////////////////
		//list_of_courses = new ArrayList<Course>();
		//list_of_courses.add(new Course("999",new TeachingUnit("some","thing"), 2, "poetry"));
		/////////////////////////////////////////////////////////////////////////////////
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
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_studentClassesByID);
		received_object = myMain.getConnection().getMessage();
		list_of_classes = (ArrayList<StudentsClass>)received_object;

		/////////////////////////////////////////////////////////////////////////////////
		//list_of_classes = new ArrayList<StudentsClass>();
		//list_of_classes.add(new StudentsClass("A1","A","5678"));
		/////////////////////////////////////////////////////////////////////////////////
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
		query_studentsOfClass.add(chosen_class.getClassId().toString());
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_studentsOfClass);
		received_object = myMain.getConnection().getMessage();
		all_students_of_class = (ArrayList<Student>)received_object;
		
		/////////////////////////////////////////////////////////////////////////////////////
		//ArrayList<Student> all_students_of_class = new ArsrayList<Student>();
		//all_students_of_class.add(new Student("001","Hayevgeni","gitin","124"));
		/////////////////////////////////////////////////////////////////////////////////////
		
		
		// ask DB: Get list the courses that are the preconditions for the specified course
		// preconditions = coursePreconditions(chosen_course);
		
		ArrayList<String> query_coursePreconditions = new ArrayList<String>();
		query_coursePreconditions.add("coursePreconditions");
		query_coursePreconditions.add(chosen_course.getCourseNumber().toString());
		myMain.getConnection().getClient().handleMessageFromClientUI((Object) query_coursePreconditions);
		received_object = myMain.getConnection().getMessage();
		preconditions = (ArrayList<Course>)received_object;

		
		/////////////////////////////////////////////////////////////////////////////////////
		//ArrayList<Course> preconditions = new ArrayList<Course>(); 
		//preconditions.add(new Course("111",new TeachingUnit("some","thing"), 2, "Robin 101"));
		//preconditions.add(new Course("222",new TeachingUnit("some","thing"), 2, "guitar"));
		/////////////////////////////////////////////////////////////////////////////////////

		
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
			myMain.getConnection().getClient().handleMessageFromClientUI((Object) query_studentOldCourses);
			received_object = myMain.getConnection().getMessage();
			student_former_courses = (ArrayList<Course>)received_object;
			
			/////////////////////////////////////////////////////////////////////////////////////
			//ArrayList<Course> student_former_courses = new ArrayList<Course>();
			//student_former_courses.add(new Course("111",new TeachingUnit("some","thing"), 2, "Robin 101"));
			//student_former_courses.add(new Course("222",new TeachingUnit("some","thing"), 2, "guitar"));
			/////////////////////////////////////////////////////////////////////////////////////
			
			
			for (Course condition : preconditions)
			{
				if(i == 1)
				for (Course course : student_former_courses)
				{	
					if( condition.getCourseNumber() == course.getCourseNumber() )
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
		myMain.getConnection().getClient().handleMessageFromClientUI((Object) query_optionalTeachersForCourse);
		received_object = myMain.getConnection().getMessage();
		list_of_teachers = (ArrayList<Teacher>)received_object;
		
		// Keep only the teachers that have enough free teaching hours
		for (Teacher teacher : list_of_teachers)
    	{
			if( teacher.getMax_maximal_weekly_hours() - teacher.getWeekly_hours() < chosen_course.getWeeklyHours())
				list_of_teachers.remove(teacher);
    	}
		
		/////////////////////////////////////////////////////////////////////////////////////
		//list_of_teachers = new ArrayList<Teacher>();
		//list_of_teachers.add(new Teacher(30,"711", "Paskal", "Perez","111111"));
		//list_of_teachers.add(new Teacher(34,"722", "Albert", "Gerbily","111111"));
		/////////////////////////////////////////////////////////////////////////////////////

		return list_of_teachers;
	}

	/**
	 * Updates the chosen teacher and assigns all the gathered data to the DB.
	 * @param teacherName
	 */
	public static void updateChosenTeacher(String teacherName) {
		
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
		
		// update 'chosen_teacher' in DB
				
		//for(Student student: approved_students)
		//{
			// add to new class in course
			// update 'student' in DB
		//}
		
////////////////////////////////////////////////////
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
		for(int i = 0 ; i < n ; i++)
		{
			myMain.getMange().myStack.pop();
		}
		myMain.getMange().changeScene((Scene)myMain.getMange().myStack.pop());
	}

	
	
	
	
	
	/* Define Class*/
	
	
	private static ArrayList<Student> OptionalStudents;
	//private static ArrayList<StudentsClass> list_of_classes; -> Using the 'list_of_classes'
	// from the Assign Class to Course scope
	
	static StudentsClass new_class;
	static String new_grade;

	
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
	 * @return ArrayList<Student>
	 * @throws IOException
	 */
	public static ArrayList<Student> getOptionalStudents() throws IOException 
	{
		// ask DB: get a list of students in this grade that are not attached to
		// 'studentsClass' instance already
		// ArrayList<Student> OptionalStudents = DB.studnetsWithoutClass(grade);
		/*
		 * ArrayList<String> query_studentsWithoutClass = new
		 * ArrayList<String>();
		 * query_studentsWithoutClass.add("studentClassesByID");
		 * myMain.getConnection().getClient().handleMessageFromClientUI((Object)
		 * query_studentsWithoutClass); received_object =
		 * myMain.getConnection().getMessage(); OptionalStudents =
		 * (ArrayList<Student>)received_object;
		 */
		/////////////////////////////////////////////////////////////////////////////////////
		ArrayList<Student> OptionalStudents = new ArrayList<Student>();
		OptionalStudents.add(new Student("001", "yevgeni", "gitin", "124"));
		OptionalStudents.add(new Student("002", "inbar", "elfasi", "124"));
		OptionalStudents.add(new Student("003", "mariya", "portnoy", "124"));
		OptionalStudents.add(new Student("004", "galit", "elfarsi", "124"));
		OptionalStudents.add(new Student("005", "tamir", "zamoshzinsky", "124"));
		OptionalStudents.add(new Student("006", "elia", "amar", "124"));
		/////////////////////////////////////////////////////////////////////////////////////

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

		/*
		 * ArrayList<String> query_studentClassesByID = new ArrayList<String>();
		 * query_studentClassesByID.add("studentClassesByID");
		 * myMain.getConnection().getClient().handleMessageFromClientUI((Object)
		 * query_studentClassesByID); received_object =
		 * myMain.getConnection().getMessage(); list_of_classes =
		 * (ArrayList<StudentsClass>)received_object;
		 */

		/////////////////////////////////////////////////////////////////////////////////////
		ArrayList<StudentsClass> list_of_classes = new ArrayList<StudentsClass>();
		/////////////////////////////////////////////////////////////////////////////////////

		String className = generateClassName(list_of_classes, new_class);
		String classID = generateClassID(list_of_classes);

		StudentsClass new_class = new StudentsClass(className, new_grade, classID);

		// Add chosen students to the new class
		new_class.setStudents(chosen_students);
		new_class.setStudentsAmount(chosen_students.size());

		// Send to DB: the instance 'new_class' to add to DB
		
		  ArrayList<String> query_addNewClass = new ArrayList<String>();
		  query_addNewClass.add("addNewClass"); query_addNewClass.add(classID);
		  query_addNewClass.add(((Integer)
		  new_class.getStudentsAmount()).toString());
		  query_addNewClass.add(new_grade); query_addNewClass.add(className);
		  
		  myMain.getConnection().getClient().handleMessageFromClientUI((Object)
		  query_addNewClass); received_object =
		  myMain.getConnection().getMessage(); boolean answer1 = (boolean)
		  received_object;
		  
		  if (answer1 == false)
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
			  query_updateClassOfStudent.add(student.getId().toString());
			  query_updateClassOfStudent.add(new_class.getClassId().toString());
			  
			  myMain.getConnection().getClient().handleMessageFromClientUI((Object)
			  query_updateClassOfStudent); received_object =
			  myMain.getConnection().getMessage(); boolean answer2 =
			  (boolean)received_object;
			  
			  if(answer2 == false)
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
