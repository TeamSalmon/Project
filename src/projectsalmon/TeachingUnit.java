package projectsalmon;

public class TeachingUnit
{
	private String name;
	private String number;
	
	public TeachingUnit(String number, String name)
	{
		this.number = number;
		this.name = name;
	}
	
	public void setName(String name){this.name = name;}
	public void setNumber(String number){this.number = number;}
	
	public String getName(){return name;}
	public String getNumber(){return number;}
}
