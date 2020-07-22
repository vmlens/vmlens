package com.anarsoft.plugin.view.factory

import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.layout.FillLayout
import org.eclipse.ui.forms.widgets._
import org.eclipse.swt.graphics._
import org.eclipse.swt.widgets._
import org.eclipse.swt.layout._
import org.eclipse.jface.viewers._
import org.eclipse.jface.resource._
import com.anarsoft.plugin.sync.bug._
import com.anarsoft.plugin.view.action._
import com.anarsoft.plugin.view._;
import scala.collection.mutable.ArrayBuffer;
import  com.vmlens.api.internal.reports.Element4TreeViewer
import java.util.Properties

object TestView {
  
  def main(args: Array[String]): Unit = {

    val display = new Display();
    val shell = new Shell(display);

    shell.setLayout(new FillLayout());
   
    val testActivator = new TestActivator();
    testActivator.setDisplay(display)
    
    Activator.plugin = testActivator;
    
//    val wrapper = new StartViewWrapper();
//    wrapper.createPartControl(shell);
    
    
  //FactoryStartView.create( null ,  shell , ViewStartData() );
    
//    val list = new ArrayBuffer[Element4TreeViewer]
//    list.append( new TestElement4TreeViewer() );
//    list.append( new TestElement4TreeViewer() );
    
 // FactoryTreeView.create(null , shell, new ViewTreeData( list.toSeq ))
    
 //   val browser = FactoryBrowserView.create(null, shell , new ViewUrlData("google.com"));
   
    
    FactoryLaunchTabInternal.create(shell, () => {});
    
  // val dialog =  new ExternalRunDialog(shell,"-javaagent:<Path to your vmlens installation>" , new Properties());
//    
//    
    shell.pack();
    shell.open();
//
    //  dialog.open();


   while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
     //   browser.dispose();
        display.dispose();


  }
  
  
}