package com.anarsoft.race.detection.model.result

import com.anarsoft.integration._
import scala.collection.mutable.ArrayBuffer
import com.vmlens.api._
import com.vmlens.api.internal._



case class DeadlockPart( val parent : StackTraceOrdinalAndMonitor , val child : StackTraceOrdinalAndMonitor,val threadId : Long) extends  IssuePartElement 
{
  
     def searchData(modelFacade : ModelFacade) = None;
  
  
     def showDetailsInOverview() = false;
  
  def methodName(modelFacade : ModelFacade) = parent.ordinal.name(modelFacade.stackTraceGraph);
  
//   def getMonitorName(modelFacade : ModelFacade) = MonitorAccess.getName(modelFacade, parent.monitorAggregateOrdinal);
//  
//  def getMethodNameAndPosition(stackTraceGraph : StackTraceGraph,monitorFacade : MonitorFacade) = 
//    MonitorAccess.getMethodNameAndPosition(stackTraceGraph, monitorFacade,  parent.monitorAggregateOrdinal)
//  
  
 
   
  
   def name(modelFacade : ModelFacade) = 
   modelFacade.threadNames.getThreadName(threadId)
 
     def nameWithHtml( modelFacade : ModelFacade) = "<!-- skipAtCompare --> " + name(modelFacade);
   
   
  def icon(modelFacade : ModelFacade)  = IconRepository.MONITOR_IN_THREAD
  
  def children( modelFacade : ModelFacade)       =
  {
         val list = new ArrayBuffer[IssuePartElement]();
     
    
     modelFacade.stackTraceGraph.formHereToRoot(child.ordinal ,
          
       s =>
         {
           if(  ( child.ordinal == parent.ordinal) && s ==  child.ordinal )
           {
             list.append(  new StackTraceModelWithTwoMonitors(child.ordinal , parent.monitorId , child.monitorId)  );
           }
           else
           if( s ==  child.ordinal )
           {
             list.append( new  StackTraceModelWithOneMonitor(child.ordinal ,  child.monitorId) ) ;
           }
           else if( s ==  parent.ordinal )
           {
              list.append(  new StackTraceModelWithOneMonitor(parent.ordinal ,  parent.monitorId) );
           }
           else
           {
              list.append(  new StackTraceModel(s) );
           }
           
       
         }
       
      
      
      
      )
       list;
  }
   
   
    
     def name4Yaml( modelFacade : ModelFacade) = "thread: " +  name(modelFacade);
     def title4Yaml(position : Int) = Some(
         
       if( position == 0  )
       {
          "parent2Child:"
       }
       else
       {
         "child2Parent:"
       }
     
     );
    
 
  
  
 
}
