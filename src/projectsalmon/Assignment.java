package projectsalmon;

import java.util.Calendar;

public class Assignment
{
	private String assignmentId;
	private Calendar deadline;
	private int percentageOfFinalGrade;
	private String instructionForSubmission;
	
	public Assignment(String assignmentId, Calendar deadline, int precentages, String instructions)
	{
		this.assignmentId = assignmentId;
		this.deadline = deadline;
		this.percentageOfFinalGrade = precentages;
		this.instructionForSubmission = instructions;
	}
	
	public void setId(String id){this.assignmentId = id;}
	public void setDeadline(Calendar deadline){this.deadline = deadline;}
	public void setPrecentagesOfGrade(int precentage){this.percentageOfFinalGrade = precentage;}
	public void setInstructions(String instruction){this.instructionForSubmission = instruction;}
	
	public String getAssignmntId(){return this.assignmentId;}
	public Calendar getDeadline(){return this.deadline;}
	public int getPrecentagesOfFinalGrade(){return this.percentageOfFinalGrade;}
	public String getInstructions(){return this.instructionForSubmission;}
	
}
