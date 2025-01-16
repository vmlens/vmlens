package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

import java.util.concurrent.locks.Condition;

public interface WaitNotifyStrategy {
    void notifyAndWaitTillActive(ThreadLocalWhenInTest threadLocalDataWhenInTest, RunStateMachine runStateMachine,
                                 Condition threadActiveCondition)
            throws TestBlockedException;

    void waitForCanStartAtomicOperation(RunStateMachine runStateMachine,
                                        Condition threadActiveCondition)
            throws TestBlockedException;


}
