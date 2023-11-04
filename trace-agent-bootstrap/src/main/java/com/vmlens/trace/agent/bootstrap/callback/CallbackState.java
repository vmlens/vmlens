package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.NewSlidingWindowId;
import com.vmlens.trace.agent.bootstrap.threadQueue.InternalPerThreadQueueFactory;
import com.vmlens.trace.agent.bootstrap.threadQueue.QueueFacade;
import com.vmlens.trace.agent.bootstrap.util.Constants;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.hash.TLongHashSet;

import java.util.Set;


public class CallbackState {
	public static final int SLIDING_WINDOW_MUST_BE_GREATER = 0;
	public static boolean startAtBeginning;
	public static volatile int  slidingWindow = 0;
	public static volatile boolean doNotcheckStackTraceBasedDoTrace = true;
	
	static volatile int maxStackTraceDepth =  Constants.STATIC_MAXIMUM_STACK_TRACE_DEPTH;;
	
	private static Object UPDATE_MAX_STACK_TRACE_DEPTH_LOCK = new Object();
	
	static void setMaxStackTraceDepth(int newDepth)
	{
		if( newDepth <  maxStackTraceDepth)
		{
			synchronized(UPDATE_MAX_STACK_TRACE_DEPTH_LOCK)
			{
				if( newDepth <  maxStackTraceDepth)
				{
					maxStackTraceDepth = newDepth;
				}
			}
		}
		
		
	}
	
	
	
	
	
	public static volatile int shortenStacktraceAfterNEvents = 100000;
//	public static volatile boolean delaySynchronization;

	
	public static volatile boolean syncActionSameAsField4TraceCheck;
	
	
	
	
	
	private static QueueFacade createQueueFacade()
	{
		InternalPerThreadQueueFactory[] queueCollectionFactoryArray = new InternalPerThreadQueueFactory[9];
		
		for( int i = 0 ; i < queueCollectionFactoryArray.length ; i++ )
		{
			queueCollectionFactoryArray[i] = new InternalPerThreadQueueFactory(Constants.INTERNAL_QUEUE_LENGTH);
		}
		
		
		return new QueueFacade(queueCollectionFactoryArray);
	}
	
	
	
	
	
	
	
    public static final QueueFacade queueFacade =  createQueueFacade();
	  // new EventBusDebug();
    		 //VMLensExecutors.<Event>createEventBus();	
    
    

    
    
    
     
    public static void setSlidingWindowToOneIfStartAtBeginning()
    {
    	if(  startAtBeginning  )
    	{
    		slidingWindow = 1;
    	}
    }
    
    
	/*
	 * 
	 * Im Tomcat werden meine schönen callback states
	 * weggeworfen. Daher werden sie hier gecached
	 * 
	 * @param object
	 */
	
	private static final TLongObjectHashMap<CallbackStatePerThreadForParallelize> callbackStatePerThreadRecovery = new TLongObjectHashMap<CallbackStatePerThreadForParallelize>();
	
	
	private static int lastRemovedThreadCount = 0;
    public static final ThreadLocal<CallbackStatePerThreadForParallelize> callbackStatePerThread = new ThreadLocal<CallbackStatePerThreadForParallelize>() {
        @Override
        protected CallbackStatePerThreadForParallelize initialValue() {

            return getOrCreateCallbackStatePerThread();
        }
    };

	public static synchronized void clearCallbackStatePerThreadRecovery()
	{
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		TLongHashSet threadIds = new TLongHashSet();
		
		
		for( Thread t : threadSet)
		{
			threadIds.add(t.getId());
		}

        TLongObjectIterator<CallbackStatePerThreadForParallelize> iter = callbackStatePerThreadRecovery.iterator();
		
		while( iter.hasNext() )
		{
			iter.advance();
			
			if( ! threadIds.contains(iter.key()) )
			{
				iter.remove();
				
				lastRemovedThreadCount++;
				
			}
			
			
		}
		
		if(lastRemovedThreadCount > Constants.NEW_SLIDING_WINDOW_ID_EVERY_N_THREAD)
		{
		
			lastRemovedThreadCount=0;
			queueFacade.putDirect(  new NewSlidingWindowId());
		}
		
		
	}
	
	
	
	

	
	/*
	 * 
	 * SlidingWindow < 1 (default wert 0) heißt nicht tracen.
	 * 
	 * @param callbackStatePerThread
	 * @return
	 */
	

	public static boolean isSlidingWindowTrace(int slidingWindow)
	{
		return slidingWindow > SLIDING_WINDOW_MUST_BE_GREATER;
			
	}
	
	
	/*
	 * Der shutdown hook setzt slidingWindow auf -1
	 * @return
	 */
	
	public static boolean writeClassDescription()
	{
		return slidingWindow > -1;
	}

    private static synchronized CallbackStatePerThreadForParallelize getOrCreateCallbackStatePerThread() {
        long threadId = Thread.currentThread().getId();


        CallbackStatePerThreadForParallelize callbackStatePerThread = callbackStatePerThreadRecovery.get(threadId);

        if (callbackStatePerThread == null) {
            callbackStatePerThread = new CallbackStatePerThreadForParallelize(threadId, new QueueCollectionWrapper(queueFacade.createQueueCollection4ThreadLocal()));
            callbackStatePerThreadRecovery.put(threadId, callbackStatePerThread);
		}
		
		

		
		
		
		
		return callbackStatePerThread;
	}



}
