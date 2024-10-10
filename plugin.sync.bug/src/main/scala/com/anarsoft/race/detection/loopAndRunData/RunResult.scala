package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId
import com.anarsoft.race.detection.event.interleave.RunEndEvent
import com.anarsoft.race.detection.groupnonvolatilememoryaccess.NonVolatileMemoryAccessElementForResult
import com.anarsoft.race.detection.groupsyncaction.SyncActionElementForResult
import com.anarsoft.race.detection.reportbuilder.{EventForRunElement, StaticMemoryAccessId}

import scala.collection.mutable

trait RunResult extends Ordered[RunResult] {

  def foreach(f: EventForRunElement => Unit): Unit;

  def isFailure: Boolean;

  def dataRaceCount: Int;

  def loopId: Int;

  def runId: Int;;
  
}
