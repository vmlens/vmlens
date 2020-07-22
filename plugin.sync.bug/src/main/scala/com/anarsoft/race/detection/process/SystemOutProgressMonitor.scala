package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.workflow.ProgressMonitor

class SystemOutProgressMonitor extends ProgressMonitor  {
  
  var current = 0;
  
  
  def done(work : Int)
  {
    
    current = current + work;
    
    println("done " +  current);
  }
  
  def isCanceled() = false;
  
}