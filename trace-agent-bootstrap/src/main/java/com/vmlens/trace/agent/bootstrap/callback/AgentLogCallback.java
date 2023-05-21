package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.AgentLogEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AgentLogCallback {

	public static volatile boolean ENABLE_EXCEPTION_LOGGING = false;
	

	public static void logException(Throwable exception)
	{
		if(ENABLE_EXCEPTION_LOGGING)
		{
			
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;	
			
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		exception.printStackTrace(writer);
		callbackStatePerThread.queueCollection.putDirect( new AgentLogEvent("EXCEPTION:"  + stringWriter.toString())  );
		
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		}
	}
	
	public static void logError(String message)
	{
		if(ENABLE_EXCEPTION_LOGGING)
		{
			
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;	
			
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		new Exception().printStackTrace(writer);
		callbackStatePerThread.queueCollection.putDirect( new AgentLogEvent("EXCEPTION:"  + message  + "  " + stringWriter.toString() )  );
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;	
		
		}
	}
	

	public static void log(String message)
	{
        CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		callbackStatePerThread.queueCollection.putDirect( new AgentLogEvent(message)  );
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;	
	}

    public static  void close(Object obj)
	{
		CallbackStatePerThread callbackStatePerThread  = CallbackState.callbackStatePerThread.get();
        ParallelizeFacade.close(callbackStatePerThread, obj);
	}

    public static boolean hasNext(Object obj)
	{
		CallbackStatePerThread callbackStatePerThread  = CallbackState.callbackStatePerThread.get();
        return ParallelizeFacade.hasNext(callbackStatePerThread, obj);
	}

}
