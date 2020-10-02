package com.vmlens.api.internal.reports.element

import com.vmlens.api._
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.IconRepository
import com.vmlens.api.internal.reports._
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.model.result.IssueModelElement

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

        result.toSeq;
      } else {
        Nil;
      }

    }

  var link: Option[String] = None;
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

    //  ViewData(val elements : Seq[ReportElement], val warnings : Seq[ReportText],val name : String,val root : String,val context : ContextReport)

    link = contextReport.issueDetailLinks.createLink(

      new ViewData(element.children(modelFacade).map(x => new ReportElementIssuePart(x, modelFacade)), Nil, name, Some(element.titlePrefix()), "../", contextReport), None);

    //
    //          link = contextReport.issueLinks.createLink(  ( templateName ,  linkText ) =>   {
    //             val linkedElements=   element.children(modelFacade).map(  x =>  new ReportElementStackTrace(x,modelFacade)   );
    //             new ReportView( linkedElements , Nil ,   name   ,linkText   ,  templateName ,"../" , contextReport  );
    //          }
    //          );
    //
    //       }
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