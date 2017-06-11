package projectsalmon;

import java.util.Calendar;

public class Assignment
{
	private String assignmentId;
	private Course course;
	private String assignmentName;
	private Calendar deadline;
	private int percentageOfFinalGrade;
	private String instructionForSubmission;
	
	public Assignment(String assignmentId,Course course,String assignmentName, Calendar deadline, int precentages, String instructions)
	{
		this.assignmentId = assignmentId;
		this.deadline = deadline;
		this.percentageOfFinalGrade = precentages;
		this.instructionForSubmission = instructions;
		this.assignmentName = assignmentName;
		this.course = course;
	}
	
	public void setCourse(Course course){this.course = course;}
	public void setDeadline(Calendar deadline){this.deadline = deadline;}
	public void setPrecentagesOfGrade(int precentage){this.percentageOfFinalGrade = precentage;}
	public void setInstructions(String instruction){this.instructionForSubmission = instruction;}
	public void setName(String name){this.assignmentName = name;}
	
	public String getAssignmntId(){return this.assignmentId;}
	public Calendar getDeadline(){return this.deadline;}
	public int getPrecentagesOfFinalGrade(){return this.percentageOfFinalGrade;}
	public String getInstructions(){return this.instructionForSubmission;}
	public String getName(){return assignmentName;}
	public Course getCourse(){return course;}
	
	
}
