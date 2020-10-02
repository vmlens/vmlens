package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._
import scala.collection.mutable.ArrayBuffer


class ReportElementError(val exception : Throwable) extends ReportElement[ContextReport]  {
  
  
  def name() = exception.toString();
  
  def message = exception.getMessage();
  
  def trace = 
  {
    val list = new ArrayBuffer[ReportText]
    
    for( e <- exception.getStackTrace)
    {
      list.append(  new ReportText(  e.toString() ) )
      
    }
    
    
    
    list
  }
  
  
  
  
  
   def initialize( contextReport : ContextReport) 
       {
     
     
       }
  
  
}