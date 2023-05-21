package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class Run {
    private final Object singleLoopAndRunLock;
    private final WaitNotifyStrategy waitNotifyStrategy;
    private final RunStateMachine runStateMachine;
    private final int id;
    private int maxThreadIndex;

    public Run(Object singleLoopAndRunLock, int id, WaitNotifyStrategy waitNotifyStrategy, RunStateMachine runStateMachine, TestThreadState testThreadState) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.runStateMachine = runStateMachine;
        this.id = id;
        this.singleLoopAndRunLock = singleLoopAndRunLock;
        testThreadState.createNewParallelizedThreadLocal(this, maxThreadIndex);
        maxThreadIndex++;
    }

    public void after(ParallelizeAction action, TestThreadState testThreadState) {
        synchronized (singleLoopAndRunLock) {
            runStateMachine.after(action, testThreadState);
            waitNotifyStrategy.notifyAndWaitTillActive(testThreadState, runStateMachine, singleLoopAndRunLock);
        }
    }
    public void end(TestThreadState testThreadState) {
        testThreadState.setParallelizedThreadLocalToNull();
        runStateMachine.end();
    }
    public void newTask(RunnableOrThreadWrapper newWrapper, TestThreadState testThreadState) {
        synchronized (singleLoopAndRunLock) {
            if (runStateMachine.isNewTestTask(newWrapper)) {
                testThreadState.createNewParallelizedThreadLocal(this, maxThreadIndex);
                runStateMachine.processNewTestTask(testThreadState);
                maxThreadIndex++;
                waitNotifyStrategy.notifyAndWaitTillActive(testThreadState, runStateMachine, singleLoopAndRunLock);
            }
        }
    }

    // Visible for Test
    public boolean isActive(TestThreadState testThreadState) {
        return runStateMachine.isActive(testThreadState);
    }

    // Visible for Test
    public ActualRun actualRun() {
        return runStateMachine.actualRun();
    }
}
