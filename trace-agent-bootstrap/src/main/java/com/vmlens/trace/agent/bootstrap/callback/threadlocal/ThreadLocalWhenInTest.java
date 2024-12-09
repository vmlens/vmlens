package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

/**
 * Data Structure, only state no behaviour
 *
 */
public class ThreadLocalWhenInTest extends PerThreadCounter implements ThreadLocalWhenInTestForParallelize {
    private final RunAdapter runAdapter;
    private final int threadIndex;

    private boolean inCallbackProcessing = false;
    private CallbackAction atomicOperation;

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
        return atomicOperation;
    }

    public void setAtomicOperation(CallbackAction atomicOperation) {
        this.atomicOperation = atomicOperation;
    }

    public RunAdapter runAdapter() {
        return runAdapter;
    }
}
