package projectsalmon;

import java.util.ArrayList;

public class Parent extends User
{
	private ArrayList<Student> children;
	
	public Parent(String id,String first, String last, String password)
	{
		super(id,first,last,password);
		children = new ArrayList<Student>();
	}
	
	public ArrayList<Student> getChildren(){return children;}
}
