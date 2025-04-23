package com.vmlens.preanalyzed.model

sealed trait LockType {

}

sealed trait ReadOrWriteLockType {

}

case class ReadLock() extends LockType with ReadOrWriteLockType;

case class WriteLock() extends LockType with ReadOrWriteLockType;

case class ReentrantLock() extends LockType;


