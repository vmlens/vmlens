package com.vmlens.trace.agent.bootstrap.callback;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockEnter;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockExit;
import com.vmlens.trace.agent.bootstrap.interleave.operation.LockEnterOrExit;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;

public class LockTemplateCallback {

//	private static final AnarsoftWeakHashMap<LockIdAndOrder> objectToOrder =
//		    new AnarsoftWeakHashMap<LockIdAndOrder>();
//	private static final Object LOCK = new Object();
//	
	
	
	public static void conditionAwait(AbstractQueuedSynchronizer sync)
	{
		
		
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		int identity = System.identityHashCode(sync);
		access(  identity  , -1 , false, false);
		access(  identity  , -1 , true, false);
		
		
	
	}
	
	
	
	public static boolean tryLock(ReentrantLock lock , int methodId)
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		try {
		
			
			
		if(lock.tryLock())
		{
			
			access(  getIdentity( callbackStatePerThread, lock) , methodId , true, false);
			return true;
		}
		else
		{
			return false;
		}
	
		}
		finally {
			 callbackStatePerThread.doNotInterleaveFromLock--;
		}
		
		
	}
	
	
	public static boolean tryLock(ReentrantLock lock , long time,   TimeUnit unit ,int methodId)     throws InterruptedException
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		try {
		
			
			
		if(lock.tryLock(time, unit))
		{
			
			access(  getIdentity( callbackStatePerThread, lock) , methodId , true, false);
			return true;
		}
		else
		{
			return false;
		}
	
		}
		finally {
			 callbackStatePerThread.doNotInterleaveFromLock--;
		}
		
		
	}
	
	
	
	public static void lockInterruptibly(ReentrantLock lock , int methodId)     throws InterruptedException
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		try {
		
			
		lock.lockInterruptibly();
		access(  getIdentity( callbackStatePerThread, lock) , methodId , true, false);
		
		}
		finally {
			 callbackStatePerThread.doNotInterleaveFromLock--;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void lock(ReentrantLock lock , int methodId)
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
		lock.lock();
		 }
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}
	
		
		access(  getIdentity( callbackStatePerThread, lock) , methodId , true, false);
		
	}
	
	public static void unlock(ReentrantLock lock , int methodId)
	{
		
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
			 lock.unlock();
		 }
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}
			

			access(  getIdentity( callbackStatePerThread, lock) , methodId , false, false);
		
		
	}
	
	
	
	
	
	
	
	public static boolean tryLock(ReadLock lock , int methodId)
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
				
				
				
				if(lock.tryLock())
				{
					
					access( getIdentity( callbackStatePerThread, lock) , methodId , true, true);
					return true;
				}
				else
				{
					return false;
				}
			
				}
				finally {
					 callbackStatePerThread.doNotInterleaveFromLock--;
				}
	
		
		
	}
	
	public static boolean tryLock(ReadLock lock ,  long time,   TimeUnit unit , int methodId) throws InterruptedException
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
				
				
				
				if(lock.tryLock(time,unit))
				{
					
					access( getIdentity( callbackStatePerThread, lock) , methodId , true, true);
					return true;
				}
				else
				{
					return false;
				}
			
				}
				finally {
					 callbackStatePerThread.doNotInterleaveFromLock--;
				}
	
		
	}
	
	public static void lockInterruptibly(ReadLock lock , int methodId) throws InterruptedException
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
			 lock.lockInterruptibly();
		 }
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}
	
		access( getIdentity( callbackStatePerThread, lock) , methodId , true, true);
		
	}
	
	

	
	
	public static void lock(ReadLock lock , int methodId)
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
			 lock.lock();
		 }
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}
	
		access( getIdentity( callbackStatePerThread, lock) , methodId , true, true);
		
	}
	
	public static void unlock(ReadLock lock , int methodId)
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
				lock.unlock();
		 }
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}
		
		
		
			
		access(getIdentity( callbackStatePerThread, lock)  , methodId , false, true);
		
		
	}
	
	
	
	
	public static boolean tryLock(WriteLock lock , int methodId)
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		
	       try {
			if(lock.tryLock())
			{
				
				access(getIdentity( callbackStatePerThread, lock) , methodId , true, false);
				return true;
			}
			else
			{
				return false;
			}
		
			}
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}		
			
	}
	
	
	public static boolean tryLock(WriteLock lock , long time,   TimeUnit unit , int methodId) throws InterruptedException
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		
	       try {
			if(lock.tryLock(time , unit ))
			{
				
				access(getIdentity( callbackStatePerThread, lock) , methodId , true, false);
				return true;
			}
			else
			{
				return false;
			}
		
			}
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}		
			
	}
	
	
	public static void lockInterruptibly(WriteLock lock , int methodId) throws InterruptedException
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
			 lock.lockInterruptibly();
		 }
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}
	
			
			
	
		
		
		access(getIdentity( callbackStatePerThread, lock) , methodId , true, false);
		
	}
	
	
	
	
	
	
	public static void lock(WriteLock lock , int methodId)
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
			 lock.lock();
		 }
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}
	
			
			
	
		
		
		access(getIdentity( callbackStatePerThread, lock) , methodId , true, false);
		
	}
	
	public static void unlock(WriteLock lock , int methodId)
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 callbackStatePerThread.doNotInterleaveFromLock++;
		 try {
				lock.unlock();
				
		 }
			finally {
				 callbackStatePerThread.doNotInterleaveFromLock--;
			}
	
			
		
		
	
		
	
		
		
		
		
		access( getIdentity( callbackStatePerThread, lock)  , methodId , false, false);
		
		
	}
	
	
	
	
	
	public static boolean tryLock(Lock lock , int methodId)
	{
	
		if(lock instanceof ReentrantLock)
		{
			return tryLock(  (ReentrantLock) lock ,  methodId);
		}
		else if(lock instanceof ReadLock)
		{
			return tryLock(  (ReadLock) lock ,  methodId);
		}
		else if(lock instanceof WriteLock)
		{
			return tryLock(  (WriteLock) lock ,  methodId);
		}
		else
		{
			return lock.tryLock();
		}
		
		
	
		
	}
	
	
	public  static boolean  tryLock(Lock lock ,long time,
            TimeUnit unit , int methodId) throws InterruptedException
	{
	
		if(lock instanceof ReentrantLock)
		{
			return tryLock(  (ReentrantLock) lock , time , unit  ,  methodId);
		}
		else if(lock instanceof ReadLock)
		{
			return tryLock(  (ReadLock) lock , time , unit ,  methodId);
		}
		else if(lock instanceof WriteLock)
		{
			return	tryLock(  (WriteLock) lock , time , unit ,  methodId);
		}
		else
		{
			return lock.tryLock(time , unit );
		}
		
		
	
		
	}
	
	public static void lockInterruptibly(Lock lock , int methodId) throws InterruptedException
	{
	
		if(lock instanceof ReentrantLock)
		{
			lockInterruptibly(  (ReentrantLock) lock ,  methodId);
		}
		else if(lock instanceof ReadLock)
		{
			lockInterruptibly(  (ReadLock) lock ,  methodId);
		}
		else if(lock instanceof WriteLock)
		{
			lockInterruptibly(  (WriteLock) lock ,  methodId);
		}
		else
		{
			lock.lockInterruptibly();
		}
		
		
	
		
	}
	
	
	
	
	
	
	
	
	
	
	public static void lock(Lock lock , int methodId)
	{
	
		if(lock instanceof ReentrantLock)
		{
			lock(  (ReentrantLock) lock ,  methodId);
		}
		else if(lock instanceof ReadLock)
		{
			lock(  (ReadLock) lock ,  methodId);
		}
		else if(lock instanceof WriteLock)
		{
			lock(  (WriteLock) lock ,  methodId);
		}
		else
		{
			lock.lock();
		}
		
		
	
		
	}
	
	public static void unlock(Lock lock , int methodId)
	{
		
		if(lock instanceof ReentrantLock)
		{
			unlock(  (ReentrantLock) lock ,  methodId);
		}
		else if(lock instanceof ReadLock)
		{
			unlock(  (ReadLock) lock ,  methodId);
		}
		else if(lock instanceof WriteLock)
		{
			unlock(  (WriteLock) lock ,  methodId);
		}
		else
		{
			lock.unlock();
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static int getIdentity(final CallbackStatePerThread callbackStatePerThread , final Object theLock)
	{
		callbackStatePerThread.stackTraceBasedDoNotTrace++;
		try {
			return System.identityHashCode(theLock);
		}
		finally {
			callbackStatePerThread.stackTraceBasedDoNotTrace--;
		}
	}
	

	
	
	
	protected static void access(final int identity, final int methodId, boolean isLockEnter, boolean isShared  )
	{
	
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	

		if(isLockEnter)
		{
			 
			  if( isShared )
			  {
				  ParallizeFacade.afterLockOperation(callbackStatePerThread, new LockEnterOrExit(isShared));
			  }
			  
			  ParallizeFacade.onLock(callbackStatePerThread, new LockEnter(isShared, identity ));
			  
		}
		else
		{	 
			
			  ParallizeFacade.onLock(callbackStatePerThread, new LockExit(isShared, identity  ));
			
			if( ! isShared )
			  {
				  ParallizeFacade.afterLockOperation(callbackStatePerThread, new LockEnterOrExit(isShared));
			  }
			  

			
			
			
		}

		
		

			callbackStatePerThread.programCount++;
	}
	
	
	
}
