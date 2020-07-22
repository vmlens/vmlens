package com.anarsoft.plugin.scala.launch

import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchConfiguration;
import com.anarsoft.plugin.sync.bug.Activator
import com.anarsoft.plugin.preferences.PluginKeys
import java.util.Properties
import com.anarsoft.plugin.preferences.PropertyTransformer4ILaunchConfiguration


class VmlensLaunchConfigurationListener extends ILaunchConfigurationListener {
 
  
  def updateOrCreate(launchConfig : ILaunchConfiguration)
  {
     if( launchConfig.hasAttribute( PluginKeys.PROPERTY_FILE  )  )
      {
        
        val properties =  PropertyTransformer4ILaunchConfiguration.create(launchConfig);
        
        val propertyFileName = launchConfig.getAttribute(   PluginKeys.PROPERTY_FILE , "");
        
        if(  ! "".equals(propertyFileName))
        {
          
          Activator.plugin.saveConfig(  properties, propertyFileName  );
          
          
        }
        
        
        
        
      }
  }
  
  
  def launchConfigurationAdded( launchConfig : ILaunchConfiguration) {
		

     updateOrCreate(launchConfig);
    
    
    
		}
  
  
 def  launchConfigurationChanged(launchConfig : ILaunchConfiguration)  {
   
      updateOrCreate(launchConfig);
   
   
 }
 
 
 def launchConfigurationRemoved(launchConfig : ILaunchConfiguration) {
 
      if( launchConfig.hasAttribute( PluginKeys.PROPERTY_FILE  )  )
      {
         val propertyFileName = launchConfig.getAttribute(   PluginKeys.PROPERTY_FILE , "");
        
        if(  ! "".equals(propertyFileName))
        {
          try{
              Activator.plugin.deletePropertyFile( propertyFileName  );
          }
          catch
          {
            case exp : Exception =>
              {
                Activator.plugin.log(exp)
              }
          }
        
          
          
        }
      }
   
   
 }
 
  
  
}