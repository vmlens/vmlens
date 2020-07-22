package com.anarsoft.plugin.view.wizards

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import com.vmlens.api.internal.ProcessFacade
import  com.anarsoft.plugin.sync.bug.Activator

class ImportAgenFiles  extends Wizard with IImportWizard{
  
  var mainPage :  Option[AgentFilesResourceImportPage1]  = None;;
  
   override def addPages() {
        super.addPages();
        val createdMainPage = new AgentFilesResourceImportPage1();
        addPage(createdMainPage);
        
        mainPage= Some(createdMainPage);
        
    }
  
  
   def init( workbench : IWorkbench, selection : IStructuredSelection)
   {
        
     
   }
   
   
   def performFinish() = {
     
     mainPage.flatMap(  x =>   x.agentDir() ) match
     {
       case None =>
         {
           false;
         }
       
       case Some(dir) =>
         {
             val data = ProcessFacade.process4Eclipse(dir,None , 1 );
	 	        Activator.plugin.viewManager.update(data);
           
           true;
         }
       
     }
    
     
   }
  
  
}