package com.vmlens.api.internal.reports

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class ContextReport( val  issueDetailLinks : LinkProvider , 
    val elementDetailLink : LinkProvider,
    val inMaven : Boolean, 
    val showChildLinks : Boolean,
    val titleIndex: String,
    val titleElements: String,
    val fileNameElements : String) extends ContextReportAbstract {
  
  
    val helpRoot = "https://vmlens.com/help/manual/#";

  val template2HelpLink4Maven = Map(
    "templates/htmlParallizedGroups.mustache" -> "maven-interleave-report",
    "templates/htmlNothingInterleaved.mustache" -> "maven-interleave-report",
    "templates/htmlLastRun.mustache" -> "maven-interleave-detail-report",
    "templates/htmlIssue.mustache" -> "maven-interleave-stack-trace-report",
    "templates/htmlIndex.mustache" -> "maven-issues",
    "Data race:" -> "maven-data-race",
    "Deadlock:" -> "maven-deadlock");

  val template2HelpLink4Eclipse = Map(
    "templates/htmlParallizedGroups.mustache" -> "eclipse-interleave-view",
    "templates/htmlNothingInterleaved.mustache" -> "eclipse-interleave-view",
    "templates/htmlLastRun.mustache" -> "eclipse-interleave-detail-view",
    "templates/htmlIssue.mustache" -> "eclipse-interleave-stack-trace-view");

  def createHeader(headerName: String, currentTitle: String, fileName: String) =
    {
      val isActive = headerName == currentTitle;

      new Header(headerName, fileName, isActive)
    }

  def helpLinkInternal(key: String, template2HelpLink: Map[String, String]) =
    {
      template2HelpLink.get(key) match {
        case None => {
          //          println("no help link " + key);
          //          key

          throw new RuntimeException("no help link for " + key);

        }
        case Some(x) => helpRoot + x;

      }
    }

  def helpLink(inMaven: Boolean,titlePrefix: Option[String] , templateName: String) =
    {

      val map = if (inMaven) {
        template2HelpLink4Maven
      } else {
        template2HelpLink4Eclipse
      }

      titlePrefix match {
        case None =>
          {
            helpLinkInternal(templateName, map);
          }
        case Some(x) =>
          {
            helpLinkInternal(x, map);
          }

      }

    }
  
  
  def add2Header( headerList :  ArrayBuffer[Header] , reportView  : ReportView[_] )
  {
     headerList.append( reportView.createHeader(   titleIndex ,   reportView.title , Model2View.PATH_INDEX   ) );
     headerList.append( reportView.createHeader( titleElements ,   reportView.title ,   fileNameElements  ) );
  }
  
   def add2Map(map :  HashMap[String, AnyRef],titlePrefix: Option[String] , templateName: String)
   {
       map.put("helpLink", helpLink(inMaven,titlePrefix: Option[String] , templateName: String))
   }
  
  
  
  
}