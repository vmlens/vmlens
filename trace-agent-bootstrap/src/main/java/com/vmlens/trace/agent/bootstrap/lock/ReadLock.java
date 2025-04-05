package com.vmlens.trace.agent.bootstrap.lock;

public class ReadLock implements ReadOrWriteLock {

    private final int id;

    ReadLock(int id) {
        this.id = id;
    }

}
