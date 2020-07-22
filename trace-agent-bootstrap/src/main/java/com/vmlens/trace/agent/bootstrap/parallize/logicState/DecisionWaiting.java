package com.vmlens.trace.agent.bootstrap.parallize.logicState;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;



public class DecisionWaiting extends Decision {

	private final int position;
	
	
	
	
	public DecisionWaiting(int position) {
		super();
		this.position = position;
	}


	 Decision cloneDecision()
	{
	  return new  	DecisionWaiting(position);
	}

	@Override
	LogicState create(long activeThread, Operation operation, ThreadId2State threadId2State, long time) {
		
//		LoopIterator iterator = threadId2State.iterator(activeThread);
//		int index = 0;
//		
//		long threadId = -1L;
//		
//		while( iterator.hasNext() && index <= position )
//		{
//			
//			threadId = iterator.next().threadId;
//			
//		}
//		
//		if(threadId == -1L)
//		{
//			
//			
//			return new LogicStateNotWaiting(activeThread, time);
//		}
//		else
//		{
//		
//			//return new LogicStateNotWaiting(threadId, time);
//		  	return new  LogicStateWaiting4Operation(threadId,  activeThread,  operation , time);
//		}
		
		return null;
		
		
	}
	
	
	LogicState createAtThreadEnd4WaitingState( long activeThread, Operation operation , ThreadId2State threadId2State, long time)
	{
//		LoopIterator iterator = threadId2State.iterator(activeThread);
//		int index = 0;
//		
//		long threadId = -1L;
//		
//		while( iterator.hasNext() && index <= position )
//		{
//			
//			threadId = iterator.next().threadId;
//			index++;
//		}
//		
//		if(threadId == -1L)
//		{
//			return new LogicStateNotWaiting(activeThread, time);
//		}
//		else
//		{
//			//return new LogicStateNotWaiting(threadId, time);
//		  	return new  LogicStateWaiting4Operation(threadId,  activeThread,  operation , time);
//		}
		
		return null;
		
	}
	

	
	LogicState createAtThreadEndActiveState( ThreadId2State threadId2State, long time)
	{
//		LoopIterator iterator = threadId2State.iterator4All();
//		int index = 0;
//		
//		long threadId = -1L;
//		
//		while( iterator.hasNext() && index <= position )
//		{
//			
//			threadId = iterator.next().threadId;
//			
//		}
//		
//	
//			return new LogicStateNotWaiting(threadId, time);
		
		return null;
		  
	}
	
	
	
	
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + position;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DecisionWaiting other = (DecisionWaiting) obj;
		if (position != other.position)
			return false;
		return true;
	}


	@Override
	LogicState createWakeUp(long activeThread, long waitingThreadId, ThreadId2State threadId2State, long time) {
		 	
		
		return new LogicStateManyThreads(waitingThreadId, time);
		 	
		 	
		 	
	}


	@Override
	public Decision createNext(int threadCount) {
		
		if( position + 1  > threadCount )
		{
			return new DecisionActive();
		}
		
		return new DecisionWaiting(position + 1);
	}

	

//	@Override
//	public LogicState create(long time) {
//		return new  LogicStateWaiting4Operation(threadIdList.get(0),  waitingThreadId,  waiting4Operation , time);
//	}

	
}
