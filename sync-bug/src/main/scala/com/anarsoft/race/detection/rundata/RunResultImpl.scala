package com.anarsoft.race.detection.rundata

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementForResult
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileMemoryAccessElementForResult
import com.anarsoft.race.detection.reportbuilder.StaticMemoryAccessId

import scala.collection.mutable
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.warning.Warning

class RunResultImpl(loopAndRunId: LoopAndRunId,
                    nonVolatileMemoryAccessElements: List[GroupNonVolatileMemoryAccessElementForResult],
                    interleaveEvents: List[EventWithLoopAndRunId],
                    syncActionElements: List[GroupInterleaveElementForResult],
                    val warningIdList : Set[Warning],
                    val  isFailure: Boolean)  extends RunResult {

     def loopId: Int = loopAndRunId.loopId;

     def runId: Int = loopAndRunId.runId;

  def foreach(f: EventForReportElement => Unit): Unit = {
      for (element <- nonVolatileMemoryAccessElements) {
        for (sortedList <- element.sortedLists) {
          sortedList.foreach(f)
        }
      }
      for (element <- syncActionElements) {
        element.foreach(f)
      }
    }
  
    def dataRaceCount: Int = {
      val dataRaceSet = new mutable.HashSet[StaticMemoryAccessId]();
      for (elem <- nonVolatileMemoryAccessElements) {
        dataRaceSet.addAll(elem.dataRaces);
      }
      dataRaceSet.size
    }

  override def hasWarningForRun: Boolean = {
    var forRun = false;
    for( w <- warningIdList)  {
      forRun = forRun | w.forRun();
    }
    forRun
  }
}
