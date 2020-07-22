package com.anarsoft.race.detection.model.result

import scala.collection.mutable.ArrayBuffer
import com.anarsoft.integration._
import com.vmlens.api._
import com.vmlens.api.internal._
import scala.collection.mutable.HashMap



class RaceConditionElement(  val locationInClass : LocationInClass, val stackTraceOrdinal : StackTraceOrdinal, val operation : Int, val threadId : Long, val stackTraceId2MonitorId : HashMap[Int,Int])
   extends  IssuePartElement  
{
  
  
   def searchData(modelFacade : ModelFacade) = None;
  
  def name(modelFacade : ModelFacade) =   modelFacade.threadNames.id2ThreadName.get(threadId).get  //getQualifiedFieldName(viewTyp.modelFacade.stackTraceGraph);

    def nameWithHtml( modelFacade : ModelFacade) = "<!-- skipAtCompare --> " + name(modelFacade);

  def icon(modelFacade : ModelFacade)  = {
    
            IconRepository.getIconForOperation(  new  MemoryAccessType (operation))
  }
  
  
    def children( modelFacade : ModelFacade)       =
  {
         val list = new ArrayBuffer[IssuePartElement]();
     
    
     modelFacade.stackTraceGraph.formHereToRoot(stackTraceOrdinal ,
          
       s =>
         {
          stackTraceId2MonitorId.get(s.ordinal) match
        {
          case None =>
            {
               list.append( new StackTraceModel(s) )
            }
          
          case Some(monitorId) =>
            {
               list.append( new StackTraceModelWithOneMonitor(s , monitorId)  )
            }
                
         }
            
         }
      )
       list;
  }
    
    
    
     def name4Yaml( modelFacade : ModelFacade) = "thread: " +  name(modelFacade);
     def title4Yaml(position : Int) = Some(
         
     MemoryAccessType.getYaml(operation)
     
     );
    
    
    
    
    
    
    
    
    
    
    
    
      
}