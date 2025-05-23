package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.control.ControlEvent
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElement
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileElement
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class RunDataListBuilderImpl extends RunDataListBuilder {

  private[this] val loopAndRunIdToRunDataBuilder = new mutable.HashMap[LoopAndRunId, RunData]();

  override def addControlEvents(loopAndRunId: LoopAndRunId, controlEventList: List[ControlEvent]): Unit = {
    val runData = loopAndRunIdToRunDataBuilder.getOrElseUpdate(loopAndRunId, RunData.forLoopAndRun(loopAndRunId));
    loopAndRunIdToRunDataBuilder.put(loopAndRunId, runData.copy(controlEvents = controlEventList))
  }

  def addMethodEvents(loopAndRunId: LoopAndRunId, methodEventList: util.List[MethodEvent]): Unit = {
    val runData = loopAndRunIdToRunDataBuilder.getOrElseUpdate(loopAndRunId, RunData.forLoopAndRun(loopAndRunId));
    loopAndRunIdToRunDataBuilder.put(loopAndRunId, runData.copy(methodEventArray = EventArray[MethodEvent](methodEventList)))
  }

  def addSyncActionElements(loopAndRunId: LoopAndRunId, syncActionElements: List[GroupInterleaveElement]): Unit = {
    val runData = loopAndRunIdToRunDataBuilder.getOrElseUpdate(loopAndRunId, RunData.forLoopAndRun(loopAndRunId));
    loopAndRunIdToRunDataBuilder.put(loopAndRunId, runData.copy(interLeaveElements = syncActionElements))
  }

  def addNonVolatileElements(loopAndRunId: LoopAndRunId, nonVolatileElements: List[GroupNonVolatileElement]): Unit = {
    val runData = loopAndRunIdToRunDataBuilder.getOrElseUpdate(loopAndRunId, RunData.forLoopAndRun(loopAndRunId));
    loopAndRunIdToRunDataBuilder.put(loopAndRunId, runData.copy(nonVolatileElements = nonVolatileElements))
  }

  def build(): List[RunData] = {
    val arrayBuffer = new ArrayBuffer[RunData]();
    for (elem <- loopAndRunIdToRunDataBuilder) {
      arrayBuffer += elem._2
    }
    arrayBuffer.toList
  }
}
