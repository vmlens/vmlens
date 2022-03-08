package com.vmlens.trace.agent.bootstrap.interleave.lock;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.util.IntStack;

public interface LockOperation {

	void execute(IntStack monitorStack ,IntStack exclusive , IntStack shared );

	void add2Stack(MonitorLockEnterStack monitorLockEnterStack, int threadIndex, int positionInThread);
	
	
}
