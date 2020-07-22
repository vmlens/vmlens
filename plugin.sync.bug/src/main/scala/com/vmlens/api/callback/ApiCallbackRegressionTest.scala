package com.vmlens.api.callback

import com.vmlens.api._;
import java.io._;
import collection.JavaConverters._
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ArrayBuffer;
import net.liftweb.json._;

import net.liftweb.json.Extraction._
import com.anarsoft.race.detection.model.result.ModelFacadeAll
import com.vmlens.api.internal.reports._;
import com.vmlens.maven.plugin.Extended
import com.vmlens.maven.plugin.Group
import org.apache.commons.io._;
import com.anarsoft.config.MavenMojo

class ApiCallbackRegressionTest(val exceptIssues : Boolean, val expectFailure: Boolean) extends ApiCallbackParallizeAbstract {

 
  
  
  def ignoreTestErrors() = expectFailure;
  def ignoreIssues() = exceptIssues;
  
  
  def isTest  = true;
 

  

  

  
  
  def takeAgentMessage(message : String) =
  {
    ! message.startsWith("still undefined")
  }

  
  def fillExpected( map : HashMap[String,Boolean], runResult : ModelFacadeAll  )
  {
        for (m <- runResult.agentLog) {

   if( takeAgentMessage(m) )
   {
           map.get(m) match
        {
        case None =>
          {
            throw new RuntimeException("not expected " + m);
          }
          
        case Some(x) =>
          {
          // da aktuell der vergleich bei falscher sortierung der statements fehlschlagen kann 
            // kann es zu doppelten läufen und damit auch doppelten werten kommen
//            if(x)
//            {
//               throw new RuntimeException("duplicate" + m);
//            }
            
                map.put(m, true)
          }
               
        }
   }


      
     
    }
  }
   
  
  
  
  def onProcessing(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo) {

    TestUtil.log4StabilityTest(source, mavenMojo);

  
  }

  def onStartup(source: String, mavenMojo: MavenMojo) {
  
   
    

  }

  
  def checkForTimeout(runResult: ModelFacadeAll,mavenMojo: MavenMojo)
  {
    
//    if( ! mavenMojo.getDisableTimeoutCheck())
//    {
         for( message <- runResult.agentLog )
    {
      if( message.startsWith("TIMEOUT:") &&  ! mavenMojo.getDisableTimeoutCheck() )
      {
        throw new RuntimeException("timeout in agent " + message);
      }
      
        if( message.startsWith("WARNING_TIMEOUT") &&  ! mavenMojo.getDisableTimeoutWarningCheck()  &&  ! mavenMojo.getDisableTimeoutCheck())
      {
        throw new RuntimeException("WARNING_TIMEOUT" + message);
      }
      
      
    }
   // }
    
 
  }
  
  
  
  
  
  
  def onSuccess(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo) {
       TestUtil.writeAgentRun(mavenMojo);
    
    ReportFacade.save2File(runResult,  mavenMojo.getReportDir())
   
  
   TestUtil.log4StabilityTest(source, mavenMojo);

     
    checkForTimeout(runResult,mavenMojo);


    if (expectFailure) {
      throw new RuntimeException("expectFailure  =  " + expectFailure + " but everything successful");
    }

  
    
    
    
    
         ReportFacade.test(mavenMojo.getTestDir(),  mavenMojo.getReportDir()  , runResult );

  }

  
  
  
  def onTestFailure(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo) {

  
   TestUtil.writeAgentRun(mavenMojo);
    
    
   ReportFacade.save2File(runResult,  mavenMojo.getReportDir())

   
   
       TestUtil.log4StabilityTest(source, mavenMojo);

     checkForTimeout(runResult,mavenMojo);
   
   
    if (!expectFailure) {
      throw new RuntimeException("expectFailure  =  " + expectFailure + " but error");
    }

  

     
     ReportFacade.test(mavenMojo.getTestDir(),  mavenMojo.getReportDir() , runResult);
     


  }
  
  
  
  
  
  
  def onIssueFound(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo) {

  
   TestUtil.writeAgentRun(mavenMojo);
    
    
   ReportFacade.save2File(runResult,  mavenMojo.getReportDir())

   
   
       TestUtil.log4StabilityTest(source, mavenMojo);

     checkForTimeout(runResult,mavenMojo);
   
   
    if (!exceptIssues) {
      throw new RuntimeException("exceptIssues  =  " + exceptIssues + " but issues found");
    }

  

     
     ReportFacade.test(mavenMojo.getTestDir(),  mavenMojo.getReportDir() , runResult);
     


  }
  
  
  
  
  
  
  
  
  
  
  

}