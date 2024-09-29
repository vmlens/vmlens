package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.loopAndRunData.{RunData, RunResult}
import com.anarsoft.race.detection.nonvolatilememoryaccessgroup.NonVolatileMemoryAccessElementForResult
import com.anarsoft.race.detection.partialorder.{PartialOrderContainer, PartialOrderImpl}
import com.anarsoft.race.detection.process.main.ProcessRun
import com.anarsoft.race.detection.reportbuilder.RunReportBuilderAdapter

import scala.collection.mutable.ArrayBuffer

class ProcessRunImpl extends ProcessRun {

  def process(runData: RunData): RunResult = {
    /*
    check run terminated (e.g. junit assertion failed)
    should process
    process sync action
    process memory
    should process further
    process methods
    set stacktraces
    add to runBuilder
    add runBuilder to loop builder
     */

    val partialOrderContainer = new PartialOrderContainer();

    for (syncActionElement <- runData.syncActionElements) {
      syncActionElement.addToPartialOrderBuilder(partialOrderContainer);
    }

    val partialOrder = new PartialOrderImpl(partialOrderContainer);

    val nonVolatileResult = new ArrayBuffer[NonVolatileMemoryAccessElementForResult]();
    
    for (nonVolatileElement <- runData.nonVolatileMemoryAccessElements) {
      nonVolatileResult.append(nonVolatileElement.sort(partialOrder));
    }

    RunResult(runData.loopAndRunId, nonVolatileResult.toList);
  }
}
