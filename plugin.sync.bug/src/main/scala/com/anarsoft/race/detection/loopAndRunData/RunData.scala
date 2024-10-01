package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.interleave.InterleaveEvent
import com.anarsoft.race.detection.groupnonvolatilememoryaccess.NonVolatileMemoryAccessElementForProcess
import com.anarsoft.race.detection.groupsyncaction.SyncActionElementForProcess
import com.anarsoft.race.detection.util.EventArray

case class RunData(loopAndRunId: LoopAndRunId,
                   methodEventArray: EventArray[MethodEvent],
                   nonVolatileMemoryAccessElements: List[NonVolatileMemoryAccessElementForProcess],
                   syncActionElements: List[SyncActionElementForProcess],
                   interleaveEvents: List[InterleaveEvent]) {
  
}

object RunData {

  def forLoopAndRun(loopAndRunId: LoopAndRunId): RunData = {
    RunData(loopAndRunId, new EventArray[MethodEvent](Array.ofDim(0)), Nil, Nil, Nil);
  }
}