package com.vmlens.trace.agent.bootstrap.interleave.normalized;

import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.interleave.operation.VolatileFieldAccess;

import gnu.trove.list.TLinkable;

public class PositionAndOperation  implements TLinkable<PositionAndOperation> {

	private PositionAndOperation next;
	private PositionAndOperation previous;
	
	
	
	public final Position position;
	public final  OperationTyp operation;
	
	public PositionAndOperation(Position position, OperationTyp operation) {
		super();
		this.position = position;
		this.operation = operation;
	}

	public PositionAndOperation getNext() {
		return next;
	}

	public void setNext(PositionAndOperation next) {
		this.next = next;
	}

	public PositionAndOperation getPrevious() {
		return previous;
	}

	public void setPrevious(PositionAndOperation previous) {
		this.previous = previous;
	}

	@Override
	public String toString() {
		return "PositionAndOperation [position=" + position + ", operation=" + operation + "]";
	}


	
	
	
}
