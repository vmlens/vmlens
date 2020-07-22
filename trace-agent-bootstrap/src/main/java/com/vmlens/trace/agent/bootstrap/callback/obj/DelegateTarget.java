package com.vmlens.trace.agent.bootstrap.callback.obj;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;


public class DelegateTarget {

	private final ObjectCallbackState objectCallbackState;
	private final Object orig;
	

	
	
	
	
	 public DelegateTarget(ObjectCallbackState objectCallbackState, Object orig) {
		super();
		this.objectCallbackState = objectCallbackState;
		this.orig = orig;
	}






	void access(int operation, int methodId , CallbackStatePerThread callbackStatePerThread, int slidingWindowId) {
	
		objectCallbackState.access(orig , operation , methodId);
		 
		
	}
	
	
	
	
}
