package com.vmlens.trace.agent.bootstrap.threadQueue;

import java.lang.reflect.Constructor;

import gnu.trove.list.TLinkable;


class InternalPerThreadQueue implements TLinkable<InternalPerThreadQueue> {

	 final static int IDLE = 0;
	private final static int WRITING = 1;
	private final static int STOPPED = 2;
	
	
	private InternalPerThreadQueue previous;
	private InternalPerThreadQueue next;

    final int forSlidingWindowId;
    volatile int state = IDLE;
	private static final long stateOffset;
	
	int index;
	Object[] array;

	private static final sun.misc.Unsafe UNSAFE;
	
	   
	   static{
		   
		   try{
		   Constructor<sun.misc.Unsafe> unsafeConstructor = sun.misc.Unsafe.class.getDeclaredConstructor();
		   unsafeConstructor.setAccessible(true);
		   UNSAFE = unsafeConstructor.newInstance();
		   
		   stateOffset = UNSAFE.objectFieldOffset
		            (InternalPerThreadQueue.class.getDeclaredField("state"));
		   }
		   catch(Exception e)
		   {
			   throw new RuntimeException(e);
		   }
		   
		   
	   }
	
	
	
	   private final boolean compareAndSet(int expect, int update) {
	        return UNSAFE.compareAndSwapInt(this, stateOffset, expect, update);
	    }
  
	
	
	boolean isStopped()
	{
		return state == STOPPED;
	}
	
	
	
	
	 InternalPerThreadQueue(int forSlidingWindowId,Object firstElement,int arraySize) {
		super();
		
		array = new Object[arraySize];
		array[index] = firstElement;
		
		index++;
		
		
		this.forSlidingWindowId = forSlidingWindowId;
	}
	
	 
	 

	 
	 ArrayEvent stopByReaderThread()
	 {
		 if( compareAndSet(IDLE , STOPPED ))
		 {
			 return new ArrayEvent(array , index);
		 }
		 else
		 {
			return null; 
		 }
	 }
	 
	
	
	 /**
	  * stop and write to background queue
	  * 
	  */
	 
	 void stopByWriterThread(QueueFacade queueFacade)
	 {
		 queueFacade.backroundQueue.offer(  new ArrayEvent(array , index) );
		 state = STOPPED;
	 }
	 
	 
	 boolean set4Writing()
	 {
		 return compareAndSet(IDLE , WRITING );
	 }
	 
	 
	 void put(Object element,QueueFacade queueFacade)
	 {
		 array[index] = element;
		 index++;
		 
		 if(index == array.length)
		 {
			 queueFacade.backroundQueue.offer(   new ArrayEvent(array , index) );
			 array  = new Object[array.length];
			 index = 0;
			 
			
			 
		 }
		 
		 
	 }








	public InternalPerThreadQueue getPrevious() {
		return previous;
	}








	public void setPrevious(InternalPerThreadQueue previous) {
		this.previous = previous;
	}








	public InternalPerThreadQueue getNext() {
		return next;
	}








	public void setNext(InternalPerThreadQueue next) {
		this.next = next;
	}







	
}
