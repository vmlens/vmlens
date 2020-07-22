package com.vmlens.trace.agent.bootstrap.parallize.logicState;





import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

import gnu.trove.list.TLinkable;

public abstract class  Decision implements TLinkable<Decision>  {

	private Decision next;
	private Decision previous;
	public Decision getNext() {
		return next;
	}
	public void setNext(Decision next) {
		this.next = next;
	}
	public Decision getPrevious() {
		return previous;
	}
	public void setPrevious(Decision previous) {
		this.previous = previous;
	}
	
	
	
	abstract LogicState  create(long activeThread, Operation operation, ThreadId2State threadId2State , long time);
	abstract LogicState createWakeUp(long activeThread,long waitingThreadId , ThreadId2State threadId2State, long time);
	
	
	abstract Decision cloneDecision();
	
	
	public abstract Decision createNext(int threadCount);
	
	
}
