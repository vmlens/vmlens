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

class ReportView( val elements : Seq[ReportElement]  , val warnings : Seq[ReportText], title : String, titlePrefix : Option[String]  , val fileName : String ,  templateName : String, root : String, val contextReport : ContextReport  )
 extends MustacheHtmlSites( title  , titlePrefix , templateName , root )
{
  
  /*
   *     val titleIndex: String,
    val titleElements: String,
    val fileNameElements : String
   */
  
   override def add2Map(map : HashMap[String,AnyRef])
   {
       map.put("issues", elements);
       map.put("warnings", warnings);
       map.put("hasWarnings",   new java.lang.Boolean( ! warnings.isEmpty ) )
       
       
      HtmlProviderStaticSite.addStaticValues(map);
   
    
   }
  
 
    def createHeaderList() =
    {
       val headerList =  new ArrayBuffer[Header]();
     
    
    
        
        headerList.append( createHeader(   contextReport.titleIndex ,   title , Model2View.PATH_INDEX   ) );
        

        headerList.append( createHeader( contextReport.titleElements ,   title ,   contextReport.fileNameElements  ) );
        
        headerList;
    }
   
   
     
  
   def createHtmlView(path : File )
  {
    createHtmlReport(path);
  }
  
  
  
  
    def write2Stream(printStream : PrintWriter)
        {
         write2Stream(printStream,  contextReport.inMaven);
        }
 
  
  
  
  
   def createHtmlReport(reportDir : File  )
   {
     
       val printStream = new PrintWriter(reportDir.getAbsolutePath() +  "/"   + fileName);
     
     
      write2Stream(printStream);
  
     
     
    
     printStream.close();
   
     
  
   }
  
 
   var alreadyInitialized = false;
   
   
  def initialize()
  {
    
    if(! alreadyInitialized)
    {
         for( elem <- elements  ) 
    {
      elem.initialize(contextReport);
    }
         
        alreadyInitialized = true; 
         
    }
    
 
  }
  
  
 
  
}