package com.vmlens.trace.agent.bootstrap.interleave.actualAccess;

import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;

import gnu.trove.list.TLinkable;


public class LockAccess implements TLinkable<LockAccess> {
	public final int threadIndex;
	public final LockOperation operation;
	public final int position;
	public final int lockPosition;
	
	public LockAccess(int threadIndex, LockOperation operation, int position, int lockPosition) {
		super();
		this.threadIndex = threadIndex;
		this.operation = operation;
		this.position = position;
		this.lockPosition = lockPosition;
	}
	
	private LockAccess next;
	private LockAccess previous;

	public LockAccess getNext() {
		return next;
	}
	public void setNext(LockAccess next) {
		this.next = next;
	}
	public LockAccess getPrevious() {
		return previous;
	}
	public void setPrevious(LockAccess previous) {
		this.previous = previous;
	}
	
	
	
	
	
}
