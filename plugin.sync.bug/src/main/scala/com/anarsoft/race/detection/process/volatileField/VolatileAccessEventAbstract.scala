package com.anarsoft.race.detection.process.volatileField

import com.anarsoft.race.detection.process.aggregate._;
import com.anarsoft.race.detection.process.syncAction.SyncAction
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinal
import com.anarsoft.race.detection.process.aggregate.WithLocationInClass
import com.anarsoft.race.detection.process.field.EventField;
import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric
import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.model.WithStatementPosition
import com.anarsoft.race.detection.process.setMonitorInfo.EventSetMonitorInfo
import com.anarsoft.race.detection.process.monitorRelation.MonitorIdBlockIdStackTraceOrdinal;
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo
import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId

import com.vmlens.api.MemoryAccessType
import com.vmlens.api.internal.IconRepository

trait VolatileAccessEventAbstract[ID_PER_OBJECT] extends SyncAction 
with EventSetStacktraceOrdinal 
with EventField  
with  SyncPointGeneric[ID_PER_OBJECT]  
{
  
  def methodIdOption  = Some(methodId);
  def isStackTraceIncompleteOption  = None;
  def idPerMethod     = fieldId;
  
  
   def isVolatile = true;
 
  def operation() : Int;
 
     
//   def needsMonitorInfo =  isParallized;  
     
     
def setStackTraceOrdinal(in: StackTraceOrdinalAndMethodId)
   {
     stackTraceOrdinal = in.ordinal;
   }
  
   
  var stackTraceOrdinal: Int;
 
  
  
  def methodId  : Int;
  def fieldId : Int;
  
  
 // def objectId : Option[Long]
 
  
  

  
  def icon()  =   IconRepository.getIconForOperationAndHasRace( new MemoryAccessType(operation) , false );
  
  
    def methodModel(modelFacade : ModelFacadeAll) = modelFacade.stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(new StackTraceOrdinal(stackTraceOrdinal));
  
   def operationText(modelFacade : ModelFacadeAll)  = MemoryAccessType.getName(operation) + " " +  getLocationInClass().getNameWithBold(modelFacade.fieldAndArrayFacade, modelFacade.stackTraceGraph);
  
  
}