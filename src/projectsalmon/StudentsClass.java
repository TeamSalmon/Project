package projectsalmon;

import java.util.ArrayList;

/**
 * 
 * 
 * 
 *
 * @author Elia
 */
public class StudentsClass
{
	private ArrayList<Student> students;
	private String studentsClassId;
	private String grade;
	private String className;
	private int studentsAmount;
	
	public StudentsClass(String className, String grade, String studentsClassId)
	{
		this.setClassName(className);
		this.grade = grade;
		this.studentsAmount = 0;
		this.studentsClassId = studentsClassId;
	}
	
	
	private StudentsClass createNewClass()
	{
		StudentsClass new_class;
		String classID;
		String grade = "0";
		boolean form_list = true;
		
		// Chosen students for the new class
		ArrayList<Student> students_of_class = new ArrayList<Student>();

		// List of all the free students in the DB
		ArrayList<Student> available_students = new ArrayList<Student>();

		// List of all the classes in the DB sorted by ID
		ArrayList<StudentsClass> list_of_classes = new ArrayList<StudentsClass>();

		// ask GUI: get parameters from user: grade.
		
		// ask DB: Get list of all the classes in the DB - SORTED BY 'studentsClassId'
		// list_of_classes = DB.classesByID();
		classID = generateClassID(list_of_classes);


		new_class = new StudentsClass (grade, classID);
			
		// ask DB: get a list of students in this grade that are not attached to 'studentsClass' instance already 
		//available_students = DB.studnetsWithoutClass(grade);
		
		// GUI: user will select the chosen students from the list		
		while(form_list)
		{		
		// Send to GUI: show the list with check box and return the 
		// ones the user marked to the list 'students_of_class'
			if (students_of_class.size() > 30)
			{
				// Send to GUI: too many students, ask to choose students again
			}
			if (students_of_class.size() <= 30)
			{
				// Send to GUI: ask user if sure
				//if (finish_button)
					form_list = false;
			}
		}
		
		
		// Add chosen students to the new class	
		for (Student student : students_of_class) 	
		{
			student.setClass(new_class);
			new_class.studentsAmount++;
			
			// Send to DB: changes in student's 'StudentsClass'

		}

		// Send to DB: the instance 'new_class' to add to DB

		return new_class;
		
	}
	
	// for now not necessary
	/*
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
	*/
	// for now not necessary
	/*
	public boolean removeStudent(Student student)
	{
		if(studentsAmount == 0)
			return false;
		studentsAmount--;
		return true;
	}
	*/
	
	private String generateClassID(ArrayList<StudentsClass> list_of_classes)
	{
		String newid = "1";
		int numerical_value;

		// List is empty, this s_class will be the first
		if(list_of_classes.isEmpty() == true)
			return "1";
		
		// Scan the SORTED list of existing classes and return the 
		// smallest unused studentsClassId (starting at 1)
		for (StudentsClass sclass : list_of_classes) 	
		{
			if (sclass.studentsClassId == newid)
			{
				// Increment 'newid'
				numerical_value = Integer.valueOf(newid);
				numerical_value++;
				newid = String.valueOf(numerical_value);
			}
			if (sclass.studentsClassId != newid)
			{
				break;
			}
		
		}
		return newid;
	}
		
	
	public void setGrade(String grade){this.grade = grade;}
	public String getGrade(){return grade;}
	public int getStudentsAmount(){return studentsAmount;}
	public String getClassId(){return studentsClassId;}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
