package com.anarsoft.trace.agent.runtime.process;

public class AgentState {

	
	private final long startTimestamp;
	private final long currentTimeStamp;
	private final int state;
	private final int slidingWindowId;
	
	public AgentState(long startTimestamp, long currentTimeStamp, int state,int slidingWindowId) {
		super();
		this.startTimestamp = startTimestamp;
		this.currentTimeStamp = currentTimeStamp;
		this.state = state;
		this.slidingWindowId = slidingWindowId;
	}

	public long getStartTimestamp() {
		return startTimestamp;
	}

	public long getCurrentTimeStamp() {
		return currentTimeStamp;
	}

	public int getState() {
		return state;
	}
	
	
	

	public int getSlidingWindowId() {
		return slidingWindowId;
	}

	
	@Override
	public String toString() {
		return "AgentState [startTimestamp=" + startTimestamp + ", currentTimeStamp=" + currentTimeStamp + ", state="
				+ state + ", slidingWindowId=" + slidingWindowId + "]";
	}

	
	
	
	
}
