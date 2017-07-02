package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Secretary extends User implements Serializable{

	
	public Secretary(String id,String first, String last, String password)
	{
		super(id,first,last,password);
	}
	
}
