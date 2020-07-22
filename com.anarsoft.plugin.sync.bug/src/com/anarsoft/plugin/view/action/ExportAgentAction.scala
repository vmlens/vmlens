package com.anarsoft.plugin.view.action


import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.action.Action;
import com.anarsoft.plugin.view._;
import com.anarsoft.plugin.sync.bug._;
import org.eclipse.swt._;
import org.eclipse.swt.widgets._;
import java.io._;
import com.anarsoft.integration.TextRepository



class ExportAgentAction  extends Action(TextRepository.EXPORT_AGENT)  {
  
  
   override def run()
  {
      
       Activator.plugin.execWithDisplay( d =>
         {
           //MessageDialog.openWarning(  d.getActiveShell  , "Code Search Warning", message);
            val dialog = new DirectoryDialog ( d.getActiveShell, SWT.NONE);
           
            /*
             * Can be null
             * 
             * Returns:
    a string describing the absolute path of the first selected file, or null if the dialog was cancelled or an error occurred
             * 
             */
            
            
            val fileName = dialog.open();
            
            
            if( fileName != null )
            {
              
                Activator.plugin.copyJarFilesWithLocation( jar => new File(fileName  + "/"+ jar )  );
                
//                val properties = Activator.plugin.createAgentConfigFromPreferences
//                
//                
//               
//                properties.setProperty("eventDir","./vmlens/");
//                val stream = new FileOutputStream(new File(fileName + "/run.properties")) ;
//                properties.store(stream, "");
//                stream.close();
       
              
            }
            
            
           
         });
  }
   
  
  
}