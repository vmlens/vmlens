package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.{AtomicMethodIdAndObjectHashcode, FieldIdAndObjectHashcode}
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationVolatileAccess


trait AtomicNonBlockingEvent  extends EventWithReadWrite[AtomicNonBlockingEvent]
  with SyncActionEventWithCompareType[AtomicNonBlockingEvent]
  with WithSetStacktraceNode
  with LoadedInterleaveActionEvent {

  def atomicMethodId: Int
  def objectHashCode: Long

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.atomicNonBlockingEvents.add(this);
  }

  def compareType(other: AtomicNonBlockingEvent): Int = {
      objectHashCode.compareTo(other.objectHashCode)
  }

  override def runElementType: ReportOperation = {
    new OperationVolatileAccess(new AtomicMethodIdAndObjectHashcode(atomicMethodId, objectHashCode),operation);
  }

}
