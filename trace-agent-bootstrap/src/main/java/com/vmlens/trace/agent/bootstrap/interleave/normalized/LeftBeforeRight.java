package com.vmlens.trace.agent.bootstrap.interleave.normalized;

public class LeftBeforeRight {

	public final Position left;
	public final Position right;
	
	public LeftBeforeRight(Position left, Position right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return  left + " < " + right ;
	}
	
	
	
	
}
