package com.vmlens.api.callback

import com.anarsoft.config.MavenMojo;
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;
import com.anarsoft.race.detection.process.mode.monitor.ProcessMonitor
import com.vmlens.api.internal.reports.ReportFacade
import com.anarsoft.race.detection.process.mode.state.ProcessSharedState
import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.process.ProzessConfigProd

class APICallbackSharedImpl extends APICallbackSharedAbstractMaven {
  
  
    def deleteReportDir() = false;
  
    def ignoreTestErrors() = false;
    
     def prozessConfig = new ProzessConfigProd();
    
  
    def onResultMonitor(runResult :   ModelFacadeMonitor,  source : String ,mavenMojo : MavenMojo) 
    {
      
    }
    
    
    def onResultState(runResult :   ModelFacadeState,  source : String ,mavenMojo : MavenMojo) 
    {
      
    }
  
}