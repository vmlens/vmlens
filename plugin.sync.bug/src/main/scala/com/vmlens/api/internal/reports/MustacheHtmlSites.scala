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

abstract class MustacheHtmlSites(val title: String, val titlePrefix: Option[String], val templateName: String, val root: String) {

  
  val helpRoot = "https://vmlens.com/help/manual/#";
  
  
  
  val template2HelpLink4Maven = Map("templates/htmlParallizedGroups.mustache" -> "maven-interleave-report" ,
                              "templates/htmlNothingInterleaved.mustache" -> "maven-interleave-report" ,   
                              "templates/htmlLastRun.mustache" -> "maven-interleave-detail-report"   ,  
                              "templates/htmlIssue.mustache" -> "maven-interleave-stack-trace-report"   ,  
                              "templates/htmlIndex.mustache" -> "maven-issues"   ,  
                              "Data race:" -> "maven-data-race"   ,  
                              "Deadlock:" -> "maven-deadlock"  );
  
    val template2HelpLink4Eclipse = Map("templates/htmlParallizedGroups.mustache" -> "eclipse-interleave-view" ,
                              "templates/htmlNothingInterleaved.mustache" -> "eclipse-interleave-view" ,   
                              "templates/htmlLastRun.mustache" -> "eclipse-interleave-detail-view"   ,  
                              "templates/htmlIssue.mustache" -> "eclipse-interleave-stack-trace-view"   );
  
  
  

  def add2Map(map: HashMap[String, AnyRef]);

  def createHeaderList(): ArrayBuffer[Header];

  def createHeader(headerName: String, currentTitle: String, fileName: String) =
    {
      val isActive = headerName == currentTitle;

      new Header(headerName, fileName, isActive)
    }

  def helpLinkInternal(key: String,template2HelpLink : Map[String,String] ) =
    {
      template2HelpLink.get(key) match {
        case None => {
//          println("no help link " + key);
//          key
          
          throw new RuntimeException("no help link for " + key);
          
        }
        case Some(x) => helpRoot +  x;

      }
    }

  def helpLink(inMaven: Boolean) =
    {
    
    val map = if(inMaven)
    {
      template2HelpLink4Maven
    }
    else
    {
      template2HelpLink4Eclipse
    }
    
    

      titlePrefix match {
        case None =>
          {
            helpLinkInternal(templateName,map);
          }
        case Some(x) =>
          {
            helpLinkInternal(x,map);
          }

      }

    }

  def write2Stream(printStream: PrintWriter, inMaven: Boolean) {
    val headerList = createHeaderList();

    //headerList.append( createHeader(  headerParallelize ,   title , filePrefixParallelize  ) );
    //headerList.append( createHeader(  headerShow ,   title , filePrefixShow  ) );

    val map = new HashMap[String, AnyRef];
    add2Map(map);

    map.put("titlePrefix", titlePrefix)
    map.put("title", Model2View.makeBreakable(title))
    map.put("headerList", headerList)
    map.put("helpLink", helpLink(inMaven))
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

}