package com.vmlens.trace.agent.bootstrap.parallize.logicState;

public class LogicStateTimeout extends LogicStateProcessing {

	long currentlyActive = -1L;
	int activeCount = 0;
	
	
	@Override
	LogicState calculateActive(ThreadId2State threadId2State, long time, long forThreadId,int runId) {
		
		if(currentlyActive == -1L || activeCount > 2)
		{
			currentlyActive = forThreadId;
			activeCount = 0;
		}
		
		activeCount++;
		
		return this;
	}

	@Override
	LogicState threadEnded(long threadId, ThreadId2State threadId2State, long time) {
		
		if(currentlyActive == threadId)
		{
			currentlyActive = -1L;
		}
		
		
		return this;
	}

	@Override
	boolean isActive(long threadId) {
		
		if(currentlyActive == -1L )
		{
			currentlyActive = threadId;
			activeCount = 0;
		}
		
		
		return currentlyActive == threadId;
	}

}
