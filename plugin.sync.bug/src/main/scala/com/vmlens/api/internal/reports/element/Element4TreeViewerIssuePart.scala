package com.vmlens.api.internal.reports.element

import  com.vmlens.api._
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.IconRepository
import com.vmlens.api.internal.reports._
import scala.collection.mutable.ArrayBuffer



class Element4TreeViewerIssuePart(val element : IssuePartElement,val parent :  Element4TreeViewer , val ModelFacadeAll : ModelFacadeAll)  extends  Element4TreeViewer
{
  
   def getChildrens()  =  null; // element.children(ModelFacadeAll).map(  x =>  new Element4TreeViewerIssuePart(x,this,ModelFacadeAll)   ).toArray[Object]
  

   
  def hasChildren() =  false; //  ! element.children(ModelFacadeAll).isEmpty
    
  
  
  
  def getParent() = parent;
 
  def getText() =  element.name(ModelFacadeAll)
 
  
  /**
   * 
   * null wenn kein icon angezeigt werden soll
   * 
   */
  
  def getIcon() = element.icon(ModelFacadeAll)
  
  
  def searchData() = element.searchData(ModelFacadeAll);
  
  
  def children4Yaml() =
   {
     
      element.children(ModelFacadeAll) .map(  x =>   new Element4TreeViewerIssuePart(x , this ,  ModelFacadeAll )   )
     
     
   }
   
  
         def name4Yaml() =    element.name4Yaml(ModelFacadeAll) 
  
       
       def title4Yaml(position : Int) =    element.title4Yaml(position) 
      
  
}