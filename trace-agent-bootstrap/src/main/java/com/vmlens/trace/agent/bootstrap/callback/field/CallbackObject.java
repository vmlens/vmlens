package com.vmlens.trace.agent.bootstrap.callback.field;

import com.vmlens.trace.agent.bootstrap.Offset2FieldId;
import com.vmlens.trace.agent.bootstrap.OffsetAndClassName;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.callback.VolatileArrayAccessCallback;
import com.vmlens.trace.agent.bootstrap.callback.getState.Class2GetStateMap;
import com.vmlens.trace.agent.bootstrap.callback.state.StateAccess;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

public class CallbackObject {

	private static final UpdateObjectState updateObjectState = new UpdateObjectState(200, 200);
	
	private static final UpdateObjectState updateBeforeObjectState = new UpdateObjectState(200, 200) {
        public void sendEventVolatile(CallbackStatePerThread callbackStatePerThread, int order,
                                      int fieldId, int methodId, int operation, long objectId) {
            callbackStatePerThread.sendEvent.writeWithoutInterleaveVolatileAccessEventGen(
                    CallbackState.traceSyncStatements(callbackStatePerThread), callbackStatePerThread.programCount,
                    order, fieldId, callbackStatePerThread.methodCount, methodId, operation, objectId);
        }

        @Override
        public void parallizeFacadeBeforeFieldAccessVolatile(long id, int fieldId, int operation,
                                                             CallbackStatePerThread callbackStatePerThread) {
        }
    };
	

	public static void non_volatile_access(Object orig, int fieldId, int methodId, boolean isWrite) {

		CallbackStatePerThread callbackStatePerThread = non_volatile_filter_and_apply_waitpoints(orig, fieldId,
				isWrite);

		if (callbackStatePerThread != null) {
			StateAccess c = Class2GetStateMap.getState(orig);

			non_volatile_access_internal(callbackStatePerThread, orig, c.obj, c.offset, fieldId, methodId,
					MemoryAccessType.getOperation(isWrite));
		}

	}

	static void non_volatile_access_generated(Object orig, long offset, int fieldId, int methodId, boolean isWrite) {

		CallbackStatePerThread callbackStatePerThread = non_volatile_filter_and_apply_waitpoints(orig, fieldId,
				isWrite);

		if (callbackStatePerThread != null) {
			non_volatile_access_internal(callbackStatePerThread, orig, orig, offset, fieldId, methodId,
					MemoryAccessType.getOperation(isWrite));
		}

	}

	private static CallbackStatePerThread non_volatile_filter_and_apply_waitpoints(Object orig, int fieldId,
			boolean isWrite) {
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

		int slidingWindowId = CallbackState.traceFields(callbackStatePerThread);

		if (!CallbackState.isSlidingWindowTrace(slidingWindowId)) {
			return null;
		}

		return callbackStatePerThread;
	}

	public static void non_volatile_access_internal(CallbackStatePerThread callbackStatePerThread, Object orig,
			Object stateHolder, long offset, int fieldId, int methodId, int operation) {

		int slidingWindowId = CallbackState.traceFields(callbackStatePerThread);

		if (slidingWindowId <= CallbackState.SLIDING_WINDOW_MUST_BE_GREATER) {
			return;
		}
        updateObjectState.nonVolatileAccess(callbackStatePerThread.isInInterleaveLoop(), stateHolder, offset,
                callbackStatePerThread.threadId, slidingWindowId, fieldId, methodId, operation,
                callbackStatePerThread);


	}

	public static void nonVolatileAccessWithOffset(Object orig, sun.misc.Unsafe unsafe, long offset, int methodId,
			int operation) {

		// direct memory, sollte ich implementieren

		if (orig == null) {

			return;
		}

		// siehe renessaince db-shootout benchmark

		if (orig.getClass() == null) {
			return;
		}

		if (orig.getClass().isArray()) {
			return;
		}

		OffsetAndClassName offsetAndClassName = new OffsetAndClassName(offset, orig.getClass().getName());
		int fieldId = Offset2FieldId.getFieldId(orig, unsafe, offsetAndClassName);
		// Object orig, int fieldId, int methodId, boolean isWrite
		non_volatile_access(orig, fieldId, methodId, MemoryAccessType.containsWrite(operation));

	}

	//// volatile access
	//// --------------------------------------------------------------

