package com.vmlens.trace.agent.bootstrap.callback;

public class PreviousProgramCounter {

	 long threadId;
	 int programCounter;
	
	 public PreviousProgramCounter(long threadId, int programCounter) {
		super();
		this.threadId = threadId;
		this.programCounter = programCounter;
	}
	 
	 
	
	
	
}
