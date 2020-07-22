package com.vmlens.trace.agent.bootstrap.parallize.logicState;

import com.vmlens.trace.agent.bootstrap.parallize.operation.LoopDetection;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

public class ThreadState {

	final LoopDetection loopDetection = new LoopDetection();
	final int index;
	
	int timesReturnedForEndOfActiveThreadIndex;

	final Thread thread;
	int operationCount;
	
	
	int position;
	
	boolean activated;
	//boolean atThreadJoin;
	
	long joinWithThreadId = -1L;
	
	
	
	int addedCount = 1;
	
	
	public ThreadState(int index,Thread thread) {
		super();
		this.index = index;
		this.thread = thread;
	}


	@Override
	public String toString() {
		return "ThreadState [timesReturnedForEndOfActiveThreadIndex=" + timesReturnedForEndOfActiveThreadIndex + ", thread=" + thread
				+ ", activated=" + activated + ", addedCount=" + addedCount + "]";
	}
	
	
	
}
