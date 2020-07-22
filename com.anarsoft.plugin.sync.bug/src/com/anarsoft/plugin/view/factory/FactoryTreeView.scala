package com.anarsoft.plugin.view.factory

import com.anarsoft.plugin.view.action._
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.browser.Browser
import org.eclipse.ui.forms.widgets._;
import org.eclipse.swt.graphics._;
import org.eclipse.swt.widgets._;
import org.eclipse.swt.layout._
import org.eclipse.jface.viewers._;
import java.util._;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets._;
import org.eclipse.jface.window.ToolTip;
import scala.collection.mutable.ArrayBuffer
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource._
import org.eclipse.swt.events._;
import com.anarsoft.plugin.sync.bug.Activator;
import org.eclipse.ui.part.ViewPart
import org.eclipse.jface.resource.JFaceResources;
import com.anarsoft.plugin.view._;
import org.eclipse.jface.action._;
import com.anarsoft.plugin.view.internalView._
import com.anarsoft.plugin.view.tree.OpenDeclarationOnSelection
import com.anarsoft.plugin.view.launch.TreeViewerView
import  com.vmlens.api.internal.reports.Element4TreeViewer

object FactoryTreeView {
 
   def create(viewPart : ViewPart,  parent: Composite, data : ViewTreeData) = {
     
      val viewer = new TreeViewer(parent , SWT.MULTI |  SWT.H_SCROLL | SWT.V_SCROLL);
 
    ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.NO_RECREATE);


    viewer.setContentProvider(new ModelViewContentProvider());
    viewer.setLabelProvider(new ModelViewLabelProvider());
   // viewer.setUseHashlookup(true);
    
     viewer.setInput(data.elements); 
   viewer.refresh();
     
   viewer.addPostSelectionChangedListener(new OpenDeclarationOnSelection());
   
      Activator.plugin.attachMenuBarToForm(viewPart,viewer.getControl.getShell ,
        
    toolbar =>
      {
        
        toolbar.removeAll();
        
        toolbar.add(  new ExpandAllAction(viewer) ) 
        toolbar.add(  new CollapseAllAction(viewer) )
        toolbar.add(  new ExportAsYamlAction(data.elements) )
        
      } )
   
   
   
   
   
       val menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
       
        menuMgr.addMenuListener(new IMenuListener() {
        def menuAboutToShow( manager : IMenuManager) {
          
            val list = new ArrayBuffer[Element4TreeViewer]();
      
             val selection = viewer.getSelection().asInstanceOf[IStructuredSelection];
        
            val iter =  selection.iterator();
            
            while( iter.hasNext() )
            {
              val c = iter.next();
              list.append( c.asInstanceOf[Element4TreeViewer] );
            }
      
          
          if( list.isEmpty )
          {
            
          }
          else 
          {
             
            menuMgr.add(new CopyAction(list));
         
        
          }
          
          
          
        } } );
      
      
      
   
    viewer.getTree().setMenu(menuMgr.createContextMenu( viewer.getTree()))
   
   
   
   
     new TreeViewerView(viewer);
   }
  
  
}