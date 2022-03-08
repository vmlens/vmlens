package com.vmlens.trace.agent.bootstrap.interleave;



import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.util.ObjectStack;



public class MonitorLockEnterStack {

	
	public final ObjectStack<com.vmlens.trace.agent.bootstrap.interleave.normalized.Position> monitor = new 	ObjectStack<com.vmlens.trace.agent.bootstrap.interleave.normalized.Position>();
	public final ObjectStack<com.vmlens.trace.agent.bootstrap.interleave.normalized.Position> atomic = new 	ObjectStack<com.vmlens.trace.agent.bootstrap.interleave.normalized.Position>();
	public final ObjectStack<com.vmlens.trace.agent.bootstrap.interleave.normalized.Position> lock = new 	ObjectStack<com.vmlens.trace.agent.bootstrap.interleave.normalized.Position>();
	public final ObjectStack<com.vmlens.trace.agent.bootstrap.interleave.normalized.Position> sharedLock = new 	ObjectStack<Position>();
}
