package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric;
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinal
import com.anarsoft.race.detection.model.WithStatementPosition;
import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinalVisitor
import com.vmlens.api.internal.reports.element._
import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId
import com.vmlens.api.internal.IconRepository

trait SyncActionLock extends SyncPointGeneric[Int] with SyncAction with EventSetStacktraceOrdinal {

  def idPerMemoryLocation = monitorId;
  def compareIdPerMemoryLocation(other: Int) = java.lang.Integer.compare(idPerMemoryLocation, other);

  def monitorId: Int;

  def uniqueId = monitorId;

  def isLockEnter(): Boolean;

  def isShared: Boolean;

  var stackTraceOrdinal = -1;

  def isStackTraceIncompleteOption = None;
  def methodIdOption = None;

  def setStackTraceOrdinal(in: StackTraceOrdinalAndMethodId) {
    stackTraceOrdinal = in.ordinal;
  }

  def icon() =
    if (isLockEnter()) {
      IconRepository.LOCK_ENTER
    } else {
      IconRepository.LOCK_EXIT
    }

  def accept(visitor: EventSetStacktraceOrdinalVisitor) {
    visitor.visit(this);
  }

}