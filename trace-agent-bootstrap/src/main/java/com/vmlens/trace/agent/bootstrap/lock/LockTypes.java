package com.vmlens.trace.agent.bootstrap.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTypes {

    public static final ReentrantLock REENTRANT_LOCK = new ReentrantLock(1);
    public static final ReadLock READ_LOCK = new ReadLock(2);
    public static final WriteLock WRITE_LOCK = new WriteLock(3);

}
