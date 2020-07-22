package com.anarsoft.plugin.view.launch

import org.eclipse.jface.viewers.TreeViewer
import com.anarsoft.plugin.view.internalView.InternalView

class TreeViewerView (val form : TreeViewer) extends InternalView {
  
    def dispose()
    {
      form.getControl.dispose()
    }
    
    
  def setFocus()
  {
       form.getControl.setFocus();
  }
}