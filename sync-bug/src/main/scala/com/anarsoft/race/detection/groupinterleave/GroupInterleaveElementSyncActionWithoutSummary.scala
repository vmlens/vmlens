package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createdominatortreeevent.{BuildDominatorTreeContext, CreateDominatorTreeEvent}
import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.util.EventArray

class GroupInterleaveElementSyncActionWithoutSummary[EVENT  <: SyncActionEventWithCompareType[EVENT] with WithSetStacktraceNode with CreateDominatorTreeEvent]
(eventArray: EventArray[EVENT], createContainer: (EVENT) => EventContainer[EVENT])  extends GroupInterleaveElementSyncActionImpl[EVENT](eventArray,createContainer) {
  
  override def addToBuildDominatorTreeContext(context : BuildDominatorTreeContext): Unit = {
    for(event <- eventArray) {
      context.eventList.append(event)
    }
  }

}
