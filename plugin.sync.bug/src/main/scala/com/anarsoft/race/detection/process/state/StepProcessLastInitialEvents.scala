package com.anarsoft.race.detection.process.state


import com.anarsoft.race.detection.process.workflow.SingleStep

class StepProcessLastInitialEvents extends SingleStep[ContextInitialState] {
  
  
  def execute(context : ContextInitialState)
  {
   
     InitialStateReadCallback.serialize(context.initialStateList , context.stream4State);
     context.stream4State.close();
  }
}