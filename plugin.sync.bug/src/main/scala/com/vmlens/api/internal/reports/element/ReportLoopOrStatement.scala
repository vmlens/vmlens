package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.StatementLoop
import com.anarsoft.race.detection.model.result.ModelFacadeAll

class ReportLoopOrStatement(val loop : Option[ReportLoop] , val statement : Option[ReportStatementBlock]) extends ReportElement {
  
    def initialize( contextReport : ContextReport ) 
    {
      loop.foreach( x => x.initialize(contextReport) )
      statement.foreach( x => x.initialize(contextReport) )
    }
    
    
    def name()  = "ReportLoopOrStatement";
  
}