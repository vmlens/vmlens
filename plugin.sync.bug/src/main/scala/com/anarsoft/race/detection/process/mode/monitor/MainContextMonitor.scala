package com.anarsoft.race.detection.process.mode.monitor

import com.anarsoft.race.detection.process.monitor._;
import com.anarsoft.race.detection.process.result._;
import com.anarsoft.race.detection.process.syncAction._;
import com.anarsoft.race.detection.process.field._;
import com.anarsoft.race.detection.process.aggregate.ContextBuildAggregate
import com.anarsoft.race.detection.process._
import com.anarsoft.race.detection.process.facade.ContextFacade

import com.anarsoft.race.detection.process.sharedState.ContextSharedMonitor
import com.anarsoft.race.detection.process.interleave.InterleaveEventList


class MainContextMonitor  extends
         ContextReadDescriptionMethodOnly
     with ContextStackTraceGraphBuilder   
     with NoOpContext
     with ContextSharedMonitor
     with ContextCreateModelFacadeMonitor 
      with ContextProcessTemplate
      with ContextReadMethodAndMonitorEvent
      with ContextMethodId2Ordinal
{
   var interleaveEventList : InterleaveEventList = null;

  
  
}