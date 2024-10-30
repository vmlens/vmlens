package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.CallbackObject;

import java.lang.invoke.VarHandle;

public class VarHandleCallback {

	
	public static void beforeVolatileAccess(VarHandle varHandle, Object obj, int methodId , int operation)
	{
		
		int fieldId = MethodHandlesLookupCallback.getFieldId2VarHandle(varHandle);
		
		if(  fieldId > -1)
		{
			CallbackObject.before_volatile_access( obj , fieldId, methodId, operation);
		}
		
		
		
		
	}
	
	
	public static void beforeVolatileAccessArray(long index, Object obj, int methodId , int operation)
	{
		VolatileArrayAccessCallback.before_access(index, obj, methodId, operation);
		
		
		
	}
	
	
	
	
	
	public static void volatileAccess(VarHandle varHandle, Object obj, int methodId , int operation) {

        int fieldId = MethodHandlesLookupCallback.getFieldId2VarHandle(varHandle);

        if (fieldId > -1) {
            CallbackObject.volatile_access(obj, fieldId, methodId, operation);
        }

        // Fixme Callback
        // ParallelizeFacade.afterFieldAccess4UnsafeOrVarHandle(CallbackState.callbackStatePerThread.get(), fieldId, operation);

    }
	
	
	public static void volatileAccessArray(long index, Object obj, int methodId , int operation) {
        VolatileArrayAccessCallback.access(index, obj, methodId, operation);

        // Fixme Callback
        // ParallelizeFacade.afterVolatileArrayAccess4UnsafeOrVarHandle(CallbackState.callbackStatePerThread.get(), index, operation);
    }


}
