package com.vmlens.trace.agent.bootstrap.threadQueue;


import java.lang.reflect.Constructor;
import java.util.Iterator;
import gnu.trove.list.linked.TLinkedList;

public class SingleEventReader implements Runnable {

	private static final sun.misc.Unsafe UNSAFE;
	
	   
	   static{
		   
		   try{
		   Constructor<sun.misc.Unsafe> unsafeConstructor = sun.misc.Unsafe.class.getDeclaredConstructor();
		   unsafeConstructor.setAccessible(true);
		   UNSAFE = unsafeConstructor.newInstance();
		   }
		   catch(Exception e)
		   {
			   throw new RuntimeException(e);
		   }
		   
		   
	   }	
	
	
	 final QueueFacade queueFacade;
	 final EventSink eventSink;
	
	 final TLinkedList<InternalPerThreadQueue> queueList = new TLinkedList<InternalPerThreadQueue>();
	 
	 
	
	private int currentSlidingWindowId = 1;
	
	private final ProzessBackgroundQueue prozessBackgroundQueue;
	private final ProzessQueueOfQueue prozessQueueOfQueue;
	

	
	public SingleEventReader(QueueFacade queueFacade, EventSink eventSink) {
		super();
		this.queueFacade = queueFacade;
		this.eventSink = eventSink;
		
		prozessBackgroundQueue = new ProzessBackgroundQueue(this);
		prozessQueueOfQueue = new ProzessQueueOfQueue(this);
	}



	/**
	 * 
	 * Background queue auslesen
	 * 	 events prozessieren+
	 * 			interface Array
	 * 			einzelnes event
	 * 			stop event
	 * 
	 * 	queueOfQueues auswerten
	 * 			alte queue of queues stoppen
	 * 			
	 * 
	 * currentSlidingWindowId auf neue slidingWindowId umsetzen
	 * 	
	 * 
	 * 
	 * 
	 */
	
	
	@Override
	public void run() {
		
	   int emptyQueueCount =0;
	   int stoppedCount = 0;
	   
	   
		while(emptyQueueCount < 5 && stoppedCount < 100)
		{
			prozessBackgroundQueue.prozessedEvents = 0;
			
			
			
			queueFacade.backroundQueue.drain(prozessBackgroundQueue);
			
			queueFacade.readCount += prozessBackgroundQueue.prozessedEvents;
			
			
			int prozessedEvents = 0;
			
            queueFacade.queueOfQueues.drain(prozessQueueOfQueue);
			
            Iterator<InternalPerThreadQueue> iterator = queueList.iterator();
            
            while( iterator.hasNext() )
            {
            	InternalPerThreadQueue current =   iterator.next(); 
            	
            	if( current.isStopped() )
            	{
            		iterator.remove();	
            	}
            	else
            	{
            		if(  current.forSlidingWindowId <  currentSlidingWindowId )
            		{
            			ArrayEvent arrayEvent =   current.stopByReaderThread();
            			
            			if(arrayEvent != null )
            			{
            				for(int i = 0 ; i< arrayEvent.length ; i++)
            				{
            					eventSink.consume( arrayEvent.array[i] );
            				}
            				
            				prozessedEvents += arrayEvent.length;
            			}
            			
            		}
            		
            		
            	}
            	
            		
            	
            }
            
            queueFacade.readCount += prozessedEvents;
            
            prozessedEvents +=  prozessBackgroundQueue.prozessedEvents;
            
            
            if( prozessBackgroundQueue.stopEventReceived )
            {
            	eventSink.onStop();
            	
            	currentSlidingWindowId= Integer.MAX_VALUE;
            	
            	if(queueList.isEmpty() &&  prozessedEvents == 0 )
            	{
            		emptyQueueCount++;
            		
            		 UNSAFE.park(false, 10);
            	}
            	else
            	{
            		stoppedCount++;
            	}
            	
            	
            }
            else
            {
            	currentSlidingWindowId = eventSink.getSlidingWindowId(queueFacade.writeCount);
            }
            
            
            if(prozessedEvents == 0)
            {
            	eventSink.onWait();
            }
            
            
           
            
            
        	
            
            
			
		}
		
		
	
		eventSink.close(emptyQueueCount,stoppedCount);
		
	}

}
