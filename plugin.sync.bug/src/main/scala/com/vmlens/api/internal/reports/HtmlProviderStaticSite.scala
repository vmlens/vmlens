package com.vmlens.api.internal.reports

import com.vmlens.api.internal.gen.Version

import java.io._
import scala.collection.JavaConverters._
import scala.collection.mutable.HashMap;


class HtmlProviderStaticSite(val templateName: String) extends HtmlProvider with CreateTemplate {
 
  
  def write2Stream(path : String , printStream : PrintWriter)
  {
      val map = new HashMap[String,AnyRef];
      map.put("showHeader", new java.lang.Boolean(false))
    
       HtmlProviderStaticSite.addStaticValues(map);

    val mustache  =  create(templateName);
    mustache.execute(map.asJava,printStream);

  }
  
  
}

object HtmlProviderStaticSite
{
  def addStaticValues(map : HashMap[String,AnyRef])
  {
        // map.put("apacheRoot", "http://localhost")
       
       map.put("apacheRoot", "https://vmlens.com")
       map.put("vmlensVersion", Version.LABEL)
  }
  
  
  
}