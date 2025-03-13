package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;

import java.util.Objects;

public class LockPair {

    private final LockKey parent;
    private final LockKey child;

    public LockPair(LockKey parent, LockKey child) {
        this.parent = parent;
        this.child = child;
    }

    public LockKey parent() {
        return parent;
    }

    public LockKey child() {
        return child;
    }

    public  LockPair normalized() {
        if(parent.compareTo(child) < 0)  {
            return this;
        }
        return new LockPair(child,parent);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        LockPair lockPair = (LockPair) o;
        return Objects.equals(parent, lockPair.parent) && Objects.equals(child, lockPair.child);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(parent);
        result = 31 * result + Objects.hashCode(child);
        return result;
    }
}
