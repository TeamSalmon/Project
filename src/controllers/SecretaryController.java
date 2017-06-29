package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import ClientGui.Main;
import javafx.scene.Scene;
import projectsalmon.*;
import projectsalmon.Class;
import ServerClient.ClientConsole;


public abstract class SecretaryController {

	static Main myMain = Main.getInstance();

	private static ArrayList<Course> list_of_courses;
	private static ArrayList<StudentsClass> list_of_classes;
	private static ArrayList<Teacher> list_of_teachers;
	private static ArrayList<Student> all_students_of_class;
	
	private static ArrayList<Student> approved_students;
	
	private static Course chosen_course;
	private static StudentsClass chosen_class;
	private static Teacher chosen_teacher;
		
	// New instance to fill and save to DB
	private static StudentsClassInCourse new_class_in_course;
		
	private static boolean save_changes = false;
	
	
	public static ArrayList<Course> getOptionalCourses() throws IOException
	{
		
		// ask DB: Get list of all the courses in the DB (sorted by anything???)
		// save it to 'list_of_courses'
		
		ArrayList<String> query_coursesByID = new ArrayList<String>();
		query_coursesByID.add("courseByID");
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_coursesByID);
		myMain.getConnection().getAnswer(list_of_courses);

		/////////////////////////////////////////////////////////////////////////////////
		//list_of_courses = new ArrayList<Course>();
		//exampli gracia: list_of_courses.add(new Course("999",new TeachingUnit("some","thing"), 2, "poetry"));
		/////////////////////////////////////////////////////////////////////////////////
		return list_of_courses;
	}
	
	
	public static ArrayList<StudentsClass> getOptionalClasses() throws IOException
	{	
		// ask DB: Get list of all the classes in the DB (sorted by ID)
		// save it to 'list_of_courses'
	
		ArrayList<String> query_studentClassesByID = new ArrayList<String>();
		query_studentClassesByID.add("studentClassesByID");
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_studentClassesByID);
		myMain.getConnection().getAnswer(list_of_classes);

		/////////////////////////////////////////////////////////////////////////////////
		//list_of_classes = new ArrayList<StudentsClass>();
		//list_of_classes.add(new StudentsClass("A1","A","5678"));
		/////////////////////////////////////////////////////////////////////////////////
		return list_of_classes;
	}
	
	
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
	
	
	public static ArrayList<Student> getMisfitStudents() throws IOException
	{
		// ask DB: Get list of all the students in a specified class (sorted ? ? ?)
		//ArrayList<Student> all_students_of_class = DB.studentsOfClass(StudentsClass);
		
		ArrayList<String> query_studentsOfClass = new ArrayList<String>();
		query_studentsOfClass.add("studentsOfClass");
		query_studentsOfClass.add("chosen_class");
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_studentsOfClass);
		myMain.getConnection().getAnswer(all_students_of_class);
		
		/////////////////////////////////////////////////////////////////////////////////////
		//ArrayList<Student> all_students_of_class = new ArrayList<Student>();
		//all_students_of_class.add(new Student("001","Hayevgeni","gitin","124"));
		/////////////////////////////////////////////////////////////////////////////////////
		
		
		// ask DB: Get list the courses that are the preconditions for the specified course
		ArrayList<Course> preconditions = new ArrayList<Course>();
		// preconditions = coursePreconditions(chosen_course);
		
		preconditions.add(new Course("111",new TeachingUnit("some","thing"), 2, "Robin 101"));
		preconditions.add(new Course("222",new TeachingUnit("some","thing"), 2, "guitar"));
		
		
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

			//ArrayList<Course> student_former_courses;
			// ask DB: Get list of all the courses that the student ALREADY FINISHED (not including the current semester)
			// save it to 'student_former_courses'
			//student_former_courses = DB.studentOldCourses(student);
			
			
			/////////////////////////////////////////////////////////////////////////////////////
			ArrayList<Course> student_former_courses = new ArrayList<Course>();
			//student_former_courses.add(new Course("111",new TeachingUnit("some","thing"), 2, "Robin 101"));
			student_former_courses.add(new Course("222",new TeachingUnit("some","thing"), 2, "guitar"));
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

	
	public static ArrayList<Teacher> getOptionalTeachers() {

		// ask DB: Get list of all the teachers that teach this course + have ENOUGH FREE WEEKLY HOURS
		//list_of_teachers = DB.optionalTeachers(chosen_course.getCourseNumber());	
		
		
		/////////////////////////////////////////////////////////////////////////////////////
		list_of_teachers = new ArrayList<Teacher>();
		list_of_teachers.add(new Teacher(30,"711", "Paskal", "Perez","111111"));
		list_of_teachers.add(new Teacher(34,"722", "Albert", "Gerbily","111111"));
		/////////////////////////////////////////////////////////////////////////////////////

		return list_of_teachers;
	}
	
	


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
		// update 'chosen_teacher' in DB
		// send 'new_class_in_course' to DB: 
		
		//for(Student student: approved_students)
		//{
			// add to new class in course
			// update 'student' in DB
		//}
		
