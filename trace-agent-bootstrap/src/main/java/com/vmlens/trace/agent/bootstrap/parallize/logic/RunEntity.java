package com.vmlens.trace.agent.bootstrap.parallize.logic;

import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.BeginNewThreadResult;

public class RunEntity {

	
	private volatile RunState runState;
	
	public final AllInterleavings allInterleavings;
	
	
	
	public RunEntity(RunStateActive runState,AllInterleavings allInterleavings) {
		super();
		this.runState = runState;
		this.allInterleavings = allInterleavings;
	}

	public void stop(CallbackStatePerThread callbackStatePerThread,WhileLoop whileLoop) {
		
		if( whileLoop.loopId() ==  runState.loopId())
		{
			runState.stop(callbackStatePerThread);
			
		
			
			runState.sendStopEvent(callbackStatePerThread);
			
			runState= new RunStateStopped();
		}
		
	
		
	}

	public BeginNewThreadResult beginNewThread(long threadId, Thread currentThread, RunnableOrThreadWrapper beganTask) {
		return runState.beginNewThread(threadId, currentThread, beganTask);
	}

	public void after(CallbackStatePerThread callbackStatePerThread, OperationTyp op) {
		
		runState.afterOperation(callbackStatePerThread, op);
	}

	
	
	public boolean isAtomicActivated(int atomicId)
	{
		return runState.isAtomicActivated(atomicId);
	}
	

	
	public void afterThreadStart(CallbackStatePerThread callbackStatePerThread) {
		runState.afterThreadStart(callbackStatePerThread);
	}

	public void beforeStart(CallbackStatePerThread callbackStatePerThread2,
			RunnableOrThreadWrapper runnableOrThreadWrapper) {
		runState.beforeStart(callbackStatePerThread2, runnableOrThreadWrapper);
	}

	public boolean endThread(CallbackStatePerThread callbackStatePerThread) {
		return runState.endThread(callbackStatePerThread);
	}

	

	public int loopId() {
		return runState.loopId();
	}

	public int runId() {
		return runState.runId();
	}

	public int nextPosition() {
		return runState.nextPosition();
	}

	public boolean isInInterleaveLoop() {
		return runState.isInInterleaveLoop();
	}

	

	public void onLock(CallbackStatePerThread callbackStatePerThread, LockOperation monitorEnter) {
		runState.onLock(callbackStatePerThread , monitorEnter);
		
	}


	public boolean sendAsInterleaveEvent(int doNotInterleave, int doNotInterleaveFromLock , Class cl) {
	
		return runState.sendAsInterleaveEvent(doNotInterleave, doNotInterleaveFromLock ,  cl);
	}

	public void beforeThreadJoin(CallbackStatePerThread callbackStatePerThread,long joinWithThreadId) {
		 runState.beforeThreadJoin(callbackStatePerThread,joinWithThreadId);
		
	}

	
}
