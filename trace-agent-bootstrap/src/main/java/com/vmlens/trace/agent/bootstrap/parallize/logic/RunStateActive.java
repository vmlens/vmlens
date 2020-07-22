package com.vmlens.trace.agent.bootstrap.parallize.logic;

import java.util.concurrent.FutureTask;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.event.gen.LockEnterEventGen;
import com.vmlens.trace.agent.bootstrap.event.gen.LockExitEventGen;
import com.vmlens.trace.agent.bootstrap.interleave.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.parallize.FutureTask2ThreadId;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeSingelton;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.BeginNewThreadResult;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

public class RunStateActive implements RunState {

	 final RunLogic logic;
	public final WhileLoopActive whileLoopActive;
	private final int loopId;
	private final int runId;
	private int position = 1;

	public RunStateActive(InterleaveFacade interleaveFacade , WhileLoopActive whileLoopActive , int loopId, int runId) {
		super();
		this.whileLoopActive = whileLoopActive;
		this.logic = new RunLogic(this ,  interleaveFacade );
		this.loopId = loopId;
		this.runId = runId;
	}

	@Override
	public void beforeStart(CallbackStatePerThread callbackStatePerThread, RunnableOrThreadWrapper threadWrapper) {
		if (callbackStatePerThread.stackTraceBasedDoNotTrace > 0) {
			return;
		}

		callbackStatePerThread.stackTraceBasedDoNotTrace++;

		

			synchronized (ParallizeSingelton.SINGLE_LOCK) {
				logic.setDeactivated(callbackStatePerThread.threadId);
				logic.beforeStart(threadWrapper);
				logic.setActivated(callbackStatePerThread.threadId);

			}

		
		callbackStatePerThread.stackTraceBasedDoNotTrace--;

	}

	@Override
	public void afterThreadStart(CallbackStatePerThread callbackStatePerThread) {

		if (callbackStatePerThread.stackTraceBasedDoNotTrace > 0) {
			return;
		}

		callbackStatePerThread.stackTraceBasedDoNotTrace++;

	

			synchronized (ParallizeSingelton.SINGLE_LOCK) {
				logic.setDeactivated(callbackStatePerThread.threadId);
				logic.afterThreadStart(callbackStatePerThread.threadId);
				logic.setActivated(callbackStatePerThread.threadId);

			}



		callbackStatePerThread.stackTraceBasedDoNotTrace--;

	}

	@Override
	public void afterOperation(CallbackStatePerThread callbackStatePerThread, OperationTyp operation) {
		if (callbackStatePerThread.stackTraceBasedDoNotTrace > 0 ||  callbackStatePerThread.doNotInterleave > 0 ||  callbackStatePerThread.doNotInterleaveFromLock > 0 ) {
			return;
		}
		
		
		

		callbackStatePerThread.stackTraceBasedDoNotTrace++;

		
		

			synchronized (ParallizeSingelton.SINGLE_LOCK) {

				logic.setDeactivated(callbackStatePerThread.threadId);

				if (ParallizeFacade.ENABLE_LOGGING) {
					AgentLogCallback.log("operation:{\"loopId\":" + loopId + ",\"runId\":" + runId + ",\"threadId\":"
							+ callbackStatePerThread.threadId + ",\"actualOperation\":" + operation.toString() + "}");
				}

				logic.afterOperation(callbackStatePerThread.threadId, operation);

				logic.setActivated(callbackStatePerThread.threadId);

			}

		

		callbackStatePerThread.stackTraceBasedDoNotTrace--;
	}

	
	@Override
	public void onLock(CallbackStatePerThread callbackStatePerThread, LockOperation operation) {
		if (callbackStatePerThread.stackTraceBasedDoNotTrace > 0) {
			return;
		}

		callbackStatePerThread.stackTraceBasedDoNotTrace++;

		

			synchronized (ParallizeSingelton.SINGLE_LOCK) {

				
				logic.lockOperation(callbackStatePerThread.threadId, operation);

				

			}

		
		callbackStatePerThread.stackTraceBasedDoNotTrace--;
		
	}
	
	
	
	
	
	
	
	
	
