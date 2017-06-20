package projectsalmon;

import java.util.ArrayList;

public class Secretary extends User{

	
	public Secretary(String id,String first, String last, String password)
	{
		super(id,first,last,password);
	}

	
	private void assignSClassToCourse()
	{
		
		Course chosen_course;
		StudentsClass chosen_class;
		
		// New instance to fill and save to DB
		StudentsClassInCourse new_class_in_course;
		
		// List of all the 'Student's in a specified class
		ArrayList<Student> students_of_class;
		// List of 'Student's that lack the preconditions of the course
		ArrayList<Student> misfit_students = new ArrayList<Student>();
		// List of 'Student's that has the preconditions of the course
		ArrayList<Student> fitting_students = new ArrayList<Student>();
				
				
		// ask DB: Get list of all the courses in the DB (sorted by anything???)
		// save it to 'list_of_courses'
		ArrayList<Course> list_of_courses = new ArrayList<Course>();
		//list_of_courses = DB.coursesByID(); 
	
		// ask DB: Get list of all the classes in the DB (sorted by ID)
		// save it to 'list_of_courses'
		ArrayList<StudentsClass> list_of_classes = new ArrayList<StudentsClass>();
		// list_of_classes = DB.classesByID();

		// ask DB: Get list of all the students in a specified class (sorted by anything???)
		// save it to 'all_students'
		// students_of_class = DB.studentsOfClass(StudentsClass);
		
		// ask DB: Get list the courses that are the preconditions for the specified course
		ArrayList<Course> preconditions = new ArrayList<Course>();
		// preconditions = coursePreconditions(chosen_course);
		
		
		// Check for each 'Student' from the 'chosen_class' if they have the 'preconditions' of the 'chosen_course' 
		for (Student student : students_of_class)
		{
			int exists = 0;
			ArrayList<Course> student_former_courses;
			// ask DB: Get list of all the courses that the student ALREADY FINISHED (not including the current semester)
			// save it to 'student_former_courses'
			//student_former_courses = DB.studentOldCourses(student);
			
			for (Course condition : preconditions)
			{
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
				fitting_students.add(student);
			}
			else
			{
				misfit_students.add(student);
			}
		}
		
		
		// send to GUI: "All students has the preconditions / Some students don't"
		
		
		// ask DB: Get list of all the teachers that teach this course + have ENOUGH FREE WEEKLY HOURS (not including the current semester)
		// save it to 'optional_teachers'
		// ArrayList<Teacher> optional_teachers = DB.optionalTeachers(student);
		
		
		
		
		
		// send to DB: 
	}
	
	
}
