package controllers;

import java.util.ArrayList;

import projectsalmon.Course;
import projectsalmon.Student;
import projectsalmon.StudentsClass;
import projectsalmon.StudentsClassInCourse;
import projectsalmon.Teacher;
import projectsalmon.TeachingUnit;

public abstract class SecretaryController {

	private static ArrayList<Course> list_of_courses;
	private static ArrayList<StudentsClass> list_of_classes;
	
	private static Course chosen_course;
	private static StudentsClass chosen_class;
	private static Teacher chosen_teacher;
	
	
	public static ArrayList<Course> getOptionalCourses()
	{
		// ask DB: Get list of all the courses in the DB (sorted by anything???)
		// save it to 'list_of_courses'
		//list_of_courses = DB.coursesByID(); 
		list_of_courses = new ArrayList<Course>();
		return list_of_courses;
	}
	
	
	public static ArrayList<StudentsClass> getOptionalClasses()
	{	
		// ask DB: Get list of all the classes in the DB (sorted by ID)
		// save it to 'list_of_courses'
		// list_of_classes = DB.classesByID();
		list_of_classes = new ArrayList<StudentsClass>();
		return list_of_classes;
	}
	
	
	public static void updateChosenCourseAndClass(String courseName, String className)
	{
		for (Course course : list_of_courses)
    	{
			if (course.getName() == courseName)
				chosen_course = course;
    	}
		
		for (StudentsClass sclass : list_of_classes)
    	{
			if (sclass.getClassName() == className)
				chosen_class = sclass;
    	}
	}
	
	
	public static ArrayList<Student> getMisfitStudents()
	{
		// ask DB: Get list of all the students in a specified class (sorted ? ? ?)
		//ArrayList<Student> all_students_of_class = DB.studentsOfClass(StudentsClass);
		
		/////////////////////////////////////////////////////////////////////////////////////
		ArrayList<Student> all_students_of_class = new ArrayList<Student>();
		all_students_of_class.add(new Student("001","Hayevgeni","gitin","124"));
		all_students_of_class.add(new Student("002","Hainbar","elfasi","124")); 	
		/////////////////////////////////////////////////////////////////////////////////////
		
		
		// ask DB: Get list the courses that are the preconditions for the specified course
		ArrayList<Course> preconditions = new ArrayList<Course>();
		// preconditions = coursePreconditions(chosen_course);
		
		preconditions.add(new Course("111",new TeachingUnit("some","thing"), 2, "Robin 101"));
		preconditions.add(new Course("222",new TeachingUnit("some","thing"), 2, "guitar"));
		
		
		// List of 'Student's that lack the preconditions of the course
		ArrayList<Student> misfit_students = new ArrayList<Student>();
		
		// List of 'Student's that has the preconditions of the course
		ArrayList<Student> fitting_students = new ArrayList<Student>();
						

		
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
			ArrayList<Course> student_former_courses1 = new ArrayList<Course>();
			student_former_courses1.add(new Course("111",new TeachingUnit("some","thing"), 2, "Robin 101"));
			student_former_courses1.add(new Course("222",new TeachingUnit("some","thing"), 2, "guitar"));

			ArrayList<Course> student_former_courses2 = new ArrayList<Course>();
			student_former_courses1.add(new Course("111",new TeachingUnit("some","thing"), 2, "Robin 101"));
			
			/////////////////////////////////////////////////////////////////////////////////////
			
			
			
			for (Course condition : preconditions)
			{
				if(i == 1)
				for (Course course : student_former_courses1)
				{	
					if( condition.getCourseNumber() == course.getCourseNumber() )
					{	
						// count the precondition
						exists++;
						break;
					}
				}
				//////////////////////
				if(i == 2)
					for (Course course : student_former_courses2)
					{	
						if( condition.getCourseNumber() == course.getCourseNumber() )
						{	
							// count the precondition
							exists++;
							break;
						}
					}
				//////////////////////
			}
		
			// counted all the preconditions? -> add student to fit/misfit list
			if ( exists == preconditions.size() )
			{
				fitting_students.add(student);
			}
			else
			{
				misfit_students.add(student);
			}
		}
		
		return misfit_students;		
		
	}

	
	
	
	
	public static void assignSClassToCourse()
	{
		
		
		// New instance to fill and save to DB
		StudentsClassInCourse new_class_in_course;
	
		
		
				
		
		
		// ask DB: Get list of all the teachers that teach this course + have ENOUGH FREE WEEKLY HOURS (not including the current semester)
		// save it to 'optional_teachers'
		// ArrayList<Teacher> optional_teachers = DB.optionalTeachers(chosen_course);
		
		
		
		// send to GUI: Show list of optional teachers
		// Gui returns: chosen_teacher = (Gui_select_teacher_function);

		
		//new_class_in_course  = new StudentsClassInCourse(chosen_course, chosen_class, ArrayList<Class> classSchedule, chosen_teacher);

		
		
		// Add weekly hours of 'chosen_course' to 'chosen_teacher''s weekly hours
		chosen_teacher.setWeekly_hours( chosen_teacher.getWeekly_hours() + chosen_course.getWeeklyHours());
		
		
		
		
		
		// send 'new_class_in_course' to DB: 
	}
	
	
	public static Course get_chosen_course()
	{
		return chosen_course;
	}
	public static StudentsClass get_chosen_class()
	{
		return chosen_class;
	}
		
}
