package com.vmlens.trace.agent.bootstrap.parallize.operation;

public class OperationVolatileArrayAccess implements Operation  {

	private final long index;
	private final int operation;
	
	
	
	
	
	
	@Override
	public String toString() {
		return "new OperationVolatileArrayAccess(" + index + "," + operation + ")";
	}

	public OperationVolatileArrayAccess(long index, int operation) {
		super();
		this.index = index;
		this.operation = operation;
	}

	@Override
	public boolean createHappensBefore(Operation in) {
		
		if(  in instanceof OperationVolatileArrayAccess)
		{
			OperationVolatileArrayAccess other = (OperationVolatileArrayAccess) in;
			if(  other.index != index )
			{
				return false;
			}
			else
			{
				return  (operation | other.operation) >= 3;
			}
			
		}
		else
		{
			return false;
		}
		
	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = OperationIds.VOLATILE_ARRAY_ACCESS;
		result = prime * result + operation;
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
		OperationVolatileArrayAccess other = (OperationVolatileArrayAccess) obj;
		if (operation != other.operation)
			return false;
		return true;
	}

	

	
	
	
	
	
	
	
	

}

