package com.vmlens.api.internal.reports.element

import  com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports._



class ReportParentMethodWithMonitor(val parentMethod: ParentMethodWithMonitor, val  modelFacade : ModelFacadeMonitor)  extends ReportElement {
  
   
  def methodContainsMonitor = parentMethod.methodContainsMonitor;
  
  
  def name() = Model2View.makeBreakable( parentMethod.methodOrdinal.fullName( modelFacade ));
  
  
    def group() = parentMethod.group;
   
  
  def color =
  {
    if( group() % 2 == 1 )
    {
     // Some("Wheat")
      Some("#d8dbdd")
      
    }
    else
    {
      None;
    }
    
    
    
  }
  
  
    var parentChildLink   : Option[String] = None;
  
  
def initialize( contextReport : ContextReport) 
  {
  
  /*
   *  link = contextReport.issueLinks.createLink(  
           
         new ViewData(  element.children(modelFacade).map(  x =>  new ReportElementStackTrace(x,modelFacade)   )  , Nil , name ,   "../" , contextReport  )
       
       );
   */
  
    parentChildLink = contextReport.elementDetailLink.createLink(  
        new ViewData(   ReportParentChildMonitor.create4Parent( this , 20 )   , Nil ,  parentMethod.methodOrdinal.fullName( modelFacade )  ,  None ,  "../" , contextReport  ) , Some( "../")  )
  
  
  
//    parentChildLink = contextReport.parentChildLink.createLinkFromChild(   "../" , 
//         ( templateName , link  ) =>
//           {
//              new ReportView(  ReportParentChildMonitor.create4Parent( this , 20 ) , Nil ,  parentMethod.methodOrdinal.fullName( modelFacade ) ,
//                  link   ,  templateName ,"../" , contextReport  )
//             
//             
//           }
//         )
  }
  
}