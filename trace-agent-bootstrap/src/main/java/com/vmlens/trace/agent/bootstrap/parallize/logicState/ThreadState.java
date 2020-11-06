package com.vmlens.trace.agent.bootstrap.parallize.logicState;

public class ThreadState {


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
		return "ThreadState [index=" + index + ", timesReturnedForEndOfActiveThreadIndex="
				+ timesReturnedForEndOfActiveThreadIndex + ", thread=" + thread + ", operationCount=" + operationCount
				+ ", position=" + position + ", activated=" + activated + ", joinWithThreadId=" + joinWithThreadId
				+ ", addedCount=" + addedCount + "]";
	}



	
}
