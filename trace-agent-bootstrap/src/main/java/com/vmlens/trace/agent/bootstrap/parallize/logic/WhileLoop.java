package com.vmlens.trace.agent.bootstrap.parallize.logic;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

public interface WhileLoop {

	
	RunEntity createNextRun(CallbackStatePerThread callbackStatePerThread,Thread forThread);

	
	
	
}
