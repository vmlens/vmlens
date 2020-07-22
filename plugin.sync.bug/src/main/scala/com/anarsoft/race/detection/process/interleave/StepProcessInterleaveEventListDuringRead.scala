package com.anarsoft.race.detection.process.interleave

import com.anarsoft.race.detection.process.workflow.SingleStep

class StepProcessInterleaveEventListDuringRead extends SingleStep[ContextInterleave]  {
  
  def execute(context : ContextInterleave)
  {
     
  
  context.interleaveEventList.processListDuringRead();
  
   
   
  }
}