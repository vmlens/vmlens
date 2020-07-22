package com.vmlens.trace.agent.bootstrap.callback.state;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.callback.field.UpdateObjectState;

public class ObjectStateSingleThreaded implements ObjectState {

	public final long threadId;

	public ObjectStateSingleThreaded(long threadId) {
		super();
		this.threadId = threadId;
	}

	@Override
	public void sendNonVolatile(long threadId, int slidingWindowId, int fieldId, int methodId, int operation,
			UpdateObjectState updateObjectStateNew, CallbackStatePerThread callbackStatePerThread) {
		
		
	}

	
	
	

	
	
	
	
	
}
