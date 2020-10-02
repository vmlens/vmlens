package com.vmlens.api.internal.reports


// val issues : Seq[ReportElement], val elements : Seq[ReportElement]

class ViewResult[+ISSUE](val issues : Seq[ISSUE] , val elements : Seq[ReportElement[ContextReport]])  {
  
}