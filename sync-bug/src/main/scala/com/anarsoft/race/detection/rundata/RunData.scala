package com.anarsoft.race.detection.rundata

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.control.ControlEvent
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElement
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileElement
import com.anarsoft.race.detection.util.EventArray

case class RunData(loopAndRunId: LoopAndRunId,
                   methodEventArray: EventArray[MethodEvent],
                   nonVolatileElements: List[GroupNonVolatileElement],
                   interLeaveElements: List[GroupInterleaveElement],
                   controlEvents: List[ControlEvent]) {
  
}

object RunData {

  def forLoopAndRun(loopAndRunId: LoopAndRunId): RunData = {
    RunData(loopAndRunId, new EventArray[MethodEvent](Array.ofDim(0)), Nil, Nil, Nil);
  }
}