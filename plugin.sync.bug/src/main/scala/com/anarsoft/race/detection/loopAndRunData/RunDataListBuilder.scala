package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.syncactiongroup.SyncActionElementForProcess

import java.util

trait RunDataListBuilder {
  def add(loopAndRunId: LoopAndRunId, methodEventList: util.List[MethodEvent]): Unit;

  def add(loopAndRunId: LoopAndRunId, syncActionElements: List[SyncActionElementForProcess]): Unit;
}
