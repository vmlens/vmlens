package com.anarsoft.race.detection.process.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.util.EventArray

import scala.collection.mutable.{ArrayBuffer, HashMap}

class RunDataListBuilder {

  private[this] val loopAndRunIdToRunDataBuilder = new HashMap[LoopAndRunId, RunDataBuilder]();

  def add(loopAndRunId: LoopAndRunId, methodEventArray: EventArray[MethodEvent]): Unit = {
    val runDataBuilder = loopAndRunIdToRunDataBuilder.getOrElseUpdate(loopAndRunId, new RunDataBuilder());
    runDataBuilder.withMethodEventArray(methodEventArray);
  }

  def build(): List[RunData] = {
    val arrayBuffer = new ArrayBuffer[RunData]();
    for (elem <- loopAndRunIdToRunDataBuilder) {
      arrayBuffer += elem._2.build(elem._1)
    }
    arrayBuffer.toList
  }
}
