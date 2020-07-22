package com.vmlens.trace.agent.bootstrap.interleave.actualAccess;





import com.vmlens.trace.agent.bootstrap.interleave.normalized.NormalizedAccess;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;

import gnu.trove.list.TLinkable;

public class ActualAccess  implements TLinkable<ActualAccess>  {

	public final int threadIndex;
	public final OperationTyp operation;
	public final int position;
	
	
	
	
	@Override
	public String toString() {
		return "ActualAccess [threadIndex=" + threadIndex + ", operation=" + operation + ", position=" + position + "]";
	}




	public NormalizedAccess create()
	{
		return new NormalizedAccess(operation);
	}
	
	
	
	
	public ActualAccess(int threadIndex, OperationTyp operation, int position) {
		super();
		this.threadIndex = threadIndex;
		this.operation = operation;
		this.position = position;
	}
	private ActualAccess next;
	private ActualAccess previous;
	public ActualAccess getNext() {
		return next;
	}
	public void setNext(ActualAccess next) {
		this.next = next;
	}
	public ActualAccess getPrevious() {
		return previous;
	}
	public void setPrevious(ActualAccess previous) {
		this.previous = previous;
	}
	
	
	
	
	
}
