package com.vmlens.trace.agent.bootstrap.callback.threadlocal;


import com.vmlens.trace.agent.bootstrap.callbackdeprecated.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;


/**
 * Data Structure, only state no behaviour
 *
 */

public class ThreadLocalWhenInTest extends PerThreadCounter {
    private final RunAdapter runAdapter;
    private final int threadIndex;

    private boolean inCallbackProcessing = false;

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

    public int threadIndex() {
        return threadIndex;
    }

}
