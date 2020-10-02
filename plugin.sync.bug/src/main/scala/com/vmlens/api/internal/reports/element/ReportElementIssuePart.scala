package com.vmlens.api.internal.reports.element

import  com.vmlens.api._
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.IconRepository
import com.vmlens.api.internal.reports._



class ReportElementIssuePart(val element : IssuePartElement, val modelFacade : ModelFacadeAll) extends ReportElement[ContextReport]  {
  
  

  
  
     def nameWithHtml() = Model2View.addBreak( element.nameWithHtml(modelFacade));
  
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
     
      def children = element.children(modelFacade).map(  x =>  new ReportElementIssuePart(x,modelFacade)  )
   
      
  
      
   
   
   
     
   def name = element.name(modelFacade)
   
   def initialize( contextReport : ContextReport) 
   {
        
   }
  
      

       
       
}