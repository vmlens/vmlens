package com.vmlens.api.internal.reports.element

import  com.vmlens.api._
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.IconRepository
import com.vmlens.api.internal.reports._
import scala.collection.mutable.ArrayBuffer



class Element4TreeViewerIssuePart(val element : IssuePartElement,val parent :  Element4TreeViewer , val modelFacade : ModelFacade)  extends  Element4TreeViewer
{
  
   def getChildrens()  =  null; // element.children(modelFacade).map(  x =>  new Element4TreeViewerIssuePart(x,this,modelFacade)   ).toArray[Object]
  

   
  def hasChildren() =  false; //  ! element.children(modelFacade).isEmpty
    
  
  
  
  def getParent() = parent;
 
  def getText() =  element.name(modelFacade)
 
  
  /**
   * 
   * null wenn kein icon angezeigt werden soll
   * 
   */
  
  def getIcon() = element.icon(modelFacade)
  
  
  def searchData() = element.searchData(modelFacade);
  
  
  def children4Yaml() =
   {
     
      element.children(modelFacade) .map(  x =>   new Element4TreeViewerIssuePart(x , this ,  modelFacade )   )
     
     
   }
   
  
         def name4Yaml() =    element.name4Yaml(modelFacade) 
  
       
       def title4Yaml(position : Int) =    element.title4Yaml(position) 
      
  
}