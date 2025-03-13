package com.vmlens.trace.agent.bootstrap.interleave.run.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockExit;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Monitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class LockOrMonitorTestBuilder {
    private final Lock lockOrMonitor;

    public LockOrMonitorTestBuilder(Lock lockOrMonitor) {
        this.lockOrMonitor = lockOrMonitor;
    }

    public static LockOrMonitorTestBuilder firstMonitor() {
        return new LockOrMonitorTestBuilder(new Monitor(1));
    }

    public static LockOrMonitorTestBuilder monitor(long objectHashCode) {
        return new LockOrMonitorTestBuilder(new Monitor(objectHashCode));
    }


    public InterleaveActionTestFactory enter() {
        return new LockOrMonitorEnterImplFactory(lockOrMonitor);
    }

    public InterleaveActionTestFactory exit() {
        return new LockOrMonitorExitFactory(lockOrMonitor);
    }

    private static class LockOrMonitorEnterImplFactory implements InterleaveActionTestFactory {

        private final Lock lockOrMonitor;

        public LockOrMonitorEnterImplFactory(Lock lockOrMonitor) {
            this.lockOrMonitor = lockOrMonitor;
        }

        @Override
        public InterleaveAction create(int threadIndex) {
            return new LockEnterImpl(threadIndex, lockOrMonitor);
        }
    }

    private static class LockOrMonitorExitFactory implements InterleaveActionTestFactory {

        private final Lock lockOrMonitor;

        public LockOrMonitorExitFactory(Lock lockOrMonitor) {
            this.lockOrMonitor = lockOrMonitor;
        }

        @Override
        public InterleaveAction create(int threadIndex) {
            return new LockExit(threadIndex, lockOrMonitor);
        }
    }

}
