package com.vmlens.trace.agent.bootstrap.parallize.logic;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

public class WhileLoopStopped implements WhileLoop  {



	@Override
	public RunEntity createNextRun(CallbackStatePerThread callbackStatePerThread, Thread forThread) {
	
		return null;
	}

	
	

}
