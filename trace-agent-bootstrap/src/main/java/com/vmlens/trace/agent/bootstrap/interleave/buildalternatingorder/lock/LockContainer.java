package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.DependentOperationAndPositionOrContainer;

/**
 * A lock interleaveoperation is used for all blocking operations
 *
 * locks and monitors use the following operations:
 *      lock
 *      unlock
 *      readLockState
 *      tryLock
 *      convertLock
 *      wait
 *
 *     convertlock gets translated into lock exit, try lock
 *     wait gets translated into condition wait and lock exit, lock enter
 *
 *  notify is not a lock operation only a condition operation
 *  wait gets translated into lock enter, exit and condition wait
 *
 */

public abstract class LockContainer implements DependentOperationAndPositionOrContainer<LockContainer>,
        LockContainerVisitor {

    @Override
    public TreeBuilderNode addToAlternatingOrder(LockContainer lockOrConditionContainer,
                                                 BuildAlternatingOrderContext context,
                                                 TreeBuilderNode treeBuilderNode) {
        AddToAlternatingOrder tuple = lockOrConditionContainer.accept(this);
        if(tuple != null) {
            return tuple.addToAlternatingOrder(context,treeBuilderNode);
        }
      return treeBuilderNode;
    }

    public abstract AddToAlternatingOrder accept(LockContainerVisitor visitor);

}
