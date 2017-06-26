package controllers;

import java.io.IOException;
import java.util.ArrayList;

import ClientGui.Main;
import javafx.scene.Scene;
import projectsalmon.*;


public abstract class StudentsClassController {
		
	static Main myMain = Main.getInstance();

	private static ArrayList<Student> OptionalStudents;
	private static ArrayList<StudentsClass> list_of_classes;
	private static Object received_object;	
	
	
	static StudentsClass new_class;
	static String new_grade;
	
	
	public static void setNewGrade(String grade)
	{
		new_grade = grade;
	}
	
	
	public static ArrayList<Student> getOptionalStudents() throws IOException
	{
		// ask DB: get a list of students in this grade that are not attached to 'studentsClass' instance already 
		//ArrayList<Student> OptionalStudents = DB.studnetsWithoutClass(grade);
		
		ArrayList<String> query_studentsWithoutClass = new ArrayList<String>();
		query_studentsWithoutClass.add("studentClassesByID");
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_studentsWithoutClass);
		received_object = myMain.getConnection().getMessage();
		OptionalStudents = (ArrayList<Student>)received_object;
		
		/////////////////////////////////////////////////////////////////////////////////////
		//ArrayList<Student> OptionalStudents = new ArrayList<Student>();
		//OptionalStudents.add(new Student("001","yevgeni","gitin","124"));
		//OptionalStudents.add(new Student("002","inbar","elfasi","124"));
		//OptionalStudents.add(new Student("003","mariya","portnoy","124"));
		//OptionalStudents.add(new Student("004","galit","elfarsi","124"));
		//OptionalStudents.add(new Student("005","tamir","zamoshzinsky","124"));
		//OptionalStudents.add(new Student("006","elia","amar","124"));    	
		/////////////////////////////////////////////////////////////////////////////////////
		
		return OptionalStudents;
	}
	
	
	public static StudentsClass createNewClass(ArrayList<Student> chosen_students) throws IOException
	{
		
		// ask DB: Get list of all the classes in the DB - SORTED BY 'studentsClassId'
		//ArrayList<StudentsClass> list_of_classes = DB.classesByID();
		
		ArrayList<String> query_studentClassesByID = new ArrayList<String>();
		query_studentClassesByID.add("studentClassesByID");
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_studentClassesByID);
		received_object = myMain.getConnection().getMessage();
		list_of_classes = (ArrayList<StudentsClass>)received_object;
		
		/////////////////////////////////////////////////////////////////////////////////////
		//ArrayList<StudentsClass> list_of_classes = new ArrayList<StudentsClass>();
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
			if( sclass.getlevel() == new_grade )
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
	

	public static void defineClassEXIT(int n) throws IOException {
		for(int i = 0 ; i < n ; i++)
		{
			myMain.getMange().myStack.pop();
		}
		myMain.getMange().changeScene((Scene)myMain.getMange().myStack.pop());
	}
	
}

	
	
