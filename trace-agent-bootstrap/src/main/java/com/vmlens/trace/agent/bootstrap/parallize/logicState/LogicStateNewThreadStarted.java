package com.vmlens.trace.agent.bootstrap.parallize.logicState;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;



public class LogicStateNewThreadStarted extends LogicState {

	
	private  long startingThreadIdAfterStart = -1L;
	private final long startingThreadIdBeforeStart;
	
	
	private  long newBeganThreadId = -1L;
	private final RunnableOrThreadWrapper expected2Start;
	private final long created;
	

	public LogicStateNewThreadStarted(RunnableOrThreadWrapper expected2Start, long created) {
		super();
		this.expected2Start = expected2Start;
		this.startingThreadIdBeforeStart = Thread.currentThread().getId();
		this.created = created;
	}

	
	
	
	

	@Override
	LogicState calculateActive(ThreadId2State threadId2State, long time, long forThreadId,int runId) {
		
		if(   time > (  created + InterleaveControlLogic.TIMEOUT )  )
		{
				
			threadId2State.setTimeout( startingThreadIdAfterStart );
			
			
				AgentLogCallback.logTimeout(threadId2State , this);
			
			
			
			return new LogicStateTimeout( );

		}
		
		if(startingThreadIdAfterStart == -1L || newBeganThreadId == -1L )
		{
			return this;
		}
		
		
			threadId2State.afterThreadStart(startingThreadIdAfterStart, newBeganThreadId);
	
		
		 return  new LogicStateManyThreads(threadId2State.getActiveThreadId4AfterOperation(forThreadId) , time);  //threadId2State.decideBewteenWaitingOrWakeup().createWakeUp(startingThreadId, newBeganThreadId, threadId2State, time); 
	}

	@Override
	boolean isActive(long threadId) {	
		
		if(  threadId ==  startingThreadIdBeforeStart )
		{
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			
			if(callbackStatePerThread.inThreadStart > 0)
			{
				return true;
			}
			
		}
		
		
		return false;
	
	}

	@Override
	LogicState afterOperation(ThreadId2State threadId2State, long threadId, long time) {
		return this;
	}

	@Override
	LogicState threadEnded(long threadId ,ThreadId2State threadId2State, long time) {
		return this;
	}



	@Override
	LogicState startNewThread(long threadId) {
	
		startingThreadIdAfterStart = threadId;
		
		return this;
	}

	@Override
	LogicStateAndBeginNewThreadResult beginWithNewThread(long threadId,RunnableOrThreadWrapper beganTask,ThreadId2State threadId2State,Thread thread ) {
	
		BeginNewThreadResult result = BeginNewThreadResult.UNKNOWN_THREAD; 
		
		if(expected2Start.isSame(beganTask))
		{
			if(threadId2State.addThread(threadId,thread))
			{
				 result = BeginNewThreadResult.NEW_THREAD;
			}
			else
			{
				 result = BeginNewThreadResult.EXISITING_THREAD;
			}
			newBeganThreadId = threadId;
			
			
		
		}
		
		return 	new LogicStateAndBeginNewThreadResult(result, this);
	
	}

	@Override
	LogicState beforeStart(RunnableOrThreadWrapper startedTaskWrapper, long time) {
		return this;
	}




}
