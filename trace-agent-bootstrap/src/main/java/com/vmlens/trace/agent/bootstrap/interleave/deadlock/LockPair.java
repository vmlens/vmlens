package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockEnterOrTryLock;

import java.util.Objects;

public class LockPair {

    private final LockEnterOrTryLock parent;
    private final LockEnterOrTryLock child;

    public LockPair(LockEnterOrTryLock parent, LockEnterOrTryLock child) {
        this.parent = parent;
        this.child = child;
    }

    public LockEnterOrTryLock parent() {
        return parent;
    }

    public LockEnterOrTryLock child() {
        return child;
    }

    public  LockPair normalized() {
        if(parent.key().compareTo(child.key()) < 0)  {
            return this;
        }
        return new LockPair(child,parent);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        LockPair lockPair = (LockPair) o;
        return Objects.equals(parent.key(), lockPair.parent.key()) && Objects.equals(child.key(), lockPair.child.key());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(parent.key());
        result = 31 * result + Objects.hashCode(child.key());
        return result;
    }
}
