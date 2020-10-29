package com.vmlens.trace.agent.bootstrap.parallize.logicState;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

public class LogicStateManyThreads extends LogicStateProcessing {

	private final long activeThreadId;
	private final long lastUpdated;
	

	

	public LogicStateManyThreads(long activeThreadId, long lastUpdated) {
		super();
		this.activeThreadId = activeThreadId;
		this.lastUpdated = lastUpdated;
	}

	@Override
	public LogicState calculateActive(ThreadId2State threadId2State, long currentTime, long forThreadId,int runId) {
		
		if( !  threadId2State.isActive(activeThreadId) )
		{
				AgentLogCallback.logTimeoutWarning(threadId2State , this , runId );
			
				
				threadId2State.setTimeout( activeThreadId );
		
				return new LogicStateTimeout( );
			
			
			
		}
		
		if(   currentTime > (  lastUpdated + InterleaveControlLogic.TIMEOUT )  )
		{
		
				AgentLogCallback.logTimeout( threadId2State , this  );
			
			
			
			
			threadId2State.setTimeout( activeThreadId );
			
			return new LogicStateTimeout();

		}
		
			return this;
		
	}

	@Override
	public boolean isActive(long threadId) {	
		if(threadId == activeThreadId)
		{
		
			return true;
		}
		
		return false;
	}



	@Override
	public String toString() {
		return "LogicStateNotWaiting [activeThreadId=" + activeThreadId + ", lastUpdated=" + lastUpdated + "]";
	}

	@Override
	LogicState threadEnded(long threadId,ThreadId2State threadId2State,long time) {
		
		if( threadId2State.isSingleThreaded()  )
		{
			return new LogicStateSingleThread();
		}
		
		
	    return	 new LogicStateManyThreads(threadId2State.getActiveThreadId4ThreadEnded() , time);
		
	
	}




	
	

}
