package com.vmlens.trace.agent.bootstrap.interleave.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.DependentOperationAndPositionOrContainer;

/**
 * A lock interleaveoperation is used for all blocking operations
 *
 * locks and monitors use the following operations:
 *      lock
 *      unlock
 *      readLockState
 *      tryLock
 *      convertLock:  is a lock, unlock in one interleaveoperation
 *
 * monitors only implement lock, unlock
 *
 * as a wait releases also the lock and requires it, conditions must also be
 * processed with the same lock
 *
 * we have the following combinatorics of operations:
 *       readLockState
 *       lock enter lock exit
 *       lock enter wait
 *       wait lock exit
 *       notify lock exit
 *       notify lock wait
 *
 */

public interface LockOrConditionContainer extends DependentOperationAndPositionOrContainer<LockOrConditionContainer> {

    AddToAlternatingOrder accept(LockOrConditionContainerVisitor visitor);

}
