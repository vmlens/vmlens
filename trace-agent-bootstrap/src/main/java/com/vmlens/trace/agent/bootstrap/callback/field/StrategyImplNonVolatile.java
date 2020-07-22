package com.vmlens.trace.agent.bootstrap.callback.field;



public class StrategyImplNonVolatile implements Strategy {

	
	private final boolean isWrite;
	
	
	public StrategyImplNonVolatile(boolean isWrite) {
		super();
		this.isWrite = isWrite;
		
	}

	
	
	
	@Override
	public void field_access_default(Object orig, int fieldId, int methodId) {
		
		CallbackObject.non_volatile_access(orig, fieldId, methodId, isWrite);
		
	}




	@Override
	public void field_access_static(int fieldId, int methodId) {
	
		CallbackStatic.non_volatile_access(fieldId, methodId, isWrite);
		
	}




	@Override
	public void field_access_generated(Object orig, long  offset, int fieldId, int methodId) {
		
		CallbackObject.non_volatile_access_generated(orig,offset, fieldId, methodId, isWrite);
		
	}
	
	
	
	
}
