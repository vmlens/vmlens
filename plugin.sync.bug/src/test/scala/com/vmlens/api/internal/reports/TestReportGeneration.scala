package com.vmlens.api.internal.reports

import scala.collection.mutable.HashMap;
import org.fusesource.scalate._
import org.fusesource.scalate.support.StringTemplateSource
import org.apache.commons.io.IOUtils


object TestReportGeneration {

  
  def main( args : Array[String])
  {
    
    val templateName = "test.mustache"
      
      val map = new HashMap[String,AnyRef]();
      
     map.put("elements" , new TestData());
      
      
        val engine = new TemplateEngine
        
        val template = this.getClass.getClassLoader.getResourceAsStream( "templates/" +  templateName);
        
        val templateText = IOUtils.toString(template)
        
        
        
        
     println(   engine.layout( new StringTemplateSource(  "templates/" +  templateName  ,  templateText)  , map.toMap));
    
  }
  
  
}
