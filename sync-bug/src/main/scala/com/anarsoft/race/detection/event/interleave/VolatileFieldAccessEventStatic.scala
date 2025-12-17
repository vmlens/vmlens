package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.FieldId
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationVolatileAccess

trait VolatileFieldAccessEventStatic extends EventWithReadWrite[VolatileFieldAccessEventStatic]
  with SyncActionEventWithCompareType[VolatileFieldAccessEventStatic]
  with WithSetStacktraceNode
  with LoadedInterleaveActionEvent {

  def fieldId: Int
  
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.staticVolatileAccessEvents.add(this);
  }

  def compareType(other: VolatileFieldAccessEventStatic): Int = {
    fieldId.compareTo(other.fieldId)
  }

  override def runElementType: ReportOperation = {
    new OperationVolatileAccess(new FieldId(fieldId),operation);
  }
}
