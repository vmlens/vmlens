package com.vmlens.trace.agent.bootstrap.parallize.operation;

public class OperationThreadBegin implements Operation {
	@Override
	public boolean createHappensBefore(Operation other) {
		
		return other instanceof OperationThreadStart;
	}

	@Override
	public int hashCode() {
		int result = OperationIds.THREAD_BEGIN;
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
