package com.vmlens.trace.agent.bootstrap.callback;

public class StackTraceBasedFilterCallback {

	
	public static void onMethodExitDoNotTrace()
	{
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		
	
		
		
//System.out.println(  Thread.currentThread().getName() +  " onMethodExit " + CallbackState.classLoaderMethodCount.get().count);
		
	}
	
	public static void onMethodEnterDoNotTrace()
	{
		

	
		
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;
		
		
//		System.out.println( Thread.currentThread().getName() +  "  onMethodEnter " + CallbackState.classLoaderMethodCount.get().count);
		
	}
	
	
	
	
	
	public static void onMethodExitDoTrace()
	{
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoTrace--;
		
//System.out.println(  Thread.currentThread().getName() +  " onMethodExit " + CallbackState.classLoaderMethodCount.get().count);
		
	}
	
	public static void onMethodEnterDoTrace()
	{
		
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoTrace++;
		
//		System.out.println( Thread.currentThread().getName() +  "  onMethodEnter " + CallbackState.classLoaderMethodCount.get().count);
		
	}
	
	
	
	
	
	
	
	
}

