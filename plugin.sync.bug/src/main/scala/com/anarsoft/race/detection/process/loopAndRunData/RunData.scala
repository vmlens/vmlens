package com.anarsoft.race.detection.process.loopAndRunData

import com.anarsoft.race.detection.createStacktrace.MethodEvent
import com.anarsoft.race.detection.util.EventArray

case class RunData(loopAndRunId: LoopAndRunId, methodEventArray: EventArray[MethodEvent]) {

}
