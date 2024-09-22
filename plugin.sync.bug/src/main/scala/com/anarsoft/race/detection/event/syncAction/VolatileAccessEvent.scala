package com.anarsoft.race.detection.event.syncAction

import com.anarsoft.race.detection.createpartialorder.SyncActionEvent
import com.anarsoft.race.detection.event.nonVolatileField.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.setstacktrace.EventWithStacktraceNode
import com.anarsoft.race.detection.sortutil.MemoryAccessEvent
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait VolatileAccessEvent extends MemoryAccessEvent[VolatileAccessEvent]
  with SyncActionEvent[VolatileAccessEvent]
  with EventWithStacktraceNode
  with LoadedSyncActionEvent {

  def fieldId: Int
  def objectHashCode: Long
  
  override def addToContext(context: LoadedSyncActionContext): Unit = {
    context.addVolatileAccessEvent(this);
  }

  def compareType(other: VolatileAccessEvent): Int = {
    if (objectHashCode != other.objectHashCode) {
      objectHashCode.compareTo(other.objectHashCode)
    } else {
      fieldId.compareTo(other.fieldId)
    }
  }
}
