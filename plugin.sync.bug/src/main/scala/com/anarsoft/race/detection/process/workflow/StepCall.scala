package com.anarsoft.race.detection.process.workflow



class StepCall[C]  ( val f : C => Unit  ) extends SingleStep[C] {
  
  def execute( context : C )
  {
    f(context);
  }
  
  

  
}