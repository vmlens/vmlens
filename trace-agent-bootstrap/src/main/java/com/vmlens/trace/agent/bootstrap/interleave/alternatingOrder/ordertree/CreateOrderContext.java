package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class CreateOrderContext {

    public final TLinkedList<TLinkableWrapper<LeftBeforeRight>> newOrder;
    public final THashSet<LockKey> potentialDeadlockActivated = new THashSet<>();

    public CreateOrderContext(TLinkedList<TLinkableWrapper<LeftBeforeRight>> newOrder) {
        this.newOrder = newOrder;
    }

    public CreateOrderContext() {
        this.newOrder = new TLinkedList<>();
    }

    public void addOrder(LeftBeforeRight order) {
        newOrder.add(wrap(order));
    }

}
