package com.vmlens.trace.agent.bootstrap.callback.state;

public class MonitorIdAndOrder {
	

	public int id;
	public int order;

	
	
	private static int maxId = 0;
	
	public synchronized static int getNewId()
	{
		maxId++;
		return maxId;
	}
	
	
	

}
