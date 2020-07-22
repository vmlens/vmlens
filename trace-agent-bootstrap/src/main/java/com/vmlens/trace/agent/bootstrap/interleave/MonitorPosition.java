package com.vmlens.trace.agent.bootstrap.interleave;

public class MonitorPosition {

	private final int methodId;
	private final int position;
	public MonitorPosition(int methodId, int position) {
		super();
		this.methodId = methodId;
		this.position = position;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + methodId;
		result = prime * result + position;
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
		MonitorPosition other = (MonitorPosition) obj;
		if (methodId != other.methodId)
			return false;
		if (position != other.position)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MonitorPosition [methodId=" + methodId + ", position=" + position + "]";
	}
	
	
	
	
	
	
}
