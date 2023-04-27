package com.vmlens.trace.agent.bootstrap.parallelize.run;

public interface WaitNotifyStrategy {
    void notifyAndWaitTillActive(TestThreadState testThreadState, RunStateMachine runStateMachine, Object lock );
}
