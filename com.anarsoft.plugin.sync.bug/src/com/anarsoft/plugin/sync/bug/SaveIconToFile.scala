package com.anarsoft.plugin.sync.bug

import com.anarsoft.plugin.sync.bug._
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.SWT
import org.eclipse.ui.forms.widgets._
import org.eclipse.swt.graphics._
import org.eclipse.swt.widgets._
import org.eclipse.swt.layout._
import org.eclipse.jface.viewers._
import java.util._
import org.eclipse.swt.widgets._

import org.eclipse.jface.resource._
import org.eclipse.swt.events._
import com.anarsoft.plugin.view._
import com.anarsoft.race.detection.model._
import com.anarsoft.race.detection._
import java.io._;
import com.vmlens.api.internal.IconRepository
import org.apache.commons.io.FileUtils

object SaveIconToFile {
   def main(args: Array[String]): Unit = {
    
    val display = new Display();
   
    
    
    val testActivator =  new TestActivator();
     Activator.plugin =testActivator;
    
    testActivator.setDisplay(display);
    
      val path =  "../plugin.sync.bug/src/main/resources/copy/mvnHtmlReport/img/"
     // val path =  "../icons/created_by_overlay/"
   
      val dir = new File(path);
      
      
     FileUtils.deleteDirectory(dir);
     
    
     dir.mkdir();
     
    IconRepository.foreachIcon ( x =>  
      {
        
        
        
        
        if( x.isObj() )
        {
          
          
          println(x);
          
          
               val imageLoader = new ImageLoader();

   imageLoader.data = Array.ofDim[ImageData](1);
   
  //   println( Activator.getImageDescriptor(x) );
   
   
  // println(Activator.getImage(x).getClass);
   
   imageLoader.data (0) =  Activator.getImage(x).getImageData();
   
   
 
   imageLoader.save( path +    x.getName() + ".png",SWT.IMAGE_PNG);
   
        }
 
    
    
      }
    )
    
    
   CopyWebFiles.copy();
    
  }
}