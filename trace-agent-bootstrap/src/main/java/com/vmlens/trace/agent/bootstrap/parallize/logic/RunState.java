package com.vmlens.trace.agent.bootstrap.parallize.logic;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.BeginNewThreadResult;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;

public interface RunState {

	BeginNewThreadResult beginNewThread(long threadId, Thread currentThread, RunnableOrThreadWrapper beganTask);

	void afterOperation(CallbackStatePerThread callbackStatePerThread, OperationTyp op);

	void afterThreadStart(CallbackStatePerThread callbackStatePerThread);

	void beforeStart(CallbackStatePerThread callbackStatePerThread2,
			RunnableOrThreadWrapper runnableOrThreadWrapper);

	boolean endThread(CallbackStatePerThread callbackStatePerThread);
	
	 int loopId();
	 

	int runId();

	 int nextPosition();

	void sendStopEvent(CallbackStatePerThread callbackStatePerThread);

	boolean isInInterleaveLoop();

	void stop(CallbackStatePerThread callbackStatePerThread);

	

	void onLock(CallbackStatePerThread callbackStatePerThread, LockOperation monitorEnter);

	boolean isAtomicActivated(int atomicId);



	boolean sendAsInterleaveEvent(int doNotInterleave, int doNotInterleaveFromLock , Class cl);

	void beforeThreadJoin(CallbackStatePerThread callbackStatePerThread,long joinWithThreadId);

}
