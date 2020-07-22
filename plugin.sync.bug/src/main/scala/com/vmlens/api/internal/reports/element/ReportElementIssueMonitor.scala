package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports._
import scala.collection.mutable.ArrayBuffer

class ReportElementIssueMonitor(val element : IssueModelElementMonitor , 
    val modelFacade : ModelFacadeMonitor) extends ReportElement  with Element4TreeViewer {
  
  
  
  def children =
  {
    
    
      Nil;
   
  }
  
  
  
  
   var link : Option[String] = None;
   def imagePath=
   {
   
     val icon = element.icon(modelFacade);
    
  
    if( icon == null )
    {
      null;
    }
    else
    {
      "img/" + icon.getName() + ".png" 
    }
  
   }
     
     
     
     
   def name = element.name(modelFacade)
   

 
  
   def initialize( contextReport : ContextReport) 
       {
     
           
       link = contextReport.issueDetailLinks.createLink(  
           
         new ViewData(  element.children(modelFacade).map(  x =>  new ReportElementIssuePart(x,modelFacade)   )  , Nil , name ,  None ,  "../" , contextReport  ) , None
       
       );
     

       }
   
   
   
       def name4Yaml() =    element.name4Yaml(modelFacade) 
  
       
       def title4Yaml(position : Int) =    element.title4Yaml(position) 
   
   
   // for yaml start
   
   
   def children4Yaml =
   {
     
      element.children(modelFacade) .map(  x =>   new Element4TreeViewerIssuePart(x , this ,  modelFacade )   )
     
     
   }
   
   
   
   
   
   
   
   // for yaml end
   
   
   
   
    // for eclipse plugin start
   
    def getChildrens()  =  {
      
      val list = new ArrayBuffer[IssuePartElement]
      
      for( c <-    element.children(modelFacade) )
      {
        list.append(c);
        
        for( gc <- c.children(modelFacade) )
        {
          list.append(gc);
        }
        
      }
      
      
      list.map(  x =>  new Element4TreeViewerIssuePart(x,this,modelFacade)   ).toArray[Object]
    }
  

   
  def hasChildren() = true;
    
  
  
  
  def getParent() = null;
 
  def getText() = name
 
  
  /**
   * 
   * null wenn kein icon angezeigt werden soll
   * 
   */
  
  def getIcon() = null; //element.icon(modelFacade)
  
   
   def searchData() = None;
  
  
    // for eclipse plugin end
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
  
  
  
}