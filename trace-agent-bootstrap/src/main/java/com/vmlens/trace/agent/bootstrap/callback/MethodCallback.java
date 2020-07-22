package com.vmlens.trace.agent.bootstrap.callback;

import java.lang.reflect.Field;

import com.vmlens.trace.agent.bootstrap.AtomicClassRepo;
import com.vmlens.trace.agent.bootstrap.event.StackTraceEvent;
import com.vmlens.trace.agent.bootstrap.event.ThreadNameEvent;
import com.vmlens.trace.agent.bootstrap.interleave.operation.LockEnterOrExit;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeSingelton;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

import gnu.trove.map.hash.TIntObjectHashMap;


public class MethodCallback {

	public static void sendStackTraceEventIfNeccesary(final CallbackStatePerThread callbackStatePerThread,
			int slidingWindowId) {
		if (!callbackStatePerThread.methodTracingStarted) {

			final StackTraceElement[] stackTraceArray = Thread.currentThread().getStackTrace();

			/**
			 * java.lang.Thread getStackTrace Thread.java 105
			 * java.anarsoft.trace.agent.bootstrap.callback.MethodCallback
			 * sendStackTraceEventIfNeccesary MethodCallback.java 105
			 * java.anarsoft.trace.agent.bootstrap.callback.MethodCallback methodEnter
			 * MethodCallback.java 105 java.lang.Class getName Class.java 105
			 * com.anarsoft.trace.agent.runtime.AgentRuntimeImpl retransform
			 * AgentRuntimeImpl.java 105 com.anarsoft.trace.agent.runtime.AgentRuntimeImpl
			 * instrument AgentRuntimeImpl.java 105
			 */

			if (stackTraceArray.length > StackTraceEvent.MIN_LENGTH) {
				callbackStatePerThread.queueCollection.putDirect(

						new StackTraceEvent(callbackStatePerThread.threadId, stackTraceArray));
			}

			callbackStatePerThread.methodTracingStarted = true;

		}
	}

	public static void methodEnterOwner(int methodId) {

		try {

			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

			if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
				sendStackTraceEventIfNeccesary(callbackStatePerThread, slidingWindowId);

				// callbackStatePerThread.parallizedMethodCount++; // macht dases auf jeden fall
				// getraced wird
				callbackStatePerThread.stackTraceDepth++;

				callbackStatePerThread.methodCount++;
				callbackStatePerThread.sendEvent.writeParallizedMethodEnterEventGen(slidingWindowId, methodId,
						callbackStatePerThread.methodCount, 0);

			}

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void methodExitOwner(int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

			if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

				sendStackTraceEventIfNeccesary(callbackStatePerThread, slidingWindowId);

				callbackStatePerThread.methodCount++;

				callbackStatePerThread.sendEvent.writeParallizedMethodExitEventGen(slidingWindowId, methodId,
						callbackStatePerThread.methodCount);

				callbackStatePerThread.stackTraceDepth--;

			}

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void atomicMethodEnterWithCallback(int atomicId, int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			
				ParallizeFacade.onAtomicMethodEnter(callbackStatePerThread, methodId, true,atomicId);
				

			

			methodEnterInternal(methodId, callbackStatePerThread);

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void atomicMethodEnterWithoutCallback(int atomicId, int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

		
				ParallizeFacade.onAtomicMethodEnter(callbackStatePerThread, methodId, false,atomicId);
				

			

			methodEnterInternal(methodId, callbackStatePerThread);
		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void atomicMethodExitWithCallback(int atomicId, int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

		
			 ParallizeFacade.onAtomicMethodExit(callbackStatePerThread, methodId, atomicId, true);
			

			methodExitInternal(methodId, callbackStatePerThread);

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void atomicMethodExitWithoutCallback(int atomicId, int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			
				ParallizeFacade.onAtomicMethodExit(callbackStatePerThread, methodId, atomicId, false);
			

			methodExitInternal(methodId, callbackStatePerThread);

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void methodEnterThreadRun(int methodId) {
		/**
		 * nur wenn nicht instanceof worker thread
		 * 
		 */

		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			callbackStatePerThread.stackTraceBasedDoNotTrace++;

			Thread thread = Thread.currentThread();
			ParallizeSingelton.beginThreadMethodEnter(callbackStatePerThread, new RunnableOrThreadWrapper(thread));

			methodEnterInternal(methodId, callbackStatePerThread);

			callbackStatePerThread.stackTraceBasedDoNotTrace--;

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void methodExitThreadRun(int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			callbackStatePerThread.stackTraceBasedDoNotTrace++;

			Thread thread = Thread.currentThread();

			ParallizeFacade.beginThreadMethodExit(callbackStatePerThread);

			methodExitInternal(methodId, callbackStatePerThread);

			callbackStatePerThread.stackTraceBasedDoNotTrace--;

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	public static void methodEnter(int methodId) {

		
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			methodEnterInternal(methodId, callbackStatePerThread);
			
		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void methodExit(int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			methodExitInternal(methodId, callbackStatePerThread);

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private static void methodEnterInternal(int methodId, CallbackStatePerThread callbackStatePerThread) {

		int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

		if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

			sendStackTraceEventIfNeccesary(callbackStatePerThread, slidingWindowId);

			callbackStatePerThread.stackTraceDepth++;

			if (callbackStatePerThread.traceMethodCall()) {

				callbackStatePerThread.methodCount++;
				callbackStatePerThread.sendEvent.writeMethodEnterEventGen(slidingWindowId, methodId,
						callbackStatePerThread.methodCount);

			}
		}
	}

	private static void methodExitInternal(int methodId, CallbackStatePerThread callbackStatePerThread) {
		int slidingWindowId = CallbackState.traceMethods(callbackStatePerThread);

		if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

			sendStackTraceEventIfNeccesary(callbackStatePerThread, slidingWindowId);

			if (callbackStatePerThread.traceMethodCall()) {

				callbackStatePerThread.methodCount++;

				callbackStatePerThread.sendEvent.writeMethodExitEventGen(slidingWindowId, methodId,
						callbackStatePerThread.methodCount);

			}

			callbackStatePerThread.stackTraceDepth--;

		}
	}

	
	
	public static void doNotInterleaveEnter()
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		callbackStatePerThread.doNotInterleave++;
		
	
	}
	
	
	
	public static void doNotInterleaveExit()
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		callbackStatePerThread.doNotInterleave--;
		
	
	}
	
	
	
	public static void taskMethodEnter()
	{
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			ParallizeFacade.taskMethodEnter(callbackStatePerThread);
		

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public static void taskMethodExit()
	{
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			ParallizeFacade.taskMethodExit(callbackStatePerThread);
		

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	
	private static int threadCount = 0;
	private static final Object PARALLIZE_LOCK = new Object();
	
	
	public static void parallelizeEnter()
	{
		synchronized(PARALLIZE_LOCK)
		{
			threadCount++;
			
			
			if( threadCount > 1 )
			{
				PARALLIZE_LOCK.notifyAll();
				return;
			}
			
			
			
			try {
				PARALLIZE_LOCK.wait(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static void semaphoreAcquireExit()
	{
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();	
		 ParallizeFacade.afterLockOperation(callbackStatePerThread, new LockEnterOrExit(true));
	}
	
	

}
