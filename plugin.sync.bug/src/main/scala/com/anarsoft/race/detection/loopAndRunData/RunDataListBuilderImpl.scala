package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.interleave.InterleaveEvent
import com.anarsoft.race.detection.groupsyncaction.SyncActionElementForProcess
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class RunDataListBuilderImpl extends RunDataListBuilder {

  private[this] val loopAndRunIdToRunDataBuilder = new mutable.HashMap[LoopAndRunId, RunData]();

  override def addInterleaveEvents(loopAndRunId: LoopAndRunId, interleaveEventList: List[InterleaveEvent]): Unit = {
    val runData = loopAndRunIdToRunDataBuilder.getOrElseUpdate(loopAndRunId, RunData.forLoopAndRun(loopAndRunId));
    loopAndRunIdToRunDataBuilder.put(loopAndRunId, runData.copy(interleaveEvents = interleaveEventList))
  }

  def addMethodEvents(loopAndRunId: LoopAndRunId, methodEventList: util.List[MethodEvent]): Unit = {
    val runData = loopAndRunIdToRunDataBuilder.getOrElseUpdate(loopAndRunId, RunData.forLoopAndRun(loopAndRunId));
    loopAndRunIdToRunDataBuilder.put(loopAndRunId, runData.copy(methodEventArray = EventArray[MethodEvent](methodEventList)))
  }

  def addSyncActionElements(loopAndRunId: LoopAndRunId, syncActionElements: List[SyncActionElementForProcess]): Unit = {
    val runData = loopAndRunIdToRunDataBuilder.getOrElseUpdate(loopAndRunId, RunData.forLoopAndRun(loopAndRunId));
    loopAndRunIdToRunDataBuilder.put(loopAndRunId, runData.copy(syncActionElements = syncActionElements))
  }

  def build(): List[RunData] = {
    val arrayBuffer = new ArrayBuffer[RunData]();
    for (elem <- loopAndRunIdToRunDataBuilder) {
      arrayBuffer += elem._2
    }
    arrayBuffer.toList
  }
}
