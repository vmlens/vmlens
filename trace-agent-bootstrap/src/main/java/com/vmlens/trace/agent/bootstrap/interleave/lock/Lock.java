package com.vmlens.trace.agent.bootstrap.interleave.lock;

public interface Lock {

    LockKey key();
    boolean startsAlternatingOrder(Lock lockOrMonitor);
}
