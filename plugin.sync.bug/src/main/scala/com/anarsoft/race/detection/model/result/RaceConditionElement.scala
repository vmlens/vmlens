package com.anarsoft.race.detection.model.result

import scala.collection.mutable.ArrayBuffer
import com.anarsoft.integration._
import com.vmlens.api._
import com.vmlens.api.internal._
import scala.collection.mutable.HashMap



class RaceConditionElement(  val locationInClass : LocationInClass, val stackTraceOrdinal : StackTraceOrdinal, val operation : Int, val threadId : Long, val stackTraceId2MonitorId : HashMap[Int,Int])
   extends  IssuePartElement  
{
  
  
   def searchData(ModelFacadeAll : ModelFacadeAll) = None;
  
  def name(ModelFacadeAll : ModelFacadeAll) =   ModelFacadeAll.threadNames.id2ThreadName.get(threadId).get  //getQualifiedFieldName(viewTyp.ModelFacadeAll.stackTraceGraph);

    def nameWithHtml( ModelFacadeAll : ModelFacadeAll) = "<!-- skipAtCompare --> " + name(ModelFacadeAll);

  def icon(ModelFacadeAll : ModelFacadeAll)  = {
    
            IconRepository.getIconForOperation(  new  MemoryAccessType (operation))
  }
  
  
    def children( ModelFacadeAll : ModelFacadeAll)       =
  {
         val list = new ArrayBuffer[IssuePartElement]();
     
    
     ModelFacadeAll.stackTraceGraph.formHereToRoot(stackTraceOrdinal ,
          
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
    
    
    
     def name4Yaml( ModelFacadeAll : ModelFacadeAll) = "thread: " +  name(ModelFacadeAll);
     def title4Yaml(position : Int) = Some(
         
     MemoryAccessType.getYaml(operation)
     
     );
    
    
    
    
    
    
    
    
    
    
    
    
      
}