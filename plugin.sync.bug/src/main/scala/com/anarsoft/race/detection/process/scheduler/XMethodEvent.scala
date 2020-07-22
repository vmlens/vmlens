package com.anarsoft.race.detection.process.scheduler


import com.anarsoft.race.detection.process.partialOrder.EventWithOrder;
 
 import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinal 
 import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId
 import com.anarsoft.race.detection.model.result.ModelFacadeAll
 import com.anarsoft.race.detection.model.result.StackTraceOrdinal 
 
 
trait XMethodEvent extends SchedulerEvent with InterleaveEventStatement  with EventSetStacktraceOrdinal {
  
   
   def isStackTraceIncompleteOption = None;
   
   
    var  stackTraceOrdinal  = -1;
   
    
     def setStackTraceOrdinal(in: StackTraceOrdinalAndMethodId)
   {
     stackTraceOrdinal = in.ordinal;
   }

   
  def threadId  : Long;

 
  
       def isSame(InterleaveEventStatement :  InterleaveEventStatement)  = false;
       
       
       
  def  getOpText() : String;
       
       
      def methodModel(modelFacade : ModelFacadeAll) = modelFacade.stackTraceGraph.getSecondaryMethodModel(stackTraceOrdinal);
  
   def operationText(modelFacade : ModelFacadeAll)  = getOpText()  + " " + modelFacade.stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(new StackTraceOrdinal(stackTraceOrdinal)).getFullNameWithBoldName();
     
       
       
       
}