package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.process.loopAndRunData.RunData
import com.anarsoft.race.detection.report.LoopReportBuilder

class ProcessRun {

  def process(runData: RunData, loopReportBuilder: LoopReportBuilder): Unit = {
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


  }

}
