package com.vmlens.trace.agent.bootstrap.parallize;

import java.util.Iterator;
import java.util.Map.Entry;

import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.event.WhileLoopNameEvent;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunEntity;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallize.logic.WhileLoop;
import com.vmlens.trace.agent.bootstrap.parallize.logic.WhileLoopActive;
import com.vmlens.trace.agent.bootstrap.parallize.logic.WhileLoopStopped;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.BeginNewThreadResult;

import gnu.trove.map.hash.THashMap;

public class ParallizeSingelton {

	public static void printAllStackTraces() {
		System.err.println(
				"--------------------------------------------------------------------------------------------");

		Iterator<Entry<Thread, StackTraceElement[]>> iter = Thread.getAllStackTraces().entrySet().iterator();

		while (iter.hasNext()) {
			Entry<Thread, StackTraceElement[]> current = iter.next();

			System.err.println(current.getKey().getName());

			for (StackTraceElement elem : current.getValue()) {
				System.err.println(elem);
			}

		}

	}

	public static boolean MULTIPLE_DECISIONS_AT_THREAD_END = true;
	public static boolean MULTIPLE_DECISIONS_AT_WAKE_UP = true;

	private static final THashMap<Object, WhileLoop> object2InterleaveMultipleRuns = new THashMap<Object, WhileLoop>();
	private static WhileLoop currentLoop = null;
	private static RunEntity currentRun = null;
	private static int loopId;

	private static boolean isInAdvance = false;

	public static final Object SINGLE_LOCK = new Object();

	public static boolean hasNext(CallbackStatePerThread callbackStatePerThread, Object obj) {
		callbackStatePerThread.stackTraceBasedDoNotTrace++;
		try {

			CallbackState.clearCallbackStatePerThreadRecovery();
			isInAdvance = true;
			synchronized (SINGLE_LOCK) {

				boolean result = hasNextInternal(callbackStatePerThread, obj);
				isInAdvance = false;
				return result;

			}
		} finally {
			callbackStatePerThread.stackTraceBasedDoNotTrace--;
		}
	}

	private static WhileLoop getOrCreateWhileLoop(Object obj) {
		
		
		
		AllInterleavings allInterleavings = (AllInterleavings) obj;
		
		WhileLoop loop = object2InterleaveMultipleRuns.get(allInterleavings);

		if (loop == null) {
			int currentId = loopId++;
			loop = new WhileLoopActive(currentId,allInterleavings);
			object2InterleaveMultipleRuns.put(allInterleavings, loop);
		}

		return loop;
	}

	private static boolean hasNextInternal(CallbackStatePerThread callbackStatePerThread, Object obj) {

		currentLoop = getOrCreateWhileLoop(obj);

		if (currentRun != null) {
			currentRun.stop(callbackStatePerThread,currentLoop);
		}

		currentRun = currentLoop.createNextRun(callbackStatePerThread, Thread.currentThread());

		if (currentRun != null) {

			callbackStatePerThread.parallizedThread = new ParallizedThreadFacade(callbackStatePerThread, currentRun);

			return true;
		} else {
			callbackStatePerThread.parallizedThread = null;
			currentLoop = new WhileLoopStopped(currentLoop.loopId());
			object2InterleaveMultipleRuns.put(obj, currentLoop);

			return false;
		}

	}

	public static boolean beginThreadMethodEnter(CallbackStatePerThread callbackStatePerThread,
			RunnableOrThreadWrapper beganTask) {

		if (isInAdvance) {
			return false;
		}
		try {
		callbackStatePerThread.stackTraceBasedDoNotTrace++;
		synchronized (SINGLE_LOCK) {
			if (currentRun != null) {

				if (currentRun.beginNewThread(callbackStatePerThread.threadId, Thread.currentThread(),
						beganTask) == BeginNewThreadResult.NEW_THREAD) {					
					if (ParallizeFacade.ENABLE_LOGGING) {
						AgentLogCallback.log("new thread " + callbackStatePerThread.threadId + " prevoius "
								+ callbackStatePerThread.parallizedThread);
					}
					
					if(  callbackStatePerThread.parallizedThread != null && callbackStatePerThread.parallizedThread.runEntity == currentRun )
					{
						//callbackStatePerThread.parallizedThread.startedCount++;
						return false;
					}
					else
					{
						callbackStatePerThread.parallizedThread = new ParallizedThreadFacade(callbackStatePerThread,
								currentRun);
						return true;
					}
					

				
				}
			}
		}
		
		return false;
		}
		finally {
			callbackStatePerThread.stackTraceBasedDoNotTrace--;
		}
		
	}

	public static void close(CallbackStatePerThread callbackStatePerThread, Object obj) {
		
		callbackStatePerThread.stackTraceBasedDoNotTrace++;
		try {
			synchronized (SINGLE_LOCK) {
				WhileLoop temp = object2InterleaveMultipleRuns.get(obj);
				if (temp != null) {
					
					callbackStatePerThread.parallizedThread = null;
					currentLoop = new WhileLoopStopped(currentLoop.loopId());
					object2InterleaveMultipleRuns.put(obj, currentLoop);
				}

			}
		} finally {
			callbackStatePerThread.stackTraceBasedDoNotTrace--;
		}

	}

	

}
