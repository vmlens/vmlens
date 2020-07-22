package com.vmlens.trace.agent.bootstrap.callback.field;


public class StrategyImplVolatile implements Strategy {

	
	private final boolean isWrite;

	
	public StrategyImplVolatile(boolean isWrite) {
		super();
		this.isWrite = isWrite;

	}

	
	
	private  int getOperation()
	{
		if(isWrite)
		{
			return MemoryAccessType.IS_WRITE;
		}
		else
		{
			return MemoryAccessType.IS_READ;
		}
	}
	
	
	
	@Override
	public void field_access_default(Object orig, int fieldId, int methodId) {
		
		CallbackObject.volatile_access(orig, fieldId, methodId, getOperation());
		
	}




	@Override
	public void field_access_static(int fieldId, int methodId) {
	
		CallbackStatic.volatile_access(fieldId, methodId, isWrite);
		
	}




	@Override
	public void field_access_generated(Object orig, long  offset, int fieldId, int methodId) {
		
		CallbackObject.volatile_access_generated(orig, offset ,  fieldId, methodId, getOperation());
		
	}
	
	
	
	
}

