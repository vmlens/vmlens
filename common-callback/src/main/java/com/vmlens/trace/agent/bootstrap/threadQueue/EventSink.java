package com.vmlens.trace.agent.bootstrap.threadQueue;

/**
 * 
 * Implement this interface to consume events. This class runs single threaded.
 * 
 */


public interface EventSink   {
	
	
	int getSlidingWindowId(int currentWriteCount);
	
	
	/**
	 * 
	 * consumes one event.
	 * 
	 * @param event
	 */
	void consume(Object event);
	
	/**
	 * 
	 * last event was processed
	 * 
	 */
	void close( int emptyQueueCount , int stoppedCount );
	
	
	/**
	 * 
	 * received stop message
	 * achtung kann häufiger aufgerufen werden
	 * 
	 */
    void onStop();
    
    
    
    void onWait();


	
	

}
