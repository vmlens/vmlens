package com.anarsoft.plugin.sync.bug.startup

import com.anarsoft.trace.agent.runtime.process._;
import com.anarsoft.trace.agent.runtime.process.TimeValues;
import java.util.concurrent.LinkedBlockingQueue


class EclipsePluginController(val pluginController : PluginController, val pluginCallback : CallbackStateMachineEclipse)  extends Runnable {
  
     var lastTimeFileSystemChecked = System.currentTimeMillis();
  
  def execLicenseOk()
  {
      val fromLaunch = EclipsePluginController.queue.poll();
			      
			      if(  fromLaunch != null )
			      {
			        pluginCallback.setProcessFromLaunch(fromLaunch);
			        fromLaunch.received();
			      }
			     
			        
			        if(lastTimeFileSystemChecked + TimeValues.READ_STATE_EVERY_MS < System.currentTimeMillis() )
			        {
			          pluginController.execute();
			          lastTimeFileSystemChecked = System.currentTimeMillis();
			        }
  }
  
  
  
  
  def run()
  {
    
  
  
			while(true)
			{
			
				
		
             execLicenseOk()
      
      
    
         
          Thread.sleep(10);
         
         
     }
			      
			      
			 
		}
		
  
  
}


object EclipsePluginController
{
  
  val queue = new LinkedBlockingQueue[ProcessDataAndResult](1);
  
  
  
  
  def apply( eventDir : String) =
  {
    
    val pluginCallback = new CallbackStateMachineEclipse(eventDir);
    new EclipsePluginController(    PluginController.create(eventDir,pluginCallback )  ,  pluginCallback );
  }
  
  
}