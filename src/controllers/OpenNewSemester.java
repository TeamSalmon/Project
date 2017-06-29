package controllers;

import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import ServerClient.ClientConsole;
import controllers.SecretaryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import projectsalmon.Student;
import projectsalmon.StudentsClass;
import javafx.fxml.Initializable;

import projectsalmon.*;

public abstract class OpenNewSemester 
{

	//get current semester from DB
	static String former_year = "2017";
	static String former_semester = "A";
	static String new_semester;
	static String new_year ;
		
	
	static ArrayList<Course> courses = new  ArrayList<Course>();
	static ArrayList<StudentsClass> classes = new  ArrayList<StudentsClass>();
	static ArrayList<Student> students = new  ArrayList<Student>();
	static ArrayList<String> lineInReport = new  ArrayList<String>();
	static ArrayList<StudentsClassInCourse> coursesGroups = new  ArrayList<StudentsClassInCourse>();


	
	public static void func1()
	{
			if ( former_semester.equals("A") )
			{
				new_semester = "B";
				new_year = former_year;
			}
			else
			{
				new_semester = "A";
				new_year = ((Integer)(Integer.valueOf(former_year) + 1)).toString();
			}
	
			
		//classes = DB.classesByID();		
		//change the levels of the classes if a new year starts
		if(new_semester.equals(1))
		{
			for (StudentsClass sclass :classes)
			{
				String level = sclass.getlevel(); 
				String name = sclass.getClassName(); 

				switch (level)
				{
					case "10":
						sclass.setlevel("11");
						sclass.setClassName( name.charAt(0) + "1" + name.charAt(2));
						break;
					case "11":
						sclass.setlevel("12"); 
						sclass.setClassName( name.charAt(0) + "2" + name.charAt(2));
						break;
					case "12":
						sclass.setlevel("X");
						sclass.setClassName("X");
						break;
				}
				
				//
				// update the class in the DB
				//
			}
			
			
			
			// ask from the DB: actual courses (not types of courses) that are studied 
			// during the current semester.  
	
			for (Course course : courses)
			{
				
				// ask DB: list of groups in this class
				for (StudentsClassInCourse cic : coursesGroups)
				{
				
					//ask from DB: get list of the students in this course-class	
					for (Student student : students)
					{
						
						// ask DB for average grade in the course for this student
						String gradeInCourse = "0";
						//lineInReport.add();
						lineInReport.add(course.getTeacher());
						lineInReport.add(student.getId());
						lineInReport.add(student.getStudentClass().getClassId());
						lineInReport.add(gradeInCourse);
						lineInReport.add(course.getCourseNumber());
						lineInReport.add(course.getYear());
						lineInReport.add(course.getSemester());
					}
				}
			}
			
		}
	}
	
	
	
}
