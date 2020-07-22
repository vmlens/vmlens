package com.vmlens.trace.agent.bootstrap.interleave.normalized;

import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;

import gnu.trove.list.TLinkable;

public  class NormalizedAccess  implements TLinkable<NormalizedAccess> {

	
	public final OperationTyp operationTyp;
	
	
	
	
	
	public NormalizedAccess(OperationTyp operationTyp) {
		super();
		this.operationTyp = operationTyp;
	}

	private NormalizedAccess next;
	private NormalizedAccess previous;
	
	public NormalizedAccess getNext() {
		return next;
	}


	public void setNext(NormalizedAccess next) {
		this.next = next;
	}


	public NormalizedAccess getPrevious() {
		return previous;
	}


	public void setPrevious(NormalizedAccess previous) {
		this.previous = previous;
	}
	
	


	@Override
	public String toString() {
		return "NormalizedAccess [operationTyp=" + operationTyp + "]";
	}


	
	
}
