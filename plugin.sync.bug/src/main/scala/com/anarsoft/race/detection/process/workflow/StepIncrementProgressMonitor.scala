package com.anarsoft.race.detection.process.workflow

class StepIncrementProgressMonitor[IN](val work : Int) extends Step[IN] {
  
  
    def execute( context : IN,progressMonitor : ProgressMonitor)
   {
      
      progressMonitor.done(work);
  
   }
  
  
}