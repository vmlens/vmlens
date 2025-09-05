package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.activelock.LockStartOperation;

import java.util.Objects;

public class LockPair {

    private final LockStartOperation parent;
    private final LockStartOperation child;

    public LockPair(LockStartOperation parent, LockStartOperation child) {
        this.parent = parent;
        this.child = child;
    }

    public LockStartOperation parent() {
        return parent;
    }

    public LockStartOperation child() {
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
