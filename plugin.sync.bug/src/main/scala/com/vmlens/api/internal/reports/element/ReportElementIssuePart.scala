package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports._

import scala.collection.JavaConverters._


class ReportElementIssuePart(val element: IssuePartElement, val modelFacade: ModelFacadeAll) extends ReportElement[ContextReport] {


  def nameWithHtml() = Model2View.addBreak(element.nameWithHtml(modelFacade));

  var link: String = "";

  def imagePath = {
    val icon = element.icon(modelFacade);
    if (icon == null) {
      null;
    }
    else {
      "img/" + icon.getName() + ".png"
    }
  }

  def children = element.children(modelFacade)
    .map(x => new ReportElementIssuePart(x, modelFacade)).asJava


  def name = element.name(modelFacade)

  def initialize(contextReport: ContextReport) {
  }

}