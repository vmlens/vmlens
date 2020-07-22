package com.vmlens.trace.agent.bootstrap.parallize.operation;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;

public class OperationFieldAccess implements Operation  {

	private final int fieldId;
	private final int operation;
	
	
	
	
	
	
	@Override
	public String toString() {
		return "OperationFieldAccess(" + fieldId + "," + operation + ")";
	}

	public OperationFieldAccess(int fieldId, int operation) {
		super();
		this.fieldId = fieldId;
		this.operation = operation;
	}

	@Override
	public boolean createHappensBefore(Operation in) {
		
		if(  in instanceof OperationFieldAccess)
		{
			OperationFieldAccess other = (OperationFieldAccess) in;
			if(  other.fieldId != fieldId )
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

	
	

	public int getFieldId() {
		return fieldId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = OperationIds.FIELD_ACCESS;
		result = prime * result + fieldId;
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
		OperationFieldAccess other = (OperationFieldAccess) obj;
		if (fieldId != other.fieldId)
			return false;
		if (operation != other.operation)
			return false;
		return true;
	}

	
	
	
	
	
	
	
	

}
