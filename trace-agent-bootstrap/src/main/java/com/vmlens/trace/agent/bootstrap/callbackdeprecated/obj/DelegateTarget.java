package com.vmlens.trace.agent.bootstrap.callbackdeprecated.obj;


import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class DelegateTarget {

	private final ObjectCallbackState objectCallbackState;
	private final Object orig;


    public DelegateTarget(ObjectCallbackState objectCallbackState, Object orig) {
		super();
		this.objectCallbackState = objectCallbackState;
		this.orig = orig;
	}


	void access(int operation, int methodId, ThreadLocalForParallelize callbackStatePerThread, int slidingWindowId) {


	}
	
	
	
	
}
