package com.vmlens.trace.agent.bootstrap.interleave.lock;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorLockEnterStack;
import com.vmlens.trace.agent.bootstrap.util.IntStack;

import gnu.trove.list.linked.TIntLinkedList;

public class LockExit implements LockOperation {
	
	private final boolean isShared;
	private final int lockId;
	public LockExit(boolean isShared, int lockId) {
		super();
		this.isShared = isShared;
		this.lockId = lockId;
	}
	
	@Override
	public void execute(IntStack monitorStack ,IntStack exclusive , IntStack shared) {
		
		
		if(  isShared )
		{
			if( ! shared.isEmpty() )
			{	
				if( shared.peek()==  lockId)
				{
					shared.poll();
				}
			}
			
			
			
			
		}
		else
		{
			
				
			if( ! exclusive.isEmpty() )
			{
			
				if( exclusive.peek() ==  lockId)
				{	
					exclusive.poll();
				}
			}
			
			
		
		}
		
		
	}

	@Override
	public String toString() {
		return "LockExit [isShared=" + isShared + ", lockId=" + lockId + "]";
	}

	@Override
	public void add2Stack(MonitorLockEnterStack monitorLockEnterStack, int threadIndex, int positionInThread) {
		// TODO Auto-generated method stub
		
	}
	
	
}
