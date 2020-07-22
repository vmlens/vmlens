package com.vmlens.trace.agent.bootstrap.parallize;


import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.lock.MonitorEnter;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.interleave.operation.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;


public class ParallizeFacade {

	
	public static volatile boolean ENABLE_LOGGING = false;
	public static volatile boolean ENABLE_PERFORMANCE_LOGGING = false;
	

	
	public static final int MAX_OPERATION_LIST_SIZE = 2;
	

	
	public static void afterLockOperation(CallbackStatePerThread callbackStatePerThread, OperationTyp operation)
	{
		if( callbackStatePerThread.parallizedThread != null )
		{			
			callbackStatePerThread.parallizedThread.afterLockOperation(operation);	
		}
	}
	
	
	
	public static void onLock(CallbackStatePerThread callbackStatePerThread,LockOperation lockOperation)
	{
		if( callbackStatePerThread.parallizedThread != null )
		{			
			callbackStatePerThread.parallizedThread.onLock(lockOperation);
			
		}
	}
	
	
	
	
	
	
	
	
	
	

	public static void afterFutureGet(CallbackStatePerThread callbackStatePerThread , long threadId)
	{
		if( callbackStatePerThread.parallizedThread != null )
		{			
			callbackStatePerThread.parallizedThread.afterThreadJoin( threadId    );	
		}
	}
	
	
	
	
	public static void beforeFieldAccessVolatile(CallbackStatePerThread callbackStatePerThread ,long objectHashCode ,  int fieldId, int operation)
	{
		if( callbackStatePerThread.parallizedThread != null )
		{			
	
			callbackStatePerThread.parallizedThread.before(   new VolatileFieldAccess(fieldId , operation)    );	
		}
	}
	
	
	
	
	
	
	
	
	
	

	public static void beginThreadMethodExit(CallbackStatePerThread callbackStatePerThread)
	{
		if( callbackStatePerThread.parallizedThread != null )
		{
			callbackStatePerThread.parallizedThread.beginThreadMethodExit(callbackStatePerThread);
		}
	}
	
	
	
	public static void beforeThreadStart(CallbackStatePerThread callbackStatePerThread , Thread startedThread)
	{
		if( callbackStatePerThread.parallizedThread != null )
		{
			if( callbackStatePerThread.inThreadStart == 0 )
			 {
				callbackStatePerThread.parallizedThread.beforeStart(callbackStatePerThread, new RunnableOrThreadWrapper(startedThread));
			 }
			
		}
	}


	
	public static void onAtomicMethodEnter(CallbackStatePerThread callbackStatePerThread, int methodId,boolean hasCallback,int atomicId) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			callbackStatePerThread.parallizedThread.onAtomicMethodEnter( methodId , atomicId ,hasCallback);
		}
	}

	public static void onAtomicMethodExit(CallbackStatePerThread callbackStatePerThread, int methodId,int atomicId, boolean hasCallback) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			callbackStatePerThread.parallizedThread.onAtomicMethodExit( methodId, atomicId , hasCallback);
		}
	}
	
	

	public static void afterFieldAccess4UnsafeOrVarHandle(CallbackStatePerThread callbackStatePerThread  , int fieldId, int operation) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			
			 callbackStatePerThread.parallizedThread.afterFieldAccess4UnsafeOrVarHandle(fieldId, operation);
		}
	}

	public static void afterVolatileArrayAccess4UnsafeOrVarHandle(CallbackStatePerThread callbackStatePerThread  , long index, int operation) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			 callbackStatePerThread.parallizedThread.afterVolatileArrayAccess4UnsafeOrVarHandle(index, operation);
		}
	}

	public static void beforeMonitorExit(CallbackStatePerThread callbackStatePerThread , int  monitorId, int methodId,int position) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			 callbackStatePerThread.parallizedThread.beforeMonitorExit(monitorId, methodId,position);
		
		}
	}

	public static void beforeMonitorExitStatic(CallbackStatePerThread callbackStatePerThread , int id, int methodId) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			 callbackStatePerThread.parallizedThread.beforeMonitorExitStatic(id, methodId);
			 

			 
		}
	}



	
	public static void afterThreadStart4ExecutorService(CallbackStatePerThread callbackStatePerThread)
	{
		if( callbackStatePerThread.parallizedThread != null )
		{
			 callbackStatePerThread.parallizedThread.afterThreadStart();
		}
	}



	public static void beforeExecutorStart(CallbackStatePerThread callbackStatePerThread, Object runnable) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			if( callbackStatePerThread.inThreadStart == 0 )
			 {
				
				
				callbackStatePerThread.parallizedThread.beforeStart(callbackStatePerThread, new RunnableOrThreadWrapper(runnable));
				
				
				
			 }
			
		}
		
	}


	
	
	
	
	public static void onMonitorEnter(CallbackStatePerThread callbackStatePerThread, int monitorId) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			callbackStatePerThread.parallizedThread.onLock( new MonitorEnter(monitorId));
			
		}
		
	}
	
	

	







	public static void taskMethodEnter(CallbackStatePerThread callbackStatePerThread) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			callbackStatePerThread.parallizedThread.taskMethodEnter( );
		}
		
	}











	public static void taskMethodExit(CallbackStatePerThread callbackStatePerThread) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			callbackStatePerThread.parallizedThread.taskMethodExit( );
		}
		
	}











	public static void callableFromTaskMethodEnter(CallbackStatePerThread callbackStatePerThread) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			callbackStatePerThread.parallizedThread.callableFromTaskMethodEnter( );
		}
		
	}











	public static void callableFromTaskMethodExit(CallbackStatePerThread callbackStatePerThread) {
		if( callbackStatePerThread.parallizedThread != null )
		{
			callbackStatePerThread.parallizedThread.callableFromTaskMethodExit( );
		}
		
	}
	
	
	
	
	
	
	
}
