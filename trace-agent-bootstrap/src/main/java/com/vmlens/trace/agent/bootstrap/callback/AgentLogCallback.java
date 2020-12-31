package com.vmlens.trace.agent.bootstrap.callback;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import com.vmlens.trace.agent.bootstrap.AtomicClassRepo;
import com.vmlens.trace.agent.bootstrap.event.AgentLogEvent;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeSingelton;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.InterleaveControlLogic;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.LogicState;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.ThreadId2State;

public class AgentLogCallback {

	
	public static volatile boolean ENABLE_EXCEPTION_LOGGING = false;
	
	
	private static String createTimeoutMessage(ThreadId2State threadId2State,LogicState logicState)
	{
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		writer.println(  logicState );
		threadId2State.debug(writer);
		

		Map<Thread, StackTraceElement[]> entry = Thread.getAllStackTraces();
		
		for( Entry<Thread, StackTraceElement[]> elem : entry.entrySet()  )
		{
			writer.println( elem.getKey().getId() + " " + elem.getKey().getName() );
			{
				for( StackTraceElement s : elem.getValue())
				{
					writer.println(  s.toString() );
				}
				
			}
			
			
			
		}
		
		return stringWriter.toString();
	}
	
	
	public static void logThreadId2StateError(ThreadId2State threadId2State)
	{
		if(ENABLE_EXCEPTION_LOGGING)
		{
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;
			
			
			StringWriter stringWriter = new StringWriter();
			PrintWriter writer = new PrintWriter(stringWriter);
			
			threadId2State.debug(writer);
			

			Map<Thread, StackTraceElement[]> entry = Thread.getAllStackTraces();
			
			for( Entry<Thread, StackTraceElement[]> elem : entry.entrySet()  )
			{
				writer.println( elem.getKey().getId() + " " + elem.getKey().getName() );
				{
					for( StackTraceElement s : elem.getValue())
					{
						writer.println(  s.toString() );
					}
					
				}
				
				
				
			}
			
			
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
				
			callbackStatePerThread.queueCollection.putDirect( new AgentLogEvent("EXCEPTION:"  +stringWriter.toString() )  );
			
			
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		}

	}
	
	
	public static void logTimeout(ThreadId2State threadId2State,LogicState logicState)
	{
		if(ENABLE_EXCEPTION_LOGGING)
		{
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;
			
			
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
				
			callbackStatePerThread.queueCollection.putDirect( new AgentLogEvent("TIMEOUT:"  +createTimeoutMessage(threadId2State,logicState) )  );
			
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		}

	}
	
	
	public static void logTimeoutWarning(ThreadId2State threadId2State,LogicState logicState,int runId)
	{
		
		
		if(ENABLE_EXCEPTION_LOGGING)
		{
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;
		
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		
		callbackStatePerThread.queueCollection.putDirect( new AgentLogEvent("WARNING_TIMEOUT: for runId: " + runId +createTimeoutMessage(threadId2State,logicState)  ));
		
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		
		}
	}
	
	
	public static void logException(Throwable exception)
	{
		if(ENABLE_EXCEPTION_LOGGING)
		{
			
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;	
			
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		
//		callbackStatePerThread.doNotInterleave++;
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		exception.printStackTrace(writer);
		callbackStatePerThread.queueCollection.putDirect( new AgentLogEvent("EXCEPTION:"  + stringWriter.toString())  );
		
		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		
//		callbackStatePerThread.doNotInterleave--;
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
		ParallizeSingelton.close( callbackStatePerThread,obj );
	}
	
	
	
	public static boolean hasNext(Object obj)
	{
		CallbackStatePerThread callbackStatePerThread  = CallbackState.callbackStatePerThread.get();
		
		return ParallizeSingelton.hasNext(callbackStatePerThread,obj);
	}
	
	
	

	
	
	
	
}
