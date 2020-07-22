package com.anarsoft.plugin.view.action

/**
 * copied from org.eclipse.search2.internal.ui.basic.views.ExpandAllAction
 * 
 * 
 */
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.action.Action;
import com.anarsoft.plugin.view._;
import com.anarsoft.plugin.sync.bug._;
import com.vmlens.api.internal.IconRepository;
import com.anarsoft.integration.TextRepository

class ExpandAllAction(val view : TreeViewer) extends Action(TextRepository.EXPAND_ALL) {
  
  
   setImageDescriptor(Activator.getImageDescriptor(IconRepository.EXPAND_ALL));
  
  
  
   override def run()
  {
     view.expandAll();
  }
   
     
  
}