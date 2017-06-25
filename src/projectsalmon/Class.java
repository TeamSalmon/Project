package projectsalmon;

import java.util.Calendar;

public class Class
{
	Calendar startsAt;
	Calendar endsAt;

	
	public Class(){}

	public Class(Calendar start, Calendar end)
	{
		this.startsAt = start;
		this.endsAt = end;
	}
	
	public void setStart(Calendar start){this.startsAt = start;}
	public void setEnd(Calendar end){this.endsAt = end;}
	
	public Calendar getStart(){return this.startsAt;}
	public Calendar getEnd(){return this.endsAt;}	
}
