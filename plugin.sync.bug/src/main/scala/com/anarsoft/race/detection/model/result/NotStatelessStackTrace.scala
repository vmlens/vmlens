package com.anarsoft.race.detection.model.result

import scala.collection.mutable.ArrayBuffer
import com.vmlens.api.internal.IconRepository

class NotStatelessStackTrace(val count : Int, val stackTraceOrdinal : StackTraceOrdinal) extends IssuePartElement  {
  
  def name( modelFacade : ModelFacade) = count + ". StackTrace"
  def icon(modelFacade : ModelFacade)  = IconRepository.STACK_FRAME
  
    def nameWithHtml( modelFacade : ModelFacade) = name(modelFacade);
  
 def children( modelFacade : ModelFacade)       =
  {
         val list = new ArrayBuffer[IssuePartElement]();
     
    
     modelFacade.stackTraceGraph.formHereToRoot(stackTraceOrdinal ,
          
       s =>
         {
              list.append(  new StackTraceModel(s) );
      
         }
       
      
      
      
      )
       list;
  }
  
  
  def name4Yaml( modelFacade : ModelFacade) = name(modelFacade);
  def title4Yaml(position : Int) = Some(count + ". StackTrace");
  
  
  
    def searchData(modelFacade : ModelFacade) = None;
  
  
}