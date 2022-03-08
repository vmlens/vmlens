package com.vmlens.trace.agent.bootstrap.parallize.logicState;


import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunStateActive;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;


public class InterleaveControlLogic {

	
	public boolean hasNotTerminatingLoop = false;
	
	public final static int TIMEOUT = 2000;
	//public final static int MAX_STEPS_NOT_WAKEUP = 100;


	public final ThreadId2State threadId2State;
	public LogicState logicState = new LogicStateSingleThread();
	
	private final int maximum_operation_per_thread_count;
	private final RunStateActive runStateActive;
	
	public InterleaveControlLogic(InterleaveFacade interleaveFacade, RunStateActive runStateActive)
	{
		threadId2State = new ThreadId2State(interleaveFacade  );
		this.runStateActive = runStateActive;
		this.maximum_operation_per_thread_count = runStateActive.whileLoopActive.allInterleavings.maximumSynchronizationActionsPerThread;
		
		
		
	}
	
	public void startNewThread(long threadId) {
		
		logicState = logicState.startNewThread(threadId);
		
	}
	
	
	public void lockOperation(long threadId, LockOperation operation)
	{
		if(ParallizeFacade.ENABLE_LOGGING  )
		{
			AgentLogCallback.log( "lockOperation " +threadId + " " + operation );
		}
		
		
		threadId2State.lockOperation(threadId , operation);
	}
	
	
	
	public void afterOperation(long threadId, OperationTyp operation, long time) {
		
		   int operationCount = threadId2State.incrementOperationCount(threadId);


		if(maximum_operation_per_thread_count > 0 &&   operationCount >maximum_operation_per_thread_count)
		    {
		    	hasNotTerminatingLoop = true;
		    	throw new RuntimeException(operationCount  + " operations executedin the AllInterleavings instance + " +  runStateActive.whileLoopActive.allInterleavings.name +   ". This is more than the currently configured maximum. See https://vmlens.com/help/manual/#maximum-operation-count "
		    			+ "for more information and possible solutions." 
		    			 );
		    }
		
			threadId2State.after(threadId , operation);
			LogicState temp = logicState.afterOperation(threadId2State, threadId, time);
			logicState = temp;
	}


	public boolean needs2Wait(long threadId, long time) {	
		logicState = logicState.calculateActive(threadId2State, time,threadId,runStateActive.runId());		
		return ! logicState.isActive(threadId);
	

	}

	public void startFirstThread(long threadId,Thread thread) {
		threadId2State.addThread(threadId,thread);
		

	}
	public BeginNewThreadResult beginWithNewThread(long threadId,Thread thread, RunnableOrThreadWrapper beganTask) {
			
		
		LogicStateAndBeginNewThreadResult result = logicState.beginWithNewThread(threadId,beganTask ,threadId2State , thread );
		
			logicState = result.logicState;
			
			return result.beginNewThreadResult;

	}



	public boolean endThread(long threadId,long time) {
		boolean result = threadId2State.removeThread(threadId);
		
		
		
		
		logicState = logicState.threadEnded(threadId, threadId2State, time);
		
		return result;
	}



	public void beforeStart(RunnableOrThreadWrapper threadWrapper, long time) {
		LogicState temp = logicState.beforeStart(threadWrapper,time);
		
		
		
		logicState = temp;
	}




	public boolean isMultiThreaded() {
	
		return ! threadId2State.isSingleThreaded();
	}



	public long[] activeThreadIds()
	{
		return threadId2State.activeThreadIds();
	}
	
	

	public boolean stillThreadsRunning() {
	
		return threadId2State.size() > 0;
	}

	



	// end API für InterleaveControl


}
