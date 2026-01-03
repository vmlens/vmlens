package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createdominatortree.DominatorMemoryAccessKeyToOperation
import com.anarsoft.race.detection.createdominatortreeevent.EventForSummary
import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.{AtomicMethodIdAndObjectHashcode, FieldIdAndObjectHashcode}
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.AtomicNonBlockingKey
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationVolatileAccess
import com.vmlens.report.dominatortree.{SortKeyAtomicObject, SortKeyObjectField, UIStateElementSortKey}


trait AtomicNonBlockingEvent  extends EventWithReadWrite[AtomicNonBlockingEvent]
  with SyncActionEventWithCompareType[AtomicNonBlockingEvent]
  with WithSetStacktraceNode
  with LoadedInterleaveActionEvent
  with EventForSummary[AtomicNonBlockingKey] {

  var objectHashCodeMap: ObjectHashCodeMap = null;
  var objectHashCodeToOperation: DominatorMemoryAccessKeyToOperation = null;
  
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

  override def memoryAccessKey: AtomicNonBlockingKey =
    new AtomicNonBlockingKey(atomicMethodId, objectHashCode)

  override def setMapsForDominatorTree(objectHashCodeMap: ObjectHashCodeMap, 
                                       objectHashCodeToOperation : DominatorMemoryAccessKeyToOperation): Unit = {
    this.objectHashCodeMap = objectHashCodeMap;
    this.objectHashCodeToOperation = objectHashCodeToOperation
    objectHashCodeMap.add(objectHashCode, threadIndex);
    objectHashCodeToOperation.add(memoryAccessKey,operation)
  }

  override def isReadOnly : Boolean = objectHashCodeToOperation.isReadOnly(memoryAccessKey)

  override def createUIStateElementSortKey(): Option[UIStateElementSortKey] = {
    objectHashCodeMap.id(objectHashCode).map(id => new SortKeyAtomicObject(id, atomicMethodId))
  }
}
