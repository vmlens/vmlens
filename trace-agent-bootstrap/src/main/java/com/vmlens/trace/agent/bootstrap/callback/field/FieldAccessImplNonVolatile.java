package com.vmlens.trace.agent.bootstrap.callback.field;



public class FieldAccessImplNonVolatile {


    private final boolean isWrite;


    public FieldAccessImplNonVolatile(boolean isWrite) {
        super();
        this.isWrite = isWrite;

    }


    public void field_access_default(Object orig, int fieldId, int methodId) {
		
		CallbackObject.non_volatile_access(orig, fieldId, methodId, isWrite);
		
	}


    public void field_access_static(int fieldId, int methodId) {
	
		CallbackStatic.non_volatile_access(fieldId, methodId, isWrite);
		
	}


    public void field_access_generated(Object orig, long  offset, int fieldId, int methodId) {
		
		CallbackObject.non_volatile_access_generated(orig,offset, fieldId, methodId, isWrite);
		
	}
	
	
	
	
}
