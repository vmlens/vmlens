package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.model.result._;
import  com.vmlens.api.internal.reports.Model2View

trait InterleaveNamesAsLock {
  
  
    def stackTraceOrdinal : Int;
  
    def isLockEnter() : Boolean;
     

  
    def methodModel(modelFacade : ModelFacadeAll) = modelFacade.stackTraceGraph.methodModel4Lock(stackTraceOrdinal);
   

   def operationText(modelFacade : ModelFacadeAll)  = {
     
     val text = modelFacade.stackTraceGraph.operationText4Lock(stackTraceOrdinal);
   
     if( text.endsWith("<strong>await</strong><wbr>()") )
     {
       if(isLockEnter())
       {
          Model2View.makeBreakable(   "<signal received> " ) + text;
       }
       else
       {
         Model2View.makeBreakable(  "<wait for signal> "  ) + text;
       }
       
       
     }
     else
     {
       text
     }
   
   }
     
 
  
  
}