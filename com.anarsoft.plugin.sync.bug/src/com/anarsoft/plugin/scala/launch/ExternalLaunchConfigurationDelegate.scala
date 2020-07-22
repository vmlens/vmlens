package com.anarsoft.plugin.scala.launch

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core._;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaArgumentsTab;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import com.anarsoft.plugin.sync.bug.SWTFactory;
import com.anarsoft.plugin.sync.bug.Activator$;
import java.util.HashMap;
import org.eclipse.debug.core.ILaunchManager;
import java.util.Map;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.core.runtime.IProgressMonitor;
import com.anarsoft.plugin.sync.bug.Activator
import com.anarsoft.plugin.view.factory.ExternalRunDialog
import java.util.Properties
import com.anarsoft.plugin.preferences.PluginKeys
import com.anarsoft.plugin.preferences.PropertyTransformer4ILaunchConfiguration

class ExternalLaunchConfigurationDelegate extends   LaunchConfigurationDelegate  {
  
   def launch( configuration : ILaunchConfiguration,  mode : String,  launch : ILaunch,  monitor : IProgressMonitor){
//        String attribute = configuration.getAttribute(VogellaLaunchConfigurationAttributes.CONSOLE_TEXT, "Simon says \"RUN!\"");
//        System.out.println(attribute);
     
        val propertyFileName = configuration.getAttribute(PluginKeys.PROPERTY_FILE, "");
        val agentString =  Activator.agentString(propertyFileName).trim();
        
        val properties = PropertyTransformer4ILaunchConfiguration.create( configuration );
        
        
     
        Activator.plugin.execWithShell( shell =>   {
          
          val dialog = new ExternalRunDialog( shell, agentString , properties);
           val fileName = dialog.open();
            
        
          
        } )
        
        
    }
  
  
}