////////////////////////////////////////////////////
	}

	
	public static Course get_chosen_course()
	{
		return chosen_course;
	}

	
	public static StudentsClass get_chosen_class()
	{
		return chosen_class;
	}



	
	public static boolean isSave_changes() {
		return save_changes;
	}

	
	public static void setSave_changes(boolean save_changes) {
		SecretaryController.save_changes = save_changes;
	}


	public static void assignClassToCourseEXIT(int n) throws IOException {
		for(int i = 0 ; i < n ; i++)
		{
			myMain.getMange().myStack.pop();
		}
		myMain.getMange().changeScene((Scene)myMain.getMange().myStack.pop());
	}

//Mariya-----------------------------------------------------------------------------------

	//	static String searchStudentID;
	
	public static boolean sendAppointTeacherRequest(String teacherID,String classCourse, String description){
		boolean sentsucceaafully=false;
		ArrayList<String> arrsend  =  new ArrayList<String>();
				arrsend.clear();
				 arrsend.add("sendAppointTeacherRequest");
				 arrsend.add(teacherID);
				 arrsend.add(classCourse);
				 arrsend.add(description);
				 arrsend.add("2");
				 try {
					myMain.getConnection().getClient().handleMessageFromClientUI((Object)arrsend);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 sentsucceaafully = (boolean)myMain.con.getMessage();
			
		return sentsucceaafully;
	}
	
	
	public static boolean removeTeacherfromCourseRequest(String courseNumber,String teacherID, String description){
		boolean sendRequestSucceded=false;
		 ArrayList<String> arrsend  =  new ArrayList<String>();
		 arrsend.clear();
		 arrsend.add("removeTeacherfromCourseRequest");
		 arrsend.add(courseNumber);
		 arrsend.add(teacherID);
		 arrsend.add(description);
		 arrsend.add("3");
		 
		 try {
			myMain.getConnection().getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	sendRequestSucceded = (boolean) myMain.getConnection().getMessage();
		return sendRequestSucceded;
			}
			
/**
 * 
 * @param courseNumber
 * @param studentID
 * @param description
 * @return
 */
		public static boolean  removeStudentfromCourseRequest(String courseNumber, String studentID,String description){
			boolean sendRequestSucceded=false;
			 ArrayList<String> arrsend  =  new ArrayList<String>();
			 arrsend.clear();
			 arrsend.add("removeStudentfromCourseRequest");
			 arrsend.add(studentID);
			 arrsend.add(courseNumber);
			 arrsend.add(description);
			 arrsend.add("1");
			 
			 try {
				myMain.getConnection().getClient().handleMessageFromClientUI(arrsend);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		sendRequestSucceded = (boolean) myMain.getConnection().getMessage();
			return sendRequestSucceded;
		}
		
		public static float  getCourseWHours(String courseNum){
			ArrayList<String> arrsend  =  new ArrayList<String>();
			 arrsend.clear();
			 arrsend.add("getCourseWHours");
			 arrsend.add(courseNum);
			 try {
				myMain.getConnection().getClient().handleMessageFromClientUI((Object)arrsend);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return 	 (float) myMain.getConnection().getMessage();
		}
		
		
		/**
		 * send to DB teacherID and expected to accept object of teacher
		 * @param TeacherID
		 * @return object Teacher
		 */
		public static Teacher  searchTeacherID(String TeacherID){
			
			 ArrayList<String> arrsend  =  new ArrayList<String>();
			 arrsend.clear();
			 arrsend.add("searchTeacherID");
			 arrsend.add(TeacherID);
		
				//myMain.con.getClient().handleMessageFromClientUI(arrsend);
				//	currentSemester = (Semester)myMain.con.getmessage;
//				try {
//					myMain.con.getClient().handleMessageFromClientUI((Object)arrsend);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Student newStudent = (Student)myMain.con.getMessage();
				try {
					myMain.getConnection().getClient().handleMessageFromClientUI((Object)arrsend);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Teacher newTeacher =  (Teacher) myMain.getConnection().getMessage();
				
			
		//}
		//----------
		//	Student newStudent = new Student("123","Galit" ,"Alfarsi" ,"0000");
			
			if (newTeacher == null)
				return (Teacher)null;
			return newTeacher;
		}

		
		
		public static Student  searchStudentID(String studentID){
			
			 ArrayList<String> arrsend  =  new ArrayList<String>();
			 arrsend.clear();
			 arrsend.add("searchStudentID");
			 arrsend.add(studentID);
		
				//myMain.con.getClient().handleMessageFromClientUI(arrsend);
				//	currentSemester = (Semester)myMain.con.getmessage;
//				try {
//					myMain.con.getClient().handleMessageFromClientUI((Object)arrsend);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Student newStudent = (Student)myMain.con.getMessage();
				try {
					myMain.getConnection().getClient().handleMessageFromClientUI((Object)arrsend);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Student newStudent =  (Student) myMain.getConnection().getMessage();
				
			
		//}
		//----------
		//	Student newStudent = new Student("123","Galit" ,"Alfarsi" ,"0000");
			
			if (newStudent == null)
				return (Student)null;
			return newStudent;
		}
		


	public static ArrayList<StudentsClassInCourse>  searchCourseNum(String courseNum){
		
	//	ArrayList<String> classCourseArr = new ArrayList<String>();
		ArrayList<StudentsClassInCourse> StudentsClassInCourseArr= new ArrayList<StudentsClassInCourse>();
//		 ArrayList<String> arrsend  =  new ArrayList<String>();
//		arrsend.clear();
//		 arrsend.add("searchCourseNum");
//		 arrsend.add(CourseNum);
//		try
//		{
//			this.con.getClient().handleMessageFromClientUI(arrsend);
//		}
//		catch(IOException e)
//		{
//			ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
//		}
//		ClientConsole.getLog().setText(con.getStringOut());
	//}

		  Calendar startsAt = Calendar.getInstance();
		  Calendar endsAt = Calendar.getInstance();
		  startsAt.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		  startsAt.set(Calendar.HOUR, 10);
		  startsAt.set(Calendar.MINUTE, 30);
		  endsAt.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		  endsAt.set(Calendar.HOUR, 12);
		  endsAt.set(Calendar.MINUTE, 30);
		  Class newClass= new Class(startsAt,endsAt);
		  ArrayList<Class> classschedule = new ArrayList<>();
		  classschedule.add(newClass);
		  TeachingUnit newTeachingUnit = new TeachingUnit("22","Math");
		  Teacher newTeacher = new Teacher(20,"123456789","Elena", "Smith", "abcdefg");
		
		  Course newCourse = new Course("123", "Algebra1",newTeachingUnit, "In course Algebra 1 you will learn how to calculate matrices", (float)3.0);
		  
		  StudentsClass newStudentsClass = new StudentsClass("2646452","10A", "10");
		  
		StudentsClassInCourse newClassCourse1 = new StudentsClassInCourse("1000001",newCourse,newStudentsClass,classschedule,newTeacher);
		StudentsClassInCourse newClassCourse2 = new StudentsClassInCourse("1000002",newCourse,newStudentsClass,classschedule,newTeacher);
		
		StudentsClassInCourseArr.add(newClassCourse1);
		StudentsClassInCourseArr.add(newClassCourse2);
		
			
			
			return StudentsClassInCourseArr;
		}

	/**
	 * sendStudentRequest will save the report in DB
	 * @param studentIDTB
	 * @param classCourse
	 * @param description
	 * @return true if report data successfully saved in DB, else return false
	 */
		public static boolean sendAssignStudentRequest(String studentID, String classCourse, String description){
//			 ArrayList<String> arrsend  =  new ArrayList<String>();
	//		arrsend.clear();
//			 arrsend.add("sendStudentRequest");
//			 arrsend.add(studentID);
//			 arrsend.add(classCourse);
//			 arrsend.add(description);
//			 arrsend.add("0");
//			try
//			{
//				this.con.getClient().handleMessageFromClientUI(arrsend);
//			}
//			catch(IOException e)
//			{
//				ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
//			}
//			ClientConsole.getLog().setText(con.getStringOut());
		
		return true;
		}
		
		
	
		
		
		public static boolean  searchStudentInCourseCurrentSemester(String courseNum,String StudentID){
			
//			 ArrayList<String> arrsend  =  new ArrayList<String>();
//			arrsend.clear();
//			 arrsend.add("searchStudentInCourse");
//			 arrsend.add(courseNum);
//			 arrsend.add(StudentID);
//			try
//			{
//				this.con.getClient().handleMessageFromClientUI(arrsend);
//			}
//			catch(IOException e)
//			{
//				ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
//			}
//			ClientConsole.getLog().setText(con.getStringOut());
			
		//	if (studentIncourse)
			return true;
		//	else return false;
		}
		
		/**
		 * function that asking from DB to check if the teacher already participates the course current semester
		 * @param courseNum
		 * @param teacherID
		 * @return true if the teacher participates this course this semester, else will return false
		 */
		public static boolean searchTeacherInClassCourseCurrentSemester(String classCourseNum,String teacherID){
			boolean teacherInTheClassCourse= false;
//			 ArrayList<String> arrsend  =  new ArrayList<String>();
//				arrsend.clear();
//				 arrsend.add("searchTeacherInCourseCurrentSemester");
//				 arrsend.add(classCourseNum);
//				 arrsend.add(teacherID);
			//this.con.getClient().handleMessageFromClientUI(arrsend);
			//teacherInTheCourse = (boolean)myMain.con.getMessage();
			
			return teacherInTheClassCourse;
			
		}
	}
