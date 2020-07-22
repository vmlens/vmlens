package com.vmlens.api.internal.reports.element

import  com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports._


class ReportParentMethod(val parentMethod: ParentMethod, val  modelFacade : ModelFacadeState)  extends ReportElement {
  
   
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
  
    parentChildLink = contextReport.elementDetailLink.createLink(  
        new ViewData(   ReportParentChild.create4Parent( this , 20 )  , Nil ,  parentMethod.methodOrdinal.fullName( modelFacade )  ,  None ,  "../" , contextReport  ) , Some( "../")   )
  
  
//    parentChildLink = contextReport.parentChildLink.createLinkFromChild(   "../" , 
//         ( templateName , link  ) =>
//           {
//              new ReportView(  ReportParentChild.create4Parent( this , 20 ) , Nil ,  parentMethod.methodOrdinal.fullName( modelFacade ) ,
//                  link   ,  templateName ,"../" , contextReport  )
//             
//             
//           }
//         )
  }
 
 
}