package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._
import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.interleave.StatementLoop
import scala.collection.JavaConverters._

class ReportLoop(val loopCount : Int, val  listInternal : List[ReportStatementBlockInLoop]) extends ReportElement[ContextReport]  {
  
  def list() = listInternal.asJava


   def trStyle() = 
   {
     if( listInternal.isEmpty )
     {
       "";
     }
     else
     {
       listInternal(0).trStyle().getOrElse("");
     }
     
     
   }
     
     
  
  def tdStyle = 
  {
   // " rowspan=\""   + list.length +   "\" 

     " rowspan=\""   + listInternal.length +   "\"  style=\"border-top-style:solid;border-top-color:black;border-top-width:2px;border-bottom-style:solid  \" ";
    
   //   " style=\"border-top-style: solid; border-bottom-style:solid  \" ";
    
  }
   
   def initialize( contextReport : ContextReport) 
       {
         listInternal.foreach(x => x.initialize(contextReport))
     
       
       }

  def name() = "ReportLoop"; 
   
   
}