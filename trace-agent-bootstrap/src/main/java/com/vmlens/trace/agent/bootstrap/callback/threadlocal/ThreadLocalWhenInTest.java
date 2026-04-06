package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.StacktraceDepthProvider;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class ThreadLocalWhenInTest  implements ThreadLocalWhenInTestForParallelize {

    private final RunAdapter runAdapter;
    private final int threadIndex;
    // is null for the main thread (thread index == 0)
    private final FirstMethodInThread firstMethodInThread;

    private int inAtomicCount = 0;
    private int methodCount;
    private int dominatorTreeCount;
    private InMethodIdAndPosition inMethodIdAndPosition;
    private ExecuteAfterOperation executeAfterOperation;
    private int doNotTraceInTestCount;

    public ThreadLocalWhenInTest(RunForCallback run,
                                 int threadIndex,
                                 FirstMethodInThread firstMethodInThread) {
        this.runAdapter = new RunAdapter(run);
        this.threadIndex = threadIndex;
        this.firstMethodInThread = firstMethodInThread;
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

    public boolean processAction() {
        return doNotTraceInTestCount == 0;
    }

    public void startDoNotTrace() {
        doNotTraceInTestCount++;
    }

    public void stopDoNotTrace() {
        doNotTraceInTestCount--;
    }

    public FirstMethodInThread firstMethodInThread() {
        return firstMethodInThread;
    }

    @Override
    public int dominatorTreeCount() {
        return dominatorTreeCount;
    }

    @Override
    public int incrementDominatorTreeAndGetMiddle() {
        dominatorTreeCount++;
        int temp = dominatorTreeCount;
        dominatorTreeCount++;
        return temp;
    }
}