	// von singelton, unter synchronized lock aufgerufen

	@Override
	public BeginNewThreadResult beginNewThread(long threadId, Thread thread, RunnableOrThreadWrapper beganTask) {
		
		BeginNewThreadResult result = logic.beginNewThread(threadId, thread, beganTask);
		
		if( result != BeginNewThreadResult.UNKNOWN_THREAD )
		{
			
			if(  beganTask.runnable instanceof FutureTask )
			{
				FutureTask2ThreadId.put(beganTask.runnable, threadId);
			}
			
			
			
			
			logic.setActivated(threadId);
			
			
		}
		return result;
		
	}

	@Override
	public boolean endThread(CallbackStatePerThread callbackStatePerThread) {

		boolean result = false;
		
		callbackStatePerThread.stackTraceBasedDoNotTrace++;
		
		if (ParallizeFacade.ENABLE_LOGGING) {
			AgentLogCallback.log("ThreadEnd  loopId\":" + loopId + ",\"runId\":" + runId + ",\"threadId\":"
					+ callbackStatePerThread.threadId );
		}

		
		
		synchronized (ParallizeSingelton.SINGLE_LOCK) {
			result = logic.endThread(callbackStatePerThread.threadId);
			
		}
		callbackStatePerThread.stackTraceBasedDoNotTrace--;

		return result;
		
	}


	private boolean isMultiThreaded() {
		synchronized (ParallizeSingelton.SINGLE_LOCK) {
			return logic.isMultiThreaded();
		}

	}

	@Override
	public int loopId() {
		return loopId;
	}

	@Override
	public int runId() {
		return runId;
	}

	@Override
	public int nextPosition() {
		synchronized (ParallizeSingelton.SINGLE_LOCK) {
			return position++;
		}
	}

	@Override
	public void sendStopEvent(CallbackStatePerThread callbackStatePerThread) {
		callbackStatePerThread.sendEvent.writeRunEndEventGen(CallbackState.slidingWindow, loopId, runId);

	}

	@Override
	public boolean isInInterleaveLoop() {
		return true;
	}

	@Override
	public void stop(CallbackStatePerThread callbackStatePerThread) {
		callbackStatePerThread.stackTraceBasedDoNotTrace++;
		synchronized (ParallizeSingelton.SINGLE_LOCK) {
			logic.stop(callbackStatePerThread.threadId);
			callbackStatePerThread.parallizedThread = null;
		}
		callbackStatePerThread.stackTraceBasedDoNotTrace--;

	}

	
	




	
	
	


	@Override
	public boolean isAtomicActivated(int atomicId) {
	
		return whileLoopActive.isActivated(atomicId);
	}

	@Override
	public boolean sendAsInterleaveEvent(int doNotInterleave, int doNotInterleaveFromLock , Class cl) {
		
		boolean okFromDoNotInterleave = false;
		
		if(  doNotInterleave <= 0)
		{
			okFromDoNotInterleave = true;
		}
		
		if(whileLoopActive.allInterleavings.showStatementsInExecutor)
		{
			okFromDoNotInterleave = true;
		}
			
		boolean okFromMultiThreaded = this.isMultiThreaded();
		
		if(whileLoopActive.allInterleavings.showStatementsWhenSingleThreaded)
		{
			okFromMultiThreaded = true;
		}
		
		
		if( okFromDoNotInterleave )
		{
			if( doNotInterleaveFromLock  <= 0 )
			{
				return okFromMultiThreaded;
			}
			
			if(cl == LockEnterEventGen.class || cl == LockExitEventGen.class)
			{
				
				return okFromMultiThreaded;
			}
		}
		
		return false;
		
	
		
	
	}

	@Override
	public void beforeThreadJoin(CallbackStatePerThread callbackStatePerThread,long joinWithThreadId) {


		synchronized (ParallizeSingelton.SINGLE_LOCK) {

			logic.setAtThreadJoin(callbackStatePerThread.threadId,joinWithThreadId);

		

		}

		
	}

	


}
