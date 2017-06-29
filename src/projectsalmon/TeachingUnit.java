package projectsalmon;



import java.io.Serializable;

public class TeachingUnit implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1044475922242648917L;
	private String name;
	private String number;
	
	public TeachingUnit(String number, String name)
	{
		this.number = number;
		this.name = name;
	}
	public TeachingUnit()
	{
		this.number = null;
		this.name = null;
	}
	
	public void setName(String name){this.name = name;}
	public void setNumber(String number){this.number = number;}
	
	public String getName(){return name;}
	public String getNumber(){return number;}
}
