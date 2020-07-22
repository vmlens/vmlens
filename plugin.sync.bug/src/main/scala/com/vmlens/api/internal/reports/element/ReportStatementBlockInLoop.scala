package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._
import com.anarsoft.race.detection.model.result._
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

class ReportStatementBlockInLoop( element : InterleaveEventStatement,  modelFacade : ModelFacadeAll , context : ContextLastRun,val borderAtTop : Boolean, val borderAtBootom : Boolean ) extends ReportStatementBlock( element ,  modelFacade  , context ){
 
  
  def needsTrStart = ! borderAtTop;
  
   def tdStyle = 
  {
     if(borderAtTop && borderAtBootom)
     {
        "style=\"border-top-style:solid;border-top-color:black;border-top-width:2px;border-bottom-style:solid  \" ";
     }
     else if(borderAtTop )
     {
      " style=\"border-top-style:solid;border-top-color:black;border-top-width:2px \" ";  
     } 
     else 
       if(borderAtBootom)
     {
        " style=\"border-bottom-style:solid  \" ";
     }
     else
     {
       ""
     }
    
  }
  
  
}