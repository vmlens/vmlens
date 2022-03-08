package com.vmlens.api.internal.reports

import com.vmlens.api.internal.reports.element.ReportText

import java.io._
import scala.collection.JavaConverters._
import scala.collection.mutable.{ArrayBuffer, HashMap}

class ReportView[CONTEXT <: ContextReportAbstract](
                                                    val elements: Seq[ReportElement[CONTEXT]],
                                                    val warnings: Seq[ReportText], val title: String, val titlePrefix: Option[String],
                                                    val fileName: String, val templateName: String, val root: String, val contextReport: CONTEXT) extends CreateTemplate {

  def createHeader(headerName: String, currentTitle: String, fileName: String) = {
    val isActive = headerName == currentTitle;

    new Header(headerName, fileName, isActive)
  }


  def write2Stream(printStream: PrintWriter, inMaven: Boolean) {
    val headerList = createHeaderList();

    //headerList.append( createHeader(  headerParallelize ,   title , filePrefixParallelize  ) );
    //headerList.append( createHeader(  headerShow ,   title , filePrefixShow  ) );

    val map = new HashMap[String, AnyRef];

    contextReport.add2Map(map, titlePrefix, templateName);

    map.put("issues", elements.asJava);
    map.put("warnings", warnings.asJava);
    map.put("hasWarnings", new java.lang.Boolean(!warnings.isEmpty))

    HtmlProviderStaticSite.addStaticValues(map);

    map.put("titlePrefix", titlePrefix.getOrElse(""))
    map.put("title", Model2View.makeBreakable(title))
    map.put("headerList", headerList.asJava)

    map.put("root", root)
    map.put("showHeader", new java.lang.Boolean(inMaven))

    if (inMaven) {
      map.put("containerTyp", "container")
    } else {
      map.put("containerTyp", "container-fluid")
    }

    map.put("href", "")

    val mustache = create(templateName);
    mustache.execute(map.asJava, printStream);

  }

  def createHeaderList() = {
    val headerList = new ArrayBuffer[Header]();

    contextReport.add2Header(headerList, this);

    headerList;
  }

  def createHtmlView(path: File) {
    createHtmlReport(path);
  }

  def write2Stream(printStream: PrintWriter) {
    write2Stream(printStream, contextReport.inMaven);
  }

  def createHtmlReport(reportDir: File) {

    val printStream = new PrintWriter(reportDir.getAbsolutePath() + "/" + fileName);

    write2Stream(printStream);

    printStream.close();

  }

  var alreadyInitialized = false;

  def initialize() {

    if (!alreadyInitialized) {
      for (elem <- elements) {
        elem.initialize(contextReport);
      }

      alreadyInitialized = true;

    }

  }

}