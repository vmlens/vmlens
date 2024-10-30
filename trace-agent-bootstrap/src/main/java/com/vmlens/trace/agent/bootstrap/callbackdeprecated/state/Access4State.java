package com.vmlens.trace.agent.bootstrap.callbackdeprecated.state;

public class Access4State {

	public final int methodNumber;
	public final int methodId;
	public final int operation;
	public final int slidingWindowId;
	public Access4State(int methodNumber, int methodId, int operation, int slidingWindowId) {
		super();
		this.methodNumber = methodNumber;
		this.methodId = methodId;
		this.operation = operation;
		this.slidingWindowId = slidingWindowId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + methodId;
		result = prime * result + methodNumber;
		result = prime * result + operation;
		result = prime * result + slidingWindowId;
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
		Access4State other = (Access4State) obj;
		if (methodId != other.methodId)
			return false;
		if (methodNumber != other.methodNumber)
			return false;
		if (operation != other.operation)
			return false;
		if (slidingWindowId != other.slidingWindowId)
			return false;
		return true;
	}
	
	
	
	
	
	
}
