package com.vmlens.trace.agent.bootstrap.callback.obj;

import com.vmlens.trace.agent.bootstrap.callback.AnarsoftWeakHashMap;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.callback.field.CallbackObject;
import com.vmlens.trace.agent.bootstrap.callback.state.StateAccess;
import com.vmlens.trace.agent.bootstrap.callback.state.StateHolder;

public class ObjectCallbackState {
	private final int fieldId;
    private final AnarsoftWeakHashMap<StateAccess> object2State = new AnarsoftWeakHashMap<StateAccess>();
	private final Object LOCK = new Object();

	public ObjectCallbackState(int fieldId) {
		super();
		this.fieldId = fieldId;
	}

    public void access(Object obj, int operation, int methodId) {
        CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
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
