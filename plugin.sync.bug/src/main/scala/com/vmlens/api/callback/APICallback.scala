package com.vmlens.api.callback


import com.vmlens.api._;
import java.io.File;
import com.anarsoft.config.MavenMojo;
import com.vmlens.maven.plugin.Extended
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;
import com.vmlens.trace.agent.bootstrap.typeDesc._
import java.io._;
import scala.collection.mutable.ArrayBuffer
import net.liftweb.json._;
import org.apache.commons.io._;


/**
 * 
 * 
 * 
 * 
 */


trait APICallback {
  
  
  
  
  def ignoreTestErrors() : Boolean;
  
  def onStartup(source : String,mavenMojo : MavenMojo);
  
  @throws(classOf[IssuesFoundException])
  def prozess(source : String,mavenMojo : MavenMojo,progressMonitor : ProgressMonitor) : Boolean;
   
  
  def prozessTestError(source : String,mavenMojo : MavenMojo,progressMonitor : ProgressMonitor) 
  
   

  
  
  
  def saveParallizeData(eventDir : String, expected : ArrayBuffer[SerializableData])
  {
     val stream = new DataOutputStream(new FileOutputStream(eventDir + "/parallize.info"));
     
     for( elem <- expected )
     {   
        elem.serialize( stream )
     }
     
     
     
     stream.close();
     
  }
  
  
}