package com.anarsoft.race.detection.process.loopAndRunData

import com.anarsoft.race.detection.createStacktrace.MethodEvent
import com.anarsoft.race.detection.util.EventArray

class RunDataBuilder {

  private[this] var methodEventArray: EventArray[MethodEvent] = new EventArray[MethodEvent](Array.ofDim(0));

  def withMethodEventArray(methodEventArray: EventArray[MethodEvent]): Unit = {
    this.methodEventArray = methodEventArray;
  }

  def build(loopAndRunId: LoopAndRunId): RunData = {
    RunData(loopAndRunId, methodEventArray)
  }
}
