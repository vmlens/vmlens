package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.event.control.RunEndEvent
import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementForResult
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileMemoryAccessElementForResult
import com.anarsoft.race.detection.reportbuilder.{EventForReportElement, StaticMemoryAccessId}

import scala.collection.mutable

class RunResultImpl(loopAndRunId: LoopAndRunId,
                    nonVolatileMemoryAccessElements: List[GroupNonVolatileMemoryAccessElementForResult],
                    interleaveEvents: List[EventWithLoopAndRunId],
                    syncActionElements: List[GroupInterleaveElementForResult],
                    val warningIdList : Set[Int],
                    val  isFailure: Boolean) extends RunResult {

  override def loopId: Int = loopAndRunId.loopId;

  override def runId: Int = loopAndRunId.runId;

  override def foreach(f: EventForReportElement => Unit): Unit = {
    for (element <- nonVolatileMemoryAccessElements) {
      for (sortedList <- element.sortedLists) {
        sortedList.foreach(f)
      }
    }
    for (element <- syncActionElements) {
      element.foreach(f)
    }
  }

  override def compare(that: RunResult): Int = {
    if (isFailure && that.isFailure) {
      dataRaceCount.compare(that.dataRaceCount)
    }
    else if (isFailure) {
      warningIdList.size + 1
    } else {
      -1
    }
  }
  

  override def dataRaceCount: Int = {
    val dataRaceSet = new mutable.HashSet[StaticMemoryAccessId]();
    for (elem <- nonVolatileMemoryAccessElements) {
      dataRaceSet.addAll(elem.dataRaces);
    }
    dataRaceSet.size
  }
}
