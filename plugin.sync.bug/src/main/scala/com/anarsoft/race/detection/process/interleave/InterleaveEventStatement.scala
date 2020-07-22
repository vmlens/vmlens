package com.anarsoft.race.detection.process.interleave

import com.anarsoft.race.detection.model.result.LocationInClass
import com.vmlens.api.internal.reports.element.ReportStatementBlock;
import com.vmlens.api.internal.reports.element.ContextLastRun
import com.anarsoft.race.detection.model.result.ModelFacadeAll
import com.anarsoft.race.detection.model.result.MethodOrdinal
import com.vmlens.api.Icon
import com.anarsoft.race.detection.model.description.MethodModel

trait InterleaveEventStatement {

  def loopId: Int;
  def runId: Int;
  def runPosition: Int;

  def stackTraceOrdinal: Int;

  def threadId: Long;

  def isSame(interleaveEventStatement: InterleaveEventStatement): Boolean;

  def acceptStatementVisitor(statementVisitor: StatementVisitor);

  def operationText(modelFacade: ModelFacadeAll): String;

  def methodModel(modelFacade: ModelFacadeAll): MethodModel;

  def icon(): Icon;

  def canStartLoop(): Boolean;
  def isSameStatement(interleaveEventStatement: InterleaveEventStatement): Boolean;
  
  
  def objectId : Option[String]
  

}