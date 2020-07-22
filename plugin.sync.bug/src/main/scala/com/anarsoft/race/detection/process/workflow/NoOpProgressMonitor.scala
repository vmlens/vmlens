package com.anarsoft.race.detection.process.workflow

class NoOpProgressMonitor extends ProgressMonitor {
  
   def done( work : Int)
   {
     
   }
   
   def isCanceled() = false;
  
}