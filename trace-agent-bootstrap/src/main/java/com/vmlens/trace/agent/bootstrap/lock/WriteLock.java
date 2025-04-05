package com.vmlens.trace.agent.bootstrap.lock;

public class WriteLock implements ReadOrWriteLock {

   private final int id;

   WriteLock(int id) {
        this.id = id;
    }
}
