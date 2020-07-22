package com.vmlens.api.internal.reports

import com.vmlens.api.internal.reports.element.ReportText

class ViewData(val elements : Seq[ReportElement], val warnings : Seq[ReportText],val name : String,val titlePrefix : Option[String] , val root : String,val context : ContextReport) {
  
}