package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createdominatortree.DominatorMemoryAccessKeyToOperation
import com.anarsoft.race.detection.createdominatortreeevent.EventForSummary
import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.{FieldId, FieldIdAndObjectHashcode}
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationVolatileAccess
import com.vmlens.report.dominatortree.{SortKeyObjectField, UIStateElementSortKey}

trait VolatileFieldAccessEvent extends EventWithReadWrite[VolatileFieldAccessEvent]
  with SyncActionEventWithCompareType[VolatileFieldAccessEvent]
  with WithSetStacktraceNode
  with LoadedInterleaveActionEvent
  with EventForSummary[FieldIdAndObjectHashcode] {

  var objectHashCodeMap : ObjectHashCodeMap = null;
  var objectHashCodeToOperation: DominatorMemoryAccessKeyToOperation = null;
  
  def fieldId: Int
  def objectHashCode: Long
  
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.volatileAccessEvents.add(this);
  }

  def compareType(other: VolatileFieldAccessEvent): Int = {
    if (objectHashCode != other.objectHashCode) {
      objectHashCode.compareTo(other.objectHashCode)
    } else {
      fieldId.compareTo(other.fieldId)
    }
  }

  override def runElementType: ReportOperation = {
    new OperationVolatileAccess(new FieldIdAndObjectHashcode(fieldId, objectHashCode),operation);
  }

  override def memoryAccessKey: FieldIdAndObjectHashcode = {
    new FieldIdAndObjectHashcode(fieldId, objectHashCode);
  }

  override def setMapsForDominatorTree(objectHashCodeMap: ObjectHashCodeMap,
                                       objectHashCodeToOperation: DominatorMemoryAccessKeyToOperation): Unit = {
    this.objectHashCodeMap = objectHashCodeMap;
    this.objectHashCodeToOperation = objectHashCodeToOperation
    objectHashCodeMap.add(objectHashCode, threadIndex);
    objectHashCodeToOperation.add(memoryAccessKey, operation)
  }

  override def isReadOnly: Boolean = objectHashCodeToOperation.isReadOnly(memoryAccessKey)


  override def createUIStateElementSortKey(): Option[UIStateElementSortKey] = {
    objectHashCodeMap.id(objectHashCode).map(  id => new SortKeyObjectField(id,fieldId)  )
  }
}
