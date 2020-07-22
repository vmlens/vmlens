package com.vmlens.trace.agent.bootstrap.parallize.logicState;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

public class DecisionActive extends Decision {

	@Override
	LogicState create(long activeThread, Operation operation, ThreadId2State threadId2State, long time) {
		
		
		
		
		return new LogicStateManyThreads(activeThread, time);
	}
	
	
     LogicState createWakeUp(long activeThread,long waitingThreadId , ThreadId2State threadId2State, long time) {
		
		return new LogicStateManyThreads(activeThread, time);
	}
	
	

	 Decision cloneDecision()
		{
		  return new  	DecisionActive();
		}

	
	 @Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
		
			return true;
		}


	@Override
	public Decision createNext(int threadCount) {
	
		if( threadCount < 2 )
		{
			return this;
		}
		
		
		return new DecisionWaiting(0);
	}

}
