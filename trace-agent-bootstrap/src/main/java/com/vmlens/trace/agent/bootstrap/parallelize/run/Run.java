package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class Run {
    private final Object lock = new Object();
    private final WaitNotifyStrategy waitNotifyStrategy;
    private final RunStateMachine runStateMachine;
    private final int id;
    private int maxThreadIndex;
    public Run(int id, WaitNotifyStrategy waitNotifyStrategy, RunStateMachine runStateMachine,  TestThreadState testThreadState) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.runStateMachine = runStateMachine;
        this.id = id;
        testThreadState.createNewParallelizedThreadLocal(this, maxThreadIndex);
        maxThreadIndex++;
    }
    public void after(ParallelizeAction action, TestThreadState testThreadState) {
        synchronized (lock) {
            runStateMachine.after(action, testThreadState);
            waitNotifyStrategy.notifyAndWaitTillActive(testThreadState,runStateMachine,lock);
        }
    }
    public void end(TestThreadState testThreadState) {
        testThreadState.setParallelizedThreadLocalToNull();
        runStateMachine.end();
    }
    public void newTask(RunnableOrThreadWrapper newWrapper, TestThreadState testThreadState) {
        synchronized (lock) {
            if (runStateMachine.isNewTestTask(newWrapper)) {
                testThreadState.createNewParallelizedThreadLocal(this,maxThreadIndex);
                runStateMachine.processNewTestTask(testThreadState);
                maxThreadIndex++;
                waitNotifyStrategy.notifyAndWaitTillActive(testThreadState,runStateMachine,lock);
            }
        }
    }
    // Visible for Test
    public RunStateMachine getRunStateMachine() {
        return runStateMachine;
    }
}
