package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import ClientGui.Main;
import javafx.scene.Scene;
import projectsalmon.*;
import projectsalmon.Class;


public abstract class SecretaryController {

	static Main myMain = Main.getInstance();

	private static ArrayList<Course> list_of_courses;
	private static ArrayList<StudentsClass> list_of_classes;
	private static ArrayList<Teacher> list_of_teachers;
	
	private static ArrayList<Student> approved_students;
	
	private static Course chosen_course;
	private static StudentsClass chosen_class;
	private static Teacher chosen_teacher;
		
	// New instance to fill and save to DB
	private static StudentsClassInCourse new_class_in_course;
		
	private static boolean save_changes = false;
	
	
	public static ArrayList<Course> getOptionalCourses()
	{
		// ask DB: Get list of all the courses in the DB (sorted by anything???)
		// save it to 'list_of_courses'
		//list_of_courses = DB.coursesByID(); 
		list_of_courses = new ArrayList<Course>();
		list_of_courses.add(new Course("999",new TeachingUnit("some","thing"), 2, "poetry"));
		return list_of_courses;
	}
	
	
	public static ArrayList<StudentsClass> getOptionalClasses()
	{	
		// ask DB: Get list of all the classes in the DB (sorted by ID)
		// save it to 'list_of_courses'
		// list_of_classes = DB.classesByID();
		list_of_classes = new ArrayList<StudentsClass>();
		list_of_classes.add(new StudentsClass("A1","A","5678"));
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
	
	
	public static ArrayList<Student> getMisfitStudents()
	{
		// ask DB: Get list of all the students in a specified class (sorted ? ? ?)
		//ArrayList<Student> all_students_of_class = DB.studentsOfClass(StudentsClass);
		
		/////////////////////////////////////////////////////////////////////////////////////
		ArrayList<Student> all_students_of_class = new ArrayList<Student>();
		all_students_of_class.add(new Student("001","Hayevgeni","gitin","124"));
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
		
		new_class_in_course  = new StudentsClassInCourse(chosen_course, chosen_class, schedule, chosen_teacher);

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


}
