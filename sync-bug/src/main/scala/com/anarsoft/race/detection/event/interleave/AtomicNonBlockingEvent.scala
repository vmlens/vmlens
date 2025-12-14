package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.vmlens.report.input.run.memoryaccesskey.{AtomicMethodIdAndObjectHashcode, FieldIdAndObjectHashcode}
import com.vmlens.report.input.run.{RunElementType, VolatileAccess}

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

  override def runElementType: RunElementType = {
    new VolatileAccess(new AtomicMethodIdAndObjectHashcode(atomicMethodId, objectHashCode),operation);
  }

}
