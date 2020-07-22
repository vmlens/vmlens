package com.anarsoft.plugin.view.factory

import org.eclipse.ui.forms.widgets._
import org.eclipse.swt.graphics._
import org.eclipse.swt.widgets._
import org.eclipse.swt.layout._
import org.eclipse.jface.viewers._
import org.eclipse.jface.resource._
import org.eclipse.swt.events._
import org.eclipse.jface.dialogs._
import com.anarsoft.plugin.sync.bug._
import com.anarsoft.integration.TextRepository
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.SWT
import org.eclipse.ui.forms.widgets._
import org.eclipse.swt.graphics._
import org.eclipse.swt.widgets._
import org.eclipse.swt.layout._
import org.eclipse.jface.viewers._
import org.eclipse.jface.resource._
import com.anarsoft.plugin.sync.bug._
import com.anarsoft.plugin.view.action._
import org.eclipse.ui.forms.events._
import com.vmlens.api.internal.IconRepository
import org.eclipse.ui.part.ViewPart
import com.anarsoft.plugin.view.ViewStartData
import com.anarsoft.plugin.view.internalView._
import org.eclipse.jface.window._;

object FactoryLaunchTabInternal {

  def create(control: Composite, onViewChanged : () => Unit) = {
    control.setLayout(new GridLayout(1, true));
    val groupCombo = SWTFactory.createGroup(control, "mode", 1, 1, 768);

    val combo = new Combo(groupCombo, SWT.READ_ONLY);
    combo.setItems("interleave", "state", "monitor");

    combo.select(0);
    
    combo.addSelectionListener(new SelectionAdapter() {
      override def widgetSelected(event: SelectionEvent) {
           onViewChanged();
      }
    });
    
    

    val listTrace         = createListEditor(control, "trace" , onViewChanged );
    val listDoNotTraceIn  = createListEditor(control, "do not trace" , onViewChanged);
    val excludeFromStackTrace         = createListEditor(control, "exclude from stacktrace" , onViewChanged );
    val suppress  = createListEditor(control, "suppress" , onViewChanged);
    
    new LaunchTabInternal( combo , listTrace ,  listDoNotTraceIn , excludeFromStackTrace ,  suppress);
  }

  def createButton(control: Composite, key: String) =
    {
      val button = new Button(control, SWT.PUSH);
      button.setText(JFaceResources.getString(key));

      val gd = new GridData(GridData.FILL_HORIZONTAL);
      button.setLayoutData(gd)

      button;
    }

  /*
   * addButton = createPushButton(box, "ListEditor.add");//$NON-NLS-1$
        removeButton = createPushButton(box, "ListEditor.remove");//$NON-NLS-1$
   */

  def createListEditor(control: Composite, title: String, onViewChanged : () => Unit ) = {
    val group = SWTFactory.createGroup(control, title, 2, 1, 768);

    val groupLayoutData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
    group.setLayoutData(groupLayoutData)

    val list = new List(group, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);

    val gd = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
    gd.verticalAlignment = GridData.FILL;
    gd.horizontalSpan = 1;
    gd.grabExcessHorizontalSpace = true;

    list.setLayoutData(gd)

    val buttonBox = new Composite(group, SWT.NULL);

    buttonBox.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING))

    val layout = new GridLayout();
    layout.marginWidth = 0;
    buttonBox.setLayout(layout);

    val addButton = createButton(buttonBox, "ListEditor.add")
    val editButton = createButton(buttonBox, "Edit...")
    val removeButton = createButton(buttonBox, "ListEditor.remove")

    removeButton.setEnabled(false);
    editButton.setEnabled(false);
    
    
    addButton.addSelectionListener(new SelectionAdapter() {
      override def widgetSelected(event: SelectionEvent) {
        val input = getNewInputObject(addButton.getShell);

        if (input != null) {
          val index = list.getSelectionIndex();
          if (index >= 0) {
            list.add(input, index + 1);
          } else {
            list.add(input, 0);
          }
          selectionChanged(list, removeButton, editButton );
          onViewChanged();
          
        }

      }
    });
    
    
    editButton.addSelectionListener(new SelectionAdapter() {
      override def widgetSelected(event: SelectionEvent) {
        val index = list.getSelectionIndex();
        
        
        if(index >= 0)
        {
          val input = getChangedInputObject(addButton.getShell, list.getItem(index) );

        if (input != null) {
          
         
          list.setItem( index , input);
        
          onViewChanged();
          
        }
        
        }

      }
    });
    
    
    
    
    
    
    

    removeButton.addSelectionListener(new SelectionAdapter() {
      override def widgetSelected(event: SelectionEvent) {
        val index = list.getSelectionIndex();
        if (index >= 0) {
          list.remove(index);
          
          if(index >= list.getItemCount())
          {
             list.select(  index - 1 );
          }
          else
          {
             list.select( index);
          }
          
           onViewChanged();
            selectionChanged(list, removeButton, editButton);
        }
      }

    });

    list.addSelectionListener(new SelectionAdapter() {
      override def widgetSelected(event: SelectionEvent) {
        selectionChanged(list, removeButton, editButton);
      }
    });

    
   list; 
    
  }

  def getChangedInputObject(shell: Shell, text : String) =
    {
      val dialog = new NewOrEditElementDialog(shell,TextRepositoryEclipse.TILE_EDIT_ELEMENT, TextRepositoryEclipse.DESCRIPTION_EDIT_ELEMENT  , text);
      if (dialog.open() == Window.OK) {
        dialog.getElement();
      } else {
        null;
      }
    }
  
  
  
  def getNewInputObject(shell: Shell) =
    { 
      val dialog = new NewOrEditElementDialog(shell,TextRepositoryEclipse.TILE_NEW_ELEMENT, TextRepositoryEclipse.DESCRIPTION_NEW_ELEMENT , "");
      if (dialog.open() == Window.OK) {
        dialog.getElement();
      } else {
        null;
      }
    }

  def selectionChanged(list: List, removeButton: Button, editButton : Button) {
    val index = list.getSelectionIndex();
    removeButton.setEnabled(index >= 0);
    editButton.setEnabled(index >= 0);
  }

}