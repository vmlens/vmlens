package com.vmlens.trace.agent.bootstrap.callback;

import java.lang.invoke.VarHandle;

import com.vmlens.trace.agent.bootstrap.callback.field.CallbackObject;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;

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
	
	
	
	
	
	public static void volatileAccess(VarHandle varHandle, Object obj, int methodId , int operation)
	{
		
		int fieldId = MethodHandlesLookupCallback.getFieldId2VarHandle(varHandle);
		
		if(  fieldId > -1)
		{
			CallbackObject.volatile_access( obj , fieldId, methodId, operation);
		}
		
		
		ParallizeFacade.afterFieldAccess4UnsafeOrVarHandle( CallbackState.callbackStatePerThread.get() , fieldId, operation);
		
	}
	
	
	public static void volatileAccessArray(long index, Object obj, int methodId , int operation)
	{
		VolatileArrayAccessCallback.access(index, obj, methodId, operation);
		
		
		ParallizeFacade.afterVolatileArrayAccess4UnsafeOrVarHandle( CallbackState.callbackStatePerThread.get() , index, operation);
	}
	
	
//	public static int getVolatile5(VarHandle varHandle, Object obj , int methodId)
//	{
//		
//		volatileAccess( varHandle , obj , methodId  , false );		
//		return (int) varHandle.getVolatile(obj);
//		
//	}
//	
//	public static void getVolatile0(VarHandle varHandle, Object obj , int methodId)
//	{
//		volatileAccess( varHandle , obj , methodId  , false);		
//		varHandle.getVolatile(obj);
//		
//	}
//	
//	
//	public static Object getVolatile10(VarHandle varHandle, Object obj , int methodId)
//	{
//		
//		volatileAccess( varHandle , obj , methodId  , false);		
//		
//		return (Object) varHandle.getVolatile(obj);
//		
//	
//		
//	}
	
	
	
}
