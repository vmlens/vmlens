package com.anarsoft.race.detection.process.loopAndRunData

import com.anarsoft.race.detection.stacktrace.MethodEvent
import com.anarsoft.race.detection.util.EventArray

case class RunData(loopAndRunId: LoopAndRunId, methodEventArray: EventArray[MethodEvent]) {

}
