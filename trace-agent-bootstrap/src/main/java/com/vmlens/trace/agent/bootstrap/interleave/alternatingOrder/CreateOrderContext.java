package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

public class CreateOrderContext {

    public final TLinkedList<TLinkableWrapper<LeftBeforeRight>> newOrder;
    public final THashSet<LockKey> potentialDeadlockActivated = new THashSet<>();

    public CreateOrderContext(TLinkedList<TLinkableWrapper<LeftBeforeRight>> newOrder) {
        this.newOrder = newOrder;
    }
}
