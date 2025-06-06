package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.createstacktrace.WithMethodCounter
import com.anarsoft.race.detection.loopAndRunData.LoopAndRunId
import com.anarsoft.race.detection.util.EventArray

case class LoopAndRunIdAndEventArray[EVENT <: WithMethodCounter](loopAndRunId: LoopAndRunId,
                                                                 eventArray: EventArray[EVENT]) {

}
