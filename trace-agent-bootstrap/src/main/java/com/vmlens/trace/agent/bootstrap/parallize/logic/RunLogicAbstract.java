package com.vmlens.trace.agent.bootstrap.parallize.logic;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.interleave.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.interleave.operation.ThreadEnd;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeSingelton;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.BeginNewThreadResult;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.InterleaveControlLogic;

public abstract class RunLogicAbstract {

	public final InterleaveControlLogic interleaveControlLogic;
	private final RunStateActive runStateActive;
	
	public RunLogicAbstract(RunStateActive runStateActive , InterleaveFacade interleaveFacade){
			this.runStateActive = runStateActive;
			interleaveControlLogic = new InterleaveControlLogic(interleaveFacade,runStateActive);

	}

	
	 protected abstract void waitTillActive(long threadId) throws InterruptedException;
	
	// 
	
	
	protected abstract void notifyMonitor();

	//
	
	// end API for creation
	
	// start API
	
	
	  void setActivated(long threadId)
	 {
		 
		  interleaveControlLogic.threadId2State.setActivated(threadId);
	 }
	 
	 
	  void setDeactivated(long threadId)
	 {
		  interleaveControlLogic.threadId2State.setDeactivated(threadId);
	 }
	
	  public void setAtThreadJoin(long threadId,long joinWithThreadId) {
		  interleaveControlLogic.threadId2State.setAtThreadJoin(threadId,joinWithThreadId);
			
		}

	  
	  
	  
	
	 void startFirstThread(long threadId,Thread thread)
	{
			interleaveControlLogic.startFirstThread(threadId,thread);

	}

	 BeginNewThreadResult beginNewThread(long threadId,Thread thread, RunnableOrThreadWrapper beganTask)
	{
		try {		
			BeginNewThreadResult result =  interleaveControlLogic.beginWithNewThread(threadId,thread, beganTask);
			
			if(result != BeginNewThreadResult.UNKNOWN_THREAD )
			{
				notifyMonitor();
				waitTillActive(threadId);
				
			}
			
			
			return result;
			
			
			
		
		}
		catch (InterruptedException e) {
			if (ParallizeFacade.ENABLE_LOGGING ) {
				AgentLogCallback.log("beginNewThread interrupt " + threadId );
			}
			
			Thread.currentThread().interrupt();
			return BeginNewThreadResult.UNKNOWN_THREAD;
		}
	}
	
	
	
	
	 void afterThreadStart(long threadId) {
		
		try {	
		
	
			interleaveControlLogic.startNewThread(threadId);
			
			notifyMonitor();
			
			waitTillActive(threadId);
			
			
			
		
		} catch (InterruptedException e) {
			if (ParallizeFacade.ENABLE_LOGGING ) {
				AgentLogCallback.log("afterThreadStart interrupt " + threadId );
			}
			
			Thread.currentThread().interrupt();
		}
		
	}
	 
	  void lockOperation(long threadId, LockOperation operation) {
		  interleaveControlLogic.lockOperation(threadId, operation);
			
		}
	

	 void afterOperation(long threadId , OperationTyp operation) {
		
			try {	
				
					interleaveControlLogic.afterOperation(threadId, operation, System.currentTimeMillis());
					notifyMonitor();
					
					waitTillActive(threadId);
					
					
				
				} catch (InterruptedException e) {
					if (ParallizeFacade.ENABLE_LOGGING ) {
						AgentLogCallback.log("afterOperation interrupt " + threadId + " " + operation);
					}
					
					Thread.currentThread().interrupt();
					
				}
		
	}
	
  boolean endThread(long threadId) {
		
	    
		interleaveControlLogic.afterOperation(threadId,  new ThreadEnd()  , System.currentTimeMillis());
		notifyMonitor();
	  
	  
	  boolean result = interleaveControlLogic.endThread(threadId, System.currentTimeMillis());
		    notifyMonitor();
		
		  return result;  
		    
	}

	 void beforeStart(RunnableOrThreadWrapper threadWrapper) {
		 interleaveControlLogic.beforeStart(threadWrapper, System.currentTimeMillis());	
	}




	 boolean isMultiThreaded() {	
		return interleaveControlLogic.isMultiThreaded();
	}




	public void stop(long threadId) {
		
	
		
	    interleaveControlLogic.endThread(threadId, System.currentTimeMillis());
		
	    notifyMonitor();
		
		try {	
		
		long start = System.currentTimeMillis();
//		boolean printed = false;	
		
		
		while( interleaveControlLogic.stillThreadsRunning() )
		{
			ParallizeSingelton.SINGLE_LOCK.wait(10);
			
			if(System.currentTimeMillis() - start  > InterleaveControlLogic.TIMEOUT)
			{
				CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

				callbackStatePerThread.sendEvent.writeLoopWarningEventGen(CallbackState.slidingWindow, runStateActive.loopId(), runStateActive.runId());
				
				//AgentLogCallback.logTimeout("stop timeout, active thread ids  " + Arrays.toString(interleaveControlLogic.activeThreadIds()));
				return;
			}
			
		}
		
//		AgentLogCallback.log("stop took " +  (System.currentTimeMillis() - start));
		
		
		
		} catch (InterruptedException e) {
			if (ParallizeFacade.ENABLE_LOGGING ) {
				AgentLogCallback.log("stop interrupt " + threadId );
			}
			
			Thread.currentThread().interrupt();
		}
	}


	
		







	
	// end API
}
