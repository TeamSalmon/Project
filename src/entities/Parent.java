package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Parent extends User implements Serializable
{
	private ArrayList<Student> children;
	private boolean blockedFlag = false;
	
	public Parent(String id,String first, String last, String password)
	{
		super(id,first,last,password);
		children = new ArrayList<Student>();
	}
	
	public ArrayList<Student> getChildren(){return children;}
	public boolean getBlockedFlag(){return blockedFlag;}
	
	public void setBlockedFlag(boolean flag){blockedFlag = flag;}
}
