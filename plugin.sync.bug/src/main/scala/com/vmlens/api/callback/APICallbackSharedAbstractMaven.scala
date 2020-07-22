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
import com.anarsoft.race.detection.process.ProzessConfig


abstract class APICallbackSharedAbstractMaven extends APICallbackShared {
  
    def ignoreTestErrors() : Boolean;
  
    def onResultMonitor(runResult :   ModelFacadeMonitor,  source : String ,mavenMojo : MavenMojo) ;
    def onResultState(runResult :   ModelFacadeState,  source : String ,mavenMojo : MavenMojo) ;
   
   
    def prozessConfig : ProzessConfig;
    
    
   def deleteReportDir() : Boolean;
  
   @throws(classOf[IssuesFoundException])
  def prozessMonitor(source : String,mavenMojo : MavenMojo,progressMonitor : ProgressMonitor)
   {

    // Some(new File("memoryAccess"))
    val modelFacade = (new ProcessMonitor(prozessConfig)).prozessMavenPlugin(source, progressMonitor)
    
       ReportFacade.save2File(modelFacade,  mavenMojo.getReportDir() )
    
       
       
         if( modelFacade.hasIssues() )
      {
          if( ! ignoreTestErrors() )
          {
            throw new IssuesFoundException("There are race conditions and/or deadlocks in the test run. \n" +
              "Please see " + mavenMojo.getReportDir() + "/issues.html for the details.")
          }
          
      }
    
    
       onResultMonitor(modelFacade ,source ,mavenMojo  );
       
       
    
   }
  
    
    
      @throws(classOf[IssuesFoundException])
  def prozessState(source : String,mavenMojo : MavenMojo,progressMonitor : ProgressMonitor)
      {
         
                   
        if(deleteReportDir() )
        {
          FileUtils.cleanDirectory( mavenMojo.getReportDir() );
        }
                   
                   
    val modelFacade = (new ProcessSharedState(prozessConfig)).prozessMavenPlugin(source, progressMonitor)
    
       ReportFacade.save2File(modelFacade,  mavenMojo.getReportDir() )
       
       
                if( modelFacade.hasIssues() )
      {
          if( ! ignoreTestErrors() )
          {
            throw new IssuesFoundException("There are issues in the test run. \n" +
              "Please see " + mavenMojo.getReportDir() + "/issues.html for the details.")
          }
          
      }
    
    
       onResultState(modelFacade ,source ,mavenMojo  );
       
       
       
       
       
       
       
       
      }
  
  
  
}