package com.vmlens.api.internal.reports

import java.io._;
import com.vmlens.api._;
import org.fusesource.scalate._
import java.io.PrintStream
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ArrayBuffer;
import org.apache.commons.io.IOUtils
import org.fusesource.scalate.support.StringTemplateSource
import scala.collection.JavaConverters._
import com.vmlens.api.internal._;
import com.anarsoft.race.detection.model.result._;
import java.io._;
import com.anarsoft.integration._;
import org.apache.commons.lang.StringEscapeUtils
import com.vmlens.api.internal.reports.element.ReportText

class ReportView[CONTEXT <: ContextReportAbstract](
  val elements: Seq[ReportElement[CONTEXT]],
  val warnings: Seq[ReportText], val title: String, val titlePrefix: Option[String],
  val fileName: String, val templateName: String, val root: String, val contextReport: CONTEXT) {

 def createHeader(headerName: String, currentTitle: String, fileName: String) =
    {
      val isActive = headerName == currentTitle;

      new Header(headerName, fileName, isActive)
    }
  
  
  def write2Stream(printStream: PrintWriter, inMaven: Boolean) {
    val headerList = createHeaderList();

    //headerList.append( createHeader(  headerParallelize ,   title , filePrefixParallelize  ) );
    //headerList.append( createHeader(  headerShow ,   title , filePrefixShow  ) );

    val map = new HashMap[String, AnyRef];

    contextReport.add2Map(map,titlePrefix,templateName);

    map.put("issues", elements);
    map.put("warnings", warnings);
    map.put("hasWarnings", new java.lang.Boolean(!warnings.isEmpty))

    HtmlProviderStaticSite.addStaticValues(map);

    map.put("titlePrefix", titlePrefix)
    map.put("title", Model2View.makeBreakable(title))
    map.put("headerList", headerList)

    map.put("root", root)
    map.put("showHeader", new java.lang.Boolean(inMaven))

    if (inMaven) {
      map.put("containerTyp", "container")
    } else {
      map.put("containerTyp", "container-fluid")
    }

    val engine = new TemplateEngine {
      allowReload = false;
    }

    printStream.println(engine.layout(templateName, map.toMap));
  }

  def createHeaderList() =
    {
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