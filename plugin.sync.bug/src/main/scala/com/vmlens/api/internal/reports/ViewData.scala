package com.vmlens.api.internal.reports

import com.vmlens.api.internal.reports.element.ReportText

class ViewData[CONTEXT](val elements : Seq[ReportElement[CONTEXT]], 
    val warnings : Seq[ReportText],val name : String,
    val titlePrefix : Option[String] , val root : String,val context : CONTEXT) {
  
}