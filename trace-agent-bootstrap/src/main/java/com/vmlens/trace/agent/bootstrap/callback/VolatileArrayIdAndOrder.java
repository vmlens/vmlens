package com.vmlens.trace.agent.bootstrap.callback;

public class VolatileArrayIdAndOrder {

	public final long id;
	public int order;
	
	
	
	
	
    public VolatileArrayIdAndOrder() {
		this.id = getNewId();
	}

	private static long maxId = 0;
	
	public synchronized static long getNewId()
	{
		maxId++;
		return maxId;
	}
	
	
	
}
