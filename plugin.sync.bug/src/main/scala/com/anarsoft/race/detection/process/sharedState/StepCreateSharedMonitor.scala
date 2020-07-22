package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.process.workflow.SingleStep

class StepCreateSharedMonitor extends SingleStep[ContextSharedMonitor] {
  
 
  
  
  def execute(context : ContextSharedMonitor)
  {
     context.sharedStateCollection = 
       CreateMonitorGroupListFacade.createRoot(context.stackTraceGraph.rootSet,  context.stackTraceGraph , context.stackTraceGraphMonitorAnnotation) ;
 

  }
  
}