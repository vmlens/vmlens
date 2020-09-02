package com.anarsoft.plugin.view.factory

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
import org.eclipse.jface.action.Action;
import com.anarsoft.integration.TextRepository
import com.vmlens.api.internal.IconRepository
import com.vmlens.api.Icon
import org.eclipse.ui.PlatformUI
/*
 * https://www.eclipse.org/articles/Article-SWT-browser-widget/browser.html
 */


object FactoryBrowserView {
  
  
  
    def addAction2Toolbar(toolbar : IToolBarManager, action :  (   ) => Unit , name : String , icon : Icon)
    {
        val back = new Action(name)
          {
              override def run()
              {
      
                 Activator.plugin.execWithDisplay( d =>
                 {
                     action();
           
                 } )
             } 
            
          }
          
           back.setImageDescriptor(Activator.getImageDescriptor(icon));
           toolbar.add( back  );
    }
  
  
  
  
  
  
  
    def create(viewPart : ViewPart,  parent: Composite, data : ViewUrlData) = {
  
         Activator.plugin.debug("url  " + data.url);
      
      
    val  browser = new Browser(parent, SWT.NONE);
    
    
     PlatformUI.getWorkbench().getHelpSystem().setHelp(browser,
       Activator.PLUGIN_ID + ".vmlens_view");
    
     browser.setUrl( data.url);
     
     
     
     
      Activator.plugin.attachMenuBarToForm(viewPart,browser.getShell ,
        
    toolbar =>
      {
        
        toolbar.removeAll();
        
        addAction2Toolbar( toolbar ,  () =>
          {
            if(browser.isBackEnabled() )
            {
              browser.back();
            }
            
            
          } , TextRepository.BACK, IconRepository.BACK );
        
        
        
        
           addAction2Toolbar( toolbar ,  () =>
          {
            if(browser.isForwardEnabled() )
            {
              browser.forward();
            }
            
            
          } , TextRepository.FORWARD, IconRepository.FORWARD );
        
        
           addAction2Toolbar( toolbar ,  () =>
          {
              browser.setUrl( data.url);
            
            
          } , TextRepository.HOME, IconRepository.HOME );
           
           
           
    
//     val importTraceFilesAction = new ImportTraceFilesAction();
//      importTraceFilesAction.setImageDescriptor(Activator.getImageDescriptor(IconRepository.INTERNALIZE));
//    toolbar.add( importTraceFilesAction  );
        
      } )
     
     
     
     
     
     
     
     
    new BrowserView(browser);
     
   }
  
  
  
}