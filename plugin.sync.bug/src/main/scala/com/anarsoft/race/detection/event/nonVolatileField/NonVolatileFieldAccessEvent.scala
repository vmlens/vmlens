package com.anarsoft.race.detection.event.nonVolatileField

import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder
import com.anarsoft.race.detection.setstacktrace.EventWithStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

trait NonVolatileFieldAccessEvent extends LoadedNonVolatileFieldEvent
  with NonVolatileMemoryAccessEvent[NonVolatileFieldAccessEvent]
  with EventWithStacktraceNode {

  var stackTraceNode: StacktraceNode = null;

  def fieldId: Int

  def objectHashCode: Long

  def operation: Int


  def setStacktraceNode(node: StacktraceNode): Unit = {
    stackTraceNode = node;
  }

  def compareType(other: NonVolatileFieldAccessEvent): Int = {
    if (objectHashCode != other.objectHashCode) {
      objectHashCode.compareTo(other.objectHashCode)
    } else {
      fieldId.compareTo(other.fieldId)
    }
  }

  def isRead: Boolean = {
    (operation | MemoryAccessType.IS_READ) == MemoryAccessType.IS_READ;
  }

  def add(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit = {
    builder.add(this);
  }

  def addAsDataRace(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit = {
    builder.addAsDataRace(this);
  }
  
  
}
