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
import com.anarsoft.race.detection.process.read.event._;

class ApiCallbackCheckEvents extends ApiCallbackParallizeAbstract {

 
  
   def ignoreIssues() = false;
  def ignoreTestErrors() = false;
  def isTest  = true; 
 

  

  

  
  
  
  def onProcessing(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo) {

    TestUtil.log4StabilityTest(source, mavenMojo);

  
  }

  def onStartup(source: String, mavenMojo: MavenMojo) {
  
   
    

  }

  def onSuccess(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo) {
    
    
    ReportFacade.save2File(runResult,  mavenMojo.getReportDir())
    
    
  
   TestUtil.log4StabilityTest(source, mavenMojo);
    
    
   val expected2Found = 
     ReadExpectedEvents.read(mavenMojo.getTestDir() +    "/expected.events") 
    
    
    
    
        ReadEvents.process(source, new ReadEventConfigCallback() ,
            new OnEvent(expected2Found, None)  );
      
      
   for( elem <- expected2Found )
   {
     if( ! elem._2.isSet )
     {
       throw new RuntimeException("not there " + elem._1);
     }
       
     
     
     
   }
    

    

  }

  def onTestFailure(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo) {


    TestUtil.log4StabilityTest(source, mavenMojo);
    
    
      throw new RuntimeException("now failure expected ");
    

  
   
    

  }
  
  
    def onIssueFound(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo) {


    TestUtil.log4StabilityTest(source, mavenMojo);
    
    
      throw new RuntimeException("now failure expected ");
    

  
   
    

  }
  
  
  

  
}