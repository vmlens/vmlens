package com.anarsoft.plugin.view

import org.eclipse.jface.viewers._;
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.graphics._;
import com.anarsoft.plugin.sync.bug.Activator;
import org.eclipse.swt.graphics.Image;
import  com.vmlens.api.internal.reports.Element4TreeViewer
import com.anarsoft.plugin.sync.bug.Activator


class ModelViewLabelProvider extends ColumnLabelProvider {
  
  
  
  override
  def  getText( element : Object) = {
     element.asInstanceOf[Element4TreeViewer].getText();
     
  }
  
  
   override def getImage( element : Object) =
  {
     val icon = element.asInstanceOf[Element4TreeViewer].getIcon();
     
        if( icon == null )
     {
       null;
     }
     else
     {
       Activator.getImage( icon );
     }
     
     
     
  }
  
  
}