package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._
import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.interleave.StatementLoop


class ReportLoop(val loopCount : Int, val  list : List[ReportStatementBlockInLoop]) extends ReportElement[ContextReport]  {
  
  
   def trStyle() = 
   {
     if( list.isEmpty )
     {
       None;
     }
     else
     {
     list(0).trStyle();
     }
     
     
   }
     
     
  
  def tdStyle = 
  {
   // " rowspan=\""   + list.length +   "\" 

     " rowspan=\""   + list.length +   "\"  style=\"border-top-style:solid;border-top-color:black;border-top-width:2px;border-bottom-style:solid  \" ";
    
   //   " style=\"border-top-style: solid; border-bottom-style:solid  \" ";
    
  }
   
   def initialize( contextReport : ContextReport) 
       {
     list.foreach(x => x.initialize(contextReport))
     
       
       }

  def name() = "ReportLoop"; 
   
   
}