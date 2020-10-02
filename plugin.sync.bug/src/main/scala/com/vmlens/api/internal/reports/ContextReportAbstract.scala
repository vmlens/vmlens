package com.vmlens.api.internal.reports

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


trait ContextReportAbstract {
  
 def inMaven : Boolean;


 def add2Header( headerList :  ArrayBuffer[Header], reportView  : ReportView[_] );
 
 
 def add2Map(map :  HashMap[String, AnyRef],titlePrefix: Option[String] , templateName: String);
 
 

}