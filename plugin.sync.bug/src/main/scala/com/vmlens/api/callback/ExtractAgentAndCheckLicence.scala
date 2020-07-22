package com.vmlens.api.callback


import java.io._;
import scala.collection.mutable.ArrayBuffer;
import org.apache.commons.io.IOUtils
import com.anarsoft.integration.AgentConstants;
import java.util.Properties
import com.anarsoft.config.PropertyTransformer;
import org.apache.commons.io.FileUtils
import com.anarsoft.config.MavenMojo


class ExtractAgentAndCheckLicence {
  
  
  
  def extractAndCheckAndSetPropertiesInRunProperties(agentTargetDir : File, agentConfig : MavenMojo) =
  {
    
   
  /*
   * -rw-rw-r-- 1 thomas thomas   38626 Nov 16  2016 bigqueue-0.7.0.jar
-rw-rw-r-- 1 thomas thomas  326755 Nov 16  2016 lang-6.6.2.jar
-rw-rw-r-- 1 thomas thomas  285304 Nov 16  2016 kryo-3.0.1.jar
-rw-rw-r-- 1 thomas thomas   74308 Nov 16  2016 reflectasm-1.11.0-shaded.jar
-rw-rw-r-- 1 thomas thomas   45172 Nov 16  2016 objenesis-1.4.jar
-rw-rw-r-- 1 thomas thomas    4965 Nov 16  2016 minlog-1.2.jar
-rw-rw-r-- 1 thomas thomas  489883 Nov 16  2016 log4j-1.2.17.jar
-rw-rw-r-- 1 thomas thomas      10 Nov 28  2016 local_issues_v1.json
-rw-rw-r-- 1 thomas thomas    3392 Feb 28 09:04 vmlens.log
-rw-rw-r-- 1 thomas thomas 2642229 Jun  5 16:16 trove-3.1a1.jar
-rw-rw-r-- 1 thomas thomas     294 Jun  5 16:17 run.properties
drwxrwxr-x 2 thomas thomas    4096 Jun  7 12:03 vmlens
-rw-rw-r-- 1 thomas thomas 2680702 Jun 20 15:14 agent_bootstrap.jar
-rw-rw-r-- 1 thomas thomas 2523218 Jun 20 15:14 trove-3.0.3.jar
-rw-rw-r-- 1 thomas thomas   10162 Jun 20 15:14 process-0.0.1-SNAPSHOT.jar
-rw-rw-r-- 1 thomas thomas    8504 Jun 20 15:14 event_serialization.jar
-rw-rw-r-- 1 thomas thomas  321450 Jun 20 15:14 asm-debug-all-4.0_RC1.jar
-rw-rw-r-- 1 thomas thomas   96354 Jun 20 15:14 agent_runtime.jar
-rw-rw-r-- 1 thomas thomas    2885 Jun 20 15:14 agent.jar
   * 
   */
    
    
    for(jar <- AgentConstants.oldJars )
    {
      val toBeDeleted = new File( agentTargetDir.getAbsolutePath + "/" + jar);
      FileUtils.deleteQuietly(toBeDeleted)
    }
    
  
    
    
     for( jar <- AgentConstants.jars )
     {
        val target = new FileOutputStream( agentTargetDir.getAbsolutePath + "/" + jar );
        val input = Thread.currentThread().getContextClassLoader().getResourceAsStream("agent_lib/" + jar);
        
         IOUtils.copy(input, target)
           input.close();
           target.close();
     }
     val properties =  PropertyTransformer.create(agentConfig);
    
 
     val dir = agentTargetDir.getAbsolutePath + "/vmlens/";
     
     (new File(dir)).mkdirs();
     
     
      properties.setProperty("eventDir",dir);
      
       val stream = new FileOutputStream(new File(agentTargetDir.getAbsolutePath + "/run.properties")) ;
       
       properties.store(stream, "");
       
        stream.close();
     
     dir;
    
  }
  
  
  
  
}