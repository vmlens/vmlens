package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

import gnu.trove.set.hash.TLongHashSet;

public class ThreadStartCallback {
	
	private static TLongHashSet startedThreadIds = new TLongHashSet();
	
	private static boolean wasStarted(long threadId)
	{
		synchronized(startedThreadIds)
		{
			return startedThreadIds.contains(threadId);
		}
		
		
	}
	
	
	private static void addToStarted(long threadId)
	{
		synchronized(startedThreadIds)
		{
			 startedThreadIds.add(threadId);
		}
		
	}
	
	
	
	public static void isAliveObject(Object object , boolean isAlive)
	{
			
		if( ! isAlive && object instanceof Thread)
		{
		
			
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			
			
			 int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);
			
			 
			 
			 
			if(CallbackState.isSlidingWindowTrace( slidingWindowId ) ) 
			{
				
				if( wasStarted(((Thread)object).getId()) )
				{
					
					callbackStatePerThread.programCount++;
					
					 callbackStatePerThread.sendEvent.writeThreadStoppedEventGen( slidingWindowId , 	((Thread)object).getId() , 
							callbackStatePerThread.programCount, callbackStatePerThread.methodCount );
					
					
					
					
				
		
					callbackStatePerThread.programCount++;
				}
			
				
				
				
				
			}
		}
		
		
	}
	
	
	public static boolean isAlive(Thread thread)
	{
		

		boolean isAlive = thread.isAlive();
		
		
		if( ! isAlive )
		{
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			
			
			 int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);
			
			 
			 
			 
			if(CallbackState.isSlidingWindowTrace( slidingWindowId ) ) 
			{
				
				if( wasStarted(thread.getId()) )
				{
				
				
				callbackStatePerThread.programCount++;
				
				 callbackStatePerThread.sendEvent.writeThreadStoppedEventGen( slidingWindowId , 	thread.getId() , 
						callbackStatePerThread.programCount, callbackStatePerThread.methodCount );
				
				
				
				
			
	
				callbackStatePerThread.programCount++;
				
				}
				
				
			}
		}

		
		
		
		
		
		return isAlive;
	}
	



	public static void threadStart(Object newThread)
	{
	//	if(  CallbackState.trace() )
	
		{

			
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			
			ParallizeFacade.beforeThreadStart(callbackStatePerThread,  (Thread) newThread );
			
			
			 int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);
			
			 
			 
			 
			if(CallbackState.isSlidingWindowTrace( slidingWindowId ) ) 
			{
				
				
		
				callbackStatePerThread.programCount++;

				addToStarted( ((Thread) newThread).getId());
				
				 callbackStatePerThread.sendEvent. writeThreadBeginEventGen( slidingWindowId ,  ((Thread) newThread).getId() , 
						callbackStatePerThread.programCount, callbackStatePerThread.methodCount);
				
				
				
			
			
	
				callbackStatePerThread.programCount++;

			}


		}
	}


}
