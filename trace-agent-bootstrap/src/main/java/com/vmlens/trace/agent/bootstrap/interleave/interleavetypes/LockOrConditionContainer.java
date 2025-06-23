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
 * if we have a block, for example try lock, lock exit
 * we are sure to have the two order try lock lock exit try lock and lock exit and reverse
 *
 * so we process first the start of the block, which might be wait, try lock, lock convert or lock enter
 * and then calculate the additional blocks
 *
 * try lock and lock convert requires choice
 * spurious wake up does not
 *      we handle this using flags, isTryLock and isWait
 *
 * we treat notify as single operation
 *
 *
 */

public interface LockOrConditionContainer extends DependentOperationAndPositionOrContainer<LockOrConditionContainer> {

    AddToAlternatingOrder accept(LockOrConditionContainerVisitor visitor);

}
