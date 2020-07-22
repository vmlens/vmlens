package com.anarsoft.plugin.view.action

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.action.Action;
import com.anarsoft.plugin.view._;
import com.anarsoft.plugin.sync.bug._;
import org.eclipse.swt._;
import org.eclipse.swt.widgets._;
import java.io._;
import com.anarsoft.race.detection.model.result._; 
import com.vmlens.api.internal._;
import com.anarsoft.integration.TextRepository
import  com.vmlens.api.internal.reports.Element4TreeViewer
import  com.vmlens.api.internal.reports.WriteYaml



class ExportAsYamlAction(val elements : Seq[Element4TreeViewer]) extends Action(TextRepository.EXPORT_RACE_CONDITIONS)  {
  
   setImageDescriptor(  Activator.getImageDescriptor( IconRepository.EXTERNALIZE )); 
  
  
   override def run()
  {
      
       Activator.plugin.execWithDisplay( d =>
         {
           //MessageDialog.openWarning(  d.getActiveShell  , "Code Search Warning", message);
            val dialog = new FileDialog ( d.getActiveShell, SWT.SAVE);
           
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
              
              val stream = new PrintStream(fileName);
              

               WriteYaml.write(elements , stream );
              
              
              stream.close();
              
              
            }
            
            
           
         });
  }
   
  
  
}