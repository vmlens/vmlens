package com.anarsoft.plugin.view.factory

import org.eclipse.jface.dialogs.Dialog;
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.forms.widgets.FormToolkit
import org.eclipse.swt.layout._
// ,val  label : String


class NewOrEditElementDialog(parentShell : Shell,  val title : String, val  message : String, val  currentText : String ) extends Dialog(parentShell) {
  
  
  var elementField : Text = null;
  var element : String = null;
  var okButton : Button = null
  
   
  
  
  def getElement() = element;
  
  
  
  def validateElement() =
  {
    if( element == null )
    {
      false;
    }
    else
    {
    NewOrEditElementDialog.checkText(element);
    
    }
  }
  
  override def   createDialogArea( parent : Composite) = {
    
    val   contents = new Composite(parent, SWT.NONE);
		contents.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		val layout = new GridLayout();
    layout.numColumns = 1;
		contents.setLayout(layout);
//		setTitle(title);
//		setMessage(message);

//		new Label(contents, SWT.LEFT)
//				.setText(label);
		
			val label = 	new Label(contents, SWT.WRAP)
		label.setText(message);
		

		elementField = new Text(contents, SWT.WRAP | SWT.BORDER);
		elementField.setText(currentText);
		
	//	 elementField.setLayoutData(new RowData(50, 80));
		
		val                gridData = new GridData();
                gridData.horizontalAlignment = GridData.FILL;
                gridData.verticalAlignment = GridData.FILL;
                gridData.grabExcessHorizontalSpace = true;
                gridData.grabExcessVerticalSpace = true;
		 
     elementField.setLayoutData(gridData)           
                
                
		 
		elementField.addModifyListener(new ModifyListener() {
			
			def  modifyText( event : ModifyEvent) {
				if (event.widget == elementField) {
					element = elementField.getText().trim();
					okButton.setEnabled(validateElement());
				}
			}
		});
		elementField.setFocus();

//   val toolkit = new FormToolkit(parent.getDisplay());
//		val formText = toolkit.createFormText(parent, true);
//		
//		// https://www.eclipse.org/articles/Article-Forms/article.html
//		
//		formText.setText(message, true, false)

	
		
	//	label.setSize(400, 150)

//		val defaultMargins = LayoutConstants.getMargins();
//		GridLayoutFactory.fillDefaults().numColumns(1).margins(
//				defaultMargins.x, defaultMargins.y).generateLayout(contents);

		contents.layout();
		contents.pack();
		
		 contents;
    
    
  }
  
  
   override def createButtonsForButtonBar( parent : Composite) {
        okButton = createButton(parent, IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL, true);
        okButton.setEnabled(false);
        createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);
    }
  
  
   override def  configureShell( newShell : Shell) {
        super.configureShell(newShell);
        newShell.setText(title);
    }
   
   
   override def getInitialSize() ={
         new Point(450, 500);
    }
   
   
}

object NewOrEditElementDialog
{
   val classPattern = Pattern.compile("(\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierStart}*\\.)*(\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*)*");
  
  def checkText( text : String) =
  {
     val string =  text.replace('*' , 'x');
    
      val matcher = classPattern.matcher(string);
       
       matcher.matches();
  }
  
  
 
  
  def main(args : Array[String])
  {
     val string = "test.**.*".replace('*' , 'x');
    
      val matcher = classPattern.matcher(string);
       
     println( matcher.matches() );
  }
  
  
  
  
}





