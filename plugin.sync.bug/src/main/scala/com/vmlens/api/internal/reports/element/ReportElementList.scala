package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports.ContextReport
import com.vmlens.api.internal.reports.ReportElement



class  ReportElementList(val name : String, val issues : Seq[ReportElement]) extends ReportElement  {
  
  
  def hasElements() = ! issues.isEmpty
   
  
    def initialize( contextReport : ContextReport) 
       {
           for( c<- issues )
           {
             c.initialize(contextReport);
           }
       }
  
  
}

