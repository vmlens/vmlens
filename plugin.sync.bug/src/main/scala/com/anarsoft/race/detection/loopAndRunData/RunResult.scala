package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.nonvolatilememoryaccessgroup.NonVolatileMemoryAccessElementForResult

case class RunResult(loopAndRunId: LoopAndRunId,
                     nonVolatileMemoryAccessElements: List[NonVolatileMemoryAccessElementForResult]) {

}
