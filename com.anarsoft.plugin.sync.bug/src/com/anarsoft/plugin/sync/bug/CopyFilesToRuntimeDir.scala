package com.anarsoft.plugin.sync.bug

import org.eclipse.jdt.launching.JavaLaunchDelegate
import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.debug.core.ILaunch
import org.eclipse.core.runtime.Platform
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.FileLocator
import java.io.FileOutputStream
import org.apache.commons.io.IOUtils
import org.eclipse.swt.widgets.Display
import org.eclipse.ui.PlatformUI
import java.io.File
import org.eclipse.debug.ui.DebugUITools
import org.eclipse.core.runtime.CoreException
import java.util.UUID
import java.util.Properties
import java.util.LinkedList
import com.anarsoft.integration._;
import org.apache.commons.io.FileUtils
import java.io.File


trait CopyFilesToRuntimeDir extends CopyFilesToRuntimeDirTrait {

    
    lazy  val agentPath = getPluginLocationPath("agent.jar");
    lazy val outputDirPlugin = getPluginLocationPath().toString() + "/vmlens";
  
   def getJavaAgentString(propertyFileName : String) =
  {

    " -javaagent:\"" + agentPath.toString() + "=" +  propertyFileName + "\"" ;
  }
    
  
  
  val default_exclude_classes = "";
  val default_max_stacktrace  = 20;
  val default_delay_synchronization = false;
  val default_startTracingOfAgentManually = false;
  
  var exclude_classes = default_exclude_classes;
  var max_stacktrace = default_max_stacktrace;
  var onlyTraceIn = default_exclude_classes;
  var startTracingOfAgentManually = default_startTracingOfAgentManually;
  
  @volatile var disable_tracing = false;
 // var delay_synchronization = default_delay_synchronization;
  
//  lazy val property_filename = getPluginLocationPath("run.properties");
  
  
  
  def createOutputDir()
  {
    val dir = new File(outputDirPlugin);
    dir.mkdir();
  }

  
  
  
  
//  def createPropertiesWithoutEventDir() =
//  {
//     val properties = new Properties();
//       properties.setProperty("vmlens.excludeClasses", exclude_classes)
//       properties.setProperty("vmlens.excludeMethods", exclude_classes)
//       properties.setProperty("vmlens.excludeFields", exclude_classes)
//       
//       properties.setProperty("vmlens.onlyTraceIn", onlyTraceIn)
//  
//       properties.setProperty("disableTracing", disable_tracing.toString());
//       properties.setProperty("vmlens.maxStackTraceDepth", max_stacktrace.toString())
//       
//       properties.setProperty("vmlens.startManually",  startTracingOfAgentManually.toString())
//       properties;
//  }
  
  
  
  def saveConfig(properties : Properties, propertyFileName : String)
  {
  
     properties.setProperty("eventDir",outputDirPlugin)
     
      val stream = new FileOutputStream(getPluginLocationPath(propertyFileName)) ;
       
       properties.store(stream, "");
       
        stream.close();
  }
  
  
  
  def deletePropertyFile( propertyFileName : String )
  {
    getPluginLocationPath(propertyFileName).delete();
    
    
  }
  
  
 
 
  
  
  
  
  
  
  
//   def saveProperties()
//   {
//       val properties = createPropertiesWithoutEventDir();
//       
//       properties.setProperty("eventDir",outputDir)
//     
//       
//    //   properties.setProperty("vmlens.delaySynchronization", delay_synchronization.toString()) 
//       
//       val stream = new FileOutputStream(property_filename) ;
//       
//       properties.store(stream, "");
//       
//        stream.close();
//       
//   }
  
  
   val fileName : String = "run.properties";
  
  
     def copyJarFiles( ) = copyJarFilesWithLocation( jar => getPluginLocationPath(jar ) );
   
   
    def copyJarFilesWithLocation( createTargetFile : String => File  )
    {
      val basePath = "agent_lib/";
      
//      val jars = Array[String]("agent_bootstrap.jar", 
//          "agent_runtime.jar", "agent.jar", "asm-debug-all-4.0_RC1.jar", "event_serialization.jar",
//         "trove-3.0.3.jar" , "process-0.0.1-SNAPSHOT.jar");
      
      val bundle = Platform.getBundle(Activator.PLUGIN_ID);

   
    for(jar <- AgentConstants.oldJars )
    {
          val path = new Path(basePath + jar);
         val resultPath = createTargetFile(jar) ;
      
     
      FileUtils.deleteQuietly(resultPath)
    }
    

    	 // val fileURLArray = FileLocator.findEntries(bundle, path, null);

    	 for(jar <- AgentConstants.jars)
    	 {
    	    val path = new Path(basePath + jar);
    	    val inputStream  = FileLocator.openStream(bundle,path,false);

    	  //   println( fileUrl.getFile() );
    	// = fileUrl.openStream() ;

    	   val resultPath = createTargetFile(jar) ;



    	      val outputStream = new FileOutputStream(    resultPath.getAbsoluteFile() );

    	   IOUtils.copy(inputStream,outputStream );

    	   inputStream.close();
    	   outputStream.close();
        }
    }


     def getPluginLocationPath(file : String) =
  {
      val path = Activator.plugin.getStateLocation().append(file).toFile();
	  path;
  }


      def getPluginLocationPath() =
  {
      val path = Activator.plugin.getStateLocation().toFile();
	  path;
  }





}