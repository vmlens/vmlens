package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementForResult
import com.anarsoft.race.detection.groupnonvolatilememoryaccess.GroupNonVolatileMemoryAccessElementForResult
import com.anarsoft.race.detection.loopAndRunData.{RunData, RunResult, RunResultImpl}
import com.anarsoft.race.detection.partialorder.{PartialOrderContainer, PartialOrderImpl}
import com.anarsoft.race.detection.process.main.ProcessRun

import scala.collection.mutable.ArrayBuffer

class ProcessRunImpl extends ProcessRun {

  def process(runData: RunData): RunResult = {
    /*
    check run terminated (e.g. junit assertion failed)
    should createreport
    createreport sync action
    createreport memory
    should createreport further
    createreport methods
    set stacktraces
    addMethodEvents to runBuilder
    addMethodEvents runBuilder to loop builder
     */

    val partialOrderContainer = new PartialOrderContainer();

    for (syncActionElement <- runData.syncActionElements) {
      syncActionElement.addToPartialOrderBuilder(partialOrderContainer);
    }

    val partialOrder = new PartialOrderImpl(partialOrderContainer);
    val nonVolatileResult = new ArrayBuffer[GroupNonVolatileMemoryAccessElementForResult]();
    for (nonVolatileElement <- runData.nonVolatileMemoryAccessElements) {
      nonVolatileResult.append(nonVolatileElement.sort(partialOrder));
    }


    new RunResultImpl(runData.loopAndRunId, nonVolatileResult.toList, runData.controlEvents,
      runData.syncActionElements);
  }
}
