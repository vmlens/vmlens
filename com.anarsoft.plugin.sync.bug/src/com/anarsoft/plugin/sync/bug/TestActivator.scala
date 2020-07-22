package com.anarsoft.plugin.sync.bug

import org.eclipse.swt.graphics.Image
import org.eclipse.swt.widgets.Display
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IViewSite
import com.anarsoft.plugin.view._;
import java.io._;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers._;
import org.eclipse.ui.views.properties._;
import org.eclipse.ui.ISelectionListener
import scala.collection.mutable.HashSet;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Shell
import org.eclipse.ui.part.ViewPart

class TestActivator extends Activator {
  
  private var display : Display =  null;
  private var imageRegistry : ImageRegistry = null;
  
  
   def setDisplay(in : Display)
  {
    display = in;
    imageRegistry = new ImageRegistry(display);
    initializeImageRegistry( imageRegistry ) ;
    
    
  }  
 
   
   override def execWithDisplay(f : Display => Unit )
  {
      display.asyncExec(new Runnable() {
   def run() {
   
       f(display);
     
   }
      });
    
  }
   
   
   
  
   
   
   // override def getJavaAgentString() =  " -javaagent:<path to javaagent>";
  
   
   override def getImageRegistry() = imageRegistry;
   
   override def addSingleImage(id : String, registry : ImageRegistry,bundle : Bundle)
  {
     
    val newPath =  id.replace(".", "@2x.")
    
    println( newPath );
     
    if(  new File(newPath).exists() )
    {
       registry.put(id, new Image(display,newPath))
    }
    else
    {
       registry.put(id, new Image(display, id))
    }
    
     
     
   
  }
   
  
 override def attachMenuBarToForm(form : ViewPart,  shellForTest : Shell ,  callback : IToolBarManager  => Unit)
   {
      val toolBar = new ToolBar( shellForTest  , SWT.FLAT | SWT.RIGHT);
      val manager = new ToolBarManager(toolBar);
  
    callback(manager);
  //   wrapper.addActionsTheToolBarManager(manager,modelViewView);
  
    manager.update(true);

    
 
   
   }

   
   
   
     override  def getSharedImage(name : String) = null;
   
   
    
}