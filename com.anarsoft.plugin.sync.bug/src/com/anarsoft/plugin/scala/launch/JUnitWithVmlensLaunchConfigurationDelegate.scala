package com.anarsoft.plugin.scala.launch

import org.eclipse.jdt.junit.launcher.JUnitLaunchConfigurationDelegate
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
import org.eclipse.debug.core.ILaunchManager;
import java.util.Map;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.core.runtime.IProgressMonitor;
import com.anarsoft.plugin.sync.bug.Activator
import com.anarsoft.plugin.sync.bug.startup._;
import com.vmlens.api.internal.reports.ReportFacade
import com.vmlens.api.internal.ReportData4Plugin
import com.anarsoft.race.detection.model.result._;
import java.io._;
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.plugin.preferences.PluginKeys
import java.nio.file.Files;
import org.apache.commons.io.FileUtils
import com.vmlens.api.internal.reports.CreateParallizedReportAlgo
import scala.collection.mutable.HashMap
import org.eclipse.jdt.junit.JUnitCore

class JUnitWithVmlensLaunchConfigurationDelegate extends JUnitLaunchConfigurationDelegate {

  def showResult(data: ReportData4Plugin) {
    Activator.plugin.viewManager.update(data);
  }

  override def launch(configuration: ILaunchConfiguration, mode: String, launch: ILaunch, monitor: IProgressMonitor) {

    val copy = configuration.getWorkingCopy();

    val vmArgs = copy.getAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, "");

    val propertyFileName = copy.getAttribute(PluginKeys.PROPERTY_FILE, "");

    val agentString = Activator.agentString(propertyFileName).trim();

    Activator.plugin.debug("agentString " + agentString)

    copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, vmArgs + " " + agentString);

    val source = Activator.plugin.outputDirPlugin;

    val waitPointFile = new File(source + "/parallize.info");
    Files.deleteIfExists(waitPointFile.toPath());

    var currentLaunch = launch;

    val errorTestRunListener = new ErrorTestRunListener();

    JUnitCore.addTestRunListener(errorTestRunListener)

    val processDataAndResult = new ProcessDataAndResult()
    EclipsePluginController.queue.put(processDataAndResult);

    val junitLaunchDelegate = new JUnitLaunchConfigurationDelegate();

    processDataAndResult.waitTillReceived();

    junitLaunchDelegate.launch(copy, mode, currentLaunch, monitor);

    //super.launch(  copy , mode, launch, monitor);

    processDataAndResult.result.take() match {
      case Left(modelFacade) =>
        {

          showResult(ReportFacade.createReportData4Plugin(modelFacade));

        }

      case Right(exp) =>
        {
          showResult(ReportFacade.createExceptionReport4Plugin(exp));

        }

    }

    errorTestRunListener.hasErrors = false;

    currentLaunch = new Launch(configuration, mode, launch.getSourceLocator);

    Activator.plugin.debug("launchCreated " + currentLaunch)

    DebugPlugin.getDefault().getLaunchManager().addLaunch(currentLaunch)

    JUnitCore.removeTestRunListener(errorTestRunListener)

  }

}