package com.vmlens.preanalyzed.model

sealed trait PreAnalyzed extends PreAnalyzedOrList {

  def asList() : List[PreAnalyzed] = List(this);
  def take(className : String) : Boolean;
  
}

case class Filter(name : String) extends PreAnalyzed {
  def take(className: String) : Boolean = className.startsWith(name);
}

case class Include(name : String) extends PreAnalyzed {
  def take(className: String) : Boolean = className.startsWith(name);
}

case class ThreadModel(name : String) extends PreAnalyzed {
  def take(className: String) : Boolean = className.equals(name);
}

case class VMLensApi(name : String) extends PreAnalyzed {
  def take(className: String) : Boolean = className.equals(name);
}

case class GetReadWriteLock(name : String) extends PreAnalyzed {
  def take(className: String) : Boolean = className.equals(name);
}

case class Lock(name : String, lockType : LockType, methods : List[LockMethod]) extends PreAnalyzed {
  def take(className: String) : Boolean = className.equals(name);
}

case class AtomicReadWriteLock(name : String, methods : List[MethodWithLock]) extends PreAnalyzed {
  def take(className: String) : Boolean = className.equals(name);
}

case class AtomicNonBlocking(name : String, methods : List[AtomicNonBlockingMethod]) extends PreAnalyzed {
  def take(className: String) : Boolean = className.equals(name);
}
