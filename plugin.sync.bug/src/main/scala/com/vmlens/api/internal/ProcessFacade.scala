package com.vmlens.api.internal


import com.anarsoft.race.detection.process.ReadAndProcessEvents;
import com.anarsoft.race.detection.process.ProzessConfigProd
import com.vmlens.api.internal.reports.ReportFacade
import java.io._;
import java.util.Properties
import com.vmlens.trace.agent.bootstrap.mode.ModeNames
import com.anarsoft.race.detection.process.mode.monitor.ProcessMonitor
import com.anarsoft.race.detection.process.mode.state.ProcessSharedState
import scala.collection.mutable.HashMap
import com.vmlens.api.internal.reports.HtmlProviderStaticSite
import com.anarsoft.trace.agent.runtime.process.PluginController
import com.anarsoft.config._;

object ProcessFacade {
  
  
  def createHtmlProviderBuyOnly() = new HtmlProviderStaticSite("templates/htmlBuy.mustache");
  def createHtmlProviderBuyOrAnswer() = new HtmlProviderStaticSite("templates/htmlBuyOrAnswer.mustache");
  
  def createHtmlProviderStart() = new HtmlProviderStaticSite("templates/htmlStart.mustache");
  
  
  def processEclipseFromLaunch(eventDir : String,maxSlidingWindowIdOption  : Option[Int] ) =
  {
    
    val maxSlidingWindowId =
    maxSlidingWindowIdOption match
    {
      
      case Some(x) => x;
      
      case None =>
        {
          PluginController.loadCurrentState(eventDir).getSlidingWindowId()
        }
      
      
      
    }
    
    
    
      try{
       
     val stream =   new FileInputStream(  eventDir  +  "/properties.vmlens")  ;
       
       val properties = new Properties();
      properties.load( stream ); 
       
       stream.close();
       
       
      val mode = properties.getProperty("vmlens.mode")
      
      if( ModeNames.MONITOR.equals(mode) )
      {
        Left( (new ProcessMonitor(new ProzessConfigProd())).prozess(eventDir, maxSlidingWindowId) )
    
           
         
      }
      else  if( ModeNames.STATE.equals(mode) )
      {
       
        Left(   (new ProcessSharedState(new ProzessConfigProd())).prozess(eventDir, maxSlidingWindowId) )
    
         
      }
      else
      {
         val readAndProcessEvents =  new ReadAndProcessEvents(new ProzessConfigProd() , PropertyTransformer.createConfigValues(new PropetyContainer2Properties(properties)));
        Left(  readAndProcessEvents.prozess(eventDir,maxSlidingWindowId) )
      
      }
            
	   
     }
     catch
     {
       case exp : Throwable =>
         {
          Right( exp );
         }
       
       
       
     }
  }
  
  
  
  
  
  
  def process4Eclipse(eventDir : String,maxSlidingWindowIdOption  : Option[Int],runCount : Int) =
  {
   
    
      val maxSlidingWindowId =
    maxSlidingWindowIdOption match
    {
      
      case Some(x) => x;
      
      case None =>
        {
          PluginController.loadCurrentState(eventDir).getSlidingWindowId()
        }
      
      
      
    }
    
    
     try{
       
     val stream =   new FileInputStream(  eventDir  +  "/properties.vmlens")  ;
       
       val properties = new Properties();
      properties.load( stream ); 
       
       stream.close();
       
       
      val mode = properties.getProperty("vmlens.mode")
      
      if( ModeNames.MONITOR.equals(mode) )
      {

    // Some(new File("memoryAccess"))
         val modelFacade = (new ProcessMonitor(new  ProzessConfigProd())).prozess(eventDir, maxSlidingWindowId)
    
           ReportFacade.createReportData4Plugin(modelFacade);
         
      }
      else  if( ModeNames.STATE.equals(mode) )
      {
          
    // Some(new File("memoryAccess"))
         val modelFacade = (new ProcessSharedState( new ProzessConfigProd())).prozess(eventDir, maxSlidingWindowId)
    
           ReportFacade.createReportData4Plugin(modelFacade);
      }
      else
      {
         val readAndProcessEvents = new ReadAndProcessEvents(new ProzessConfigProd() , PropertyTransformer.createConfigValues(new PropetyContainer2Properties(properties)));
       val runResult = readAndProcessEvents.prozess(eventDir,maxSlidingWindowId)
       ReportFacade.createReportData4Plugin(runResult);
      }
            
	   
     }
     catch
     {
       case exp : Throwable =>
         {
           ReportFacade.createExceptionReport4Plugin(exp);
         }
       
       
       
     }
     
     
     
  }
  
  
  
  
}