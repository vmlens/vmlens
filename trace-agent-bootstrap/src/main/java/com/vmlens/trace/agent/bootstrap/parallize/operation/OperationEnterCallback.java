package com.vmlens.trace.agent.bootstrap.parallize.operation;

public class OperationEnterCallback  implements Operation {



	public OperationEnterCallback() {
		super();
		
	}
	
	
	@Override
	public boolean createHappensBefore(Operation in) {
		if(  in instanceof OperationEnterCallback)
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
		int result = OperationIds.ENTER_CALLBACK;
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
