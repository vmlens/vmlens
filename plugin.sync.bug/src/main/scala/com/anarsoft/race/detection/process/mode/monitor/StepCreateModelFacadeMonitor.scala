package com.anarsoft.race.detection.process.mode.monitor

import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.model.description.ThreadNames;
import scala.collection.mutable.HashSet;
import com.anarsoft.integration._;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.workflow.SingleStep
import  com.anarsoft.race.detection.process.filter.FilterMap
import  com.anarsoft.race.detection.process.detectRaceConditions.EventWrapperDetectRaceConditions
import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import com.vmlens.api.MethodDescription

class StepCreateModelFacadeMonitor extends SingleStep[ContextCreateModelFacadeMonitor]  {
  
  
  def execute(context : ContextCreateModelFacadeMonitor)
  {
    
    /*
     *  val  sharedStateCollection : ArrayBuffer[MethodWithSharedState] ,
    val  fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade   ,
    val stackTraceGraph : StackTraceGraph ,
     val fieldAndArrayFacade : FieldAndArrayPerMethodFacade,
     val threadNames : ThreadFacade
     */
    
    context.modelFacadeMonitor = new ModelFacadeMonitor(context.sharedStateCollection ,
       context.stackTraceGraph , context.threadNames.createThreadFacade() , context.deadlocks   , context.ownerId2Name , context.stackTraceGraphMonitorAnnotation ,
       context.agentLog
      
    );
  }
  
}