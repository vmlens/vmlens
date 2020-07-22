package com.vmlens.trace.agent.bootstrap.callback;



import com.vmlens.trace.agent.bootstrap.threadQueue.QueueCollection;
import com.vmlens.trace.agent.bootstrap.util.Constants;


public class QueueCollectionWrapper {

	private final QueueCollection queueCollection;

	public QueueCollectionWrapper(QueueCollection queueCollection) {
		super();
		this.queueCollection = queueCollection;
	}
	
	public void putDirect(Object in)
	{
		queueCollection.putDirect(in);
	}
	
	int writeEventCount = 0;

	
	/**
	 * haupt methode
	 * 	
	 * 
	 * 
	 * put(index, Object, slidingWindowId)

	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param index
	 * @param element
	 * @param sldingWindowId
	 * @return
	 */
	
	
	private static final long THOUSEND         =   1000L;
	private static final long TEN_THOUSEND     =  10000L;
	private static final long HUNDRED_THOUSEND = 100000L;
	

	public void put(int index, Object element, int slidingWindowId)
	{
		
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		
		callbackStatePerThread.stackTraceBasedDoNotTrace++;  
		
		queueCollection.put(index, element, slidingWindowId);
		
		writeEventCount++;
			
		if(writeEventCount >=  Constants.WRITE_WRITE_COUNT_EVERY_X)
		{
			writeEventCount = 0;
			queueCollection.increamentWriteCount();
		}
		

		
		while( queueCollection.getQueueLength() > 100000 )
		{
			
			//AgentLogCallback.log("queue full " + currentSize + " " +  callbackStatePerThread.threadId);
			
			queueCollection.park(10);
			callbackStatePerThread.queueIsFull = true;
		} 
//		else 
//			if(queueCollection.getLastProzessedEventCount() < Constants.INTERNAL_QUEUE_LENGTH )
//		{
//			callbackStatePerThread.queueIsFull = false;
//		}
		
		
		callbackStatePerThread.stackTraceBasedDoNotTrace--;  
	}
}
