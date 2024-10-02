package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId
import com.anarsoft.race.detection.event.interleave.RunEndEvent
import com.anarsoft.race.detection.groupnonvolatilememoryaccess.NonVolatileMemoryAccessElementForResult
import com.anarsoft.race.detection.groupsyncaction.SyncActionElementForResult
import com.anarsoft.race.detection.reportbuilder.{EventForRunElement, StaticMemoryAccessId}

import scala.collection.mutable

case class RunResult(loopAndRunId: LoopAndRunId,
                     nonVolatileMemoryAccessElements: List[NonVolatileMemoryAccessElementForResult],
                     interleaveEvents: List[EventWithLoopAndRunId],
                     syncActionElements: List[SyncActionElementForResult])
  extends Ordered[RunResult] {

  def foreach(f: EventForRunElement => Unit): Unit = {
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
      1
    } else {
      -1
    }
  }

  def isFailure: Boolean = {
    var hasEndEvent = false;
    for (interleave <- interleaveEvents) {
      if (interleave.isInstanceOf[RunEndEvent]) {
        hasEndEvent = true
      }
    }
    !hasEndEvent;
  }

  def dataRaceCount: Int = {
    val dataRaceSet = new mutable.HashSet[StaticMemoryAccessId]();
    for (elem <- nonVolatileMemoryAccessElements) {
      dataRaceSet.addAll(elem.dataRaces);
    }
    dataRaceSet.size
  }
}
