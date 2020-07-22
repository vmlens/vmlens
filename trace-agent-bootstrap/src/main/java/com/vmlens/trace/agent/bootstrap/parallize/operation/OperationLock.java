package com.vmlens.trace.agent.bootstrap.parallize.operation;

public class OperationLock implements Operation {

	private final Object objectKey;
	private final boolean isShared;
	



	public OperationLock(Object objectKey, boolean isShared) {
		super();
		this.objectKey = objectKey;
		this.isShared = isShared;
	}


	@Override
	public boolean createHappensBefore(Operation in) {
		if(  in instanceof OperationLock)
		{	
			OperationLock other = (OperationLock) in;
			
			// other.objectKey == objectKey &&  siehe objectKey bei OperationMonitor
			
			return  isShared == other.isShared;
			
		
		
		}
		else
		{
			return false;
		}
	
	}

	@Override
	public String toString() {
		return "OperationLock";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = OperationIds.LOCK;
		result = prime * result + (isShared ? 1231 : 1237);
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
		OperationLock other = (OperationLock) obj;
		if (isShared != other.isShared)
			return false;
		return true;
	}


	
}

