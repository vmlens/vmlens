package com.anarsoft.plugin.view.launch

import org.eclipse.jdt.internal.junit.launcher.JUnitTabGroup
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaArgumentsTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaClasspathTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaJRETab;
import org.eclipse.jdt.junit.launcher.JUnitLaunchConfigurationTab;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.sourcelookup.SourceLookupTab;


class JUnitWithVmlensTabGroup extends JUnitTabGroup {
  
  override def createTabs( dialog : ILaunchConfigurationDialog,  mode : String) {
		val tabs=  Array[ILaunchConfigurationTab] (
			new JUnitLaunchConfigurationTab(),
			new VMLensLaunchTab(),
			new JavaArgumentsTab(),
			new JavaClasspathTab(),
			new JavaJRETab(),
			new SourceLookupTab(),
			new EnvironmentTab(),
			new CommonTab()
		);
		setTabs(tabs);
	}
  
  
}