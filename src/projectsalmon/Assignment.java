package projectsalmon;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class Assignment
{
	private String assignmentId;
	private String courseId;
	private String assignmentName;
	private Calendar deadline;
	private String instructionForSubmission;
	private File file;
	
	public Assignment(String assignmentId,String courseId,String assignmentName, Calendar deadline, String instructions, File file)
	{
		this.file = file;
		this.assignmentId = assignmentId;
		this.deadline = deadline;
		this.instructionForSubmission = instructions;
		this.assignmentName = assignmentName;
		this.courseId = courseId;
	}
	public Assignment(String assignmentId,String courseId,String assignmentName, String deadline, String instructions)
	{
		this.assignmentId = assignmentId;
		this.instructionForSubmission = instructions;
		this.assignmentName = assignmentName;
		this.courseId = courseId;
		this.deadline = stringToCalendar(deadline);
	}
	
	public void setFile(String filePath){this.file = new File(filePath);}
	public void setCourse(String course){this.courseId = course;}
	public void setDeadline(Calendar deadline){this.deadline = deadline;}
	public void setInstructions(String instruction){this.instructionForSubmission = instruction;}
	public void setName(String name){this.assignmentName = name;}
	
	public File getfile(){return file;}
	public String getAssignmntId(){return this.assignmentId;}
	public Calendar getDeadline(){return this.deadline;}
	public String getInstructions(){return this.instructionForSubmission;}
	public String getName(){return assignmentName;}
	public String getCourse(){return courseId;}
	public String getDeadlineAsString(){return deadline.get(Calendar.DAY_OF_MONTH) + "/" + deadline.get(Calendar.MONTH) + "/" + deadline.get(Calendar.YEAR);}
	public String toString(){return assignmentName + ", deadline- " + deadline.get(Calendar.DAY_OF_MONTH) + "/" + deadline.get(Calendar.MONTH) + "/" + deadline.get(Calendar.YEAR);} 
	public static Calendar stringToCalendar(String date)
	{
		Calendar c = Calendar.getInstance();
		String[]time;
		time = date.split("/");
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(time[0]));		
		c.set(Calendar.MONTH, Integer.parseInt(time[1]));
		c.set(Calendar.YEAR, Integer.parseInt(time[2]));
		return c;
	}
}
