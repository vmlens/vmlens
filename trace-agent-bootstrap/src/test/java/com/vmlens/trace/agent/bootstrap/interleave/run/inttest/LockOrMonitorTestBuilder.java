package com.vmlens.trace.agent.bootstrap.interleave.run.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockOrMonitorEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockOrMonitorExit;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.LockOrMonitor;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.Monitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class LockOrMonitorTestBuilder {
    private final LockOrMonitor lockOrMonitor;

    public LockOrMonitorTestBuilder(LockOrMonitor lockOrMonitor) {
        this.lockOrMonitor = lockOrMonitor;
    }

    public static LockOrMonitorTestBuilder firstMonitor() {
        return new LockOrMonitorTestBuilder(new Monitor(1));
    }


    public InterleaveActionTestFactory enter() {
        return new LockOrMonitorEnterImplFactory(lockOrMonitor);
    }

    public InterleaveActionTestFactory exit() {
        return new LockOrMonitorExitFactory(lockOrMonitor);
    }

    private static class LockOrMonitorEnterImplFactory implements InterleaveActionTestFactory {

        private final LockOrMonitor lockOrMonitor;

        public LockOrMonitorEnterImplFactory(LockOrMonitor lockOrMonitor) {
            this.lockOrMonitor = lockOrMonitor;
        }

        @Override
        public InterleaveAction create(int threadIndex) {
            return new LockOrMonitorEnterImpl(threadIndex, lockOrMonitor);
        }
    }

    private static class LockOrMonitorExitFactory implements InterleaveActionTestFactory {

        private final LockOrMonitor lockOrMonitor;

        public LockOrMonitorExitFactory(LockOrMonitor lockOrMonitor) {
            this.lockOrMonitor = lockOrMonitor;
        }

        @Override
        public InterleaveAction create(int threadIndex) {
            return new LockOrMonitorExit(threadIndex, lockOrMonitor);
        }
    }

}
