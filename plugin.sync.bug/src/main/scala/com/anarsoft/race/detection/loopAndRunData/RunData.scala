package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.nonvolatilememoryaccessgroup.NonVolatileMemoryAccessElementForProcess
import com.anarsoft.race.detection.syncactiongroup.SyncActionElementForProcess
import com.anarsoft.race.detection.util.EventArray

case class RunData(loopAndRunId: LoopAndRunId,
                   methodEventArray: EventArray[MethodEvent],
                   nonVolatileMemoryAccessElements: List[NonVolatileMemoryAccessElementForProcess],
                   syncActionElements: List[SyncActionElementForProcess]) {
  
}

object RunData {

  def forLoopAndRun(loopAndRunId: LoopAndRunId): RunData = {
    RunData(loopAndRunId, new EventArray[MethodEvent](Array.ofDim(0)), Nil, Nil);
  }
}