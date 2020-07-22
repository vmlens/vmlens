package com.anarsoft.plugin.view.factory

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.widgets.Button;
import java.util.regex.Pattern
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events._;
import org.eclipse.swt.widgets._;
import com.anarsoft.plugin.sync.bug.Activator
import java.io._;
import java.util.Properties
import com.anarsoft.plugin.sync.bug.TextRepositoryEclipse


class ExternalRunDialog(parentShell : Shell,  val agentString : String, val properties : Properties) extends TitleAreaDialog(parentShell) {
  
//  val title  = "trace external application";
//  val  message = "use the vmlensagent to trace an external java application.";
//  val  label = "Add the following string to the vm arguments of your application:"
  
//  var elementField : Text = null;
//  var element : String = null;
  
  
  
  
  
  override def   createDialogArea( parent : Composite) = {
    
    val   contents = new Composite(parent, SWT.NONE);
		contents.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		setTitle(TextRepositoryEclipse.RUN_DIALOG_TITLE_ALL_APPLICATIONS);
		setMessage(TextRepositoryEclipse.RUN_DIALOG_MESSAGE);

		new Label(contents, SWT.LEFT)
				.setText(TextRepositoryEclipse.RUN_DIALOG_LABEL_LOCAL_TRACE);

		new Text( contents  , SWT.READ_ONLY | SWT.BORDER| SWT.WRAP).setText(agentString)

				new Label(contents, SWT.LEFT)
				.setText(TextRepositoryEclipse.RUN_DIALOG_LABEL_EXTERNAL_TRACE);
		
		
		val defaultMargins = LayoutConstants.getMargins();
		GridLayoutFactory.fillDefaults().numColumns(1).margins(
				defaultMargins.x, defaultMargins.y).generateLayout(contents);

		 contents;
    
    
  }
  
  
   override def createButtonsForButtonBar( parent : Composite) {
     
    
     
      val exportButton =  createButton(parent, IDialogConstants.CLIENT_ID + 1,
                "Export...", true);
     
      
      exportButton.addSelectionListener(new SelectionAdapter() {
      override def widgetSelected(event: SelectionEvent) {
        val dialog = new DirectoryDialog ( parent.getShell  , SWT.NONE);
            val fileName = dialog.open();
          
               if( fileName != null )
            {
              
                Activator.plugin.copyJarFilesWithLocation( jar => new File(fileName  + "/"+ jar )  );
                
                properties.setProperty("eventDir","./vmlens/");
                val stream = new FileOutputStream(new File(fileName + "/run.properties")) ;
                properties.store(stream, "");
                stream.close();
                

       
              
            }
            
            
        
      }
    });
      
      
     
     createButton(parent, IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL, true);
       
  
       
//        okButton.setEnabled(false);
//        createButton(parent, IDialogConstants.CANCEL_ID,
//                IDialogConstants.CANCEL_LABEL, false);
    }
}