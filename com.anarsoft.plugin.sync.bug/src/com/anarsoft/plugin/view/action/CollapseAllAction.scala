package com.anarsoft.plugin.view.action

/**
 * Copied from org.eclipse.search2.internal.ui.basic.views.CollapseAllAction
 * 
 * 
 */
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.action.Action;
import com.anarsoft.plugin.view._;
import com.anarsoft.plugin.sync.bug._;
import com.vmlens.api.internal.IconRepository;
import com.anarsoft.integration.TextRepository


class CollapseAllAction(val view : TreeViewer) extends Action(TextRepository.COLLAPSE_ALL) {
  
  setImageDescriptor(Activator.getImageDescriptor(IconRepository.COLLAPSE_ALL));
  
  
  
   override def run()
  {
     view.collapseAll();
  }
   
     
  
}