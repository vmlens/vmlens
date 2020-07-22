package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.workflow.SingleStep;

class StepCreateHeapDump extends SingleStep[NoOpContext] {
  
   def execute(context : NoOpContext)
  {
    
     println("please generate Heap Dump");
     
     
     Thread.sleep(1000 * 60 * 3);
    
    
    
    
  }
  
  def desc = "";
  
  
}