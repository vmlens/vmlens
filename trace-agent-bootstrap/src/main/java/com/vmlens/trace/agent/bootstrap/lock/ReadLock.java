package com.vmlens.trace.agent.bootstrap.lock;

public class ReadLock extends ReadOrWriteLock {

    private final int id;

    ReadLock(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public boolean isRead() {
        return true;
    }

}
