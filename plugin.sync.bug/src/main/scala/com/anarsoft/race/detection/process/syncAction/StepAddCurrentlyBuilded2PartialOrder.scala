package com.anarsoft.race.detection.process.syncAction


import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process.partialOrder.ContextCreatePartialOrder
import com.anarsoft.race.detection.process.partialOrder.PartialOrderBuilder

class StepAddCurrentlyBuilded2PartialOrder extends SingleStep[ContextCreatePartialOrder] {
  
  
  def execute(context : ContextCreatePartialOrder)
  {  
      context.partialOrder.add( context.partialOrder4SlidingWindowIdBuilder );
      
       context.partialOrder4SlidingWindowIdBuilder = new PartialOrderBuilder();
  }
  
  def desc = "";
  
}