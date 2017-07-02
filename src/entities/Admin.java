package entities;

import java.io.Serializable;

public class Admin extends User implements Serializable{

		
	public Admin(String id,String first, String last, String password)
	{
	super(id,first,last,password);
	
	}

}
