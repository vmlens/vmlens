package com.vmlens.api.callback


import com.anarsoft.config.MavenMojo;
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;
import com.anarsoft.config.MavenMojo;
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;
import com.anarsoft.race.detection.process.mode.monitor.ProcessMonitor

import com.vmlens.api.internal.reports.ReportFacade
import com.anarsoft.race.detection.process.mode.state.ProcessSharedState
import com.anarsoft.race.detection.model.result._;
import org.apache.commons.io.FileUtils;


trait APICallbackShared {
  
 
  
   @throws(classOf[IssuesFoundException])
  def prozessMonitor(source : String,mavenMojo : MavenMojo,progressMonitor : ProgressMonitor);
    
    
      @throws(classOf[IssuesFoundException])
  def prozessState(source : String,mavenMojo : MavenMojo,progressMonitor : ProgressMonitor);
  
}