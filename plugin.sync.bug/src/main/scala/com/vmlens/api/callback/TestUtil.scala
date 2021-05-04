package com.vmlens.api.callback

import java.io._;
import org.apache.commons.io._;
import com.anarsoft.config.MavenMojo
import com.anarsoft.race.detection.model.result.ModelFacadeAll


object TestUtil {
  
    def log4StabilityTest(source: String, mavenMojo: MavenMojo) {

  //  if (System.getProperty("stabilityTest") != null && System.getProperty("stabilityTest").equals("true")) {
      FileUtils.copyDirectoryToDirectory(mavenMojo.getReportDir(), new File("C:/TEMP/stabilityTest/report" + "/"));
      FileUtils.copyDirectoryToDirectory(new File(source), new File("C:/TEMP/stabilityTest/event" +  "/"));
  //  }
  }
  
    
    
    def printAgentLog(modelFacade : ModelFacadeAll)
    {
      for( m <- modelFacade.agentLog )
      {
        println(m);
      }
    }
    
    
    def writeAgentRun( mavenMojo: MavenMojo)
    {
      new File( "C:/TEMP/agentRun" ).createNewFile();
    }
    
    
    def deleteAgentRun()
    {
          new File( "C:/TEMP/agentRun" ).delete();
    }
    
  
    def agentWasRun() =    new File("C:/TEMP/agentRun" ).exists();
      
    
    
}