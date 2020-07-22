package com.anarsoft.trace.agent.bootstrap.callback;

public class CallbackTestEvent {
	
	public final long  threadId;
	public final TestEventType type;
	
	public CallbackTestEvent(long threadId, TestEventType type) {
		super();
		this.threadId = threadId;
		this.type = type;
	}

	@Override
	public String toString() {
		return "CallbackTestEvent [threadId=" + threadId + ", type=" + type + "]";
	}
	
	
	
	
	
	

}
