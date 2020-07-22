package com.anarsoft.plugin.sync.bug.startup

import com.vmlens.api.internal.ProcessFacade
import com.anarsoft.trace.agent.runtime.process._;
import  com.anarsoft.plugin.sync.bug.Activator


class CallbackStateMachineEclipse(val eventDir : String) extends PluginCallback {
  
  var processMode :  ProcessMode = ProcessExternal();
  
  
  
   def agentIsWaiting()
  {
    
  }
  
	def agentIsRunning()
	{
	    
	}
	
	def prozessResultNormalStop()
	{
	 startProcessEventJob(None);
	}
	
	def prozessResultTimeout(slidingWindowId : Int)
	{
	     
	    startProcessEventJob(Some(slidingWindowId));
	    
	   
	}
  
	
	
	
	
	 	def startProcessEventJob(maxSlidingWindowId : Option[Int]) =
	{
  	  
	 	  processMode match
	 	  {
	 	    
	 	    case ProcessExternal() =>
	 	      {
	 	        val data = ProcessFacade.process4Eclipse(eventDir,maxSlidingWindowId , 1 );
	 	        Activator.plugin.viewManager.update(data);
	 	      }
	 	    
	 	    case ProcessFromLaunch(processDataAndResult) =>
	 	      {
	 	        	val result = ProcessFacade.processEclipseFromLaunch(eventDir,maxSlidingWindowId  );
	            processDataAndResult.result.offer(  result );
	 	      }
	 	    
	 	    
	 	  }
	 	  
	 	  
	 	  
 
  
   processMode = ProcessExternal()
     

  	 
  	
	  
	}
	
	
	
	
	def setProcessFromLaunch(processDataAndResult : ProcessDataAndResult)
	{
	  processMode = ProcessFromLaunch(processDataAndResult);
	  
	  

	  
	}
	
	
	
	
	
	
	
  
}