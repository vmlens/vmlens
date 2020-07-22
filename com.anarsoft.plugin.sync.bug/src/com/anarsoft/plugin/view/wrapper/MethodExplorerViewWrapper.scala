package com.anarsoft.plugin.view.wrapper

import com.anarsoft.plugin.sync.bug.Activator;
import org.eclipse.jface.viewers._;
import com.anarsoft.plugin.view._;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT
import com.anarsoft.plugin.view.action._;



class MethodExplorerViewWrapper  extends ViewPartTemplate {
  
  def id = Activator.ELEMENT_VIEW_ID;
  
    def viewData  = Activator.plugin.viewManager.viewState.elementViewData;
  
  
}