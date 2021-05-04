package com.vmlens.trace.agent.bootstrap.interleave.syncAction;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

public class VolatileFieldAccess extends SyncAction {
	
	 static final int MIN_OPERATION = 3;
	
	private final int operation;
	private final int fieldId;
	
	public VolatileFieldAccess(int operation, int fieldId,int threadIndex, int position) {
		super(threadIndex,position);
		this.operation = operation;
		this.fieldId = fieldId;
	}
	
	

	@Override
	public String toString() {
		String text = "";
		
		if(operation == MemoryAccessType.IS_READ) {
			text="R_";
		} else if(operation == MemoryAccessType.IS_WRITE){
			text="W_";
		}  else 	{
			text="A_";
		}
			
		
		return text + threadIndex;
		
	}



	@Override
	public int category() {
		return VOLATILE_FIELD;
	}



	@Override
	public int id() {
		return fieldId;
	}



	@Override
	public boolean createsSyncRelation(SyncAction other) {
		if( other instanceof  VolatileFieldAccess)
		{	
			if(((VolatileFieldAccess) other).threadIndex == threadIndex) {
				return false;
			}
			
			
			return  (operation | ((VolatileFieldAccess) other).operation ) >= MIN_OPERATION;
		}
		else
		{
			return false;
		}
	}

}
