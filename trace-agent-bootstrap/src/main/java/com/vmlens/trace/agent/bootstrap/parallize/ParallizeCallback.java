package com.vmlens.trace.agent.bootstrap.parallize;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

public class ParallizeCallback {

	public static void beforeThreadJoin(Thread toBeJoined)
	{
		
		AgentLogCallback.log("beforeThreadJoin");
		
		try {

			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			if (callbackStatePerThread.parallizedThread != null) {
				callbackStatePerThread.parallizedThread.beforeThreadJoin(toBeJoined.getId());
			}

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	
	/**
	 * 
	 * 
	 * @param toBeJoined
	 * @param millis
	 */

	public static void afterThreadJoin(Thread toBeJoined, long millis) {

		try {

			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			if (callbackStatePerThread.parallizedThread != null) {
				callbackStatePerThread.parallizedThread.afterThreadJoin(toBeJoined.getId());
			}

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void callbackMethodEnter(int atomicId) {

		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			if (callbackStatePerThread.parallizedThread != null) {
				callbackStatePerThread.parallizedThread.callbackMethodEnter(atomicId);
			}
		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void callbackMethodExit() {

		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			if (callbackStatePerThread.parallizedThread != null) {
				callbackStatePerThread.parallizedThread.callbackMethodExit();
			}
		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void afterFieldAccess(int fieldId, int operation) {

		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			if (callbackStatePerThread.parallizedThread != null) {
				callbackStatePerThread.parallizedThread.afterFieldAccess(fieldId, operation);
			}
		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void afterMonitor() {

		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			if (callbackStatePerThread.parallizedThread != null) {
				callbackStatePerThread.parallizedThread.afterMonitor();
			}
		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void afterMethod() {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			if (callbackStatePerThread.parallizedThread != null) {
				callbackStatePerThread.parallizedThread.afterMethod();
			}
		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void afterThreadStart() {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			if (callbackStatePerThread.inThreadStart == 0) {
				if (callbackStatePerThread.parallizedThread != null) {
					callbackStatePerThread.parallizedThread.afterThreadStart();
				}
			}
		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	
	public static void beginTask(int id)
	{
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
				if (callbackStatePerThread.parallizedThread != null) {
					callbackStatePerThread.parallizedThread.beginTask(id);
				}
		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	
	

}
