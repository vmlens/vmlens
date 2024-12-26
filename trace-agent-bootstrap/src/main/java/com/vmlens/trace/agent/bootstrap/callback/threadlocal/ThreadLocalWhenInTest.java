package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import gnu.trove.list.linked.TLinkedList;

/**
 * Data Structure, only state no behaviour
 *
 */
public class ThreadLocalWhenInTest extends PerThreadCounter implements ThreadLocalWhenInTestForParallelize {
    private final RunAdapter runAdapter;
    private final int threadIndex;

    private boolean inCallbackProcessing = false;
    private CallbackAction atomicVolatileFieldAccess;
    private ThreadStartEvent threadStartEvent;

    public ThreadLocalWhenInTest(Run run, int threadIndex) {
        this.runAdapter = new RunAdapter(run);
        this.threadIndex = threadIndex;
    }

    // Can be null when the runtime event should not be serialized
    public TLinkedList<TLinkableWrapper<SerializableEvent>> after(RuntimeEvent runtimeEventIn) {
        return runAdapter.after(runtimeEventIn, this);
    }

    public ThreadLocalWhenInTest startCallbackProcessing() {
        if (!inCallbackProcessing) {
            inCallbackProcessing = true;
            return this;
        }
        return null;
    }

    public void stopCallbackProcessing() {
        inCallbackProcessing = false;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    public CallbackAction atomicOperation() {
        return atomicVolatileFieldAccess;
    }

    public void setAtomicVolatileFieldAccess(CallbackAction atomicVolatileFieldAccess) {
        this.atomicVolatileFieldAccess = atomicVolatileFieldAccess;
    }

    public RunAdapter runAdapter() {
        return runAdapter;
    }

    public ThreadStartEvent threadStartEvent() {
        return threadStartEvent;
    }

    public void setThreadStartEvent(ThreadStartEvent threadStartEvent) {
        this.threadStartEvent = threadStartEvent;
    }
}
