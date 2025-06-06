package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class LeftBeforeRightPair implements Comparable<LeftBeforeRightPair> {

    private final LeftBeforeRight first;
    private final LeftBeforeRight second;
    private final LockKey firstLock;
    private final LockKey secondLock;

    public LeftBeforeRightPair(LeftBeforeRight first, LeftBeforeRight second,
                               LockKey firstLock, LockKey secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
        if(first.compareTo(second) > 0) {
            this.first = first;
            this.second = second;
        } else {
            this.first = second;
            this.second = first;
        }
    }

    public void addConstraint(CreateOrderContext createOrderContext) {
        createOrderContext.newOrder.add(wrap(first));
        createOrderContext.newOrder.add(wrap(second));
        createOrderContext.potentialDeadlockActivated.add(firstLock);
        createOrderContext.potentialDeadlockActivated.add(secondLock);
    }

    @Override
    public int compareTo(LeftBeforeRightPair o) {
        int firstCompare = first.compareTo(o.first);
        if(firstCompare != 0) {
            return firstCompare;
        }
        return second.compareTo(o.second);
    }

    @Override
    public String toString() {
        return "LeftBeforeRightPair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        LeftBeforeRightPair that = (LeftBeforeRightPair) o;
        return first.equals(that.first) && second.equals(that.second);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }
}
