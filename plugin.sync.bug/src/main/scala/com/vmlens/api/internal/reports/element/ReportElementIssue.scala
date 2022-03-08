package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports._
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.model.result.IssueModelElement
import scala.collection.JavaConverters._

class ReportElementIssue(val element: IssueModelElement, val modelFacade: ModelFacadeAll) extends ReportElement[ContextReport] with Element4TreeViewer {

  var index = 0;

  def children =
    {

      if (element.showDetailsTill(modelFacade) > 0) {
        val result = new ArrayBuffer[ReportText]

        val first = element.children(modelFacade).iterator.next();
        var index = 0;
        val iter = first.children(modelFacade).iterator;

        while (iter.hasNext && index < element.showDetailsTill(modelFacade)) {

          result.append(new ReportText(iter.next().name(modelFacade)));

          index = index + 1;
        }

        result.toSeq.asJava;
      } else {
        Nil.asJava;
      }

    }

  var link: String = "";
  def imagePath =
    {

      val icon = element.icon(modelFacade);

      if (icon == null) {
        null;
      } else {
        "img/" + icon.getName() + ".png"
      }

    }

  def name = element.name(modelFacade)

  def initialize(contextReport: ContextReport) {

    link = contextReport.issueDetailLinks.createLink(
      new ViewData(element.children(modelFacade).map(x => new ReportElementIssuePart(x, modelFacade)), Nil, name, Some(element.titlePrefix()), "../", contextReport), None);

  }

  // for yaml start

  def children4Yaml() =
    {
      element.children(modelFacade).map(x => new Element4TreeViewerIssuePart(x, this, modelFacade))
    }

  def name4Yaml() = element.name4Yaml(modelFacade)

  def title4Yaml(position: Int) = element.title4Yaml(position)

  // for yaml end

  // for eclipse plugin start

  def getChildrens() = {

    val list = new ArrayBuffer[IssuePartElement]

    for (c <- element.children(modelFacade)) {
      list.append(c);

      for (gc <- c.children(modelFacade)) {
        list.append(gc);
      }

    }

    list.map(x => new Element4TreeViewerIssuePart(x, this, modelFacade)).toArray[Object]
  }

  def hasChildren() = true;

  def getParent() = null;

  def getText() = name

  /**
   *
   * null wenn kein icon angezeigt werden soll
   *
   */

  def getIcon() = element.icon(modelFacade)

  def searchData() = element.searchData(modelFacade);

  // for eclipse plugin end

}