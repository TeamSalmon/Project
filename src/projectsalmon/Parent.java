package projectsalmon;

import java.util.ArrayList;

public class Parent
{
	private ArrayList<Student> children;
	
	public Parent(){children = new ArrayList<Student>();}
	
	public ArrayList<Student> getChildren(){return children;}
}
