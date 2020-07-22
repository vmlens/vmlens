package com.anarsoft.plugin.view.launch

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.junit.launcher.JUnitLaunchConfigurationConstants;
import org.eclipse.jdt.junit.launcher._;
import com.anarsoft.plugin.sync.bug.Activator$;

import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;


class VMLensJUnitLaunchShortcut extends JUnitLaunchShortcut {
 
  
  override def  getLaunchConfigurationTypeId()  = "com.anarsoft.plugin.scala.launch.JUnitWithVmlensLaunchConfigurationDelegate";
  
  
   override def   createLaunchConfiguration( element : IJavaElement,  testName  : String) =
   {
     val  wc =   super.createLaunchConfiguration(element, testName);
     
     VMLensLaunchTab.setDefaults(wc);
     
     wc;
   }
  
  
}