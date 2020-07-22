package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports.ContextReport
import com.vmlens.api.internal.reports.ReportElement

class ReportSharedStateDetail(  val access : ReportElementList , val detail : Option[ReportElementList] ) extends ReportElement  {
  
  def name() = "ReportSharedStateDetail";
  
  
  def initialize( contextReport : ContextReport) 
       {
    
    if(contextReport.showChildLinks   )
    {
             access.initialize(contextReport);
             detail.foreach(  x =>   x.initialize(contextReport) ) ;
          
    }
           

       }
  
  
}
