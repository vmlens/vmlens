package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.DeadlockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.LockContainer;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.VolatileAccess;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.Barrier;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

/**
 * we have the following collections:
 *      Volatile and Atomic Access
 *      Locks and conditions
 *      Barriers
 *      Independent Actions: Thread join, Thread start
 *
 *  Deadlock are processed after the collections of this context are processed as second step
 *
 */
public class KeyToOperationCollection {

    private final InterleaveLoopContext interleaveLoopContext;
    private final KeyToOperation<VolatileKey, DependentOperationAndPosition<VolatileAccess>> volatileAccess = new KeyToOperation<>();
    private final KeyToOperation<LockKey, LockContainer> lockAndConditions = new KeyToOperation<>();
    private final KeyToOperation<BarrierKey, DependentOperationAndPosition<Barrier>> barrier = new KeyToOperation<>();
    private final TLinkedList<TLinkableWrapper<ElementAndPosition<IndependentBlock>>> independentActions = new TLinkedList<>();

    private TLinkedList<TLinkableWrapper<DeadlockOperation>> deadlocks;

    public KeyToOperationCollection(InterleaveLoopContext interleaveLoopContext) {
        this.interleaveLoopContext = interleaveLoopContext;
    }

    public void addVolatile(VolatileKey key, DependentOperationAndPosition<VolatileAccess> operation) {
        volatileAccess.put(key,operation);
    }

    public void addLockOrCondition(LockKey key, LockContainer operation) {
        lockAndConditions.put(key,operation);
    }

    public void addBarrier(BarrierKey key, DependentOperationAndPosition<Barrier> operation) {
        barrier.put(key,operation);
    }

    public void addIndependent(ElementAndPosition<IndependentBlock> threadOperationAndPosition) {
        independentActions.add(wrap(threadOperationAndPosition));
    }

    public void setDeadlocks(TLinkedList<TLinkableWrapper<DeadlockOperation>> deadlocks) {
        this.deadlocks = deadlocks;
    }

    public THashMap<Position,DeadlockOperation> buildDeadLockMap() {
        THashMap<Position,DeadlockOperation> map = new THashMap<>();
        for(TLinkableWrapper<DeadlockOperation> op : deadlocks) {
            op.element().addToMap(map);
        }
        return map;
    }

    public LeftBeforeRight[] buildFixedOrders(ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrder = new TLinkedList<>();
        for(TLinkableWrapper<ElementAndPosition<IndependentBlock>> action : independentActions) {
            action.element().element().addFixedOrder(action.element().position(),fixedOrder,threadIndexToMaxPosition);
        }
        return toArray(LeftBeforeRight.class,fixedOrder);
    }

    public OrderTree buildOrderTree(BuildAlternatingOrderContext context) {
        TreeBuilder treeBuilder = new TreeBuilder();
        TreeBuilderNode node = volatileAccess.process(context, treeBuilder.start());
        node = lockAndConditions.process(context, node);
        node = barrier.process(context, node);

        for(TLinkableWrapper<DeadlockOperation> op : deadlocks) {
            node = op.element().addToAlternatingOrder(node);
        }

        return treeBuilder.build(interleaveLoopContext);
    }
}
