package entities;

import java.io.Serializable;

public class SchoolManager extends User implements Serializable{

		
	public SchoolManager(String id,String first, String last, String password)
	{
		super(id,first,last,password);
	}
}
