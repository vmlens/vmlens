package com.vmlens.trace.agent.bootstrap.parallize.logicState;

public class LoopElement {
	
	 LoopElement prevoius;
	 LoopElement next;
	
	long threadId;

	public LoopElement(long threadId) {
		super();
		this.threadId = threadId;
	}
	

}
