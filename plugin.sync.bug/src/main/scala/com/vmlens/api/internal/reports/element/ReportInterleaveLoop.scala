package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.model.result._
import com.anarsoft.race.detection.process.interleave._
import com.vmlens.api.internal.reports.{CreateParallizedReportAlgo, _}

class ReportInterleaveLoop(val nameInternal: String, val result: LoopResult, val modelFacade: ModelFacadeAll) extends ReportElement[ContextReport] {


  def name = Model2View.makeBreakable(nameInternal)


  def resultText() = result.toDisplayText();

  def count = result.count;

  var index = 0;

  var link: String = "";

  def imagePath = "img/" + result.icon.getName() + ".png"


  def initialize(contextReport: ContextReport) {
    link = contextReport.elementDetailLink.createLink(

      new ViewData((new CreateParallizedReportAlgo()).transformStatementEventList(result.list, modelFacade).toSeq, Nil, nameInternal, None, "../", contextReport), None);

  }

}