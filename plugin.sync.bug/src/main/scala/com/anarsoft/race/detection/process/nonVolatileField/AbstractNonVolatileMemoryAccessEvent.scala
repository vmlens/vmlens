package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.aggregate._;
import com.anarsoft.race.detection.process.setStacktraceOrdinal._;
import com.anarsoft.race.detection.process.detectRaceConditions._;
import com.anarsoft.race.detection.process.setStacktraceOrdinal._;
import com.vmlens.api.MemoryAccessType
import com.vmlens.api.internal.IconRepository
import com.anarsoft.race.detection.model.result.ModelFacadeAll
import com.anarsoft.race.detection.model.result.StackTraceOrdinal
import  com.vmlens.api.internal.reports.Model2View

trait AbstractNonVolatileMemoryAccessEvent[OBJECT_ID] extends EventStackTraceOrdinalAggregate[OBJECT_ID] with EventDetectRaceConditions[OBJECT_ID] with EventSetStacktraceOrdinal {
  
  
  
  def isStackTraceIncompleteOption = Some(stackTraceIncomplete);
  def methodIdOption = Some(methodId);
  
   def methodId  : Int;
   def stackTraceIncomplete  : Boolean;
  
  
   def isVolatile = false;
   
   
   
   def icon()  =   IconRepository.getIconForOperationAndHasRace( new MemoryAccessType(operation) , isRace );
  
  
    def methodModel(modelFacade : ModelFacadeAll) = modelFacade.stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(new StackTraceOrdinal(stackTraceOrdinal));
  
   def operationText(modelFacade : ModelFacadeAll)  = MemoryAccessType.getName(operation) +  " " + raceText +  getLocationInClass().getNameWithBold(modelFacade.fieldAndArrayFacade, modelFacade.stackTraceGraph);
  
   def raceText = if(isRace)
   {
     Model2View.makeBreakable( "<race> ")
   }
   else
   {
     ""
   }
   
   
}