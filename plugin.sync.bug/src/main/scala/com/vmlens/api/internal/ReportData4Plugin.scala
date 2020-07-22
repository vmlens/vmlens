package com.vmlens.api.internal

import com.vmlens.api.internal.reports.Element4TreeViewer
import com.vmlens.api.internal.reports.ResultHtmlOnline


class ReportData4Plugin(val issues : Either[Seq[Element4TreeViewer],String], val elements : String, val resultHtmlOnline : ResultHtmlOnline   ) {
  
}