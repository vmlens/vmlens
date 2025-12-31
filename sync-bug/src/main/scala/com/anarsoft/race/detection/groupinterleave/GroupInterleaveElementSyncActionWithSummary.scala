package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createdominatortree.CreateSummaryEvent
import com.anarsoft.race.detection.createdominatortreeevent.{BuildDominatorTreeContext, EventForSummary}
import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.GenericMemoryAccessKey
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.util.EventArray

class GroupInterleaveElementSyncActionWithSummary[MEMORY_ACCESS_KEY <: GenericMemoryAccessKey[MEMORY_ACCESS_KEY],
                                                  EVENT  <: SyncActionEventWithCompareType[EVENT] with WithSetStacktraceNode with EventForSummary[MEMORY_ACCESS_KEY]]
        (eventArray: EventArray[EVENT], createContainer: (EVENT) => EventContainer[EVENT])  extends GroupInterleaveElementSyncActionImpl[EVENT](eventArray,createContainer) {


  override def addToBuildDominatorTreeContext(context : BuildDominatorTreeContext): Unit = {
    new CreateSummaryEvent[MEMORY_ACCESS_KEY].process(eventArray,context);
  }
}
