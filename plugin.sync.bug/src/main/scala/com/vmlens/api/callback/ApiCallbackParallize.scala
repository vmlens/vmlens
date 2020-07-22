package com.vmlens.api.callback


import com.vmlens.api._;
import java.io._;
import collection.JavaConverters._
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ArrayBuffer;
import net.liftweb.json._;
import org.apache.commons.io._;
import net.liftweb.json.Extraction._
import com.anarsoft.race.detection.model.result.ModelFacadeAll
import com.vmlens.api.internal.reports._;
import com.vmlens.maven.plugin.Extended
import com.vmlens.maven.plugin.Group
import com.anarsoft.config.MavenMojo


class ApiCallbackParallize extends ApiCallbackParallizeAbstract {
  
 
   def ignoreTestErrors() = false;
   def ignoreIssues() = false;
   def isTest  = false;
  
  def onStartup(source : String, mavenMojo : MavenMojo)
   {
    
     
   }
  
  
   def onProcessing(runResult :   ModelFacadeAll,  source : String ,mavenMojo : MavenMojo)
   {
     
   }
  
  
  
    def onSuccess(runResult :   ModelFacadeAll,  source : String ,mavenMojo : MavenMojo)  
  {
      
      ReportFacade.save2File(runResult,  mavenMojo.getReportDir())
      
      
     
  }
  
  
  
    
    def onIssueFound(runResult :   ModelFacadeAll,  source : String ,mavenMojo : MavenMojo)  
  {
      
      
          ReportFacade.save2File(runResult,  mavenMojo.getReportDir() )
     
  }
  
     def onTestFailure(runResult :   ModelFacadeAll,  source : String ,mavenMojo : MavenMojo)  
  {
      
      
          ReportFacade.save2File(runResult,  mavenMojo.getReportDir() )
     
  }
  
  
  
  
  
  
}