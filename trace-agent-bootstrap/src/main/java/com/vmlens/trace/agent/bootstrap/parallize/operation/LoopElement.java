package com.vmlens.trace.agent.bootstrap.parallize.operation;

import com.vmlens.trace.agent.bootstrap.parallize.logicState.Decision;

public class LoopElement {

	int index;
	private Decision current;

	public LoopElement( Decision current) {
		super();
		
		this.current = current;
	}
	
	
	Decision getNext(int threadCount)
	{
		current = current.createNext(threadCount);
		return current;
	}
}
