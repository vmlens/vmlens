package com.vmlens.trace.agent.bootstrap.interleave;



import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.util.ObjectStack;



public class MonitorLockEnterStack {

	
	public final ObjectStack<Position> monitor = new 	ObjectStack<Position>();
	public final ObjectStack<Position> atomic = new 	ObjectStack<Position>();
	public final ObjectStack<Position> lock = new 	ObjectStack<Position>();
	public final ObjectStack<Position> sharedLock = new 	ObjectStack<Position>();
}
