package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.createstacktrace.ThreadIdAndMethodCounter
import com.anarsoft.race.detection.process.loopAndRunData.LoopAndRunId
import com.anarsoft.race.detection.util.EventArray

case class LoopAndRunIdAndEventArray[EVENT <: ThreadIdAndMethodCounter](loopAndRunId: LoopAndRunId,
                                                                        eventArray: EventArray[EVENT]) {

}
