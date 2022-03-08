package com.vmlens.trace.agent.bootstrap.interleave.lock;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.util.IntStack;

public class MonitorExit implements LockOperation  {
	private final int monitorId;

	public MonitorExit(int monitorId) {
		super();
		this.monitorId = monitorId;
	}

	@Override
	public void execute(IntStack monitorStack ,IntStack exclusive , IntStack shared) {
		
		if( ! monitorStack.isEmpty())
		{
			monitorStack.poll();
		}
		else
		{
			AgentLogCallback.log( "monitorStack size zero" + monitorId);
		}
	//	monitorStack.removeAt(0);
		
	}

	@Override
	public String toString() {
		return "MonitorExit [monitorId=" + monitorId + "]";
	}

	@Override
	public void add2Stack(MonitorLockEnterStack monitorLockEnterStack, int threadIndex, int positionInThread) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
