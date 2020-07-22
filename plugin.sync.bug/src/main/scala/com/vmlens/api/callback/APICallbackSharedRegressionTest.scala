package com.vmlens.api.callback

import com.anarsoft.config.MavenMojo;
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;
import com.anarsoft.race.detection.process.mode.monitor.ProcessMonitor
import com.vmlens.api.internal.reports.ReportFacade
import com.anarsoft.race.detection.process.mode.state.ProcessSharedState
import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.process.ProzessConfigTest


class APICallbackSharedRegressionTest(val expectFailure: Boolean) extends APICallbackSharedAbstractMaven  {
  
   def deleteReportDir() = true;
  
   def ignoreTestErrors() = expectFailure;
  
    def prozessConfig = new ProzessConfigTest();
   
    def onResultMonitor(runResult :   ModelFacadeMonitor,  source : String ,mavenMojo : MavenMojo) 
    {
        TestUtil.writeAgentRun(mavenMojo);
      
          TestUtil.log4StabilityTest(source, mavenMojo);
          
          TestUtil.printAgentLog(runResult);
      
         ReportFacade.test( mavenMojo.getTestDir() , mavenMojo.getReportDir() )
    }
    
    
    def onResultState(runResult :   ModelFacadeState,  source : String ,mavenMojo : MavenMojo) 
    {
         TestUtil.writeAgentRun(mavenMojo);
      
       TestUtil.log4StabilityTest(source, mavenMojo);
          TestUtil.printAgentLog(runResult);
           ReportFacade.test( mavenMojo.getTestDir() , mavenMojo.getReportDir() )
    }
  
  
  
  
  
  
  
}