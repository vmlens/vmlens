package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setinmethodidandposition.RuntimeEventAndSetInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;

/**
 * Data Structure, only state no behaviour
 *
 */
public class ThreadLocalWhenInTest extends PerThreadCounter implements ThreadLocalWhenInTestForParallelize {
    private final RunAdapter runAdapter;
    private final int threadIndex;
    private InMethodIdAndPosition inMethodIdAndPosition;
    private RuntimeEventAndSetInMethodIdAndPosition runtimeEventForAtomicAction;


    public ThreadLocalWhenInTest(RunForCallback run, int threadIndex) {
        this.runAdapter = new RunAdapter(run);
        this.threadIndex = threadIndex;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    public RuntimeEventAndSetInMethodIdAndPosition runtimeEventForAtomicAction() {
        return runtimeEventForAtomicAction;
    }

    public void setRuntimeEventForAtomicAction(RuntimeEventAndSetInMethodIdAndPosition runtimeEventForAtomicAction) {
        this.runtimeEventForAtomicAction = runtimeEventForAtomicAction;
    }

    public InMethodIdAndPosition inMethodIdAndPosition() {
        return inMethodIdAndPosition;
    }

    public void setInMethodIdAndPosition(InMethodIdAndPosition inMethodIdAndPosition) {
        this.inMethodIdAndPosition = inMethodIdAndPosition;
    }

    public RunAdapter runAdapter() {
        return runAdapter;
    }

}
