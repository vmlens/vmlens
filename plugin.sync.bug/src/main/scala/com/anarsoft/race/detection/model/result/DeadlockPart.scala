package com.anarsoft.race.detection.model.result

import com.anarsoft.integration._
import scala.collection.mutable.ArrayBuffer
import com.vmlens.api._
import com.vmlens.api.internal._



case class DeadlockPart( val parent : StackTraceOrdinalAndMonitor , val child : StackTraceOrdinalAndMonitor,val threadId : Long) extends  IssuePartElement 
{
  
     def searchData(ModelFacadeAll : ModelFacadeAll) = None;
  
  
     def showDetailsInOverview() = false;
  
  def methodName(ModelFacadeAll : ModelFacadeAll) = parent.ordinal.name(ModelFacadeAll.stackTraceGraph);
  
//   def getMonitorName(ModelFacadeAll : ModelFacadeAll) = MonitorAccess.getName(ModelFacadeAll, parent.monitorAggregateOrdinal);
//  
//  def getMethodNameAndPosition(stackTraceGraph : StackTraceGraph,monitorFacade : MonitorFacade) = 
//    MonitorAccess.getMethodNameAndPosition(stackTraceGraph, monitorFacade,  parent.monitorAggregateOrdinal)
//  
  
 
   
  
   def name(ModelFacadeAll : ModelFacadeAll) = 
   ModelFacadeAll.threadNames.getThreadName(threadId)
 
     def nameWithHtml( ModelFacadeAll : ModelFacadeAll) = "<!-- skipAtCompare --> " + name(ModelFacadeAll);
   
   
  def icon(ModelFacadeAll : ModelFacadeAll)  = IconRepository.MONITOR_IN_THREAD
  
  def children( ModelFacadeAll : ModelFacadeAll)       =
  {
         val list = new ArrayBuffer[IssuePartElement]();
     
    
     ModelFacadeAll.stackTraceGraph.formHereToRoot(child.ordinal ,
          
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
   
   
    
     def name4Yaml( ModelFacadeAll : ModelFacadeAll) = "thread: " +  name(ModelFacadeAll);
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
