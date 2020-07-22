package com.anarsoft.plugin.sync.bug.startup
import org.eclipse.debug.core._;
import org.eclipse.ui._;
import  org.eclipse.core.runtime._;
import com.anarsoft.plugin.sync.bug._;
import com.anarsoft.plugin.scala.launch._;


class OnStartup extends IStartup  {

  def   earlyStartup(){
    
     OnStartup.startFileListenerIfNotStarted();
     
    
  }
  
  
}
object OnStartup
{
  
    var fileListenerStarted = false;
   
   
   def   startFileListenerIfNotStarted()
   {
     OnStartup.synchronized{
       
         if( ! fileListenerStarted  )
       {
           fileListenerStarted = true;
           
           
      
           
     val controller =    EclipsePluginController(Activator.plugin.getPluginLocationPath("vmlens").toString() );
           
           
     val thread = new Thread( controller );
     thread.setDaemon(true);
     
     
     thread.start();
     
     
           
     
           /*
            * class ProcessControlThread(val eventDir : String,
    val manuelStartCallback : ManuelStartCallback,val agentRunningCallback : AgentRunningCallback,
    val processingTraceFieldsCallback : ProcessingTraceFieldsCallback,
    val waitAtStartMilliSeconds : Long) extends Thread {
            */
           
           
           
//          new ProcessControlThread(
//              new ProcessControlThreadPluginCallback(),
//              
//          
//          
//          
//          ).start();
       }
       
       
      
       
     }
   }


}