package com.anarsoft.race.detection.process.partialOrder

import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process.syncAction.ContextProcessSyncAction

class StepCreateNewPartialOrderBuilder4SlidingWindowId extends SingleStep[ContextCreatePartialOrder] {
  
  
  def execute(context : ContextCreatePartialOrder)
  {
     
    context.partialOrder4SlidingWindowIdBuilder = new PartialOrderBuilder();
       
      
  }
  
  def desc = "";
  
}