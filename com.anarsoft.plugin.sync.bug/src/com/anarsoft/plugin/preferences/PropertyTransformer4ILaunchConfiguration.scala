package com.anarsoft.plugin.preferences


import org.eclipse.debug.core.ILaunchConfiguration
import java.util.Properties
import com.anarsoft.trace.agent.runtime.util.AgentKeys

object PropertyTransformer4ILaunchConfiguration  {
  
   def create(config : ILaunchConfiguration) =
  {
     val properties = new Properties();
     
      for( p <- AgentKeys.ALL )
        { 
            properties.setProperty(p ,  config.getAttribute( p , ""))   
        }
    
     

     
     
     
   
     
     properties;
  }
  
  
}