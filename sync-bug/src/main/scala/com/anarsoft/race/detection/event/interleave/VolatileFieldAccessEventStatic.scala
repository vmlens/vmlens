package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createdominatortreeevent.EventForSummary
import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.FieldId
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationVolatileAccess
import com.vmlens.report.dominatortree.{SortKeyStaticField, UIStateElementSortKey}

trait VolatileFieldAccessEventStatic extends EventWithReadWrite[VolatileFieldAccessEventStatic]
  with SyncActionEventWithCompareType[VolatileFieldAccessEventStatic]
  with WithSetStacktraceNode
  with LoadedInterleaveActionEvent 
  with EventForSummary[FieldId] {

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

  override def memoryAccessKey: FieldId = {
    new FieldId(fieldId);
  }

  override def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap): Unit = {
    // Nothing to do
  }

  override def createUIStateElementSortKey(): Option[UIStateElementSortKey] = {
    Some(new SortKeyStaticField(fieldId))
  }
}
