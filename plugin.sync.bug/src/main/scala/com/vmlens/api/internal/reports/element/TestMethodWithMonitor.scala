package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._

class TestMethodWithMonitor(val name : String , val group : Int , val ownerName : Option[String] ,val methodContainsMonitor : Boolean ,  val calledMethodContainsMonitor : Boolean  )  extends ReportElement  {
  
  
   def initialize(contextReport: ContextReport) {

   }
  
}