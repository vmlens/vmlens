package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result.StackTraceGraph
import com.anarsoft.race.detection.model.result.StackTraceOrdinal
import com.anarsoft.race.detection.model.result.FieldAndArrayPerStackTraceFacade
import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap


class AnnotateStackTraceGraphAlgo4Monitor(val stackTraceGraph: StackTraceGraph) extends AnnotateStackTraceGraphAlgo[StackTraceData4Monitor]  {
  
  def create(ordinal : Int,stackTraceGraph: StackTraceGraph, packageName2Ordinal : ModelKey2OrdinalMap[String]) =
  {
    
    val stackTraceOrdinal =  new StackTraceOrdinal(ordinal)
    
    val methodModel = stackTraceGraph.getMethodModelForStackTraceNodeOrdinal( stackTraceOrdinal );
    
    
     val data=  new StackTraceData4Monitor(methodModel.isThreadSafe,stackTraceOrdinal);
     
  
    
     
     
     data.addPackageId(methodModel,packageName2Ordinal);
     
     
     
     data
     
  }
  
  
  def addFromChild(parent : StackTraceData4Monitor,child : StackTraceData4Monitor)
  {
    
    
     parent.calledMethodContainsMonitor = child.stackTraceOrdinal.hasMonitor(stackTraceGraph)  |   parent.calledMethodContainsMonitor | child.calledMethodContainsMonitor
    
    
      addAll2RoaringBitmap( parent.packageIdSet , child.packageIdSet)
   
  }
  
  
  
}