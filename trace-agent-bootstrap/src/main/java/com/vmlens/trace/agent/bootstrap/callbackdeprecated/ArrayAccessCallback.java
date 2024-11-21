package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.ModeStateFieldAccess;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.state.ArrayState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class ArrayAccessCallback {
	private static final AnarsoftWeakHashMap<ArrayState> arrayAccessedInOtherThread = new AnarsoftWeakHashMap<ArrayState>();
	private static final Object LOCK_WEAK_HASHMAP = new Object();

	private static final Object DUMMY_OBJECT = new Object();

	public static void newArray(Object theArray) {
        ThreadLocalForParallelize callbackStatePerThread = ThreadLocalWhenInTestAdapterImpl.callbackStatePerThread.get();
        // Fixme Callback
        // we could filter threads which can not be tested (finalizer...)
        //callbackStatePerThread.arraysInThisThread.put(theArray, DUMMY_OBJECT);
    }

	public static void get(Object theArray, int index, int methodId, int position) {
		accessField(theArray, index, methodId, false, position);
	}

	public static void put(Object theArray, int index, int methodId, int position) {
		accessField(theArray, index, methodId, true, position);
	}

	private static void accessField(Object theArray, int index, int methodId, boolean isWrite, int position) {
        ThreadLocalForParallelize callbackStatePerThread = (ThreadLocalForParallelize) ThreadLocalWhenInTestAdapterImpl.callbackStatePerThread
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

    private static void access_interleave(ThreadLocalForParallelize callbackStatePerThread, ArrayState state, int index,
                                          int methodId, int position, boolean isWrite, int arrayClassId) {
        // Fixme Callback
		/* writeEvent(callbackStatePerThread, callbackStatePerThread.threadId,
					slidingWindowId, index, methodId, isWrite,
					callbackStatePerThread.programCount, callbackStatePerThread.methodCount, state.id, position,arrayClassId);
		*/

    }

    private static void access_state(ThreadLocalForParallelize callbackStatePerThread, ArrayState state, int methodId,
                                     int position, boolean isWrite, int arrayClassId) {
        // Fixme Callback
//			callbackStatePerThread.sendEvent.writeStateEventArrayGen(slidingWindowId,
//					methodId, position, callbackStatePerThread.methodCount, operation, state.id,arrayClassId);
    }

}
