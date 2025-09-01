package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

/**
 * Data Structure, only state no behaviour
 *
 */
public class ThreadLocalWhenInTest  implements ThreadLocalWhenInTestForParallelize {

    private final RunAdapter runAdapter;
    private final int threadIndex;

    private int inAtomicCount = 0;
    private int methodCount;
    private InMethodIdAndPosition inMethodIdAndPosition;
    private ExecuteAfterOperation executeAfterOperation;

    public ThreadLocalWhenInTest(RunForCallback run, int threadIndex) {
        this.runAdapter = new RunAdapter(run);
        this.threadIndex = threadIndex;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    public ExecuteAfterOperation executeAfterOperation() {
        return executeAfterOperation;
    }

    public void setExecuteAfterOperation(ExecuteAfterOperation executeAfterMethodCall) {
        this.executeAfterOperation = executeAfterMethodCall;
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

    public int incrementAndGetMethodCount() {
        methodCount++;
        return methodCount;
    }

    public int methodCount() {
        return methodCount;
    }

    public int inAtomicCount() {
        return inAtomicCount;
    }

    public void setInAtomicCount(int inAtomicCount) {
        this.inAtomicCount = inAtomicCount;
    }
}
