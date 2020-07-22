package com.vmlens.trace.agent.bootstrap.interleave;

import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;

public class ThreadIndexAndOperation {
	
	private final int threadIndex;
	public final OperationTyp operationTyp;
	
	public ThreadIndexAndOperation(int threadIndex, OperationTyp operationTyp) {
		super();
		this.threadIndex = threadIndex;
		this.operationTyp = operationTyp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operationTyp == null) ? 0 : operationTyp.hashCode());
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
		ThreadIndexAndOperation other = (ThreadIndexAndOperation) obj;
		if (operationTyp == null) {
			if (other.operationTyp != null)
				return false;
		} else if (!operationTyp.equals(other.operationTyp))
			return false;
		if (threadIndex != other.threadIndex)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ThreadIndexAndOperation [threadIndex=" + threadIndex + ", operationTyp=" + operationTyp + "]";
	}
	

	
	
	
	
}
