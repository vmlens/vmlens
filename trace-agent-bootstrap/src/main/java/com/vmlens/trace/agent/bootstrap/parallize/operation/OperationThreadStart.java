package com.vmlens.trace.agent.bootstrap.parallize.operation;

public class OperationThreadStart implements Operation {

	@Override
	public boolean createHappensBefore(Operation other) {
		
		return other instanceof OperationThreadBegin;
	}

	@Override
	public int hashCode() {
		int result = OperationIds.THREAD_START;
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
	
		return true;
	}
	
	

}
