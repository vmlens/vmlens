package com.vmlens.api.internal.reports

import java.io._
import scala.collection.JavaConverters._
import scala.collection.mutable.HashMap;


class HtmlProviderStaticSiteWithHeader(val templateName: String) extends HtmlProvider with CreateTemplate {


  def write2Stream(path: String, printStream: PrintWriter) {
    val map = new HashMap[String, AnyRef];

    map.put("showHeader", new java.lang.Boolean(true))
    map.put("root", "");
    map.put("containerTyp", "container")

    HtmlProviderStaticSite.addStaticValues(map);
    val mustache = create(templateName);
    mustache.execute(map.asJava, printStream);

  }


}