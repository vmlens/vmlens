package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createdominatortreeevent.BuildDominatorTreeContext
import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.util.EventArray

class GroupInterleaveElementSyncActionNoOp[EVENT  <: SyncActionEventWithCompareType[EVENT] with WithSetStacktraceNode]
(eventArray: EventArray[EVENT], createContainer: (EVENT) => EventContainer[EVENT])  extends GroupInterleaveElementSyncActionImpl[EVENT](eventArray,createContainer) {


  override def addToBuildDominatorTreeContext(context : BuildDominatorTreeContext): Unit = {
    
  }

}
