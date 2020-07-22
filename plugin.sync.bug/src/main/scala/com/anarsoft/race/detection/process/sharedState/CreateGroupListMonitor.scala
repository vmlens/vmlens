package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result._

class CreateGroupListMonitor(val stackTraceGraphPackageAnnotation : StackTraceGraphPackageAnnotation) extends CreateGroupList[MethodWithMonitorBuilder] {
  
  

  
   def createBuilder(methodOrdinal : MethodOrdinal, group : Int,stackTraceOrdinal: StackTraceOrdinal, stackTraceGraph: StackTraceGraph ) =
     
    
     
     
     new MethodWithMonitorBuilder(methodOrdinal, group,stackTraceOrdinal.hasMonitor(stackTraceGraph) ,  methodOrdinal.getMethodModel(   stackTraceGraph ).isThreadSafe)
   
   
   
   def processChilds(parent :MethodWithMonitorBuilder ) = ! parent.isThreadSafe
   
   
   def alwaysProcessRoot = false;
}