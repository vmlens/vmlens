package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.load.FilePosition
import com.anarsoft.race.detection.loopAndRunData.LoopAndRunId

case class LoopAndRunIdToFilePosition(map: Map[LoopAndRunId, FilePosition])
