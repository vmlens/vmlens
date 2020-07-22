package com.vmlens.api.internal.reports

class ContextReportBuilder(val reportBuilder : ReportFactory,val sharedDetailTemplate : String, val titleIndex: String, val titleElements: String) {
  
  val fileNameElements = "elements.html";
  
  
  def createContext(showSharedState : Boolean, showHeader : Boolean,showChildLinks : Boolean) =
  {
    new ContextReport( new LinkProvider( "issues/" ,  "templates/htmlIssue.mustache",reportBuilder) ,  
         new LinkProvider(  "elements/", sharedDetailTemplate ,reportBuilder)  ,showHeader,showChildLinks , titleIndex , titleElements , fileNameElements);
  }
  
  
  
}