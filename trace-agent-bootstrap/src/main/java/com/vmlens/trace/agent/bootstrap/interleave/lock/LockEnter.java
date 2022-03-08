package com.vmlens.trace.agent.bootstrap.interleave.lock;

import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.util.IntStack;

public class LockEnter implements LockOperation {
	
	private final boolean isShared;
	private final int lockId;
	public LockEnter(boolean isShared, int lockId) {
		super();
		this.isShared = isShared;
		this.lockId = lockId;
	}
	@Override
	public void execute(IntStack monitorStack ,IntStack exclusive , IntStack shared) {
		
		if(  isShared )
		{
			shared.push(lockId);
		}
		else
		{
			exclusive.push(lockId);
		}
		
		
	}
	@Override
	public String toString() {
		return "LockEnter [isShared=" + isShared + ", lockId=" + lockId + "]";
	}
	@Override
	public void add2Stack(MonitorLockEnterStack monitorLockEnterStack, int threadIndex, int positionInThread) {
		if( ! isShared )
		{
			monitorLockEnterStack.lock.push( new Position(threadIndex,positionInThread) );
		}
		
	
	}
	
	
	

}
