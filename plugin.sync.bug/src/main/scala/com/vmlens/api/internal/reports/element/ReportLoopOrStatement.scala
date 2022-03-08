package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.StatementLoop
import com.anarsoft.race.detection.model.result.ModelFacadeAll

class ReportLoopOrStatement(val loopInternal : Option[ReportLoop] , val statementInternal : Option[ReportStatementBlock]) extends ReportElement[ContextReport] {

  def loop = loopInternal.getOrElse(null)

  def statement =  statementInternal.getOrElse(null)


  def initialize( contextReport : ContextReport )
    {
      loopInternal.foreach( x => x.initialize(contextReport) )
      statementInternal.foreach( x => x.initialize(contextReport) )
    }
    
    
    def name()  = "ReportLoopOrStatement";
  
}