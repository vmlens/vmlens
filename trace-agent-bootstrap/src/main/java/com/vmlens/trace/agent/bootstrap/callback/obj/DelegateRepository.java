package com.vmlens.trace.agent.bootstrap.callback.obj;

import com.vmlens.trace.agent.bootstrap.callback.AnarsoftWeakHashMap;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;


public class DelegateRepository {
    private static final AnarsoftWeakHashMap<DelegateTarget> delegate2Target = new AnarsoftWeakHashMap<DelegateTarget>();
    private static final Object LOCK = new Object();

    static void put4DelegateTarget(Object delegate, DelegateTarget delegateTarget) {
        synchronized (LOCK) {
            delegate2Target.put(delegate, delegateTarget);
        }
    }

    static void put(Object delegate, Object target, ObjectCallbackState objectCallbackState) {
        synchronized (LOCK) {
            delegate2Target.put(delegate, new DelegateTarget(objectCallbackState, target));
        }
    }

    static DelegateTarget getDelegate(Object delegate) {
        synchronized (LOCK) {
            return delegate2Target.get(delegate);
        }
    }


    public static void access(Object delegate, int operation, int methodId) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        int slidingWindowId = CallbackState.traceFields(callbackStatePerThread);
        if (!CallbackState.isSlidingWindowTrace(slidingWindowId)) {
            return;
        }
        DelegateTarget delegateTarget = null;
        synchronized (LOCK) {
            delegateTarget = delegate2Target.get(delegate);
        }
        if (delegateTarget != null) {
            delegateTarget.access(operation, methodId, callbackStatePerThread, slidingWindowId);
        }
	}
}
