package com.anarsoft.plugin.view.launch

import org.eclipse.debug.ui._

class ExternalLaunchTabGroup  extends AbstractLaunchConfigurationTabGroup  {
  
  def createTabs( dialog : ILaunchConfigurationDialog ,  mode : String) {
         	val tabs=  Array[ILaunchConfigurationTab] (
                new VMLensLaunchTab()
		);
		setTabs(tabs);
    }

  
  
}