	public static void beforeVolatileAccessWithOffset(Object orig, sun.misc.Unsafe unsafe, long offset, int methodId,
			int operation) {
		if (orig == null) {
			return;
		}

		// siehe renessaince db-shootout benchmark

		if (orig.getClass() == null) {

			return;
		}

		if (orig.getClass().isArray()) {
			VolatileArrayAccessCallback.access(offset, orig, methodId, operation);
			

		
		} else {
			OffsetAndClassName offsetAndClassName = new OffsetAndClassName(offset, orig.getClass().getName());
			int fieldId = Offset2FieldId.getFieldId(orig, unsafe, offsetAndClassName);

			before_volatile_access(orig, fieldId, methodId, operation);
		

		
		}

	}

	public static void volatileAccessWithOffset(Object orig, sun.misc.Unsafe unsafe, long offset, int methodId,
			int operation) {

		if (orig == null) {

			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

			int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);

			if (!CallbackState.isSlidingWindowTrace(slidingWindowId)) {
				return;
			}

			// Direct Memory Access
			CallbackDirectMemoryAccess.volatile_access(offset, callbackStatePerThread, operation, slidingWindowId);
			return;
		}

		// siehe renessaince db-shootout benchmark

		if (orig.getClass() == null) {

			return;
		}

		if (orig.getClass().isArray()) {
			VolatileArrayAccessCallback.access(offset, orig, methodId, operation);

            ParallelizeFacade.afterVolatileArrayAccess4UnsafeOrVarHandle(CallbackState.callbackStatePerThread.get(),
                    offset, operation);
		} else {
			OffsetAndClassName offsetAndClassName = new OffsetAndClassName(offset, orig.getClass().getName());
			int fieldId = Offset2FieldId.getFieldId(orig, unsafe, offsetAndClassName);
            volatile_access(orig, fieldId, methodId, operation);
            ParallelizeFacade.afterFieldAccess4UnsafeOrVarHandle(CallbackState.callbackStatePerThread.get(), fieldId, operation);
		}

	}

	static void volatile_access_generated(Object orig, long offset, int fieldId, int methodId, int operation) {
		CallbackStatePerThread callbackStatePerThread = volatile_filter_and_apply_waitpoints(orig, fieldId);
		if (callbackStatePerThread != null) {
			volatile_access_internal(callbackStatePerThread, orig, orig, offset, fieldId, methodId, operation);
		}
	}

	public static void volatile_access(Object orig, int fieldId, int methodId, int operation) {

		CallbackStatePerThread callbackStatePerThread = volatile_filter_and_apply_waitpoints(orig, fieldId);
		if (callbackStatePerThread != null) {
			/**
			 * Diese Methode wird sowohl direkt als auch durch unsafe aufgerufen, daher
			 * getState und nicht getStateFromMap
			 * 
			 */
			StateAccess state = Class2GetStateMap.getState(orig);
			volatile_access_internal(callbackStatePerThread, orig, state.obj, state.offset, fieldId, methodId,
					operation);
		}
	}

	public static void before_volatile_access(Object orig, int fieldId, int methodId, int operation) {

		CallbackStatePerThread callbackStatePerThread = volatile_filter_and_apply_waitpoints(orig, fieldId);

		if (callbackStatePerThread != null) {
			/**
			 * Diese Methode wird sowohl direkt als auch durch unsafe aufgerufen, daher
			 * getState und nicht getStateFromMap
			 * 
			 */

			StateAccess state = Class2GetStateMap.getState(orig);

			before_volatile_access_internal(callbackStatePerThread, orig, state.obj, state.offset, fieldId, methodId,
					operation);
		}
	}


	private static CallbackStatePerThread volatile_filter_and_apply_waitpoints(Object orig, int fieldId) {
		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
		int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);
		if (!CallbackState.isSlidingWindowTrace(slidingWindowId)) {
			return null;
		}
		return callbackStatePerThread;
	}

	private static void before_volatile_access_internal(CallbackStatePerThread callbackStatePerThread, Object orig,
			Object stateHolder, long offset, int fieldId, int methodId, int operation) {
        callbackStatePerThread.programCount += 1;
        updateBeforeObjectState.volatileAccess(stateHolder, offset, fieldId, methodId, operation, callbackStatePerThread);
        callbackStatePerThread.programCount += 1;
    }

    private static void volatile_access_internal(CallbackStatePerThread callbackStatePerThread, Object orig,
			Object stateHolder, long offset, int fieldId, int methodId, int operation) {
        callbackStatePerThread.programCount += 1;
        updateObjectState.volatileAccess(stateHolder, offset, fieldId, methodId, operation, callbackStatePerThread);
        callbackStatePerThread.programCount += 1;
    }

}
