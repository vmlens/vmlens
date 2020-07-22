package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._

class TestMethodWithSharedState(val name : String , val group : Int , val ownerName : Option[String] , val sharedState : Seq[String]   )  extends ReportElement  {
  
   def initialize(contextReport: ContextReport) {

   }
  
}