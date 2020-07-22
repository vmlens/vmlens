package com.vmlens.trace.agent.bootstrap.callback;



import com.vmlens.trace.agent.bootstrap.callback.state.LockIdAndOrder;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;


public class LockStatementCallback {


	public static final AnarsoftWeakHashMap<LockIdAndOrder> objectToOrder =
		    new AnarsoftWeakHashMap<LockIdAndOrder>();


	


//	protected static void pruneKeys() {
//
//		Reference reference  = referenceQueue.poll();
//		while( reference != null )
//		{
//			objectToOrder.remove(reference);
//			reference  = referenceQueue.poll();
//		}
//
//	}


	/*
	 * 0 aqcuire/release
	 * 1 fullyRelease acquireQueued
	 * 2 setExclusiveOwnerThread
	 * 3 tryAcquire
	 * 4 stamped lock
	 * 
	 */
	
	
	
	
	
	public static  void acquireQueued(Object in)
	{
		 acquireInternal( in,false,1);
	}
	

	public static  void acquire(Object in)
	{
		 acquireInternal( in,false,0);
	}

	public static  void acquireShared(Object in)
	{
		 acquireInternal( in,true,0);
	}

	public static  void release(Object in)
	{

		 releaseInternal( in,false,0);
	}

	
	
	public static  void fullyRelease(Object in)
	{

		 releaseInternal( in,false,1);
	}


	public static  void releaseShared(Object in)
	{

		 releaseInternal( in,true,0);
	}

	public static void tryAcquire(boolean result ,  Object in)
	{
		if( result )
		{
			acquireInternal( in,false,3);
		}
	}
	
	
	
	public static void tryAcquireSharedBoolean(boolean result ,  Object in)
	{
		if( result )
		{
			acquireInternal( in,true,3);
		}
	}
	
	

	
	public static void setExclusiveOwnerThread(Object sync , Object thread)
	{
	  if( thread != null )
	  {
		  acquireInternal( sync,false,2);
	  }
	  else
	  {
		  releaseInternal(sync,false,2);
	  }
	}

	
	
	/**
	 *     a negative value on failure; zero if acquisition in shared mode succeeded but no subsequent shared-mode acquire can succeed; and a positive value if acquisition in shared mode succeeded and subsequent shared-mode acquires might also succeed, in which case a subsequent waiting thread must check availability. (Support for three different return values enables this method to be used in contexts where acquires only sometimes act exclusively.) Upon success, this object has been acquired.
	 * @param result
	 * @param in
	 */
	
	public static void tryAcquireShared(int result ,  Object in)
	{
		if( result >= 0 )
		{
			acquireInternal( in,true,3);
		}
	}
	
	public static void tryReleaseShared(boolean success , Object in)
	{
		if( success )
		{
			 releaseInternal( in,true,3);
		}
	}
	

	// ToDo change needs object
	private static  void acquireInternal(Object objectKey,boolean isShared, int lockTyp)
	{
	
		
		
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		
		
	
		 
		int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);
		
		if(  CallbackState.isSlidingWindowTrace( slidingWindowId ) )
		{


			callbackStatePerThread.programCount++;



			

			 long currentThreadId = callbackStatePerThread.threadId;
			 int currentProgramCounter = callbackStatePerThread.programCount;


			//Integer orderInteger = objectToOrder.get(objectKey);

			int order = 0;
			LockIdAndOrder current = null;
			
			synchronized(objectToOrder)
			{
//				pruneKeys();
                current =  objectToOrder.get(objectKey);
				
				if( current == null )
				{
					current = new LockIdAndOrder();
					current.id = LockIdAndOrder.getNewId();
				}
				
				order = current.order ;
				
				current.order = current.order + 1;
				
				objectToOrder.put(objectKey, current);
				
			}

	
			  callbackStatePerThread.sendEvent.writeLockEnterEventGen(slidingWindowId ,   currentProgramCounter, order,
					 current.id ,  callbackStatePerThread.methodCount, isShared , lockTyp);
			
	
				callbackStatePerThread.programCount++;
				
			
				
		}

	}





	/*
	 *   public LockExitEventBootstrap(long threadId, int programCounter,
			int order, int monitorId) {
	 */

	private static  void releaseInternal(Object objectKey,boolean isShared, int lockTyp)
	{
		
	
		
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		
	//	ParallizeFacade.beforeLockRelease(callbackStatePerThread , objectKey,isShared);
		
		int slidingWindowId =  CallbackState.traceSyncStatements(callbackStatePerThread);
	
		if(  CallbackState.isSlidingWindowTrace(slidingWindowId  ) )
		{
			callbackStatePerThread.programCount++;


		int order = 0;
	
		LockIdAndOrder current = null;
		
		synchronized(objectToOrder)
		{
//			pruneKeys();
            current =  objectToOrder.get(objectKey);
			
			if( current == null )
			{
				current = new LockIdAndOrder();
				current.id = LockIdAndOrder.getNewId();
			}
			
			order = current.order ;
			
			current.order = current.order + 1;
			
			objectToOrder.put(objectKey, current);
		}	

		 callbackStatePerThread.sendEvent.writeLockExitEventGen ( slidingWindowId , callbackStatePerThread.programCount, order,  current.id , callbackStatePerThread.methodCount, isShared,lockTyp);
	
		
		
		callbackStatePerThread.programCount++;
		
		
		
		}
	}


}
