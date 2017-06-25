package ClientGui;





public class ObjectStack  implements Stack{
	int size;
	int top;
	Object[] myStack;
	
	public ObjectStack(int n)
	{
		if(n<=0)
		{
			 throw new IllegalArgumentException("Stack's capacity must be positive");
		}
		myStack = new Object[n];	
		this.size=0;
		this.top=-1;
	}
	
	public boolean isEmpty()
	{
		if(this.size==0)return true;
		else return false;
	}
	public void push(Object element) throws StackFullException
	{
		try{
        if (top == myStack.length) throw new StackFullException("Stack's  is overflow");
        
	
        
        top++;
        size++;
        myStack[top] = element;
		}
		catch(StackFullException e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println(this.toString());
	}

	public Object pop()
	{
		if (top == -1)return null;
		top--;
		size--;
		System.out.println(this.toString());
		return myStack[top+1];
	}
	
	public Object peek()
	{
		
		if (top == -1)return null;
		
		return myStack[top];
	}
	
	public void clear()
	{
		for(int i=0;i<myStack.length;i++)
			myStack[i]=null;
		
		this.size=0;
	}

	@Override
	public int size() 
	{
		
		return this.size;
	}
	
	
	
	
}
