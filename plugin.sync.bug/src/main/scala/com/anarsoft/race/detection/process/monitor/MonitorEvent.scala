package com.anarsoft.race.detection.process.monitor

import com.anarsoft.race.detection.process.gen._;
import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric;
import com.anarsoft.race.detection.process.monitorRelation.Event4MonitorRelation
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinal;
import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.model.WithStatementPosition
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinalVisitor
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports.element._;
import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId


trait MonitorEvent extends SyncPointGeneric[Int] with Event4MonitorRelation with EventSetStacktraceOrdinal   {
 
  
   var  stackTraceOrdinal  = -1;
   
def setStackTraceOrdinal(in: StackTraceOrdinalAndMethodId)
   {
     stackTraceOrdinal = in.ordinal;
   }
  

  def position() : Int;

   
  
  def idPerMemoryLocation = monitorId;
  def compareIdPerMemoryLocation(  other : Int)  = java.lang.Integer.compare(idPerMemoryLocation, other);
  
  def monitorId : Int;
  
  
  def isMonitorEnter() : Boolean;
  
  def accept(visitor : MonitorVisitor);
  
  
  
   
 
  
  def accept(visitor : EventSetStacktraceOrdinalVisitor)
    {
      visitor.visit(this);
    }
  
  
  
   
        
  
   /*
    *  def position() : Int;
    *  
    *  	public static void waitCall(Object in, int methodId) throws InterruptedException {
		try {
			monitorExit(in, methodId, -2);
			in.wait();
		} finally {
			monitorEnter(in, methodId, -1);
		}

	}
    *  
    *  
    */
   
   
  
      
     def getOpText() =
       if(isMonitorEnter)
       {
         if(position() == -1)
         {
           "wait exit "
         }
         else
         {
             "monitor enter "  ;
         }
         
         
         
       
       }
       else
       {
          if(position() == -2)
         {
           "wait enter"
         }
         else
         {
            "monitor exit ";
         }
         
         
         
        
       }
  
  
    def methodModel(modelFacade : ModelFacadeAll) =  modelFacade.stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(new StackTraceOrdinal(stackTraceOrdinal));
  
   def operationText(modelFacade : ModelFacadeAll)  = getOpText()
  
  
  
  
  
  
  
}