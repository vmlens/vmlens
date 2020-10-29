package com.vmlens.trace.agent.bootstrap.parallize.logicState;


public class LogicStateSingleThread extends LogicStateProcessing {

	@Override
	public LogicState calculateActive(ThreadId2State threadId2State , long time, long forThreadId,int runId) {
		return this;
	}

	@Override
	public boolean isActive(long threadId) {
		return true;
	}



	@Override
	LogicState threadEnded(long threadId, ThreadId2State threadId2State, long time) {
	
		return this;
	}



	

}
