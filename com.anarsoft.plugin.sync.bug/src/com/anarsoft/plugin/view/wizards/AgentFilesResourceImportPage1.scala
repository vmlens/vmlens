package com.anarsoft.plugin.view.wizards

import org.eclipse.ui.dialogs.WizardResourceImportPage;
import org.eclipse.jface.wizard.WizardPage
import org.eclipse.swt.widgets._;
import org.eclipse.swt.layout._
import org.eclipse.swt.SWT
import org.eclipse.swt.events._

class AgentFilesResourceImportPage1 extends WizardPage("vmlens agent files" , "vmlens" , null)  {
  
  /*
   *   Composite sourceContainerGroup = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        sourceContainerGroup.setLayout(layout);
        sourceContainerGroup.setFont(parent.getFont());
        sourceContainerGroup.setLayoutData(new GridData(
                GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
        Label groupLabel = new Label(sourceContainerGroup, SWT.NONE);
        groupLabel.setText(getSourceLabel());
        groupLabel.setFont(parent.getFont());
        // source name entry field
        sourceNameField = new Combo(sourceContainerGroup, SWT.BORDER);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL);
        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        sourceNameField.setLayoutData(data);
        sourceNameField.setFont(parent.getFont());
        BidiUtils.applyBidiProcessing(sourceNameField, StructuredTextTypeHandlerFactory.FILE);
        sourceNameField.addSelectionListener(new SelectionAdapter() {
            @Override
			public void widgetSelected(SelectionEvent e) {
                updateFromSourceField();
            }
        });
        sourceNameField.addKeyListener(new KeyListener() {
            /*
             * @see KeyListener.keyPressed
             */
            @Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					entryChanged = false;
					updateFromSourceField();
				}
            }

            /*
             * @see KeyListener.keyReleased
             */
            @Override
			public void keyReleased(KeyEvent e) {
            }
        });
        sourceNameField.addModifyListener(e -> entryChanged = true);
        sourceNameField.addFocusListener(new FocusListener() {
            /*
             * @see FocusListener.focusGained(FocusEvent)
             */
            @Override
			public void focusGained(FocusEvent e) {
                //Do nothing when getting focus
            }
            /*
             * @see FocusListener.focusLost(FocusEvent)
             */
            @Override
			public void focusLost(FocusEvent e) {
                //Clear the flag to prevent constant update
                if (entryChanged) {
                    entryChanged = false;
                    updateFromSourceField();
                }
            }
        });
        // source browse button
        sourceBrowseButton = new Button(sourceContainerGroup, SWT.PUSH);
        sourceBrowseButton.setText(DataTransferMessages.DataTransfer_browse);
        sourceBrowseButton.addListener(SWT.Selection, this);
        sourceBrowseButton.setLayoutData(new GridData(
                GridData.HORIZONTAL_ALIGN_FILL));
        sourceBrowseButton.setFont(parent.getFont());
        setButtonLayoutData(sourceBrowseButton);
   */
  
  
    /*    protected void handleSourceBrowseButtonPressed() {

        String currentSource = this.sourceNameField.getText();
        DirectoryDialog dialog = new DirectoryDialog(
                sourceNameField.getShell(), SWT.SAVE | SWT.SHEET);
        dialog.setText(SELECT_SOURCE_TITLE);
        dialog.setMessage(SELECT_SOURCE_MESSAGE);
        dialog.setFilterPath(getSourceDirectoryName(currentSource));

        String selectedDirectory = dialog.open();
        if (selectedDirectory != null) {
            //Just quit if the directory is not valid
            if ((getSourceDirectory(selectedDirectory) == null)
                    || selectedDirectory.equals(currentSource)) {
				return;
			}
            //If it is valid then proceed to populate
            setErrorMessage(null);
            setSourceName(selectedDirectory);
            selectionGroup.setFocus();
        }
    }
    * 
    */
  
  var  sourceField  : Option[Text] = None;
  
  
  
  def createControl(parent : Composite)
  {
     val composite = new Composite(parent, SWT.NULL);
    
    
    val layout = new GridLayout();
    layout.numColumns = 3;
    composite.setLayout(layout  );
  
  
    val label = new Label(composite , SWT.NONE);
    label.setText("agent directory:")
    
    val text = new Text(composite , SWT.BORDER);
    
    val textLayout = new GridData(GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL);
      
    
    text.setLayoutData(  textLayout )
    
    
    val browseButton = new Button( composite , SWT.None );
    browseButton.setText("Browse...")
    
    
    browseButton.addSelectionListener(new SelectionAdapter() {
      override def widgetSelected(event: SelectionEvent) {
       
           
        val dialog = new DirectoryDialog(
                text.getShell(), SWT.SAVE | SWT.SHEET);
        
        val   selectedDirectory = dialog.open();
        if (selectedDirectory != null) {
            text.setText( selectedDirectory )
            text.setFocus();
        }

      }
    });
    
    
    
    
    sourceField = Some(text);
    
    setControl(composite);
  
  }
  
  
  /*
   *     public boolean finish() {
        if (!ensureSourceIsValid()) {
			return false;
		}

        saveWidgetValues();

        Iterator resourcesEnum = getSelectedResources().iterator();
        List fileSystemObjects = new ArrayList();
        while (resourcesEnum.hasNext()) {
            fileSystemObjects.add(((FileSystemElement) resourcesEnum.next())
                    .getFileSystemObject());
        }

        if (fileSystemObjects.size() > 0) {
			return importResources(fileSystemObjects);
		}

        MessageDialog.openInformation(getContainer().getShell(),
                DataTransferMessages.DataTransfer_information,
                DataTransferMessages.FileImport_noneSelected);

        return false;
    }
   */
  
  
  
 def agentDir()  = sourceField.map(  x =>  x.getText()  );
  
  
  

    
  
}