package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.util.EventArray

import scala.collection.mutable.{ArrayBuffer, HashMap}

class RunDataListBuilder {

  private[this] val loopAndRunIdToRunDataBuilder = new HashMap[LoopAndRunId, RunData]();

  def add(loopAndRunId: LoopAndRunId, methodEventArray: EventArray[MethodEvent]): Unit = {
    val runData = loopAndRunIdToRunDataBuilder.getOrElseUpdate(loopAndRunId, RunData.forLoopAndRun(loopAndRunId));
    loopAndRunIdToRunDataBuilder.put(loopAndRunId, runData.copy(methodEventArray = methodEventArray))
  }

  def build(): List[RunData] = {
    val arrayBuffer = new ArrayBuffer[RunData]();
    for (elem <- loopAndRunIdToRunDataBuilder) {
      arrayBuffer += elem._2
    }
    arrayBuffer.toList
  }
}
