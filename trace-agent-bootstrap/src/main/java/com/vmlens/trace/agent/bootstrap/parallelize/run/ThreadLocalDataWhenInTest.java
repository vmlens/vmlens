package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.callback.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.action.ParallelizeActionForRuntimeEvent;

/**
 * Set when the current thread is in a vmlens unit test
 */

public class ThreadLocalDataWhenInTest extends PerThreadCounter {
    private final Run run;
    private final int threadIndex;

    private boolean inCallbackProcessing = false;
    private RunnableOrThreadWrapper createdThread;

    public ThreadLocalDataWhenInTest(Run run, int threadIndex) {
        this.run = run;
        this.threadIndex = threadIndex;
    }

    // Can be null when the runtime event should not be serialized
    public SerializableEvent after(RuntimeEvent runtimeEventIn) {
        runtimeEventIn.setThreadIndex(threadIndex);
        RuntimeEvent result = run.after(new ParallelizeActionForRuntimeEvent(runtimeEventIn), this);
        if (result != null) {
            result.setMethodCounter(incrementAndGetMethodCount());
        }
        return result;
    }

    public ThreadLocalDataWhenInTest startCallbackProcessing() {
        if (!inCallbackProcessing) {
            inCallbackProcessing = true;
            return this;
        }
        return null;
    }

    public void stopCallbackProcessing() {
        inCallbackProcessing = false;
    }


    // public for test
    public int threadIndex() {
        return threadIndex;
    }

    // visible for test
    public Run getRun() {
        return run;
    }

}
