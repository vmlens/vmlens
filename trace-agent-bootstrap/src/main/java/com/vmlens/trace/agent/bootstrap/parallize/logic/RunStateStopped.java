package com.vmlens.trace.agent.bootstrap.parallize.logic;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.BeginNewThreadResult;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

public class RunStateStopped implements RunState {

	@Override
	public BeginNewThreadResult beginNewThread(long threadId, Thread currentThread, RunnableOrThreadWrapper beganTask) {
	
		return BeginNewThreadResult.UNKNOWN_THREAD;
	}

	@Override
	public void afterOperation(CallbackStatePerThread callbackStatePerThread, OperationTyp op) {
	
		
	}

//	@Override
//	public void afterFieldAccess(CallbackStatePerThread callbackStatePerThread, int fieldId, int operation) {
//		
//		
//	}

	@Override
	public void afterThreadStart(CallbackStatePerThread callbackStatePerThread) {
		
		
	}

	@Override
	public void beforeStart(CallbackStatePerThread callbackStatePerThread2,
			RunnableOrThreadWrapper runnableOrThreadWrapper) {
	
	
	}

	@Override
	public boolean endThread(CallbackStatePerThread callbackStatePerThread) {
	
		return true;
		
	}

	

	@Override
	public int loopId() {
		return 0;
	}

	@Override
	public int runId() {
		
		return 0;
	}

	@Override
	public int nextPosition() {
		return 0;
	}

	@Override
	public void sendStopEvent(CallbackStatePerThread callbackStatePerThread) {
		// Nothing ToDo
		
	}

	@Override
	public boolean isInInterleaveLoop() {
		return false;
	}

	@Override
	public void stop(CallbackStatePerThread callbackStatePerThread) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void onLock(CallbackStatePerThread callbackStatePerThread, LockOperation monitorEnter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAtomicActivated(int atomicId) {
		return false;
	}

	

	@Override
	public void beforeThreadJoin(CallbackStatePerThread callbackStatePerThread,long joinWithThreadId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean sendAsInterleaveEvent(int doNotInterleave, int doNotInterleaveFromLock, Class cl) {
		// TODO Auto-generated method stub
		return false;
	}



	


}
