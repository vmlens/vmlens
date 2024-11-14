package com.vmlens.trace.agent.bootstrap.callbackdeprecated.obj;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ParallelizeBridgeForCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.AnarsoftWeakHashMap;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.CallbackObject;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.state.StateAccess;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.state.StateHolder;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class ObjectCallbackState {
	private final int fieldId;
    private final AnarsoftWeakHashMap<StateAccess> object2State = new AnarsoftWeakHashMap<StateAccess>();
	private final Object LOCK = new Object();

	public ObjectCallbackState(int fieldId) {
		super();
		this.fieldId = fieldId;
	}

    public void access(Object obj, int operation, int methodId) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
        StateAccess objectState = null;
        synchronized (LOCK) {
            objectState = object2State.get(obj);
            if (objectState == null) {
                objectState = StateHolder.createAccess();
                object2State.put(obj, objectState);
            }

        }
		CallbackObject.non_volatile_access_internal(callbackStatePerThread, obj,objectState.obj , objectState.offset , fieldId, methodId , operation );
	}
}
