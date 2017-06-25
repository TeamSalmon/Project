package projectsalmon;

import java.io.File;
import java.util.Calendar;

public class Assignment
{
	private String assignmentId;
	private String courseId;
	private String assignmentName;
	private Calendar deadline;
	private int percentageOfFinalGrade;
	private String instructionForSubmission;
	private File file;
	
	public Assignment(String assignmentId,String courseId,String assignmentName, Calendar deadline, int precentages, String instructions, File file)
	{
		this.file = file;
		this.assignmentId = assignmentId;
		this.deadline = deadline;
		this.percentageOfFinalGrade = precentages;
		this.instructionForSubmission = instructions;
		this.assignmentName = assignmentName;
		this.courseId = courseId;
	}
	
	public void setFile(String filePath){this.file = new File(filePath);}
	public void setCourse(String course){this.courseId = course;}
	public void setDeadline(Calendar deadline){this.deadline = deadline;}
	public void setPrecentagesOfGrade(int precentage){this.percentageOfFinalGrade = precentage;}
	public void setInstructions(String instruction){this.instructionForSubmission = instruction;}
	public void setName(String name){this.assignmentName = name;}
	
	public File getfile(){return file;}
	public String getAssignmntId(){return this.assignmentId;}
	public Calendar getDeadline(){return this.deadline;}
	public int getPrecentagesOfFinalGrade(){return this.percentageOfFinalGrade;}
	public String getInstructions(){return this.instructionForSubmission;}
	public String getName(){return assignmentName;}
	public String getCourse(){return courseId;}
	public String getDeadlineAsString(){return deadline.get(Calendar.DAY_OF_MONTH) + "/" + deadline.get(Calendar.MONTH) + "/" + deadline.get(Calendar.YEAR);}
	public String toString(){return assignmentName + ", deadline- " + deadline.get(Calendar.DAY_OF_MONTH) + "/" + deadline.get(Calendar.MONTH) + "/" + deadline.get(Calendar.YEAR);} 	
}
