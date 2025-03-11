package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.control.ControlEvent
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElement
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileElement

import java.util

trait RunDataListBuilder {

  def addControlEvents(loopAndRunId: LoopAndRunId, interleaveEventList: List[ControlEvent]): Unit;

  def addMethodEvents(loopAndRunId: LoopAndRunId, methodEventList: util.List[MethodEvent]): Unit;

  def addSyncActionElements(loopAndRunId: LoopAndRunId, syncActionElements: List[GroupInterleaveElement]): Unit;

  def addNonVolatileElements(loopAndRunId: LoopAndRunId, nonVolatileElements: List[GroupNonVolatileElement]): Unit;
}
