package com.anarsoft.plugin.view.internalView

import org.eclipse.ui.forms.widgets.Form

class FormView(val form : Form) extends InternalView {
  
    def dispose()
    {
      form.dispose();
    }
    
    
  def setFocus()
  {
      form.setFocus();
  }
  
}