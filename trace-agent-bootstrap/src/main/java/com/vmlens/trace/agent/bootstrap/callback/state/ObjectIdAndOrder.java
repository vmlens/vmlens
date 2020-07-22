package com.vmlens.trace.agent.bootstrap.callback.state;

public class ObjectIdAndOrder {

	private final long id;
	public int order;


	
	
	public ObjectIdAndOrder() {
		super();
		 id = getNewId();
	}

	
	
	
	
	public long getId() {
		return id;
	}





	private static long maxId = 0;
	
	public synchronized static long getNewId()
	{
		maxId++;
		return maxId;
	}
	
	
   
	
}
