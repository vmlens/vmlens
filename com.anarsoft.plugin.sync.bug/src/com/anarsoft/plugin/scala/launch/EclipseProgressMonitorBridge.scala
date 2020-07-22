package com.anarsoft.plugin.scala.launch



import org.eclipse.core.runtime.IProgressMonitor
import com.anarsoft.race.detection.process.workflow.ProgressMonitor
import com.anarsoft.plugin.scala.launch.callback._;

class EclipseProgressMonitorBridge(val progressMonitor : IProgressMonitor,val callbackCancelState : CallbackCancelState) extends  ProgressMonitor  {
  
  def isCanceled() = {
    
    if(callbackCancelState.isCanceled)
    {
      progressMonitor.setCanceled(true)
    }
      
    progressMonitor.isCanceled();
  }
 
  def done(work : Int)
  {
      progressMonitor.worked(work);
  }
  
  
  
  
}