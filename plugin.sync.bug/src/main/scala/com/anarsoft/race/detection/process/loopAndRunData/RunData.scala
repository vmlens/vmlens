package com.anarsoft.race.detection.process.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.nonvolatilememoryaccessgroup.NonVolatileMemoryAccessElementForProcess
import com.anarsoft.race.detection.util.EventArray

case class RunData(loopAndRunId: LoopAndRunId,
                   methodEventArray: EventArray[MethodEvent],
                   nonVolatileMemoryAccessElements: List[NonVolatileMemoryAccessElementForProcess]) {
  
}

object RunData {

  def forLoopAndRun(loopAndRunId: LoopAndRunId): RunData = {
    RunData(loopAndRunId, new EventArray[MethodEvent](Array.ofDim(0)), Nil);
  }
}