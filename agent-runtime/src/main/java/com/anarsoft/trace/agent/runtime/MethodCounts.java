package com.anarsoft.trace.agent.runtime;

public class MethodCounts {

	
	 public final  int tryCatchBlockCount;
	 public final int methodCallCount;
	 public final boolean dottyProblematic;
	 
	 
	 public MethodCounts(int tryCatchBlockCount, int methodCallCount,boolean dottyProblematic) {
		super();
		this.tryCatchBlockCount = tryCatchBlockCount;
		this.methodCallCount = methodCallCount;
		this.dottyProblematic = dottyProblematic;
	}
	 
	 
	
}
