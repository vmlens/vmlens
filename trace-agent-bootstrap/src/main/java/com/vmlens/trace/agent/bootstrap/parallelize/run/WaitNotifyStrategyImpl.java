package com.vmlens.trace.agent.bootstrap.parallelize.run;

public class WaitNotifyStrategyImpl implements WaitNotifyStrategy {

    @Override
    public void notifyAndWaitTillActive(TestThreadState testThreadState, RunStateMachine runStateMachine, Object lock) {
        try {
            lock.notifyAll();
            while (!runStateMachine.isActive(testThreadState)) {
                lock.wait(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
