package com.vmlens.trace.agent.bootstrap.interleave.lock;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.util.IntStack;

import gnu.trove.list.linked.TIntLinkedList;

public class MonitorEnter implements LockOperation  {

	private final int monitorId;

	public MonitorEnter(int monitorId) {
		super();
		this.monitorId = monitorId;
	}

	@Override
	public void execute(IntStack monitorStack ,IntStack exclusive , IntStack shared) {
		monitorStack.push(monitorId);
		
	}

	@Override
	public String toString() {
		return "MonitorEnter [monitorId=" + monitorId + "]";
	}
	@Override
	public void add2Stack(MonitorLockEnterStack monitorLockEnterStack, int threadIndex, int positionInThread) {
		monitorLockEnterStack.monitor.push( new Position(threadIndex,positionInThread) );
		
	}
	
}
