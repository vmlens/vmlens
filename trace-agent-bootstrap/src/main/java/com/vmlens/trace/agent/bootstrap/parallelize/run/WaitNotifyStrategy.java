package com.vmlens.trace.agent.bootstrap.parallelize.run;

import java.util.concurrent.locks.Condition;

public interface WaitNotifyStrategy {
    void notifyAndWaitTillActive(TestThreadState testThreadState, RunStateMachine runStateMachine, Condition threadActiveCondition)
            throws TestBlockedException;
}
