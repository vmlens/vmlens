package com.anarsoft.race.detection.process.source

import com.anarsoft.race.detection.util.{EventArray, LoopAndRunId, ThreadIdAndMethodCounter}

case class LoopAndRunIdAndEventArray[EVENT <: ThreadIdAndMethodCounter](loopAndRunId: LoopAndRunId,
                                                                        eventArray: EventArray[EVENT]) {

}
