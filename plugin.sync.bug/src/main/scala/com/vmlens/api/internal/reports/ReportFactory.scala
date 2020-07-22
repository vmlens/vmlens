package com.vmlens.api.internal.reports

trait ReportFactory {
  
  
    def addView(link : String ,viewProvider : ViewProvider);
    def addPrefix(prefix : String);
 
  
  
}