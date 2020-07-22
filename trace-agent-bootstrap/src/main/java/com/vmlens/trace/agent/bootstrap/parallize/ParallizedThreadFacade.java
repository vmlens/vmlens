package com.vmlens.trace.agent.bootstrap.parallize;


import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.operation.AtomicMethodEnter;
import com.vmlens.trace.agent.bootstrap.interleave.operation.AtomicMethodExit;
import com.vmlens.trace.agent.bootstrap.interleave.operation.CallBackMethodCall;
import com.vmlens.trace.agent.bootstrap.interleave.operation.MonitorExit;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.interleave.operation.Task;
import com.vmlens.trace.agent.bootstrap.interleave.operation.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.interleave.operation.VolatileArrayAccess;
import com.vmlens.trace.agent.bootstrap.interleave.operation.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunEntity;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.util.Constants;


public class ParallizedThreadFacade {

	private OperationTyp currentAccess;
	
	private final CallbackStatePerThread callbackStatePerThread;
	final RunEntity runEntity;
	
	
	int startedCount = 0;
	
	
	private int atomicCount;
	private int callbackCount;
	
	public ParallizedThreadFacade(CallbackStatePerThread callbackStatePerThread, RunEntity runEntity) {
		super();
		this.callbackStatePerThread = callbackStatePerThread;
		this.runEntity = runEntity;
	}

	public void afterFieldAccess4UnsafeOrVarHandle(int fieldId, int operation) {
		
		currentAccess= null;
		
		// kann bei Unsafe calls auftreten
		if (fieldId == -1) {
			return;
		}
		
		if( execOp() )
		{
			runEntity.after(callbackStatePerThread, new VolatileFieldAccess(fieldId,operation));
		}
		
	
	}

	public void afterVolatileArrayAccess4UnsafeOrVarHandle(long index, int operation) {

		currentAccess = null;

		
		
		if( execOp() )
		{
			runEntity.after(callbackStatePerThread, new VolatileArrayAccess(index,operation));
		}
		
		
		

	}
	
	

	public void beforeMonitorExit(int monitorId, int methodId,int position) {
		currentAccess =  new MonitorExit(methodId,false,position,monitorId);
		
		runEntity.onLock(callbackStatePerThread,  new com.vmlens.trace.agent.bootstrap.interleave.lock.MonitorExit(monitorId));
		
	}

	public void beforeMonitorExitStatic(int monitorId, int methodId) {
		currentAccess =  new MonitorExit(methodId,true,0,monitorId);
		
		
		runEntity.onLock(callbackStatePerThread,  new com.vmlens.trace.agent.bootstrap.interleave.lock.MonitorExit(monitorId * -1));
	}

//	public void beforeLockRelease(Object objectKey, boolean isShared) {
//		currentAccess =  new LockExit(isShared);
//	}

	public void afterMonitor() {

	
		
		if( execOp() )
		{
			runEntity.after(callbackStatePerThread,currentAccess);
		}
		
		

		currentAccess = null;
		//lastOperation = null;

	}

	public void afterMethod() {

		if (currentAccess != null) {
			
			if( execOp() )
			{
				runEntity.after(callbackStatePerThread, currentAccess);
			}
			
			
		
		}

		currentAccess = null;

	}


	 void afterThreadStart() {
	
		 currentAccess = null;
		 runEntity.afterThreadStart(callbackStatePerThread);
	}

	public void beforeStart(CallbackStatePerThread callbackStatePerThread2,
			RunnableOrThreadWrapper runnableOrThreadWrapper) {
		currentAccess = null;
		 runEntity.beforeStart(callbackStatePerThread2 , runnableOrThreadWrapper);
		
	}

	void beginThreadMethodExit(CallbackStatePerThread callbackStatePerThread2) {
	

		
		
		boolean setToNull = runEntity.endThread(callbackStatePerThread);
		
			
		
		if(setToNull)
		{
		
			callbackStatePerThread.parallizedThread = null;
		}
	}

	//  
	
	public boolean sendAsInterleaveEvent(Class cl) {
	
		
		return runEntity.sendAsInterleaveEvent(callbackStatePerThread.doNotInterleave , callbackStatePerThread.doNotInterleaveFromLock  , cl);
		
//		if(runEntity.showAllIntelreavings())
//		{
//			return  true;
//		}
//		else
//		{
//			return runEntity.isMultiThreaded() && callbackStatePerThread.doNotInterleave <= 0;
//		}
		
		
	}

	public int loopId() {
	
		return runEntity.loopId();
	}

	public int runId() {
	
		return runEntity.runId();
	}

	public int runPosition() {
	
		return runEntity.nextPosition();
	}

	public boolean isInInterleaveLoop() {
	
		return runEntity.isInInterleaveLoop();
	}






private boolean execOp()
{
	return atomicCount < 1 || callbackCount > 0;
}




 private boolean execAtomic()
 {
	 return   isInInterleaveLoop() && callbackStatePerThread.stackTraceBasedDoNotTrace < 1;
 }
 
 private boolean execAtomic(int atomicId)
 {
	 if(  !  execAtomic() )
	 {
		 return false;
	 }
	 
	 
	 return runEntity.isAtomicActivated(atomicId);
	 
	 
	 
 }
 


