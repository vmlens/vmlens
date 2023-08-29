package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.LockOrMonitor;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.LockOrMonitorKey;

public interface LockOrMonitorEnter extends DependentBlockElement {
    LockOrMonitorKey key();
}
