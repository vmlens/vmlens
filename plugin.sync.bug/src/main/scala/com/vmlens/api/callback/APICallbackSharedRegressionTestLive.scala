package com.vmlens.api.callback

import com.anarsoft.config.MavenMojo;
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;
import com.anarsoft.config.MavenMojo;
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;
import com.anarsoft.race.detection.process.mode.monitor.ProcessMonitor
import com.vmlens.api.internal.ReportData4Plugin
import com.vmlens.api.internal.reports.ReportFacade
import com.anarsoft.race.detection.process.mode.state.ProcessSharedState
import com.anarsoft.race.detection.model.result._;
import org.apache.commons.io.FileUtils;
import java.io._;
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.ProzessConfigTest


class APICallbackSharedRegressionTestLive extends APICallbackShared {
  
  
   @throws(classOf[IssuesFoundException])
  def prozessMonitor(source : String,mavenMojo : MavenMojo,progressMonitor : ProgressMonitor)
   {

    // Some(new File("memoryAccess"))
    val modelFacade = (new ProcessMonitor(new ProzessConfigTest())).prozessMavenPlugin(source, progressMonitor)
    
       
       
       
    
   }
  
    
    
      @throws(classOf[IssuesFoundException])
  def prozessState(source : String,mavenMojo : MavenMojo,progressMonitor : ProgressMonitor)
      {
        
           TestUtil.writeAgentRun(mavenMojo);
         
                   val elementDir = new File(mavenMojo.getTestDir() + "/elements/");
   
      val ids = new ArrayBuffer[Int]             
              
      if( elementDir.exists() )
      {
        
        for( f <- elementDir.list() )
        {
          if( f.endsWith(".html") )
          {
            val id = Integer.parseInt( f.substring(0 , f.length - ".html".length))
            println(id);
            ids.append(id);
          }
          
          
          
        }
        
        
      }
                   
                   
    val modelFacade = (new ProcessSharedState(new ProzessConfigTest())).prozessMavenPlugin(source, progressMonitor)
    
    
       
     ReportFacade.copyImgCssFiles(mavenMojo.getReportDir()) ; 
       
       
     val reportData =   ReportFacade.createReportData4Plugin(modelFacade);
     
    
       save( reportData ,mavenMojo.getReportDir() + "/elements.html"  , "/elements.html"  );
       
      new File(mavenMojo.getReportDir() + "/elements/").mkdir() 
       
       
      for( id <-  ids.sorted )
      {
         save(reportData : ReportData4Plugin,mavenMojo.getReportDir() + "/elements/" + id + ".html",  "/elements/" + id + ".html")
      }
       
         TestUtil.log4StabilityTest(source, mavenMojo);
                ReportFacade.test( mavenMojo.getTestDir() , mavenMojo.getReportDir() ) 
      }
      
      
      
      
      def save(reportData : ReportData4Plugin,fileName : String, url : String)
      {
      
        println(fileName);
        
        val printStream = new PrintWriter(fileName);
         reportData.resultHtmlOnline.write2Stream(url , printStream);
        printStream.close(); 
        
        
        
      }
      
      
  
  
}