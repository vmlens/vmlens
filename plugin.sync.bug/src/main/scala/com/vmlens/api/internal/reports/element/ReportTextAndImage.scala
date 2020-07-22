package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._

case class ReportTextAndImage(val name : String,val image : String  ) extends ReportElement {
  
  
   
   def initialize(contextReport: ContextReport) {
    
    
  }
  
}