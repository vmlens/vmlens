package com.vmlens.trace.agent.bootstrap.threadQueue;

public class InternalPerThreadQueueFactory {

	private  final int ARRAY_SIZE;
	
	
	
	
	
	public InternalPerThreadQueueFactory(int aRRAY_SIZE) {
		super();
		ARRAY_SIZE = aRRAY_SIZE;
	}





	InternalPerThreadQueue create(int forSlidingWindowId,Object firstElement)
	{
		return new InternalPerThreadQueue(forSlidingWindowId,firstElement,ARRAY_SIZE);
	}
	
	
}
