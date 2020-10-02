package com.vmlens.api.internal.reports

trait ReportFactory[CONTEXT <: ContextReportAbstract] {
  
  
    def addView(link : String ,viewProvider : ViewProvider[CONTEXT]);
    def addPrefix(prefix : String);
 
  
  
}