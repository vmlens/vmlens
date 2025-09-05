package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.interleavetypes;

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

public interface LockContainer extends DependentOperationAndPositionOrContainer<LockContainer> {

    AddToAlternatingOrder accept(LockContainerVisitor visitor);

}
