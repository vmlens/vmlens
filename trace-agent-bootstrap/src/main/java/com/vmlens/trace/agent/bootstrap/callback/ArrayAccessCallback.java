package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.field.ModeStateFieldAccess;
import com.vmlens.trace.agent.bootstrap.callback.state.ArrayState;

public class ArrayAccessCallback {
	private static final AnarsoftWeakHashMap<ArrayState> arrayAccessedInOtherThread = new AnarsoftWeakHashMap<ArrayState>();
	private static final Object LOCK_WEAK_HASHMAP = new Object();

	private static final Object DUMMY_OBJECT = new Object();

	public static void newArray(Object theArray) {
        CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        // Fixme Callback
        // we could filter threads which can not be tested (finalizer...)
        callbackStatePerThread.arraysInThisThread.put(theArray, DUMMY_OBJECT);
    }

	public static void get(Object theArray, int index, int methodId, int position) {
		accessField(theArray, index, methodId, false, position);
	}

	public static void put(Object theArray, int index, int methodId, int position) {
		accessField(theArray, index, methodId, true, position);
	}

	private static void accessField(Object theArray, int index, int methodId, boolean isWrite, int position) {
        CallbackStatePerThreadForParallelize callbackStatePerThread = (CallbackStatePerThreadForParallelize) CallbackState.callbackStatePerThread
                .get();
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
            access_interleave(callbackStatePerThread, state, index, methodId, position, isWrite, arrayClassId);
        }
    }

    private static void access_interleave(CallbackStatePerThreadForParallelize callbackStatePerThread, ArrayState state, int index,
                                          int methodId, int position, boolean isWrite, int arrayClassId) {
        // Fixme Callback
		/* writeEvent(callbackStatePerThread, callbackStatePerThread.threadId,
					slidingWindowId, index, methodId, isWrite,
					callbackStatePerThread.programCount, callbackStatePerThread.methodCount, state.id, position,arrayClassId);
		*/

    }

    private static void access_state(CallbackStatePerThreadForParallelize callbackStatePerThread, ArrayState state, int methodId,
                                     int position, boolean isWrite, int arrayClassId) {
        // Fixme Callback
//			callbackStatePerThread.sendEvent.writeStateEventArrayGen(slidingWindowId,
//					methodId, position, callbackStatePerThread.methodCount, operation, state.id,arrayClassId);
    }

}
