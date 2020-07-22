package com.vmlens.trace.agent.bootstrap.parallize.operation;

public class OperationAtomicMethod implements Operation {

	private final int methodId;
	
	
	
	
	
	public OperationAtomicMethod(int methodId) {
		super();
		this.methodId = methodId;
	}

	@Override
	public boolean createHappensBefore(Operation in) {
		if(  in instanceof OperationAtomicMethod)
		{	
		
			return true;
		
		}
		else
		{
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = OperationIds.ATOMIC_METHOD;
		result = prime * result + methodId;
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
		OperationAtomicMethod other = (OperationAtomicMethod) obj;
		if (methodId != other.methodId)
			return false;
		return true;
	}

	
	
	
	
	
	

}
