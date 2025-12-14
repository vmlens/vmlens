package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.vmlens.report.input.run.memoryaccesskey.FieldId
import com.vmlens.report.input.run.{RunElementType, VolatileAccess}

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

  override def runElementType: RunElementType = {
    new VolatileAccess(new FieldId(fieldId),operation);
  }
}
