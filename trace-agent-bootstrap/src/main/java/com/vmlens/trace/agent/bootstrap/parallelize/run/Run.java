package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
Fixme auseinander ziehen damit ohne lock testbar
 */
public class Run {
    private final ReentrantLock lock;
    private final Condition threadActiveCondition;
    private final WaitNotifyStrategy waitNotifyStrategy;
    private final RunStateMachine runStateMachine;

    private final int id;
    private int maxThreadIndex;

    public Run(ReentrantLock lock, int id, WaitNotifyStrategy waitNotifyStrategy,
               RunStateMachine runStateMachine) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.runStateMachine = runStateMachine;
        this.id = id;
        this.lock = lock;
        this.threadActiveCondition = lock.newCondition();
        maxThreadIndex++;
    }

    public void after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        lock.lock();
        try {
            runStateMachine.after(action, threadLocalDataWhenInTest);
            try {
                waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
            } catch (TestBlockedException e) {
                runStateMachine.setStateRecording();
            }
        } finally {
            lock.unlock();
        }
    }

    public void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            if (runStateMachine.isNewTestTask(newWrapper)) {
                ThreadLocalDataWhenInTest threadLocalDataWhenInTest = threadLocalForParallelize.createNewParallelizedThreadLocal(this, maxThreadIndex);
                runStateMachine.processNewTestTask(threadLocalDataWhenInTest);
                maxThreadIndex++;
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {
                    runStateMachine.setStateRecording();
                }
            }
        } finally {
            lock.unlock();
        }
    }


    // Visible for Test
    public boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return runStateMachine.isActive(threadLocalDataWhenInTest);
    }

    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        threadLocalForParallelize.setParallelizedThreadLocalToNull();
        return runStateMachine.end();
    }

}
