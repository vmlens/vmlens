package com.vmlens.trace.agent.bootstrap.parallize.logic;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

public interface WhileLoop {

	int loopId();
	RunEntity createNextRun(CallbackStatePerThread callbackStatePerThread,Thread forThread);

	
	
	
}
