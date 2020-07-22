package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.process.workflow.SingleStep
import scala.collection.mutable.ArrayBuffer


class StepCreateSharedState(  ) extends SingleStep[ContextSharedState] {
  
 
  
  
  def execute(context : ContextSharedState)
  {
     context.sharedStateCollection = 
       CreateSharedStateGroupListFacade.createRoot(context.stackTraceGraph.rootSet,  context.stackTraceGraph , context.fieldAndArrayPerStackTraceFacade,context.stackTraceGraphStateAnnotation) ;
 

  }
  
}