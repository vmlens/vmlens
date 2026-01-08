package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createdominatortreeevent.CreateDominatorTreeEvent
import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.lockcontainer.LockContainer

trait WithLockEvent extends SyncActionEventWithCompareType[WithLockEvent] 
  with EventForReportElement 
  with WithSetStacktraceNode  
  with CreateDominatorTreeEvent {

  def objectHashCode: Long;

  override def compareType(other: WithLockEvent): Int = {
    objectHashCode.compareTo(other.objectHashCode)
  }
  
  def asEither() : Either[WithLockEnterEvent,WithLockExitEvent];
  
  def create(): LockContainer;

  def update(lockContainer: LockContainer): LockContainer

  def foreachOpposite(lockContainer: LockContainer, f: WithLockEvent => Unit): Unit
  
}
