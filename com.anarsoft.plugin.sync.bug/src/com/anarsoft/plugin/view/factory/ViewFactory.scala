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
import com.anarsoft.plugin.view._


object ViewFactory {
  
  def create(viewPart : ViewPart, parent: Composite, data : ViewData) =
  {
    data match
    {
      
    
        case x :  ViewUrlData =>
        {
          FactoryBrowserView.create(viewPart , parent, x);
        }  
      
         case x :  ViewTreeData =>
        {
          FactoryTreeView.create(viewPart , parent, x);
        }
      
    }
  }
  
  
  
  
}