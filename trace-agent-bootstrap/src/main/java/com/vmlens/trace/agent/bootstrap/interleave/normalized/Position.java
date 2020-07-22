package com.vmlens.trace.agent.bootstrap.interleave.normalized;

public class Position {

	 public final int threadIndex; 
	 public final int position;
	
	 
	 public Position(int threadIndex, int position) {
		super();
		this.threadIndex = threadIndex;
		this.position = position;
	}


	@Override
	public String toString() {
		return "(index:" + threadIndex + "," + position + ")";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + position;
		result = prime * result + threadIndex;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (position != other.position)
			return false;
		if (threadIndex != other.threadIndex)
			return false;
		return true;
	}
	
	
}
