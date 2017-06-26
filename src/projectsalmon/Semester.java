package projectsalmon;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Semester implements Serializable
{
	private String year;
	private String semesterNumber;
	private boolean correntSemester;
	
	public Semester(String year, String semesterNumber)
	{
		correntSemester = true;
		this.year = year;
		this.semesterNumber = semesterNumber;
	}
	
	public void setYear(String year){this.year = year;}
	public void setSemesterNumber(String semesterNumber){this.semesterNumber = semesterNumber;}
	public void correntSermester(boolean correntSemester){this.correntSemester = correntSemester;}
	
	public String getYear(){return year;}
	public String getSemesterNumber(){return semesterNumber;}
	public boolean getCorrentSemester(){return correntSemester;}
	public String toString(){return year + " " + semesterNumber;}
}
