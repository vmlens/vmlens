package com.vmlens.trace.agent.bootstrap.interleave.lock;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.util.IntStack;

import gnu.trove.list.linked.TIntLinkedList;

public interface LockOperation {

	void execute(IntStack monitorStack ,IntStack exclusive , IntStack shared );

	void add2Stack(MonitorLockEnterStack monitorLockEnterStack, int threadIndex, int positionInThread);
	
	
}
