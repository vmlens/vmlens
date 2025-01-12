package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.interleave.InterleaveEvent
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementForProcess

import java.util

trait RunDataListBuilder {

  def addInterleaveEvents(loopAndRunId: LoopAndRunId, interleaveEventList: List[InterleaveEvent]): Unit;

  def addMethodEvents(loopAndRunId: LoopAndRunId, methodEventList: util.List[MethodEvent]): Unit;

  def addSyncActionElements(loopAndRunId: LoopAndRunId, syncActionElements: List[GroupInterleaveElementForProcess]): Unit;
}
