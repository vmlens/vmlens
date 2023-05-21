package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.field.CallbackObject;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

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


		ParallelizeFacade.afterFieldAccess4UnsafeOrVarHandle(CallbackState.callbackStatePerThread.get(), fieldId, operation);

	}
	
	
	public static void volatileAccessArray(long index, Object obj, int methodId , int operation) {
		VolatileArrayAccessCallback.access(index, obj, methodId, operation);


		ParallelizeFacade.afterVolatileArrayAccess4UnsafeOrVarHandle(CallbackState.callbackStatePerThread.get(), index, operation);
	}


}
