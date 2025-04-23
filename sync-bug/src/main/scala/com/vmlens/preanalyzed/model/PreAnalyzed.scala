package com.vmlens.preanalyzed.model

sealed trait PreAnalyzed extends PreAnalyzedOrList {

  def asList() : List[PreAnalyzed] = List(this);
  def name : String;
  
}

case class Filter(name : String) extends PreAnalyzed;

case class Include(name : String) extends PreAnalyzed;

case class ThreadModel(name : String) extends PreAnalyzed;

case class VMLensApi(name : String) extends PreAnalyzed;

case class GetReadWriteLock(name : String) extends PreAnalyzed;

case class Lock(name : String, lockType : LockType, methods : List[LockMethod]) extends PreAnalyzed;

case class AtomicReadWriteLock(name : String, methods : List[MethodWithLock]) extends PreAnalyzed;
