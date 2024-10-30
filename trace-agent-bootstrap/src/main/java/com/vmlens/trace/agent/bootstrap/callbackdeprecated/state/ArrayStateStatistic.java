package com.vmlens.trace.agent.bootstrap.callbackdeprecated.state;

public class ArrayStateStatistic {

	
	public final long threadId;
	public int writeEventCount;
	public int readEventCount;
	
	public ArrayStateStatistic(long threadId) {
		super();
		this.threadId = threadId;
	}
	
	
	
}