	 void onAtomicMethodEnter(int methodId,int atomicId,boolean hasCallback) {
		if(  ! execAtomic(atomicId) )
		{
			return;
		}
		
		
		 if( execOp())
		 {
			 byte hasCallbackFlag = Constants.FALSE;
			 
			 if(hasCallback)
			 {
				 hasCallbackFlag = Constants.TRUE;
			 }
			 
			 
			 
			
			 if(sendAsInterleaveEvent(Object.class)  )
			 {
				 int slidingWindowId =  CallbackState.slidingWindow;   //  loopId, runId, runPosition
				  callbackStatePerThread.sendEvent.writeMethodAtomicEnterEventGen(slidingWindowId, methodId , callbackStatePerThread.methodCount , hasCallbackFlag , loopId() ,  runId()  ,   runPosition());
			 }
			 
			
	
			  afterAtomicMethodEnter();
		 }
	
			
		  
		  atomicCount++; 
	}


	 void onAtomicMethodExit(int methodId,int atomicId, boolean hasCallback) {
		 if(  ! execAtomic(atomicId) )
			{
				return;
			}
		 
		 atomicCount--;
	
		 if( execOp())
		 {
			 
			 
			 byte hasCallbackFlag = Constants.FALSE;
			 
			 if(hasCallback)
			 {
				 hasCallbackFlag = Constants.TRUE;
			 }
			 if(sendAsInterleaveEvent(Object.class)  )
			 {	 
		 int slidingWindowId =  CallbackState.slidingWindow;   //  loopId, runId, runPosition
		  callbackStatePerThread.sendEvent.writeMethodAtomicExitEventGen(slidingWindowId, methodId , callbackStatePerThread.methodCount , hasCallbackFlag , loopId() ,  runId()  ,   runPosition());
			 }
		 
		 afterAtomicMethodExit( methodId , atomicId , hasCallback);
		 }
		 
	}

	 

	 void beginTask(int id) {
			runEntity.after(callbackStatePerThread, new Task(id));
			
		}




	 void callbackMethodEnter(int atomicId) {
		 if(  ! execAtomic() )
			{
				return;
			}
		
		 if(atomicCount > 0)
		 {
			 callbackCount++;
		
			 if(sendAsInterleaveEvent(Object.class)  )
			 {
		 int slidingWindowId =  CallbackState.slidingWindow;
		  callbackStatePerThread.sendEvent.writeMethodCallbackEnterEventGen(slidingWindowId , callbackStatePerThread.methodCount ,  loopId() ,  runId()  ,   runPosition());
			 }
		 
		 afterCallbackMethodEnter(atomicId);
	
		 }
	}



	 void callbackMethodExit() {
		 if(  ! execAtomic()  )
			{
				return;
			}
		 
		 if(atomicCount > 0)
		 {
			 callbackCount--;
	
			 if(sendAsInterleaveEvent(Object.class)  )
			 {
		 int slidingWindowId =  CallbackState.slidingWindow;
		  callbackStatePerThread.sendEvent.writeMethodCallbackExitEventGen(slidingWindowId , callbackStatePerThread.methodCount ,  loopId() ,  runId()  ,   runPosition());
			 }
		  
		 }
	}
	 
	 
	 
	 private void afterAtomicMethodEnter() {
			
			
			currentAccess =null;
			runEntity.after(callbackStatePerThread, new AtomicMethodEnter());
			
			
		}
	 
	 
	 
		private void afterAtomicMethodExit(int methodId,int atomicId,boolean hasCallback) {
			
			
			currentAccess =null;
			runEntity.after(callbackStatePerThread, new AtomicMethodExit(methodId,atomicId,hasCallback));
			
			
		}

		private void afterCallbackMethodEnter(int atomicId) {
			currentAccess =null;
			//runEntity.after(callbackStatePerThread, op); CallBackMethodCall
			runEntity.after(callbackStatePerThread, new CallBackMethodCall(atomicId));
		
		
		}

		
		void afterLockOperation(OperationTyp operation)
		{
			currentAccess =null;
			if( execOp() )
			{			
			runEntity.after(callbackStatePerThread, operation);
			}
		}
		
		
		
		
		
		void before(OperationTyp actualObject) {
			currentAccess = actualObject;
			
		}

	 
	 
		public void afterFieldAccess(int fieldId, int operation) {

			// kann bei Unsafe calls auftreten
			if (fieldId == -1) {
				return;
			}

			
			
			
			if( execOp() )
			{
				if( currentAccess != null )
				{
					runEntity.after(callbackStatePerThread, currentAccess);
				}
				
			
			}
			
			currentAccess =null;
			

		}

		public void afterThreadJoin(long toBeJoined) {
			
			currentAccess =null;
			
			ThreadJoin threadJoin = new ThreadJoin(callbackStatePerThread.threadId , toBeJoined);
			
			
			runEntity.after(callbackStatePerThread,threadJoin);
			
		}

		

		public void onLock(LockOperation monitorEnter) {
			
			runEntity.onLock(callbackStatePerThread,  monitorEnter);
		}

		public void taskMethodEnter() {
			 if(  ! execAtomic() )
				{
					return;
				}
			 
			 atomicCount++;
			
		}

		public void taskMethodExit() {
			 if(  ! execAtomic() )
				{
					return;
				}
			 
			 atomicCount--;
			
		}

		public void callableFromTaskMethodEnter() {
			 if(  ! execAtomic()  )
				{
					return;
				}
			 
			 if(atomicCount > 0)
			 {
				 callbackCount++;
		
			 }	 
			
		}

		public void callableFromTaskMethodExit() {
			 if(  ! execAtomic()  )
				{
					return;
				}
			 
			 if(atomicCount > 0)
			 {
				 callbackCount--;
		
			 }	 
		}

		public boolean showNonVolatileMemoryAccess() {
			return runEntity.allInterleavings.showNonVolatileSharedMemoryAccess;
		}

		public void beforeThreadJoin(long joinWithThreadId) {
			runEntity.beforeThreadJoin(callbackStatePerThread,joinWithThreadId);
			
		}


	
		 
	 
	 
	 
	 

}
