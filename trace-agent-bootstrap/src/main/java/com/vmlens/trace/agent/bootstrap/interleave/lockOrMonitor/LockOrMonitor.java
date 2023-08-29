package com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor;

public interface LockOrMonitor {
    LockOrMonitorKey key();

    boolean startsAlternatingOrder(LockOrMonitor lockOrMonitor);
}
