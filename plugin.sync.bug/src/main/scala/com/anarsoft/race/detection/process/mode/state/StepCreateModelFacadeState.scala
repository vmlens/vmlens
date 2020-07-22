package com.anarsoft.race.detection.process.mode.state

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


class StepCreateModelFacadeState extends SingleStep[ContextCreateModelFacadeState]  {
  
  
  def execute(context : ContextCreateModelFacadeState)
  {
    
    /*
     *  val  sharedStateCollection : ArrayBuffer[MethodWithSharedState] ,
    val  fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade   ,
    val stackTraceGraph : StackTraceGraph ,
     val fieldAndArrayFacade : FieldAndArrayPerMethodFacade,
     val threadNames : ThreadFacade
     */
    
    context.modelFacadeState = new ModelFacadeState(context.sharedStateCollection ,
       context.fieldAndArrayPerStackTraceFacade  , 
       context.stackTraceGraph , context.fieldAndArrayFacade , context.threadNames.createThreadFacade(),
       context.notStateless   , context.ownerId2Name , context.stackTraceGraphStateAnnotation ,
       context.agentLog 
      
    );
  }
  
}