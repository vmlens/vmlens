package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.partialorder.{PartialOrderContainer, PartialOrderImpl}
import com.anarsoft.race.detection.process.loopAndRunData.RunData
import com.anarsoft.race.detection.process.main.ProcessRun
import com.anarsoft.race.detection.reportbuilder.RunReportBuilder

class ProcessRunImpl extends ProcessRun {

  def process(runData: RunData, runReportBuilder: RunReportBuilder): Unit = {
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

    val partialOrder = new PartialOrderImpl(new PartialOrderContainer());

    for (nonVolatileElement <- runData.nonVolatileMemoryAccessElements) {
      nonVolatileElement.sort(partialOrder, runReportBuilder)
    }
  }

}
