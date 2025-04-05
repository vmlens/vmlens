package com.vmlens.trace.agent.bootstrap.lock;

public class ReentrantLock implements LockType {

    private final int id;

    ReentrantLock(int id) {
        this.id = id;
    }

}
