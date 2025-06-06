package com.vmlens.trace.agent.bootstrap.interleave.interleavetypes;

/**
 * A lock interleaveoperation is used for all blocking operations
 *
 *
 *
 * locks and monitors use the following operations:
 *      lock
 *      unlock
 *      readLockState
 *      tryLock
 *      convertLock -> is a lock, unlock in one interleaveoperation
 *
 * monitors only implement lock, unlock
 *
 * as a wait releases also the lock and requires it, conditions must also be
 * processed with the same lock
 *
 */

public interface LockOrConditionBlock {
}
