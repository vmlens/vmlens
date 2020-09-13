package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.field.ModeStateFieldAccess;
import com.vmlens.trace.agent.bootstrap.callback.state.ArrayState;
import com.vmlens.trace.agent.bootstrap.callback.state.ArrayStateStatistic;

import gnu.trove.set.hash.TIntHashSet;

public class ArrayAccessCallback {
	private static final AnarsoftWeakHashMap<ArrayState> arrayAccessedInOtherThread = new AnarsoftWeakHashMap<ArrayState>();
	private static final Object LOCK_WEAK_HASHMAP = new Object();

	private static final Object DUMMY_OBJECT = new Object();

	public static void newArray(Object theArray) {
		CallbackStatePerThread callbackStatePerThread = (CallbackStatePerThread) CallbackState.callbackStatePerThread
				.get();

		int slidingWindowId = CallbackState.traceFields(callbackStatePerThread);

		if (!CallbackState.isSlidingWindowTrace(slidingWindowId)) {
			return;
		}

		callbackStatePerThread.arraysInThisThread.put(theArray, DUMMY_OBJECT);
	}

	public static void get(Object theArray, int index, int methodId, int position) {
		accessField(theArray, index, methodId, false, position);
	}

	public static void put(Object theArray, int index, int methodId, int position) {
		accessField(theArray, index, methodId, true, position);
	}

	private static void accessField(Object theArray, int index, int methodId, boolean isWrite, int position) {
		CallbackStatePerThread callbackStatePerThread = (CallbackStatePerThread) CallbackState.callbackStatePerThread
				.get();
		int slidingWindowId = CallbackState.traceFields(callbackStatePerThread);

		if (!CallbackState.isSlidingWindowTrace(slidingWindowId)) {
			return;
		}

		if (!callbackStatePerThread.isInInterleaveLoop())
		{
			if (callbackStatePerThread.arraysInThisThread.get(theArray) != null) {

				synchronized (LOCK_WEAK_HASHMAP) {
					if (arrayAccessedInOtherThread.get(theArray) == null) {
						return;
					}
				}

			}
		}
		
		
		
		
		

		ArrayState state = null;
		synchronized (LOCK_WEAK_HASHMAP) {
			state = arrayAccessedInOtherThread.get(theArray);

			if (state == null) {
				state = new ArrayState(ArrayState.getNewId());
				arrayAccessedInOtherThread.put(theArray, state);
			}

		}

		synchronized (state) {
		
			int arrayClassId = ModeStateFieldAccess.class2Id.getArrayClassId(theArray.getClass());
			
			if (callbackStatePerThread.mode.isInterleave()) {
				access_interleave(callbackStatePerThread, state, index, methodId, position, isWrite, arrayClassId);
			} else if (callbackStatePerThread.mode.isState()) {
				access_state(callbackStatePerThread, state, methodId, position, isWrite,arrayClassId);
			}

		}
	}

	private static void access_interleave(CallbackStatePerThread callbackStatePerThread, ArrayState state, int index,
			int methodId, int position, boolean isWrite , int arrayClassId) {
		boolean sendEvent = true;
		
		ArrayStateStatistic arrayStateStatistic = state.getArrayStateStatistic(callbackStatePerThread.threadId);
		
		
		if (isWrite) {	
			if (arrayStateStatistic.writeEventCount > 20) {
				sendEvent = false;
			} else {
				arrayStateStatistic.writeEventCount++;
			}

		} else {
			if (arrayStateStatistic.readEventCount > 20) {
				sendEvent = false;
			} else {
				arrayStateStatistic.readEventCount++;
			}
		}
		if (sendEvent) {
			
			int slidingWindowId = CallbackState.traceFields(callbackStatePerThread);
			
			writeEvent(callbackStatePerThread, callbackStatePerThread.threadId,
					slidingWindowId, index, methodId, isWrite,
					callbackStatePerThread.programCount, callbackStatePerThread.methodCount, state.id, position,arrayClassId);
		}

	}

	private static void access_state(CallbackStatePerThread callbackStatePerThread, ArrayState state, int methodId,
			int position, boolean isWrite, int arrayClassId) {
		
		ArrayStateStatistic arrayStateStatistic = state.getArrayStateStatistic(callbackStatePerThread.threadId);
		boolean sendEvent = true;
		if (isWrite) {
			if (arrayStateStatistic.writeEventCount > 20) {
				sendEvent = false;
			} else {
				arrayStateStatistic.writeEventCount++;
			}

		} else {
			if (arrayStateStatistic.readEventCount > 20) {
				sendEvent = false;
			} else {
				arrayStateStatistic.readEventCount++;
			}
		}
		if (sendEvent) {
			
			int operation = MemoryAccessType.IS_READ;
			
			if( isWrite )
			{
				operation = MemoryAccessType.IS_WRITE;
			}
			
			int slidingWindowId = CallbackState.traceFields(callbackStatePerThread);
			
//			callbackStatePerThread.sendEvent.writeStateEventArrayGen(slidingWindowId,
//					methodId, position, callbackStatePerThread.methodCount, operation, state.id,arrayClassId);
			
			
		}
		
		
		
		
		
		

	}

	private static void writeEvent(CallbackStatePerThread callbackStatePerThread, long threadId, int slidingWindowId,
			int fieldId, int methodId, boolean isWrite, int programCounter, int methodCounter, long objectId,
			int position, int arrayClassId) {
		callbackStatePerThread.sendEvent.writeArrayAccessEventGen(slidingWindowId, programCounter, fieldId,
				methodCounter, MemoryAccessType.getOperation(isWrite), methodId,
				callbackStatePerThread.isStackTraceIncomplete(), objectId, position,arrayClassId);
		/*
		 * void writeArrayAccessEventGen (int slidingWindowId , int programCounter , int
		 * fieldId , int methodCounter , boolean isWrite , int methodId , boolean
		 * stackTraceIncomplete , long objectHashCode , int position );
		 */

	}

}
