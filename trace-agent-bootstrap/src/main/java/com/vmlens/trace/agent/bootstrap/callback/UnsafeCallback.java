package com.vmlens.trace.agent.bootstrap.callback;

import java.lang.reflect.Field;


import com.vmlens.trace.agent.bootstrap.Offset2FieldId;
import com.vmlens.trace.agent.bootstrap.OffsetAndClassName;


public class UnsafeCallback {
	
	
	
	public static long objectFieldOffset(sun.misc.Unsafe unsafe, Field field,int methodId)
	{
		long offset =  unsafe.objectFieldOffset(field);
		OffsetAndClassName offsetAndClassName = new OffsetAndClassName(offset , field.getDeclaringClass().getName());
		Offset2FieldId.addOffset(offsetAndClassName, field.getName());
		
		
		
		
		return offset;
		
	}
	
	

	
	public static void monitorExit(sun.misc.Unsafe unsafe, Object in, int methodId )
	{
	
		 SynchronizedStatementCallback.monitorExit(in,methodId,-3);
		 unsafe.monitorExit(in);
		 
	}
	
	public static void monitorEnter(sun.misc.Unsafe unsafe, Object in, int methodId )
	{
		 unsafe.monitorEnter(in);
		 SynchronizedStatementCallback.monitorEnter(in,methodId,-2);
	}
	
	public static boolean tryMonitorEnter(sun.misc.Unsafe unsafe, Object in, int methodId )
	{
		 boolean success = unsafe.tryMonitorEnter(in);
		 
		 if( success )
		 {
			 SynchronizedStatementCallback.monitorEnter(in,methodId,-2);
		 }
		 
		 
		 return success;
		
	}
	
	

	

	
	
	

	
	
}
