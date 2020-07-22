package com.anarsoft.plugin.view

import org.eclipse.jface.dialogs._;
import org.eclipse.swt.widgets._;
import com.anarsoft.plugin.sync.bug.SWTFactory
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.SWT;
import  org.eclipse.swt.events._;
import  com.anarsoft.plugin.sync.bug.Activator
import com.anarsoft.integration.TextRepository
import com.anarsoft.plugin.preferences.PluginKeys

class VMLensProgressMonitorDialog(parent : Shell) extends ProgressMonitorDialog(parent)  {
  
  
  @Override
	override def configureShell(  shell : Shell) {
		super.configureShell(shell);
		shell.setText( TextRepository.TITLE_PROGRESS_MONITOR_DIALOG ); 
		
	}
  
  
  /*
   * protected Control createDialogArea(Composite parent) {
		setMessage(DEFAULT_TASKNAME, false);
		createMessageArea(parent);
		// Only set for backwards compatibility
		taskLabel = messageLabel;
		// progress indicator
		progressIndicator = new ProgressIndicator(parent);
		GridData gd = new GridData();
		gd.heightHint = convertVerticalDLUsToPixels(BAR_DLUS);
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		progressIndicator.setLayoutData(gd);
		// label showing current task
		subTaskLabel = new Label(parent, SWT.LEFT | SWT.WRAP);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = convertVerticalDLUsToPixels(LABEL_DLUS);
		gd.horizontalSpan = 2;
		subTaskLabel.setLayoutData(gd);
		subTaskLabel.setFont(parent.getFont());
		return parent;
	}
   */
  
  override def  createDialogArea( parent : Composite) =
  {
    val  progress=    super.createDialogArea(parent);
    
    val checkBox = SWTFactory.createCheckButton(parent, TextRepository.SHOW_RACE_CALCUALTION_PROGRESS_TOGGLE  , null, false, 2 )
    
     checkBox.addSelectionListener( new SelectionListener()
     {
       def widgetSelected(e : SelectionEvent)
       {
         
         
         
         Activator.plugin.getPreferenceStore().setValue(PluginKeys.SHOW_RACE_CALCUALTION_PROGRESS , ! checkBox.getSelection() 
             );
       }
       
       
       def widgetDefaultSelected(e : SelectionEvent)
       {
//          Activator.plugin.getPreferenceStore().setValue(VMLensPropertyPage.SHOW_RACE_CALCUALTION_PROGRESS ,  checkBox.getSelection() 
//             );
       }
       
       
     });
    
    
    if (arrowCursor == null) {
			arrowCursor = new Cursor(checkBox.getDisplay(), SWT.CURSOR_ARROW);
		}
		checkBox.setCursor(arrowCursor);
    
    
    progress;
    
  }
  
}