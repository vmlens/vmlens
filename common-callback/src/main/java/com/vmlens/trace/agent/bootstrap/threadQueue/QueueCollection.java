package com.vmlens.trace.agent.bootstrap.threadQueue;

import java.lang.reflect.Constructor;

import com.vmlens.trace.agent.bootstrap.util.Constants;


public class QueueCollection {

	
	
	private final QueueFacade queueFacade;
	
	private final PerThreadQueue[] queueArray;
	
	
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
	
	




	public QueueCollection(QueueFacade queueFacade, PerThreadQueue[] queueArray) {
		super();
		this.queueFacade = queueFacade;
		this.queueArray = queueArray;
	}

	
	public long getQueueLength()
	{
		return queueFacade.writeCount * Constants.WRITE_WRITE_COUNT_EVERY_X - queueFacade.readCount;
	}
	
	public void increamentWriteCount()
	{
		queueFacade.increamentWriteCount();
	}
	

	
	public void putDirect(Object in)
	{
		queueFacade.putDirect(in);
	}
	
	
	
	public void park(long nanoSeconds)
	{
		 UNSAFE.park(false, nanoSeconds);
	}
	

	public void put(int index, Object element, int slidingWindowId)
	{
		
		
// führt zu einem crash der jvm		
//		if( slidingWindowId < 1 )
//		{
//			queueFacade.queueLogger.log(  "slidingWindowId:" + slidingWindowId + " " + element);
//		}
		
		
		queueArray[index].put( element ,  slidingWindowId, queueFacade);
	
		
	}





	
	
}
