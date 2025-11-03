# Problem

Implementation of Stamped locks using the existing solutions for locks.
Mapping of methods to interleave actions.

# Solution

For Lock Enter we use LockEnterStrategy with a the new LockTypes StampedLockRead and  StampedLockWrite
For lock exit we use a new Strategy for unlock since we need to know if the lock is in read or write mode:
since for exit write, enter read we have a happens before relation
while for exit read enter read we do not have a happens before relation

unlockRead and unlockWrite can use the existing strategies

for tryOptimisticRead(), validate(long stamp),  isReadLocked(),... we use a new interleave action getLockState
this actions creates a potential before and after order with each lock enter and exit but not with other getLockState

this creates also a new interleave event which does not create a happens before relation

# try upgrade and down grade

tryConvertToOptimisticRead
tryConvertToReadLock
tryConvertToWriteLock


# conditions

stamped locks do not support conditions


