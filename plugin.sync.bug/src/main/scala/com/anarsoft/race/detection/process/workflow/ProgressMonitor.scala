package com.anarsoft.race.detection.process.workflow

trait ProgressMonitor {
 
  def done( work : Int);
  def isCanceled() : Boolean;
  
  
  
}