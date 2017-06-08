package projectsalmon;

public class Semester
{
	private String year;
	private char semesterNumber;
	private boolean correntSemester;
	
	public Semester(String year, char semesterNumber)
	{
		correntSemester = true;
		this.year = year;
		this.semesterNumber = semesterNumber;
	}
	
	public void setYear(String year){this.year = year;}
	public void setSemesterNumber(char semesterNumber){this.semesterNumber = semesterNumber;}
	public void correntSermester(boolean correntSemester){this.correntSemester = correntSemester;}
	
	public String getYear(){return year;}
	public char getSemesterNumber(){return semesterNumber;}
	public boolean getCorrentSemester(){return correntSemester;}
}
