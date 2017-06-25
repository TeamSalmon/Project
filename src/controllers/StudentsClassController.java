package controllers;

import java.util.ArrayList;
import projectsalmon.*;


public abstract class StudentsClassController {
		
	static StudentsClass new_class;
	static String new_grade;
	
	
	
	public static void setNewGrade(String grade)
	{
		new_grade = grade;
	}
	
	
	public static ArrayList<Student> getOptionalStudents()
	{
		// ask DB: get a list of students in this grade that are not attached to 'studentsClass' instance already 
		//ArrayList<Student> OptionalStudents = DB.studnetsWithoutClass(grade);
		
		/////////////////////////////////////////////////////////////////////////////////////
		ArrayList<Student> OptionalStudents = new ArrayList<Student>();
		OptionalStudents.add(new Student("001","yevgeni","gitin","124"));
		OptionalStudents.add(new Student("002","inbar","elfasi","124"));
		OptionalStudents.add(new Student("003","mariya","portnoy","124"));
		OptionalStudents.add(new Student("004","galit","elfarsi","124"));
		OptionalStudents.add(new Student("005","tamir","zamoshzinsky","124"));
		OptionalStudents.add(new Student("006","elia","amar","124"));    	
		/////////////////////////////////////////////////////////////////////////////////////
		
		return OptionalStudents;
	}
	
	
	public static StudentsClass createNewClass(ArrayList<Student> chosen_students)
	{
		
		// ask DB: Get list of all the classes in the DB - SORTED BY 'studentsClassId'
		//ArrayList<StudentsClass> list_of_classes = DB.classesByID();
		
		/////////////////////////////////////////////////////////////////////////////////////
		ArrayList<StudentsClass> list_of_classes = new ArrayList<StudentsClass>();
		/////////////////////////////////////////////////////////////////////////////////////

		String className = generateClassName(list_of_classes, new_class);
		String classID = generateClassID(list_of_classes);
		
		StudentsClass new_class = new StudentsClass (className, new_grade, classID);
		
		// Add chosen students to the new class	
		new_class.setStudents(chosen_students);
		for (Student student : chosen_students) 	
		{
			// Connect class to student and increase amount of students in class
			student.setClass(new_class);
			new_class.setStudentsAmount( new_class.getStudentsAmount() + 1 );
			
			//
			// Send to DB: changes in student's 'StudentsClass'
			//
		}
		//
		// Send to DB: the instance 'new_class' to add to DB
		//
		return new_class;
	}
	
	
	private static String generateClassID(ArrayList<StudentsClass> list_of_classes)
	{
		// List is empty, this s_class will be the first
		if(list_of_classes.isEmpty() == true)
			return "1";
		
		String newid = "1";
		int numerical_value;
		
		// Scan the SORTED list of existing classes and return the 
		// smallest unused studentsClassId (starting at 1)
		for (StudentsClass sclass : list_of_classes) 	
		{
			if (sclass.getClassId() == newid)
			{
				// Increment 'newid'
				numerical_value = Integer.valueOf(newid);
				numerical_value++;
				newid = String.valueOf(numerical_value);
			}
			if (sclass.getClassId() != newid)
			{
				break;
			}
		
		}
		return newid;
	}

	
	private static String generateClassName(ArrayList<StudentsClass> list_of_classes, StudentsClass new_class)
	{		
		// List is empty, this s_class will be the first
		if(list_of_classes.isEmpty() == true)
			return new_grade + "1";
		
		int i = 1;
		String name = new_grade + i;
		
		// Scan the SORTED list of existing classes and return the 
		// smallest unused className (starting at 1)
		for (StudentsClass sclass : list_of_classes)
		{
			// Check only the classes with the same grade
			if( sclass.getGrade() == new_grade )
			{
				if (sclass.getClassName() == name)
				{
					// Increment 'name'
					i++;
				}
				if (sclass.getClassId() != name)
				{
					// Found an unused name
					break;
				}
			}
		}
		return name;
	}

	
	/* for now not necessary
	public boolean addStudent(Student student)
	{
		if(studentsAmount >= 30)
		{
			return false;
		}
		
		studentsAmount++;
		student.setClass(this);
		return true;
	}
	
	// for now not necessary
	
	public boolean removeStudent(Student student)
	{
		if(studentsAmount == 0)
			return false;
		studentsAmount--;
		return true;
	}
	*/
	
}

	
	
