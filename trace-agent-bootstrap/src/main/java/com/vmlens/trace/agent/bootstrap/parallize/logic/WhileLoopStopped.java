package com.vmlens.trace.agent.bootstrap.parallize.logic;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

public class WhileLoopStopped implements WhileLoop  {

	private final int loopId;
	
	public int loopId()
	{
		return loopId;
	}

	public WhileLoopStopped(int loopId) {
		super();
		this.loopId = loopId;
	}



	@Override
	public RunEntity createNextRun(CallbackStatePerThread callbackStatePerThread, Thread forThread) {
	
		return null;
	}

	
	

}
