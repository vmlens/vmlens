package com.vmlens.trace.agent.bootstrap.parallize.operation;

public class OperationMonitorObject implements Operation {



	private final Object objectKey;
	private final int methodId;
	
	




	public OperationMonitorObject(Object objectKey, int methodId) {
		super();
		this.objectKey = objectKey;
		this.methodId = methodId;
	}


	@Override
	public boolean createHappensBefore(Operation in) {
		if(  in instanceof OperationMonitorObject)
		{	
			OperationMonitorObject other = (OperationMonitorObject) in;
			
			return other.objectKey == objectKey;
		
		}
		else
		{
			return false;
		}
	
	}



	@Override
	public String toString() {
		return "OperationMonitor";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = OperationIds.MONITOR_OBJECT;
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
		OperationMonitorObject other = (OperationMonitorObject) obj;
		if (methodId != other.methodId)
			return false;
		return true;
	}
	
	
	
	
}
