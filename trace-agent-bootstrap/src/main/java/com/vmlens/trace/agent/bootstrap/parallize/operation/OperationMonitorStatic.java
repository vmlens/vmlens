package com.vmlens.trace.agent.bootstrap.parallize.operation;

public class OperationMonitorStatic implements Operation {

	private final int id;
	private final int methodId;
	
	public OperationMonitorStatic(int id, int methodId) {
		super();
		this.id = id;
		this.methodId = methodId;
	}

	@Override
	public boolean createHappensBefore(Operation in) {
		if(  in instanceof OperationMonitorStatic)
		{	
			OperationMonitorStatic other = (OperationMonitorStatic) in;
			
			return other.id == id;
		
		}
		else
		{
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = OperationIds.MONITOR_STATIC;
		result = prime * result + id;
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
		OperationMonitorStatic other = (OperationMonitorStatic) obj;
		if (id != other.id)
			return false;
		if (methodId != other.methodId)
			return false;
		return true;
	}

	
	
	
}
