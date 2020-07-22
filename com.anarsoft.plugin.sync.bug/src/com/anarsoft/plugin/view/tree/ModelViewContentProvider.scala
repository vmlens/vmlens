package com.anarsoft.plugin.view

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import com.anarsoft.race.detection.model._;
import com.anarsoft.integration._;
import  com.vmlens.api.internal.reports.Element4TreeViewer


class ModelViewContentProvider extends ITreeContentProvider {
  
  def  getChildren( parentElement  :Object) = parentElement.asInstanceOf[Element4TreeViewer].getChildrens();
  
  

  
   def getElements( inputElement : Object) =
   {
      inputElement.asInstanceOf[Seq[Element4TreeViewer]].toArray[Object]
   }

   
 def  getParent( element : Object) = element.asInstanceOf[Element4TreeViewer].getParent();
 
 
 def   hasChildren( element : Object) = element.asInstanceOf[Element4TreeViewer].hasChildren();

  

 def inputChanged( viewer : Viewer,
                 oldInput : Object,
                 newInput : Object)
 {
  
 }
  
  

 def dispose()
 {
   
 }
  
  
  
  
  
  
  
}