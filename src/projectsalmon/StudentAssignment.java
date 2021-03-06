package projectsalmon;
import java.io.File;
import java.util.Calendar;

public class StudentAssignment
{//change
	private String studentId;
	private Assignment assignment;
	private File submission;
	private int grade;
	private String comments;
	private File evaluationForm;
	private boolean lateFlag;
	
	public StudentAssignment(String student, Assignment assignment)
	{
		this.studentId = student;
		this.assignment = assignment;
		comments = "";
		grade = -1;	
		lateFlag = false;
	}
	public StudentAssignment(String student)
	{
		this.studentId = student;
		comments = "";
		grade = -1;	
		lateFlag = false;
	}
	
	public String getStudentId(){return studentId;}
	public Assignment getAssignment(){return assignment;}
	public File getSubmission(){return submission;}
	public int getGrade(){return grade;}
	public String getComments(){return comments;}
	public File getEvaluationForm(){return evaluationForm;}
	public boolean getLateFlag(){return lateFlag;}
	
	public void setSubmission(File submission)
	{
		this.submission = submission;
		if((Calendar.getInstance().after(assignment.getDeadline())))
			lateFlag = true;
	}
	public void setGrade(int grade){this.grade = grade;};
	public void setComments(String comments){this.comments = comments;}
	public void setEvaluationForm(File evaluationForm){this.evaluationForm = evaluationForm;}
	public String toString(){return studentId;}
	public void setAssignment(Assignment assignment){this.assignment = assignment;}
	public void setLateFlag(boolean flag){this.lateFlag = flag;}
}
